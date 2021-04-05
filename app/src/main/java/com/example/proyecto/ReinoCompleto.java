package com.example.proyecto;

import com.example.proyecto.Clases.Aragon;
import com.example.proyecto.Clases.Austria;
import com.example.proyecto.Clases.Borgona;
import com.example.proyecto.Clases.Castilla;
import com.example.proyecto.Clases.Europa;
import com.example.proyecto.Clases.NuevaEspana;
import com.example.proyecto.Clases.NuevaGranada;
import com.example.proyecto.Clases.Peru;
import com.example.proyecto.Clases.Plata;
import com.example.proyecto.Clases.Reinos;
import com.example.proyecto.Clases.Virreinatos;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;

/**
 * 
 * @author Grupo
 *
 */

public class ReinoCompleto {

	private NuevaEspana nuevaEspana;
	/**
	 * Reino Nueva Espa�a
	 */
	private NuevaGranada nuevaGranda;
	/**
	 * Reino Nueva Granada
	 */
	private Peru peru;
	/**
	 * Reino Peru
	 */
	private Plata plata;
	/**
	 * Reino Plata
	 */
	private Castilla castilla;
	/**
	 * Reino Castilla
	 */
	private Aragon aragon;
	/**
	 * Reino Aragon
	 */
	private Borgona borgona;
	/**
	 * Reino Borgona
	 */
	private Austria austria;
	/**
	 * Reino Austria
	 */

	/**
	 * 
	 * @throws Exception envia una Excepcion si no encuentra un Producto
	 */

	public ReinoCompleto() throws Exception {
		this.nuevaEspana= new NuevaEspana("Nueva España", "Ameriaca", 100);
		this.nuevaGranda=new NuevaGranada("Nueva Granada", "America", 100);
		this.peru=new Peru("Peru","America", 100);
		this.plata= new Plata("Plata","America", 100);
		this.castilla=new Castilla("Castilla","Europa", 100,  "Madrid");
		this.aragon= new Aragon("Aragon","Europa", 100, "Zaragoza");
		this.borgona= new Borgona("Borgoña","Europa", 100, "Flandes");
		this.austria=new Austria("Austria","Europa", 100, "Austria");
	}

	/**
	 * M�todo encargado de meter mercanc�as en una flota determinada
	 * @param reino objeto del Reino
	 * @param idMercancia el identificador de la Mercanc�a
	 * @return el n�mero de kg que todav�a puede transportar
	 * @throws Exception env�a excepcion si la Flota no puede transportar determinados kg
	 */
	public String formarFlota(Reinos reino, int idMercancia) throws Exception {
		int pesoDisponibleFlota;

		if(reino.getFlota().isDisponible()) {
			pesoDisponibleFlota=reino.getFlota().anadirMercancia(reino.getMercancia().get(idMercancia));
			reino.getMercancia().remove(idMercancia);


			return "La flota todavia puede transportar "+pesoDisponibleFlota;
		}else {
			throw new Exception("La flota no esta disponible ");
		}
	}

