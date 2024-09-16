package uniandes.dpoo.aerolinea.modelo.tarifas;
import uniandes.dpoo.aerolinea.modelo.Aeropuerto;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteCorporativo;
import uniandes.dpoo.aerolinea.modelo.tarifas.CalculadoraTarifas;

public class CalculadoraTemporadaBaja extends CalculadoraTarifas {

		protected int COSTO_POR_KM_NATURAL = 600;
		protected int COSTO_POR_KM_CORPORATIVO = 900;
		protected double DESCUENTO_PEQ = 0.02;
		protected double DESCUENTO_MEDIANAS = 0.1;
		protected double DESCUENTO_GRANDES = 0.2;
		
		protected int calcularCostoBase (Vuelo vuelo, Cliente cliente) {
			
			Aeropuerto inicial =vuelo.getRuta().getOrigen();
			Aeropuerto fin =vuelo.getRuta().getDestino();
			int distancia= Aeropuerto.calcularDistancia(inicial,fin);
			int costo= 0;
			
			if (cliente.getTipoCliente()=="Natural") {
				costo =distancia*COSTO_POR_KM_NATURAL;
			}
			else {
				costo =distancia*COSTO_POR_KM_CORPORATIVO;
			}
			
			return costo;
			
		}
		
		@Override
		protected double calcularPorcentajeDescuento(Cliente cliente) {
			
			if (cliente.getTipoCliente().equals("Corporativo")) {
				
				ClienteCorporativo clienteActual = (ClienteCorporativo) cliente;
				
	            int tamanio = clienteActual.getTamanoEmpresa();
	          
	            if (tamanio == ClienteCorporativo.GRANDE) {
	                return DESCUENTO_GRANDES;
	                
	            } else if (tamanio == ClienteCorporativo.MEDIANA) {
	                return DESCUENTO_MEDIANAS;
	                
	            } else if (tamanio == ClienteCorporativo.PEQUENA) {
	                return DESCUENTO_PEQ;
	            }
	        }
	        return 0;
		}
		}