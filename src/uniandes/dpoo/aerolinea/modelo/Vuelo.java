package uniandes.dpoo.aerolinea.modelo;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import uniandes.dpoo.aerolinea.exceptions.VueloSobrevendidoException;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.tarifas.CalculadoraTarifas;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;
import uniandes.dpoo.aerolinea.tiquetes.GeneradorTiquetes;

public class Vuelo {
	
	private String fecha;
	private Avion avion;
	private Ruta ruta;
	private Map<String,Tiquete> tiquetes;
	
	public Vuelo (Ruta ruta, String fecha, Avion avion) {
		this.ruta = ruta;
		this.fecha = fecha;
		this.avion = avion;
		this.tiquetes = new HashMap<>();
	}

	public Vuelo(JSONObject vueloJson) {
		// TODO Auto-generated constructor stub
	}

	public String getFecha() {
		return this.fecha;
	}

	public Avion getAvion() {
		return this.avion;
	}

	public Ruta getRuta() {
		return this.ruta;
	}
	
	public Collection<Tiquete> getTiquetes() {
        return tiquetes.values();
}
	
	public int venderTiquetes(Cliente cliente, CalculadoraTarifas calculador, int cantidad) {
		int totalTarifa = 0;
	    for (int i = 0; i < cantidad; i++) {
	        int tarifa = calculador.calcularTarifa(this, cliente);
	        Tiquete tiquete = GeneradorTiquetes.generarTiquete(this, cliente, tarifa);
	        tiquetes.put(tiquete.getCodigo(), tiquete);
	        totalTarifa += tarifa;
	    }
	    
	    return totalTarifa;
	}
	
public boolean equals (Object obj) {
		
		return super.equals(obj);
	}
}
