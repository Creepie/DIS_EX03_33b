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
    public long mTimestamp;
    /**
     * sensortype saves a supported sensortype in it
     */
    @XmlElement
    public String mSensortype;
    /**
     * sensorvalues saves one or more sensorvalues in a array list
     */
    @XmlElement
    public ArrayList<Integer> mSensorvalues;

    /**
     * constructor init the sensorvalues array list
     */
    public EnvData(){
        mSensorvalues = new ArrayList<>();
    }

    /**
     * constructor
     * @param mTimestamp represent a timeStamp
     * @param mSensortype represent the sensor type
     * @param values represent the value
     */
    public EnvData(long mTimestamp, String mSensortype, ArrayList<Integer> values) {
        this.mTimestamp = mTimestamp;
        this.mSensortype = mSensortype;
        this.mSensorvalues = values;
    }

    /**
     * represent the envData object in a readable form
     * @return a readable form of the envData object
     */
    @Override
    public String toString() {
        return "EnvData{" +
                "timestamp=" + mTimestamp +
                ", sensortype='" + mSensortype + '\'' +
                ", values=" + mSensorvalues +
                '}';
    }
}
