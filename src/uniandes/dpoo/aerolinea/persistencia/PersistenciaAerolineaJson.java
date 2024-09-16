package uniandes.dpoo.aerolinea.persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import uniandes.dpoo.aerolinea.exceptions.AeropuertoDuplicadoException;
import uniandes.dpoo.aerolinea.exceptions.InformacionInconsistenteException;
import uniandes.dpoo.aerolinea.modelo.Aerolinea;
import uniandes.dpoo.aerolinea.modelo.Aeropuerto;
import uniandes.dpoo.aerolinea.modelo.Avion;
import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.Vuelo;

public class PersistenciaAerolineaJson implements IPersistenciaAerolinea {


	private static final String NOMBRE = "nombre";
    private static final String CAPACIDAD = "capacidad";
    private static final String HORAS = "horaSalida";
    private static final String HORAL = "horaLlegada";
    private static final String CODIGO = "codigo";
    private static final String FECHA = "fecha";
    private static final String CODIGO_RUTA = "codigoRuta";
    private static final String NOMBRE_CIUDAD = "nombreCiudad";
    private static final String LAT = "latitud";
    private static final String LON = "longitud";
	@Override
	public void cargarAerolinea(String archivo, Aerolinea aerolinea)
			throws IOException, InformacionInconsistenteException, InformacionInconsistenteException, JSONException, AeropuertoDuplicadoException {
		String jsonCompleto = new String( Files.readAllBytes( new File( archivo ).toPath( ) ) );
        JSONObject raiz = new JSONObject( jsonCompleto );
        
     
        cargarAviones( aerolinea, raiz.getJSONArray( "aviones" ) );
        

        cargarVuelos( aerolinea, raiz.getJSONArray( "vuelos" ) );
        

        cargarRutas( aerolinea, raiz.getJSONArray( "rutas" ));
    }
private void cargarAviones(Aerolinea aerolinea, JSONArray jAviones) {
		
		int numAviones = jAviones.length( );
        for( int i = 0; i < numAviones; i++ ) {
        	
        	JSONObject avion = jAviones.getJSONObject(i);
        	Avion nuevoAvion= crearAvion(avion);
        	aerolinea.agregarAvion(nuevoAvion);
        }
		}
        
        public void cargarVuelos(Aerolinea aerolinea, JSONArray jVuelos) throws AeropuertoDuplicadoException {
    		int numVuelos= jVuelos.length();
            for(int i = 0; i < numVuelos; i++) {
            	JSONObject jvuelo = jVuelos.getJSONObject(i);     
            	String fecha = jvuelo.getString(FECHA);
            	JSONObject jruta = jvuelo.getJSONObject("ruta");
            	Ruta nuevaRuta=crearRuta(jruta);
            	JSONObject javion = jvuelo.getJSONObject("avion");
            	Avion nuevoAvion=crearAvion(javion);
            	Vuelo nuevoVuelo= new Vuelo(nuevaRuta, fecha, nuevoAvion);
            	aerolinea.getVuelos().add(nuevoVuelo);
            }
        }
        private void cargarAviones1(Aerolinea aerolinea, JSONArray jAviones) {
    		
    		int numAviones = jAviones.length( );
            for( int i = 0; i < numAviones; i++ ) {
            	
            	JSONObject avion = jAviones.getJSONObject(i);
            	Avion nuevoAvion= crearAvion(avion);
            	aerolinea.agregarAvion(nuevoAvion);
    			
    		}
        }
    	public void cargarRutas(Aerolinea aerolinea, JSONArray jRutas) throws AeropuertoDuplicadoException {
        	int numRutas = jRutas.length();
            for(int i = 0; i < numRutas; i++) {
            	JSONObject ruta = jRutas.getJSONObject(i);        
            	Ruta nuevaRuta= crearRuta(ruta);
            	aerolinea.agregarRuta(nuevaRuta);
            }
    	}
    	
