package com.example.proyecto;
import java.util.HashMap;

/**
 * 
 * @author Grupo
 *
 */
public abstract class Territorio {

	private String continente;
	/**
	 * Atributo que establece el nombre del continente
	 */
	private String nombre;
	/**
	 * Atributo que almacena el nombre de la zona
	 */
	private Flota flota;
	/**
	 * Atributo que almacena la Flota de cada zona
	 */
	
	/**
	 * Constructor de la clase
	 * @param nombre nombre de la zona 
	 * @param continente nombre del continente de la zona 
	 */

	public Territorio (String nombre,String continente){
		this.nombre=nombre;
		this.continente=continente;
		this.flota= new Flota(nombre);
	}

	/**
	 * Constructor de copia
	 * @param obj Objeto que copiar
	 */
	public Territorio(Territorio obj) {
		this.continente=obj.getContinente();
		this.nombre=obj.getNombre();
		this.flota=obj.getFlota();
	}


	public String getContinente() {
		return continente;
	}

	public void setContinente(String continente) {
		this.continente = continente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Flota getFlota() {
		return flota;
	}

	public void setFlota(Flota flota) {
		this.flota = flota;
	}

	@Override
	public String toString() {
		return " Nombre : " + nombre+" ,continente : " + continente;
	}

}
