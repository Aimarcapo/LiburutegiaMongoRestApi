package liburutegi.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import liburutegi.demo.model.Geometry;
import liburutegi.demo.model.Liburutegia;
import liburutegi.demo.model.LiburutegiaRepository;
import liburutegi.demo.model.Properties;

import static com.mongodb.client.model.Filters.mod;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
@RestController
// Rest Apiaren
@RequestMapping(path = "/liburutegia")
public class LiburutegiController {
	@Autowired
	private LiburutegiaRepository liburutegiaRepository;
/**
     * Liburutegi guztiak itzultzen dituen metodoa. 
     * 
     * @param codigo Liburutegiaren kodea, hainbat erabilera posiblekin.
     * @return Liburutegi guztiak edo emandako kodearen arabera filtratutakoak.
     */
    @GetMapping()
    public @ResponseBody Iterable<Liburutegia> getAllLiburutegia(
            @RequestParam(value = "codigo", required = false) Integer codigo) {

        if (codigo == null) {
            // Kodea ez bada ematen, guztiak itzuli
            return liburutegiaRepository.findAll();
        } else {
            // Kodea ematen bada, kodearen arabera filtratutakoak itzuli
            return liburutegiaRepository.findByMunicipioCodigo(codigo);
        }
    }

  /**
 * Liburutegia kontsultatzeko edo ezabatzeko API-endpoint-a. 
 * Erabilera esemplifikatua:
 * 
 * Ezabatzeko:
 * - Izena parametroa emanez: liburutegia izenaren arabera ezabatzen du.
 * - Direccion_codigo_postal eta municipio_codigo parametroak emanez: 
 *   helbidearen kode postalaren eta udalerriaren arabera ezabatzen du.
 * 
 * @param izena Ezabatu nahi den liburutegiaren izena (Aukerakoa).
 * @param direccion_codigo_postal Helbidearen kode postal (Aukerakoa).
 * @param municipio_codigo Udalerriaren kodea (Aukerakoa).
 * @return Sarrera erantzuna (ResponseEntity): 200 OK kodea ezabaketa ondo egin denean,
 *         400 Bad Request kodea eskatutako parametroen formatu okerrarekin,
 *         404 Not Found kodea ezabaketak egin ezin denean.
 */
	@DeleteMapping(path = "/delete")
	public ResponseEntity<Void> delete(@RequestParam (value="izena" ,required = false) String izena,@RequestParam  (value="direccion_codigo_postal" ,required = false) Integer direccion_codigo_postal,
	@RequestParam (value="municipio_codigo" ,required = false) Integer municipio_codigo) {
		try {
			if (izena != null && !izena.isEmpty()) {
				long zenbat = liburutegiaRepository.deleteByName(izena);
				System.out.println("Ezabatutako liburutegi kopurua🔆: " + zenbat);
				return ResponseEntity.ok().build();
			} else if (direccion_codigo_postal != null && municipio_codigo != null) {
				long ezabatua = liburutegiaRepository.deleteByAddress(direccion_codigo_postal, municipio_codigo);
				System.out.println("Ezabatutako liburutegi kopurua🔆: " + ezabatua);
				return ResponseEntity.ok().build();
			} else {
				// Handle invalid request
				return ResponseEntity.badRequest().build();
			}
		} catch (Exception ex) {
			// Log the exception
			System.out.println("Errorea liburutegia ezabatzerakoan. " + ex.getMessage());
			return ResponseEntity.notFound().build();
		}
		
	}
	/**
	 * Creates a new library resource using the provided data through an endpoint.
	 *
	 * @param libroDTO A JSON string containing information to create the new
	 *                 library resource.
	 * @return A string indicating the success of the operation ("Saved").
	 */
	@PostMapping(path = "/create") // Liburutegi berri bat sortzeko erabiltzen den endpointa
	public @ResponseBody String add(@RequestBody String libroDTO) {
		JSONObject obj = new JSONObject(libroDTO);
		String Type="Feature";
		Properties p = new Properties(obj.getJSONObject("properties").getString("actividad_tipo"),
				obj.getJSONObject("properties").getString("nombre"),
				obj.getJSONObject("properties").getString("tipo_via_codigo"),
				obj.getJSONObject("properties").getString("tipo_via_descripcion"),
				obj.getJSONObject("properties").getString("direccion_nombre_via"),
				obj.getJSONObject("properties").getString("direccion_numero"),
				obj.getJSONObject("properties").getInt("direccion_codigo_postal"),
				obj.getJSONObject("properties").getInt("municipio_codigo"),
				obj.getJSONObject("properties").getString("municipio_nombre"),
				obj.getJSONObject("properties").getString("referencia"),
				obj.getJSONObject("properties").getString("web"),
				obj.getJSONObject("properties").getString("email"),
				obj.getJSONObject("properties").getInt("telefono"),
				obj.getJSONObject("properties").getLong("fax"),
				obj.getJSONObject("properties").getDouble("longitud"),
				obj.getJSONObject("properties").getDouble("latitud"),
				obj.getJSONObject("properties").getString("fecha_creacion"),
				obj.getJSONObject("properties").getString("fecha_actualizacion"));
				List<Double> coordinates = new ArrayList<Double>();
		coordinates.add(obj.getJSONObject("properties").getDouble("longitud"));
		coordinates.add(obj.getJSONObject("properties").getDouble("latitud"));
		Geometry g = new Geometry(obj.getJSONObject("geometry").getString("type"), coordinates);
		Liburutegia liburutegia = new Liburutegia(Type,p,g);
		liburutegia.setGeometry(g);
		System.out.println(liburutegia.toString());
		liburutegiaRepository.save(liburutegia);

		return "Saved";
	}