    	private void cargarVuelos1(Aerolinea aerolinea, JSONArray jVuelos) throws AeropuertoDuplicadoException {
    		int numVuelos= jVuelos.length( );
            for( int i = 0; i < numVuelos; i++ ) {
            	JSONObject jvuelo = jVuelos.getJSONObject(i);
            	
         
            	String fecha = jvuelo.getString( FECHA );
            	JSONObject jruta = jvuelo.getJSONObject("ruta");
            	Ruta nuevaRuta=crearRuta(jruta);
            	JSONObject javion = jvuelo.getJSONObject("avion");
            	Avion nuevoAvion=crearAvion(javion);
            	Vuelo nuevoVuelo= new Vuelo(javion);
            	aerolinea.getVuelos().add(nuevoVuelo);
            	}
    	
    		}
    	
        private void cargarRutas1(Aerolinea aerolinea, JSONArray jRutas) throws AeropuertoDuplicadoException {
        	int numRutas = jRutas.length( );
            for( int i = 0; i < numRutas; i++ ) {
            	
            	JSONObject ruta = jRutas.getJSONObject(i);
            
            	Ruta nuevaRuta= crearRuta(ruta);
            	aerolinea.agregarRuta(nuevaRuta);
            	}
    		
    		}
    	public Avion crearAvion(JSONObject avion) {
    		String nombre = avion.getString(NOMBRE);
        	int capacidad = avion.getInt(CAPACIDAD);
        	Avion nuevoAvion= new Avion(nombre, capacidad);
    		return nuevoAvion;
    	}
    	
    	public Ruta crearRuta(JSONObject ruta) throws AeropuertoDuplicadoException {
    		String codigoR = ruta.getString(CODIGO_RUTA);
        	String horaS = ruta.getString(HORAS);
        	String horaL = ruta.getString(HORAL);
        	JSONObject jAeropuertoO= ruta.getJSONObject("aeropuertoOrigen");
        	Aeropuerto aeropuertoO= crearAeropuerto(jAeropuertoO);
        	JSONObject jAeropuertoD= ruta.getJSONObject("aeropuertoDestino");
        	Aeropuerto aeropuertoD= crearAeropuerto(jAeropuertoD);
        	Ruta nuevaRuta= new Ruta(aeropuertoO, aeropuertoD, horaS, horaL, codigoR);
    		return nuevaRuta;
    	}

    	public Aeropuerto crearAeropuerto(JSONObject jAeropuerto) throws AeropuertoDuplicadoException {		
    		String nombre = jAeropuerto.getString(NOMBRE);
    		String codigo = jAeropuerto.getString(CODIGO);
    		String nombreCiudad = jAeropuerto.getString(NOMBRE_CIUDAD);
    		double latitud = jAeropuerto.getDouble(LAT);
    		double longitud = jAeropuerto.getDouble(LON);
    		Aeropuerto Aeropuerto= new Aeropuerto(nombre, codigo, nombreCiudad, latitud, longitud);
    		return Aeropuerto;
    	}

	@Override
	public void salvarAerolinea(String archivo, Aerolinea aerolinea) throws IOException {
		 try (FileWriter writer = new FileWriter(archivo)) {
	            List<Vuelo> vuelos = (List<Vuelo>) aerolinea.getVuelos(); 

	            // Create a JSON array to hold all the flights
	            JSONArray vuelosArray = new JSONArray();

	            for (Vuelo vuelo : vuelos) {
	                JSONObject vueloJson = new JSONObject();
	                vueloJson.put("numeroVuelo", vuelo.getAvion());
	                vueloJson.put("origen", vuelo.getRuta());
	                
	                
	                vuelosArray.put(vueloJson);
	            }

	            // Write the JSON array to the file
	            writer.write(vuelosArray.toString(4)); // 4 is the indentation level for pretty printing
	        } catch (IOException e) {
	            throw new IOException("Error writing to file: " + archivo, e);
	        }
	    }
	

	}


