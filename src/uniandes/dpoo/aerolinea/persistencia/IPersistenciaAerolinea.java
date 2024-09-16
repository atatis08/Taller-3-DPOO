package uniandes.dpoo.aerolinea.persistencia;
import org.json.JSONException;

import uniandes.dpoo.aerolinea.exceptions.AeropuertoDuplicadoException;
import uniandes.dpoo.aerolinea.exceptions.InformacionInconsistenteException;
import uniandes.dpoo.aerolinea.modelo.Aerolinea;

public interface IPersistenciaAerolinea {
	
	public void cargarAerolinea(String archivo, Aerolinea aerolinea) throws java.io.IOException, InformacionInconsistenteException, InformacionInconsistenteException, JSONException, AeropuertoDuplicadoException;

	
	public void salvarAerolinea(String archivo, Aerolinea aerolinea) throws java.io.IOException;
}
