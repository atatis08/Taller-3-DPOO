package uniandes.dpoo.aerolinea.modelo;

import org.json.JSONObject;

public class Avion {
	
	private String nombre;
	private int capacidad;
	
	public Avion (String nombre, int capacidad) {
		this.nombre = nombre;
		this.capacidad = capacidad;
	}
	public Avion(JSONObject avionJson) {
		// TODO Auto-generated constructor stub
	}
	public String getNombre() {
		return this.nombre;
	}
	public int getCapacidad() {
		return this.capacidad;
	}
}