	/**
	 * Updates the contact information for a library resource based on the provided
	 * data.
	 * 
	 * @param libroDTO A JSON string containing information to update the library
	 *                 resource.
	 * @param izena    The name of the library resource to update.
	 * @return ResponseEntity with HTTP status:
	 *         - 200 OK if the update was successful.
	 *         - 404 Not Found if the resource with the given name was not found or
	 *         an error occurred.
	 */
	@PutMapping(value = "/update/{izena}")
	public ResponseEntity<Liburutegia> updateContact(@Valid @RequestBody String libroDTO,
			@PathVariable String izena) {
		try {
			Liburutegia liburutegia = liburutegiaRepository.findByName(izena);
			JSONObject obj = new JSONObject(libroDTO);
			

			Properties p = new Properties(obj.getJSONObject("properties").getString("actividad_tipo"),
					obj.getJSONObject("properties").getString("nombre"),
					obj.getJSONObject("properties").getString("tipo_via_codigo"),
					obj.getJSONObject("properties").getString("tipo_via_descripcion"),
					obj.getJSONObject("properties").getString("direccion_nombre_via"),
					obj.getJSONObject("properties").getString("direccion_numero"),
					obj.getJSONObject("properties").getInt("direccion_codigo_postal"),
					obj.getJSONObject("properties").getInt("municipio_codigo"),
					obj.getJSONObject("properties").getString("municipio_nombre"),
					obj.getJSONObject("properties").getString("referencia"),
					obj.getJSONObject("properties").getString("web"),
					obj.getJSONObject("properties").getString("email"),
					obj.getJSONObject("properties").getInt("telefono"),
					obj.getJSONObject("properties").getLong("fax"),
					obj.getJSONObject("properties").getDouble("longitud"),
					obj.getJSONObject("properties").getDouble("latitud"),
					obj.getJSONObject("properties").getString("fecha_creacion"),
					obj.getJSONObject("properties").getString("fecha_actualizacion"));
					List<Double> coordinates = new ArrayList<Double>();
			coordinates.add(obj.getJSONObject("properties").getDouble("longitud"));
			coordinates.add(obj.getJSONObject("properties").getDouble("latitud"));
			Geometry g = new Geometry(obj.getJSONObject("geometry").getString("type"), coordinates);
			liburutegia.setType("Feauture");
			liburutegia.setProperties(p);
			liburutegia.setGeometry(g);
			System.out.println(liburutegia.toString());
			liburutegiaRepository.save(liburutegia);
			return ResponseEntity.ok().build();

		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.notFound().build();
		}
	}
}
