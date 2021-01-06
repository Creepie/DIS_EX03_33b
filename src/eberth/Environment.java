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
import java.io.StringWriter;
import java.sql.Timestamp;
import java.util.Random;

/**
 * @author Mario Eberth
 * this class provides a Rest Based API with XML response
 */
@Path("/EnvironmentService")
public class Environment extends Application {
    EnvData[] mList = new EnvData[2];

    /**
     * empty constructor is not in use
     */
    public Environment(){

    }

    /**
     * this method describe the API
     * @return a html based overview
     */
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

    /**
     * this method provides all supportedSensors
     * @return all supported Sensors
     */
    @GET
    @Produces({"text/xml"})
    @Path("/sensors")
    public String getSupportedSensors(){
        return "<eberth.Environment>" +
                " temperature humidity"+
                " </eberth.Environment>";
    }

    /**
     * this method is to get a single EnvData object with the given name
     * @param _sensor is the given sensor request
     * @return a EnvData object of the given sensor if supported
     */
    @GET
    @Produces({"text/xml"})
    @Path("/{sensor}")
    public String getBasedEnvData(@PathParam("sensor") String _sensor){
        if (_sensor.equals("temperature") || _sensor.equals("humidity")){
            return convertEnvDataToString(requestEnvironmentData(_sensor));
        } else {
            return "";
        }
    }

    /**
     * this method is to get a List of all supported EnvData
     * @return a xml formed list of all supported EnvData
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("/all")
    public String getBasedList(){
        fillList();
        return "<eberth.Environment>" + convertEnvDataArrayToString(mList) + "</eberth.Environment>";
    }


    /**
     * Converts an envData-Array to a xml string and returns the string
     * @param _envArr provides an Array of EnvData
     * @return an xml form of an the EnvData array
     */
    private String convertEnvDataArrayToString(EnvData[] _envArr){
        try
        {
            JAXBContext jaxbContext = JAXBContext.newInstance(EnvData.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            StringWriter sw = new StringWriter();
            for (EnvData env : _envArr) {
                jaxbMarshaller.marshal(env, sw);
            }
            String xmlContent = sw.toString();
            return xmlContent;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Converts an EnvData-object ot a xml string and returns the string
     * @param _env provides an EnvData object
     * @return an xml form of an EnvData
     */
    private String convertEnvDataToString(EnvData _env){
        try
        {
            JAXBContext jaxbContext = JAXBContext.newInstance(EnvData.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(_env, sw);
            String xmlContent = sw.toString();
            return xmlContent;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * this method creates an EnvData object
     * @param _type is the given sensor type
     * @return a EnvData object
     */
    public EnvData requestEnvironmentData(String _type){
        EnvData mData = new EnvData();
        mData.mSensortype = _type;
        Timestamp mTimestamp = new Timestamp(System.currentTimeMillis());
        mData.mTimestamp = mTimestamp.getTime();
        Random mRandom = new Random();
        mData.mSensorvalues.add(mRandom.nextInt(100)+1);
        return mData;
    }

    /**
     * this method is to fill a array with all supported objects
     */
    public void fillList(){
        mList[0] =(requestEnvironmentData("temperature"));
        mList[1] = (requestEnvironmentData("humidity"));
    }
}
