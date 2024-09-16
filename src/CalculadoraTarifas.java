import uniandes.dpoo.aerolinea.modelo.Aeropuerto;
import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;

public abstract class CalculadoraTarifas {
	public static double IMPUESTO = 0.28;
	
	public int calculartarifa(Vuelo vuelo, Cliente cliente) {
		int costobase = calcularCostoBase(vuelo, cliente);
		double porcentajeDescuento = calcularPorcentajeDescuento(cliente);
        int Final = (int) (costobase * (1 - porcentajeDescuento));
        int Impuestos = calcularValorImpuestos(costobase);
        int CostoTotal =  Final + Impuestos;
        return CostoTotal;
	}
	protected abstract int calcularCostoBase(Vuelo vuelo, Cliente cliente);
	protected abstract double calcularPorcentajeDescuento(Cliente cliente);
	
	public int calcularDistanciaVuelo(Ruta ruta) {
		
		Aeropuerto Inicial = ruta.getDestino();
		Aeropuerto Fin = ruta.getOrigen();
		int distancia = Aeropuerto.calcularDistancia(Inicial, Fin);
		return distancia;
	}
		
		protected int calcularValorImpuestos (int costoBase) {
			
			double impuesto= (double) costoBase* IMPUESTO;
			return (int) impuesto;
	}
		
}


