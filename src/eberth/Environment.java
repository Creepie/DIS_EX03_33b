package eberth;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import java.io.StringWriter;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Random;

@Path("/EnvironmentService")
public class Environment extends Application {
    EnvData[] list = new EnvData[2];

    public Environment(){

    }

    @GET
    @Produces({"text/html"})
    public String getBasedOverview(){
        return "<html>" +
                "<head>" +
                "</head>" +
                "<body>" +
                "<h1> Welcome to API Rest for Environment Data </h1>" +
                "<h2> <<<<< API calls responses in XML Format >>>>> </h2>" +
                "<ul>" +
                "<li> Path + /sensors for all supportedSensors </li>" +
                "<li> Path + /{sensor} with a supported Sensor for a Sensor object </li>" +
                "<li> Path + /all for a list with all supported Sensor objects </li>" +
                "</ul>" +
                "</body>" +
                "</html>";
    }

    @GET
    @Produces({"text/xml"})
    @Path("/sensors")
    public String getSupportedSensors(){
        return "<eberth.Environment>" +
                " temperature humidity"+
                " </eberth.Environment>";
    }

    @GET
    @Produces({"text/xml"})
    @Path("/{sensor}")
    public String getBasedEnvData(@PathParam("sensor") String sensor){
        if (sensor.equals("temperature") || sensor.equals("humidity")){
            return convertEnvDataToString(requestEnvironmentData(sensor));
        } else {
            return "";
        }

    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("/all")
    public String getBasedList(){
        fillList();
        return "<eberth.Environment>" + convertEnvDataArrayToString(list) + "</eberth.Environment>";
    }



    //Converts an envData-Array to a xml string and returns the string
    private String convertEnvDataArrayToString(EnvData[] envArr){
        try
        {
            JAXBContext jaxbContext = JAXBContext.newInstance(EnvData.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            StringWriter sw = new StringWriter();
            for (EnvData env : envArr) {
                jaxbMarshaller.marshal(env, sw);
            }
            String xmlContent = sw.toString();
            return xmlContent;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Converts an EnvData-object ot a xml string and returns the string
    private String convertEnvDataToString(EnvData env){
        try
        {
            JAXBContext jaxbContext = JAXBContext.newInstance(EnvData.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(env, sw);
            String xmlContent = sw.toString();
            return xmlContent;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public EnvData requestEnvironmentData(String _type){
        EnvData mData = new EnvData();
        mData.sensortype = _type;
        Timestamp mTimestamp = new Timestamp(System.currentTimeMillis());
        mData.timestamp = mTimestamp.getTime();
        Random mRandom = new Random();
        mData.sensorvalues.add(mRandom.nextInt(100)+1);
        return mData;
    }

    public void fillList(){
        list[0] =(requestEnvironmentData("temperature"));
        list[1] = (requestEnvironmentData("humidity"));
    }
}
