package liburutegi.demo.model;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.TransactionOptions;
import com.mongodb.WriteConcern; 
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

@Repository
public class MongoDBLiburutegiaRepository implements LiburutegiaRepository {
        private final MongoTemplate mongoTemplate;  

    private static final TransactionOptions txnOptions = TransactionOptions.builder()
                                                                           .readPreference(ReadPreference.primary())
                                                                           .readConcern(ReadConcern.MAJORITY)
                                                                           .writeConcern(WriteConcern.MAJORITY)
                                                                           .build(); 
     @Autowired
    public MongoDBLiburutegiaRepository(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
    } 
    @Autowired
    private MongoClient client;
    private MongoCollection<Liburutegia> liburutegiaCollection;

    @PostConstruct
    void init() {
        liburutegiaCollection = client.getDatabase("centros").getCollection("tenerife", Liburutegia.class);
    }

    @Override
    public List<Liburutegia> findAll() {
        return liburutegiaCollection.find()
       .into(new ArrayList<>());
    }

    @Override
    public Liburutegia findById(String id) {
        return liburutegiaCollection.find(eq("_id", new ObjectId(id))).first();        
    }
     @Override
    public Liburutegia findByName(String izena) {
        return liburutegiaCollection.find(eq("properties.nombre",izena)).first();        
    }

    @Override
    public Liburutegia save(Liburutegia liburutegia) {
        liburutegia.setId(new ObjectId());
        System.out.println(liburutegia);
        liburutegiaCollection.insertOne(liburutegia);
        return liburutegia;
    }

    @Override
    public long deleteByName(String izena) {
        return liburutegiaCollection.deleteMany(eq("properties.nombre", izena)).getDeletedCount();
    }
   
     @Override
    public long deleteById(String id) {
        return liburutegiaCollection.deleteMany(eq("_id", new ObjectId(id))).getDeletedCount();
    }
     @Override
    public long deleteByAdress(int direccion_codigo_postal, int municipio_codigo) {
       Bson filter = and(
        eq("properties.direccion_codigo_postal", direccion_codigo_postal),
        eq("properties.municipio_codigo", municipio_codigo)
    );

    return liburutegiaCollection.deleteMany(filter).getDeletedCount();
     
    }

    @Override
    public List<Liburutegia> findByMunicipioCodigo(Integer codigo) {
        // TODO Auto-generated method stub
          return liburutegiaCollection.find(eq("properties.municipio_codigo", codigo)).into(new ArrayList<>());        
    }

    @Override
    public long deleteByAddress(Integer direccion_codigo_postal, Integer municipio_codigo) {
        // TODO Auto-generated method stub
        Bson filter = and(
        eq("properties.direccion_codigo_postal", direccion_codigo_postal),
        eq("properties.municipio_codigo", municipio_codigo)
    );

    return liburutegiaCollection.deleteMany(filter).getDeletedCount();
    }

    

}
