package com.example.proyecto;

import com.example.proyecto.Clases.ProductoNombre;

/**
 * 
 * @author Grupo
 *
 */
public class Alimentos extends Productos {
	
	
	private int tiempoPerecer;
	/**
	 * tiempo determinado de vida de un Alimento 
	 */
	private String tipo;
	/**
	 * atributo que marca el tipo de Alimeto que es "verdura,fruta..."
	 */
	
	/**
	 *       
	 * @param nombre                  Par�metro encargado de introducir el nombre del producto
	 * @param costeObtencionMedia     Par�metro encargado de introducir el coste de obtencion del producto
	 * @param costeMercadoInterno     Par�metro encargado de introducir el coste de mercado interno
	 * @param costeTransporteMedio     Par�metro encargado de introducir el coste de Transporte Medio
	 * @param costeMercadoExterno     Par�metro encargado de introducir el precio que tiene en el mercado exterior 
	 * @param tipo                    Par�metro encargado de introducir el tipo de Alimento que es 
	 * @param perecer                 Par�metro encargado de estimar el tiempo de vida de dicho producto
	 */				
	public Alimentos(ProductoNombre nombre, double costeObtencionMedia, double costeMercadoInterno, double costeTransporteMedio,
					 double costeMercadoExterno, String tipo, int perecer) {
		super(nombre, costeObtencionMedia, costeMercadoInterno, costeTransporteMedio, costeMercadoExterno);
		this.tiempoPerecer = perecer;
		this.tipo = tipo;
	}
	
	/**
	 * Constructor de copia
	 * @param alimentos parametro por el que se pasa el objeto a copiar
	 */
	public Alimentos(Alimentos alimentos) {
		super(alimentos);
		this.tiempoPerecer=alimentos.getTiempoPerecer();
		this.tipo=alimentos.getTipo();
	}
	
	/**
	 * M�todo encargado de ver los datos m�s intr�nsecos de un Alimento para hacer mas sencillo el "toString"
	 */
	public String verDatosAvanzados() {
		return super.verDatosAvanzados() + " tiempo de perecer : "+this.tiempoPerecer+" tipo : "+this.tipo;  
	}

	public int getTiempoPerecer() {
		return tiempoPerecer;
	}

	public void setTiempoPerecer(int tiempoPerecer) {
		this.tiempoPerecer = tiempoPerecer;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return super.toString();
	}
		
}
