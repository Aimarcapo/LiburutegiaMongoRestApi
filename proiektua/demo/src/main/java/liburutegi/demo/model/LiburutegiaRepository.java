package liburutegi.demo.model;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface LiburutegiaRepository {
 List<Liburutegia> findAll();
    Liburutegia findById(String id);
    List<Liburutegia> findByMunicipioCodigo(Integer codigo);
    Liburutegia findByName(String izena);
    Liburutegia save(Liburutegia liburutegia);
    long deleteById(String id);
    long deleteByName(String izena);
   /*  @Query("{'direccion_codigo_postal': ?0, 'municipio_codigo': ?1}")  */
    long deleteByAdress(int direccion_codigo_postal, int municipio_codigo);
    long deleteByAddress(Integer direccion_codigo_postal, Integer municipio_codigo);
    
} 
    

