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
            /**
            * MongoTemplate-aren eraikitzailea. MongoTemplate core paketeko klase bat da eta errore komunak ekiditeko
            * erabiltzen da. Gainera, klase honek aplikazioaren kodearen dokumentua eta emaitzak ateratzea ahalbidetzen
            * du. Criteriarekin erlazionatu da proiektuan request-ak egiteko garaian parametro anitzak pasatzeko, horretarko
            * Query anotazioa ere erabili da.
            * 
            * @param mongoTemplate MongoTemplate klaseko instantzia
            */
           public MongoDBLiburutegiaRepository(MongoTemplate mongoTemplate) {
               this.mongoTemplate = mongoTemplate;
           }

    @Autowired
    private MongoClient client;
    private MongoCollection<Liburutegia> liburutegiaCollection;
 /**
     * {@code LiburutegiaService} interfazearen hasieratzea, hainbat konexio egiten dituen lehenengo metodoa.
     */
    @PostConstruct
    void init() {
        liburutegiaCollection = client.getDatabase("centros").getCollection("tenerife", Liburutegia.class);
    }
 /**
     * Datu basean dauden liburutegi guztiak itzultzen dituen metodoa.
     *
     * @return Liburutegi guztiak {@code List<Liburutegia>} formatuan
     */
    @Override
    public List<Liburutegia> findAll() {
        return liburutegiaCollection.find()
                .into(new ArrayList<>());
    }
  /**
     * Emandako izenarekin bat datorren liburutegia bilatzen duen metodoa.
     *
     * @param izena Bilatu nahi den liburutegiaren izena
     * @return Izena duten liburutegia, edo {@code null} ez bada aurkitzen
     */
    @Override
    public Liburutegia findByName(String izena) {
        return liburutegiaCollection.find(eq("properties.nombre", izena)).first();
    }
/**
     * Liburutegia gordetzen duen metodoa.
     *
     * @param liburutegia Gorde nahi den liburutegia
     * @return Gorde den liburutegia, ID-arekin eguneratuta
     */
    @Override
    public Liburutegia save(Liburutegia liburutegia) {
        liburutegia.setId(new ObjectId());
        System.out.println(liburutegia);
        liburutegiaCollection.insertOne(liburutegia);
        return liburutegia;
    }
    /**
     * Emandako izenarekin bat datorren liburutegiak guztien artean borratzen duen metodoa.
     *
     * @param izena Borratu nahi den liburutegiaren izena
     * @return Borratutako liburutegien kopurua
     */
    @Override
    public long deleteByName(String izena) {
        return liburutegiaCollection.deleteMany(eq("properties.nombre", izena)).getDeletedCount();
    }
    /**
     * Emandako udalerriaren kodearekin bat datorren liburutegi guztiak itzultzen dituen metodoa.
     *
     * @param codigo Udalerriaren kodea
     * @return Udalerriaren kodearekin bat datorren liburutegiak {@code List<Liburutegia>} formatuan
     */
    @Override
    public List<Liburutegia> findByMunicipioCodigo(Integer codigo) {
        // TODO Auto-generated method stub
        return liburutegiaCollection.find(eq("properties.municipio_codigo", codigo)).into(new ArrayList<>());
    }
/**
     * Emandako helbide kode postalarekin eta udalerriaren kodearekin bat datorren liburutegiak guztien artean borratzen duen metodoa.
     *
     * @param direccion_codigo_postal Helbidearen kode postalaren zenbakia
     * @param municipio_codigo Udalerriaren kodea
     * @return Borratutako liburutegien kopurua
     */
    @Override
    public long deleteByAddress(Integer direccion_codigo_postal, Integer municipio_codigo) {
        // TODO Auto-generated method stub
        Bson filter = and(
                eq("properties.direccion_codigo_postal", direccion_codigo_postal),
                eq("properties.municipio_codigo", municipio_codigo));

        return liburutegiaCollection.deleteMany(filter).getDeletedCount();
    }

}
