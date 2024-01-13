package liburutegi.demo.model;

import java.util.List;

import org.json.JSONObject;
//Hau geometry klasea da honek liburutegi bakoitzean dagoen atal bat da
public class Geometry {
    private String type;
    private List<Double> coordinates;
    public Geometry(){

    }
    public Geometry(String type, List<Double> coordinates) {
        this.type = type;
        this.coordinates = coordinates;
    }
    public Geometry(JSONObject jsonObject) {
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public List<Double> getCoordinates() {
        return coordinates;
    }
    public void setCoordinates(List<Double> coordinates) {
        this.coordinates = coordinates;
    }
    
}