	/**
	 * M�todo encargado de enviar una flota con mercanc�as a una determinada zona depositar toda la mercanc�a de la misma 
	 * @param destino zona a la que se env�a la flota
	 * @param reino zona desde la que se env�a la flota
	 * @throws Exception env�o de Excepcion si no se introduce el nombre correctamente
	 */
	public void enviarFlota(Reinos reino ,Reinos destino) throws Exception {

		if(reino instanceof Virreinatos) {

			if(reino.getFlota().isDisponible()) {
				
				enviarBaseDatosIdMercancias(reino,destino);

				switch (destino.getNombre().toUpperCase()) {
				case "PERU":
//					reino.getFlota().setDestino(((Virreinatos) reino).getDistanciaPeru());
					reino.getFlota().enviarFLota(((Virreinatos) reino).getDistanciaPeru());
					this.llegadaFlotaDestino(reino, destino);
					break;
				case "PLATA":
//					reino.getFlota().setDestino(((Virreinatos) reino).getDistanciaPlata());
					reino.getFlota().enviarFLota(((Virreinatos) reino).getDistanciaPlata());
					this.llegadaFlotaDestino(reino, destino);
					break;
				case "CASTILLA":
//					reino.getFlota().setDestino(((Virreinatos) reino).getDistanciaCastilla());
					reino.getFlota().enviarFLota(((Virreinatos) reino).getDistanciaCastilla());
					this.llegadaFlotaDestino(reino, destino);
					break;
				case "NUEVA GRANADA":
//					reino.getFlota().setDestino(((Virreinatos) reino).getDistanciaNuevaGranada());
					reino.getFlota().enviarFLota(((Virreinatos) reino).getDistanciaNuevaGranada());
					this.llegadaFlotaDestino(reino, destino);
					break;
				case "NUEVA ESPA�A":
					reino.getFlota().enviarFLota(((Virreinatos) reino).getDistanciaNuevaEspana());
					this.llegadaFlotaDestino(reino, destino);
					break;
				case "ARAGON":
					reino.getFlota().enviarFLota(((Virreinatos) reino).getDistanciaAragon());
					this.llegadaFlotaDestino(reino, destino);
					break;
				case "AUSTRIA":
//					reino.getFlota().setDestino(((Virreinatos) reino).getDistanciaAustria());
					reino.getFlota().enviarFLota(((Virreinatos) reino).getDistanciaAustria());
					this.llegadaFlotaDestino(reino, destino);
					break;
				case "BORGO�A":
//					reino.getFlota().setDestino(((Virreinatos) reino).getDistanciaBorgo�a());
					reino.getFlota().enviarFLota(((Virreinatos) reino).getDistanciaBorgona());
					this.llegadaFlotaDestino(reino, destino);
					break;
				default:
					throw new IllegalArgumentException(destino+" no esta disponible en las rutas de "+reino.getNombre());
				}
			}
			else {
				throw new IllegalArgumentException(reino+"No esta disponible debido a revueltas");
			}

		}else if(reino instanceof Europa) {

			if(reino.getFlota().isDisponible()) {
				
				enviarBaseDatosIdMercancias(reino,destino);

				switch (destino.getNombre().toUpperCase()) {
				case "PERU":
//					reino.getFlota().setDestino(((Europa) reino).getDistanciaPeru());
					reino.getFlota().enviarFLota(((Europa) reino).getDistanciaPeru());
					this.llegadaFlotaDestino(reino, destino);
					break;
				case "PLATA":
//					reino.getFlota().setDestino(((Europa) reino).getDistanciaPlata());
					reino.getFlota().enviarFLota(((Europa) reino).getDistanciaPlata());
					this.llegadaFlotaDestino(reino, destino);
					break;
				case "CASTILLA":
//					reino.getFlota().setDestino(((Europa) reino).getDistanciaCastilla());
					reino.getFlota().enviarFLota(((Europa) reino).getDistanciaCastilla());
					this.llegadaFlotaDestino(reino, destino);
					break;
				case "NUEVA GRANADA":
//					reino.getFlota().setDestino(((Europa) reino).getDistanciaNuevaGranada());
					reino.getFlota().enviarFLota(((Europa) reino).getDistanciaNuevaGranada());
					this.llegadaFlotaDestino(reino, destino);
					break;
				case "NUEVA ESPA�A":
//					reino.getFlota().setDestino(((Europa) reino).getDistanciaPeru());
					reino.getFlota().enviarFLota(((Europa) reino).getDistanciaNuevaEspana());
					this.llegadaFlotaDestino(reino, destino);
					break;
				case "ARAGON":
//					reino.getFlota().setDestino(((Europa) reino).getDistanciaAragon());
					reino.getFlota().enviarFLota(((Europa) reino).getDistanciaAragon());
					this.llegadaFlotaDestino(reino, destino);
					break;
				case "AUSTRIA":
//					reino.getFlota().setDestino(((Europa) reino).getDistanciaAustria());
					reino.getFlota().enviarFLota(((Europa) reino).getDistanciaAustria());
					this.llegadaFlotaDestino(reino, destino);
					break;
				case "BORGO�A":
//					reino.getFlota().setDestino(((Europa) reino).getDistanciaBorgona());
					reino.getFlota().enviarFLota(((Europa) reino).getDistanciaBorgona());
					this.llegadaFlotaDestino(reino, destino);
					break;
				default:
					throw new IllegalArgumentException(destino+" no esta disponible en las rutas de "+reino.getNombre());
				}
			}
			else {
				throw new IllegalArgumentException(reino+"No esta disponible debido a revueltas");
			}
		}
	}
	
