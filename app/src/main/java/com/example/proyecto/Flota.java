package com.example.proyecto;
import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 * 
 * @author GRUPO
 *Clase encargada de una vez creadas las mercanc�as, �stas puedan ser transportadas a otros destinos
 */
public class Flota {

	private String nombre;
	/**
	 * Nombre de la flota
	 */
	private LinkedHashMap <Integer,Mercancia>arrayMercancias;
	/**
	 * lista con un n�mero identificador donde se almacenan las mercanc�as 
	 */
	private int pesoMaximo;
	/**
	 * Peso M�ximo disponible para introducir mercanc�as
	 */
	private int destino;
	/**
	 * Km hacia el destino determinado
	 */
	private int pesoTodasMercancias;
	/**
	 * Peso de las mercanc�as hasta el momento 
	 */
	private boolean disponible;
	/**
	 * atributo encargado de estipular si esa flota est� disponible para meter mercanc�a
	 */


	/**
	 * 
	 * @param nombre nombre de la Flota
	 */
	public Flota(String nombre){
		this.nombre="Flota de ".concat(nombre);
		this.arrayMercancias= new LinkedHashMap<Integer, Mercancia>();
		this.pesoMaximo=50000;
		this.destino= 0;
		this.pesoTodasMercancias=0;
		this.disponible=true;
	}

	/**
	 * M�todo encargado de meter una mercanc�a determinada dentro de flota
	 * @param mercancia mercanc�a que queremos meter en la Flota
	 * @return el peso disponible para seguir almacenando mercanc�as
	 * @throws Exception env�a excepcion si no puede crear la Mercancia
	 */
	public int anadirMercancia(Mercancia mercancia) throws Exception {
		int pesoDisponible;

		pesoDisponible=calcularPesoMercancias();
		if(pesoDisponible>=mercancia.getTotalkg()) {
			this.arrayMercancias.put(this.arrayMercancias.size()+1, mercancia);
			pesoDisponible=pesoDisponible-mercancia.getTotalkg();
			this.setPesoTodasMercancias(pesoDisponible);
			return pesoDisponible;
		}else {
			throw new IllegalArgumentException("Esta mercancia es superior a la capacidad disponible del barco");
		}
		
//		pesoDisponible=pesoDisponible-mercancia.getTotalkg();
//		this.setPesoTodasMercancias(pesoDisponible);
//		return pesoDisponible;
	}

	/**
	 * Se encarga de recorrer todas las mercanc�as almacenadas para comprobar la cantidad de peso disponible
	 * @return peso disponible
	 * @throws Exception
	 */
	protected int calcularPesoMercancias() throws Exception {
		Iterator iterador = this.arrayMercancias.keySet().iterator();
		int idMercancias;
		int pesoTodasMercancias=0;

		while(iterador.hasNext()) {

			idMercancias=(int) iterador.next();

			pesoTodasMercancias=pesoTodasMercancias+this.arrayMercancias.get(idMercancias).getTotalkg();

		}
		this.setPesoTodasMercancias(pesoTodasMercancias);

		return this.pesoMaximo-pesoTodasMercancias;
	}
	/**
	 * M�todo encargado de mostrar todas las mercanc�as dentro de un flota determinada
	 */
	public void verMercancias() {
		Iterator iterador = this.arrayMercancias.keySet().iterator();
		int idmerca;

		while(iterador.hasNext()) {
			idmerca=(int) iterador.next();

			System.out.println(this.nombre+" almacena "+this.arrayMercancias.get(idmerca).toString());

		}
	}

	/**
	 * M�todo que se encarga de enviar la flota a una zona determinada
	 * @param destino distancia a la que esta su destino
	 */
	
	public void enviarFLota(int destino) {
		this.destino=destino;
		this.disponible=false;
	}

//getter
	
	

	public String getNombre() {
		return nombre;
	}

	//pruebas unitarias
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arrayMercancias == null) ? 0 : arrayMercancias.hashCode());
		result = prime * result + destino;
		result = prime * result + (disponible ? 1231 : 1237);
		result = prime * result + pesoTodasMercancias;
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
		Flota other = (Flota) obj;
		if (arrayMercancias == null) {
			if (other.arrayMercancias != null)
				return false;
		} else if (!arrayMercancias.equals(other.arrayMercancias))
			return false;
		if (destino != other.destino)
			return false;
		if (disponible != other.disponible)
			return false;
		if (pesoTodasMercancias != other.pesoTodasMercancias)
			return false;
		return true;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LinkedHashMap<Integer, Mercancia> getArrayMercancias() {
		return arrayMercancias;
	}

	public void setArrayMercancias(LinkedHashMap<Integer, Mercancia> arrayMercancias) {
		this.arrayMercancias = arrayMercancias;
	}

	public int getPesoMaximo() {
		return pesoMaximo;
	}

	public void setPesoMaximo(int pesoMaximo) {
		this.pesoMaximo = pesoMaximo;
	}

	public int getDestino() {
		return destino;
	}

	public void setDestino(int destino) {
		this.destino = destino;
	}

	public int getPesoTodasMercancias() {
		return pesoTodasMercancias;
	}

	public void setPesoTodasMercancias(int pesoTodasMercancias) {
		this.pesoTodasMercancias = pesoTodasMercancias;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public String datosAvanzados() {
		return "Peso Maximo "+this.pesoMaximo+" Peso de todas las mercancias "+this.pesoTodasMercancias+" kg, ditancia destino "+this.destino+" km disponible "+this.disponible;
	}

	@Override
	public String toString() {
		return "Flota nombre "+this.nombre+" con un total de "+this.arrayMercancias.size();
	}

}
