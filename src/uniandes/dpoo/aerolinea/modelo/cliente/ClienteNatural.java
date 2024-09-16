package uniandes.dpoo.aerolinea.modelo.cliente;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

public class ClienteNatural extends Cliente {
	public static final String NATURAL = "Natural";
	private String nombre;
	
	public ClienteNatural (String nombre) {
		this.setNombre(nombre);
	}

	@Override
	public String getTipoCliente() {
		// TODO Auto-generated method stub
		return NATURAL;
	}

	@Override
	public String getIdentificador() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
