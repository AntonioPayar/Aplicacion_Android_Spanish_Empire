package com.example.proyecto;

import com.example.proyecto.Clases.ProductoNombre;

/**
 * 
 * @author GRUPO
 *Clase padre de Alimentos y Materias Primas comparten atributos en com�n 
 */
public abstract class Productos {
	
	private ProductoNombre nombre;
	/**
	 * Nombre del producto
	 */
	private double costeObtencionMedia;
	/**
	 * El precio de obtener dicho producto
	 */
	private double costeMercadoInterno;
	/**
	 * Precio al que se vende dicho producto dentro de Espa�a
	 */
	private double costeTransporteMedio;
	/**
	 * Coste del Transporte de dicho producto
	 */
	private double costeMercadoExterno;
	/**
	 * Precio al que se vende dicho producto en el mercado exterior
	 */
	private int cantidad;
	/**
	 * Cantidad de dicho producto que se tiene disponible
	 */
	
	/**
	 * 
	 * @param nombre                Par�metro encargado de introducir el nombre del producto
	 * @param costeObtencionMedia   Par�metro encargado de introducir el coste de obtenci�n del producto
	 * @param costeMercadoInterno	Par�metro encargado de introducir el coste de mercado interno
	 * @param costeTransporteMedio  Par�metro encargado de introducir el coste de Transporte Medio
	 * @param costeMercadoExterno	Par�metro encargado de introducir el precio que tiene en el mercado exterior 
	 */
	public Productos(ProductoNombre nombre ,double costeObtencionMedia, double costeMercadoInterno, double costeTransporteMedio, double costeMercadoExterno) {
		this.nombre=nombre;
		this.costeObtencionMedia = costeObtencionMedia;
		this.costeMercadoInterno = costeMercadoInterno;
		this.costeTransporteMedio = costeTransporteMedio;
		this.costeMercadoExterno = costeMercadoExterno;

	}
	
	/**
	 * Constructor de copia
	 * @param productos Objeto que copiar
	 */
	public Productos(Productos productos) {
		this.nombre=productos.getNombre();
		this.cantidad=productos.getCantidad();
		this.costeObtencionMedia = productos.getCosteObtencionMedia();
		this.costeMercadoInterno = productos.getCosteMercadoInterno();
		this.costeTransporteMedio = productos.getCosteTransporteMedio();
		this.costeMercadoExterno = productos.getCosteMercadoExterno();
	}
	/**
	 * M�todo encargado de mostrar los datos no principales de un producto
	 * @return datos avazados de los productos
	 */
	public String verDatosAvanzados() {
		return this.nombre+"  coste de obtencion : "+this.costeObtencionMedia+ " coste Mercado Interno : "+this.costeMercadoInterno+" coste transporte : "+this.costeTransporteMedio+" coste mercado Externo "+ this.costeMercadoExterno ; 
	}
	
	
	//geter

	//comparar objetos para las pruebas
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cantidad;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Productos other = (Productos) obj;
		if (cantidad != other.cantidad)
			return false;
		if (nombre != other.nombre)
			return false;
		return true;
	}
	
	public ProductoNombre getNombre() {
		return nombre;
	}

	public void setNombre(ProductoNombre nombre) {
		this.nombre = nombre;
	}

	public double getCosteObtencionMedia() {
		return costeObtencionMedia;
	}

	public void setCosteObtencionMedia(double costeObtencionMedia) {
		this.costeObtencionMedia = costeObtencionMedia;
	}

	public double getCosteMercadoInterno() {
		return costeMercadoInterno;
	}

	public void setCosteMercadoInterno(double costeMercadoInterno) {
		this.costeMercadoInterno = costeMercadoInterno;
	}

	public double getCosteTransporteMedio() {
		return costeTransporteMedio;
	}

	public void setCosteTransporteMedio(double costeTransporteMedio) {
		this.costeTransporteMedio = costeTransporteMedio;
	}

	public double getCosteMercadoExterno() {
		return costeMercadoExterno;
	}

	public void setCosteMercadoExterno(double costeMercadoExterno) {
		this.costeMercadoExterno = costeMercadoExterno;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return  nombre +" cantidad"+  cantidad ;
	}
		
}
