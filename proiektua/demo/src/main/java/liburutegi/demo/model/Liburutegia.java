package liburutegi.demo.model;

import org.bson.types.ObjectId;

public class Liburutegia {
    private ObjectId id;
    private String type;
       private Properties properties;
    private Geometry geometry;
 
    public Liburutegia() {
    }
    public Liburutegia(String type, Properties properties, Geometry geometry) {
        this.type = type;
        this.properties = properties;
        this.geometry = geometry;
    }
    public ObjectId getId() {
        return id;
    }
    public void setId(ObjectId id) {
        this.id = id;
    }
    public Geometry getGeometry() {
        return geometry;
    }
    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }
    public Properties getProperties() {
        return properties;
    }
    public void setProperties(Properties properties) {
        this.properties = properties;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    @Override
    public String toString() {
        return "Liburutegia [id=" + id + ", type=" + type + ", properties=" + properties + ", geometry=" + geometry
                + "]";
    }
 
    
}