	/**
	 * Metodo encargado de instertar las importaciones a la Base de datos
	 * @param origen
	 * @param destino
	 * @throws SQLException
	 */
	protected void enviarBaseDatosIdMercancias(Reinos origen,Reinos destino) throws SQLException {
		Iterator it = origen.getFlota().getArrayMercancias().keySet().iterator();
		int id;
		int turno;
		String codigoPais;
		String aux;
		
		turno=PanelDeControl.getContadorTurnos();
		
		if(origen.getNombre().equals("Nueva Espa�a")) {
			codigoPais="NE";
		}else if(origen.getNombre().equals("Nueva Granada")) {
			codigoPais="NG";
		}else {
			codigoPais=origen.getNombre().substring(0,2);
		}
		
		aux=codigoPais;
		
		while(it.hasNext()) {
			codigoPais=aux;
			id=(int) it.next();
			
			codigoPais+=id;
			System.out.println(codigoPais);
			
//			IntroducirDatos.insertarImportaciones(codigoPais.toUpperCase(),origen.getNombre().toString(),destino.getNombre().toString(),turno);
		}
			
	}

	/**
	 * M�todo encargado de poner disponible la flota de un determinado territorio
	 * @param reino par�metro encargado de introducir el pa�s en cuesti�n del que se quiere que se ponga la flota disponible
	 */
	public void devolverFlota(Reinos reino) {
		reino.getFlota().setDisponible(true);
		reino.getFlota().setDestino(0);
	}

	/**
	 * M�todo encargado de la importaci�n de las Mercancias
	 * @param origen Desde donde se env�a la Importaci�n
	 * @param destino Lugar al que se env�a 
	 * @throws Exception 
	 */
	private void llegadaFlotaDestino(Reinos origen,Reinos destino) throws Exception {
		if(!origen.equals(destino)) {
			destino.llegadaImpotacion(origen.getFlota());
		}else {
			throw new Exception("Esa ruta comercial esta demasiado cerca no es necesario mandar una flota");
		}
	}

	public void verImportaciones(Reinos zona) {
		zona.verMercanciasImportacion();
	}


	public double verDinero(Reinos zona) {
		return zona.getDineroTotal();
	}

	public void sublevaciones(Reinos zona) {

	}
	
	/**
	 * M�todo encargado de comprobar si todas las regiones tienen los productos demandados por las mismas
	 * @return retorna una lista que contiene las zonas donde no se han satisfecho las necesidades que ped�an
	 */
	public HashSet pasarTurno() {
		//uso de HashSet para que no haya repeticiones
		HashSet<String> zonasSinProductosDemandados= new HashSet<String>();
		
		zonasSinProductosDemandados.add(this.comprobarProducotosDemandadosZonas(nuevaEspana));
	
		zonasSinProductosDemandados.add(this.comprobarProducotosDemandadosZonas(nuevaGranda));
		
		zonasSinProductosDemandados.add(this.comprobarProducotosDemandadosZonas(peru));
		
		zonasSinProductosDemandados.add(this.comprobarProducotosDemandadosZonas(plata));
		
		zonasSinProductosDemandados.add(this.comprobarProducotosDemandadosZonas(castilla));
		
		zonasSinProductosDemandados.add(this.comprobarProducotosDemandadosZonas(aragon));
		
		zonasSinProductosDemandados.add(this.comprobarProducotosDemandadosZonas(borgona));
		
		zonasSinProductosDemandados.add(this.comprobarProducotosDemandadosZonas(austria));
		
		zonasSinProductosDemandados.remove(null);
		
		return zonasSinProductosDemandados;
	}
	
