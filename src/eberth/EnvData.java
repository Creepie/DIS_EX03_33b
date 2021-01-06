package eberth;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

/**
 * @author Mario Eberth
 * this class represent an EnvData object
 */
@XmlRootElement
public class EnvData {
    /**
     * timestamp saves a timestamp in it
     */
    @XmlElement
    public long timestamp;
    /**
     * sensortype saves a supported sensortype in it
     */
    @XmlElement
    public String sensortype;
    /**
     * sensorvalues saves one or more sensorvalues in a array list
     */
    @XmlElement
    public ArrayList<Integer> sensorvalues;

    /**
     * constructor init the sensorvalues array list
     */
    public EnvData(){
        sensorvalues = new ArrayList<>();
    }

    /**
     * constructor
     * @param timestamp represent a timeStamp
     * @param sensortype represent the sensor type
     * @param values represent the value
     */
    public EnvData(long timestamp, String sensortype, ArrayList<Integer> values) {
        this.timestamp = timestamp;
        this.sensortype = sensortype;
        this.sensorvalues = values;
    }

    /**
     * represent the envData object in a readable form
     * @return a readable form of the envData object
     */
    @Override
    public String toString() {
        return "EnvData{" +
                "timestamp=" + timestamp +
                ", sensortype='" + sensortype + '\'' +
                ", values=" + sensorvalues +
                '}';
    }
}
