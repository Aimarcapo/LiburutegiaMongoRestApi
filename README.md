# LiburutegiaMongoRestApi

Proiektu honen helburua MongoDB datu-basea erabiliko duen Rest API bat garatzea da, eta nola integratu daitekeen web aplikazio baten barruan jakitea. Kasu honetan, tenerifen dauden liburutegiei buruzko JSON-a erabili dugu eta hau datu basean kargatu.
## Erabilera Orokorreko Pausoak

- Swagger: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
- MongoDB Konexio Katea: `mongodb://localhost:3000`
- Datu-basearen Izena: `centros`
- Kolekzioaren Izena: `tenerife`

## Data, MongoDB eta Docker

Link honetatik(https://datos.gob.es/es/catalogo/l03380011-centros-educativos-y-culturales-en-tenerife)) GEOJSON deiturikoa aukeratu nuen importatzeko mongo db-ra.

Dataset-a MongoDB datu-basean inportatu aurretik, aldaketa txiki batzuk egin nituen. Liburutegi batzuetan parametroak null balioa zuen eta hurrengo aldaketa egin nuen:int parametroak zirenean 0 bat jartzen nuen eta String at bazen "" hutsik lagatzen nuen.

Inportatzen hasi aurretik, MongoDB zerbitzari berri bat sortzea beharrezkoa da. Kasu honetan, localhost-era konexioa konfiguratzen da `mongodb://localhost` konexio katea erabiliz.Baita ereDocker bat erabili dut hau sortzeko docker desctopen bilatzailean mongodb bilatu du eta hau pulleatu,honeking mongodb-ren irudia lortu nuen eta horrekin kontenedorea sortzea ahalbidetzen zidan.Kontenedorea sortu baino lehen nik nahi nuen host portuan konfiguratu nuen kasu honetan 3000 portuan.Orduan MongoDbCompassen datu base ikusi nahi banuen hurrengo localhosta jarri behar nuen 'mongodb://localhost:3000'.

## Rest Zerbitzua

Rest zerbitzuak egitura partikular bat dauka, erabiltzaileak eskaera desberdinak egin ditzakeen zerbitzu honetan (CRUD eragiketak). Elkarri desberdinak diren eskaera mota bakoitzeko, edukitik abiatuta eragiketa jakin bat edo bestea egingo du zerbitzuak. Eskaera Swagger erabiliz edo beste garapen ingurumuturren bidez, hainbat erabilera ingurumutan (Insomnia edo Postman bezala) egin daiteke.

### Endpoints Ikuspegi Orokorra
<img width="1166" alt="Captura de pantalla 2024-01-13 194009" src="https://github.com/Aimarcapo/LiburutegiaMongoRestApi/assets/114486033/69bdd802-5f7d-4f23-9248-bd463f9ac5bf">


| Mota | Endpoint | Emaitza |
| ---- | -------- | ------- |
| GET | /liburutegia | Ez bada codigo parametroan ezer idatzi liburutegi guztiak itzuliko ditu JSON formatuan  bestela parametro hori dituen liburutegiak itzuliko ditu |
| POST |/liburutegia/create | "Saved" |
| PUT | /liburutegia/update/{izena} | HttpStatus |
| DELETE | /liburutegia/delete |Sarrera erantzuna (ResponseEntity): 200 OK kodea ezabaketa ondo egin denean,400 Bad Request kodea eskatutako parametroen formatu okerrarekin,404 Not Found kodea ezabaketak egin ezin denean. |

## MongoDB Java Proiektua


![Diagrama drawio](https://github.com/Aimarcapo/LiburutegiaMongoRestApi/assets/114486033/b1f24fd2-738a-4afa-84eb-c171c263438d)
