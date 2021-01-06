package eberth;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

/**
 * this class is to parse the Json from the API call into an EnvData object
 */
@XmlRootElement
public class EnvData {
    @XmlElement
    public long timestamp;
    @XmlElement
    public String sensortype;
    @XmlElement
    public ArrayList<Integer> sensorvalues;

    public EnvData(){
        sensorvalues = new ArrayList<>();
    }

    public EnvData(long timestamp, String sensortype, ArrayList<Integer> values) {
        this.timestamp = timestamp;
        this.sensortype = sensortype;
        this.sensorvalues = values;
    }

    @Override
    public String toString() {
        return "EnvData{" +
                "timestamp=" + timestamp +
                ", sensortype='" + sensortype + '\'' +
                ", values=" + sensorvalues +
                '}';
    }
}
