package uniandes.dpoo.aerolinea.modelo.tarifas;
import uniandes.dpoo.aerolinea.modelo.Aeropuerto;
import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;


public abstract class CalculadoraTarifas {
	public static double  IMPUESTO =0.28;
	
	public int calcularTarifa (Vuelo vuelo, Cliente cliente) {
			int costoBase = calcularCostoBase(vuelo, cliente);
	        double PorcentajeDescuento = calcularPorcentajeDescuento(cliente);
	        int costoFinal = (int) (costoBase * (1 - PorcentajeDescuento));
	        int Impuestos = calcularValorImpuestos(costoBase);
	        int respuesta = costoFinal + Impuestos;
	        return respuesta;
	}
	
protected abstract int calcularCostoBase (Vuelo vuelo, Cliente cliente);
	
protected abstract double calcularPorcentajeDescuento (Cliente cliente);
	
	
	protected int calcularValorImpuestos (int costoBase) {
		
		double impuesto= (double)costoBase* IMPUESTO;
		return (int)impuesto;
	}
	
	protected int calcularDistanciaVuelo(Ruta ruta) {
		Aeropuerto aeropuertoDestino = ruta.getDestino();
		Aeropuerto aeropuertoOrigen = ruta.getOrigen();

		return Aeropuerto.calcularDistancia(aeropuertoOrigen, aeropuertoDestino);
	}
}
