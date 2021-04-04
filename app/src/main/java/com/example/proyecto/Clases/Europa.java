package com.example.proyecto.Clases;

/**
 * Clase hija de Reinos
 * @author Grupo
 *
 */
public abstract class Europa extends Reinos {
	

	private String territorio;
	/**
	 * Informa sobre el lugar donde est� el Reino
	 */
	private boolean guerra;
	/**
	 * Atributo encargado de estipular si esa zona ha ca�do en guerra
	 */
	private int distanciaAragon;
	/**
	 * Informa de la distancia del Reino respecto a "Aragon"
	 */
	private int distanciaAustria;
	/**
	 * Informa de la distancia del Reino respecto a "Austria"
	 */
	private int distanciaBorgona;
	/**
	 * Informa de la distancia del Reino respecto a "Borgo�a"
	 */
	private int distanciaCastilla;
	/**
	 * Informa de la distancia del Reino respecto a "Castilla"
	 */
	private int distanciaNuevaEspana;
	/**
	 * Informa de la distancia del Reino respecto a "Nueva Espa�a"
	 */
	private int distanciaNuevaGranada;
	/**
	 * Informa de la distancia del Reino respecto a "Nueva Granada"
	 */
	private int distanciaPeru;
	/**
	 * Informa de la distancia del Reino respecto a "Peru"
	 */
	private int distanciaPlata;
	/**
	 * Informa de la distancia del Reino respecto a "Plata"
	 */
	
	
	  /**
     * Constructor donde se pasar�n por par�metros los atributos anteriores
     * @param nombre Informa sobre el nombre
     * @param continente Informa sobre el continente que se encuentra
     * @param poblacion Informa sobre la cantidad de poblaci�n que vive en el reino
     * @param territorio informa del territorio que lo compone
     * @param aragon Se introduce la distancia de Arag�n
     * @param austria Se introduce la distancia de Austria
     * @param borgona Se introduce la distancia de Borgo�a
     * @param castilla Se introduce la distancia de Castilla
     * @param nuevaEspana Se introduce la distancia de Nueva Espa�a
     * @param peru Se introduce la distancia de Per�
     * @param nuevaGranada Se introduce la distancia de Nueva Granada
     * @param plata Se introduce la distancia de Plata
     */
	public Europa(String nombre,String continente, int poblacion,String territorio ,int aragon,int austria,int borgona,int castilla,int nuevaEspana,int nuevaGranada,int peru,int plata) {
		super(nombre,continente, poblacion);
		this.territorio=territorio;
		this.guerra=false;
		this.distanciaAragon=aragon;
		this.distanciaAustria=austria;
		this.distanciaBorgona=borgona;
		this.distanciaCastilla=castilla;
		this.distanciaNuevaEspana=nuevaEspana;
		this.distanciaNuevaGranada=nuevaGranada;
		this.distanciaPeru=peru;
		this.distanciaPlata=plata;
	}
	
	/**
     * Constructor de copia
     * @param obj objeto a copiar
     */
	public Europa(Europa obj) {
		super(obj);
		this.distanciaAragon=obj.getDistanciaAragon();
		this.distanciaAustria=obj.getDistanciaAustria();
		this.distanciaBorgona=obj.getDistanciaBorgona();
		this.distanciaCastilla=obj.getDistanciaCastilla();
		this.distanciaNuevaEspana=obj.getDistanciaNuevaEspana();
		this.distanciaNuevaGranada=obj.getDistanciaNuevaGranada();
		this.distanciaPeru=obj.getDistanciaPeru();
		this.distanciaPlata=obj.getDistanciaPlata();
		this.guerra=obj.isGuerra();
		this.territorio=obj.getTerritorio();
	}
	
	  /**
     * Metodo que muestra las distancias entre unas zonas y otras
     * @return devuelve la distancia de cada zona
     */
	public String mostrarDistancias() {
		return "Distancias respecto : Aragon "+this.distanciaAragon +" km / Austria : "+this.distanciaAustria +" km / Borgo�a : " +this.distanciaBorgona +" km/  Castilla :"+this.distanciaCastilla +" km/ Nueva Espa�a : "+this.distanciaNuevaEspana +" km ";
	}
	
	public String getTerritorio() {
		return territorio;
	}

	public void setTerritorio(String territorio) {
		this.territorio = territorio;
	}

	public boolean isGuerra() {
		return guerra;
	}

	public void setGuerra(boolean guerra) {
		this.guerra = guerra;
	}

	public int getDistanciaAragon() {
		return distanciaAragon;
	}

	public void setDistanciaAragon(int distanciaAragon) {
		this.distanciaAragon = distanciaAragon;
	}

	public int getDistanciaAustria() {
		return distanciaAustria;
	}

	public void setDistanciaAustria(int distanciaAustria) {
		this.distanciaAustria = distanciaAustria;
	}

	public int getDistanciaBorgona() {
		return distanciaBorgona;
	}

	public void setDistanciaBorgona(int distanciaBorgona) {
		this.distanciaBorgona = distanciaBorgona;
	}

	public int getDistanciaCastilla() {
		return distanciaCastilla;
	}

	public void setDistanciaCastilla(int distanciaCastilla) {
		this.distanciaCastilla = distanciaCastilla;
	}

	public int getDistanciaNuevaEspana() {
		return distanciaNuevaEspana;
	}

	public void setDistanciaNuevaEsapana(int distanciaNuevaEsapana) {
		this.distanciaNuevaEspana = distanciaNuevaEsapana;
	}

	public int getDistanciaNuevaGranada() {
		return distanciaNuevaGranada;
	}

	public void setDistanciaNuevaGranada(int distanciaNuevaGranada) {
		this.distanciaNuevaGranada = distanciaNuevaGranada;
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

	@Override
	public String toString() {
		return super.toString();
	}
	
}