	/**
	 * M�todo hermano del  "PasarTurno", su principal funci�n es ir al m�todo "comprobarProductosDemandados" de cada zona y comprobar si es "true" or "false"
	 * @param reino  se pasa como par�metro el pa�s en cuesti�n del que se quiere comprobar su m�todo 
	 * @return retorna el nombre de la zona, si en la misma no se han satisfecho las necesidades y en cambio "nada", si en esa zona no hay necesidad de productos
	 */
	private String comprobarProducotosDemandadosZonas(Reinos reino) {
		
		if(reino.getProductosDemandados().length>0) {
			
			if(reino.getProductosDemandados()[0]!=null) {
				return reino.getNombre();
			}else {
				return null;
			}
			
		}else {
			return null;
		}
		


	}
	

	/**
	 * M�todo que se encarga de retornar las distancias de cada Reino del resto 
	 * @param zona  se introduce el Reino en cuesti�n del que queremos saber sus distancias 
	 * @return distancia
	 * @throws Exception env�o de Excepcion si no existe la zona
	 */
	public String verDistancias(Reinos zona) throws Exception {

		if(zona instanceof Virreinatos) {
			return ((Virreinatos)zona).mostrarDistancias();

		}else if(zona instanceof Europa) {

			return ((Europa)zona).mostrarDistancias();
		}else {

			throw new Exception();
		}
	}

	/**
	 * M�todo encargado de ver hacer una manera mas visual todos los productos del Reino Demandados
	 */
	public void verProductosDemandadosElReino() {
		System.out.println("--- Nueva Espa�a ");
		nuevaEspana.verProductosDemandados();
		System.out.println("--- Nueva Granada ");
		nuevaGranda.verProductosDemandados();
		System.out.println("--- Peru ");
		peru.verProductosDemandados();
		System.out.println("--- Plata ");
		plata.verProductosDemandados();
		System.out.println("--- Castilla ");
		castilla.verProductosDemandados();
		System.out.println("--- Aragon ");
		aragon.verProductosDemandados();
		System.out.println("--- Borgo�a ");
		borgona.verProductosDemandados();
		System.out.println("--- Austria ");
		austria.verProductosDemandados();
	}

	/**
	 * M�todo encargado de ver hacer una manera mas visual todas las flotas creadas en el Reino
	 */
	public void verFlotasConMercancias() {
		System.out.println("--- Nueva Espa�a ");
		nuevaEspana.getFlota().verMercancias();
		System.out.println("--- Nueva Granada ");
		nuevaGranda.getFlota().verMercancias();
		System.out.println("--- Peru ");
		peru.getFlota().verMercancias();
		System.out.println("--- Plata ");
		plata.getFlota().verMercancias();
		System.out.println("--- Castilla ");
		castilla.getFlota().verMercancias();
		System.out.println("--- Aragon ");
		aragon.getFlota().verMercancias();
		System.out.println("--- Borgo�a ");
		borgona.getFlota().verMercancias();
		System.out.println("--- Austria ");
		austria.getFlota().verMercancias();
	}

	public NuevaEspana getNuevaEspana() {
		return nuevaEspana;
	}

	public void setNuevaEspana(NuevaEspana nuevaEspana) {
		this.nuevaEspana = nuevaEspana;
	}

	public NuevaGranada getNuevaGranda() {
		return nuevaGranda;
	}

	public void setNuevaGranda(NuevaGranada nuevaGranda) {
		this.nuevaGranda = nuevaGranda;
	}

	public Peru getPeru() {
		return peru;
	}

	public void setPeru(Peru peru) {
		this.peru = peru;
	}

	public Plata getPlata() {
		return plata;
	}

	public void setPlata(Plata plata) {
		this.plata = plata;
	}

	public Castilla getCastilla() {
		return castilla;
	}

	public void setCastilla(Castilla castilla) {
		this.castilla = castilla;
	}

	public Aragon getAragon() {
		return aragon;
	}

	public void setAragon(Aragon aragon) {
		this.aragon = aragon;
	}

	public Borgona getBorgona() {
		return borgona;
	}

	public void setBorgona(Borgona borgona) {
		this.borgona = borgona;
	}

	public Austria getAustria() {
		return austria;
	}

	public void setAustria(Austria austria) {
		this.austria = austria;
	}

	@Override
	public String toString() {
		return "ReinoCompleto =" + nuevaEspana + " / " + peru + " / " + plata + " / "	+ castilla + " / " + aragon + " / " + borgona + " / " + austria ;
	}

}
