package com.example.proyecto.Clases;

/**
 * 
 * @author Grupo
 *
 */
public abstract class Virreinatos extends Reinos {
	
	private int distanciaPeru;
	/**
	 * Informa de la distancia del Reino respecto a "Peru"
	 */
	private int distanciaPlata;
	/**
	 * Informa de la distancia del Reino respecto a "Plata"
	 */
	private int distanciaCastilla;
	/**
	 * Informa de la distancia del Reino respecto a "Castilla"
	 */
	private int distanciaNuevaGranada;
	/**
	 * Informa de la distancia del Reino respecto a "Nueva Granada"
	 */
	private int distanciaNuevaEspana;
	/**
	 * Informa de la distancia del Reino respecto a "Nueva Espa�a"
	 */
	private int distanciaAragon;
	/**
	 * Informa de la distancia del Reino respecto a "Aragon"
	 */
	private int distanciaBorgona;
	/**
	 * Informa de la distancia del Reino respecto a "Borgo�a"
	 */
	private int distanciaAustria;
	/**
	 * Informa de la distancia del Reino respecto a "Austria"
	 */

	//Constructores
    /**
     * Constructor con 8 par�metros 
     * @param nombre Da informaci�n sobre el nombre
     * @param continente Informa sobre donde est� cada reino
     * @param poblacion Informa sobre la cantidad de personas que habitan
     * @param peru Indica la distancia sobre donde se encuentra el Reino de Per�
     * @param plata Indica la distancia sobre donde se encuentra el Reino de la Plata
     * @param castilla Indica la distancia sobre donde se encuentra el Reino de Castilla
     * @param granada Indica la distancia sobre donde se encuentra el Reino de Granada
     * @param nuevaEspana Indica la distancia sobre donde se encuentra el Reino de Nueva Granada
     * @param aragon Indica la distancia sobre donde se encuentra el Reino de Aragon
     * @param borgona Indica la distancia sobre donde se encuentra el Reino de Borgo�a
     * @param austria Indica la distancia sobre donde se encuentra el Reino de Austria
     */
	public Virreinatos(String nombre, String continente, int poblacion,int peru,int plata,int castilla,int granada,int nuevaEspana,int aragon,int borgona,int austria) {
		super(nombre, continente, poblacion);
		this.distanciaPeru=peru;
		this.distanciaPlata=plata;
		this.distanciaCastilla=castilla;
		this.distanciaNuevaGranada=granada;
		this.distanciaNuevaEspana=nuevaEspana;
		this.distanciaAragon=aragon;
		this.distanciaBorgona=borgona;
		this.distanciaAustria=austria;
	}

	/**
     * Constructor de copia
     * @param obj Objeto que copiar
     */
	public Virreinatos(Virreinatos obj) {
		super(obj);
		this.distanciaPeru=obj.getDistanciaPeru();
		this.distanciaPlata=obj.getDistanciaPlata();
		this.distanciaCastilla=obj.getDistanciaCastilla();
		this.distanciaNuevaGranada=obj.getDistanciaNuevaGranada();
		this.distanciaNuevaEspana=obj.getDistanciaNuevaEspana();
		this.distanciaAragon=obj.getDistanciaAragon();
		this.distanciaBorgona=obj.getDistanciaBorgona();
		this.distanciaAustria=obj.getDistanciaAustria();
	}
	
	/**
	 * Devuelve la distancia respecto cada zoma
	 * @return distancia de cada zona 
	 */
	public String mostrarDistancias() {
		return "Distancias respecto : Castilla "+this.distanciaCastilla+" km / Nueva Espa�a : "+this.distanciaNuevaEspana+" km / NuevaGranada : " +this.distanciaNuevaGranada+" km/  Peru :"+this.distanciaPeru+" km/ Plata : "+this.distanciaPlata+" km ";
	}

	//Getters y Setters
    /**
     * 
     * @return devuelve las distancias de cada Reino
     */

	@Override
	public String toString() {
		return super.toString();
	}

	public int getDistanciaPeru() {
		return distanciaPeru;
	}

	public void setDistanciaPeru(int distanciaPeru) {
		this.distanciaPeru = distanciaPeru;
	}

	public int getDistanciaPlata() {
		return distanciaPlata;
	}

	public void setDistanciaPlata(int distanciaPlata) {
		this.distanciaPlata = distanciaPlata;
	}

	public int getDistanciaCastilla() {
		return distanciaCastilla;
	}

	public void setDistanciaCastilla(int distanciaCastilla) {
		this.distanciaCastilla = distanciaCastilla;
	}

	public int getDistanciaNuevaGranada() {
		return distanciaNuevaGranada;
	}

	public void setDistanciaNuevaGranada(int distanciaNuevaGranada) {
		this.distanciaNuevaGranada = distanciaNuevaGranada;
	}

	public int getDistanciaNuevaEspana() {
		return distanciaNuevaEspana;
	}

	public void setDistanciaNuevaEspana(int distanciaNuevaEspana) {
		this.distanciaNuevaEspana = distanciaNuevaEspana;
	}

	public int getDistanciaAragon() {
		return distanciaAragon;
	}

	public void setDistanciaAragon(int distanciaAragon) {
		this.distanciaAragon = distanciaAragon;
	}

	public int getDistanciaBorgona() {
		return distanciaBorgona;
	}

	public void setDistanciaBorgona(int distanciaBorgona) {
		this.distanciaBorgona = distanciaBorgona;
	}

	public int getDistanciaAustria() {
		return distanciaAustria;
	}

	public void setDistanciaAustria(int distanciaAustria) {
		this.distanciaAustria = distanciaAustria;
	}
	
}
