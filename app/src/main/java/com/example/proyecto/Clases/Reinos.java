package com.example.proyecto.Clases;
import com.example.proyecto.Flota;
import com.example.proyecto.Territorio;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Random;

public abstract class Reinos extends Territorio {

	
	private int poblacion;
	/**
	 * Atributo que indica el n�mero de poblaci�n en un reino
	 */
	private LinkedHashMap <Integer, Mercancia> importacionMercancia;
	/**
	 * Atributo encargado de almacenar todas las mercanc�as que han sido enviadas de otros pa�ses
	 */
	private LinkedHashMap <Integer,Mercancia> mercancia;
	/**
	 * Atributo encargado de almacemar las mercanc�as que el usuario crea
	 */
	private double dineroTotal;
	/**
	 * Atributo encargado de alamcenar el dinero del Reino
	 */
	private boolean sublevaciones;
	/**
	 * Atributo encargado de especificar si el Reino est� "disponible "o "no"
	 */
	private int idMercancias;
	/**
	 * Atributo encargado de establecer el id de las Mercanc�as
	 */
	private int idImportaciones;
	/**
	 * Atributo encargado de establecer el id de las importaciones
	 */
	private ProductoNombre [] productosDemandados;
	/**
	 * Atributo encargado de almacenar el n�mero que productos que el Reino quiere 
	 */
	
	/**
	 * Constructor de Reinos
	 * @param nombre Par�metro que establece el nombre del Reino
	 * @param continente Par�metro que establece el nombre del continente del Reino
	 * @param poblacion Par�metro que establece el n�mero de poblaci�n del Reino
	 */
	public Reinos(String nombre, String continente,int poblacion) {
		super(nombre, continente);
		this.poblacion= poblacion;
		this.importacionMercancia=new LinkedHashMap<Integer, Mercancia>();
		this.mercancia=new LinkedHashMap<Integer, Mercancia>();
		this.productosDemandados=new ProductoNombre[(int)(Math.random()*2)];
		this.sublevaciones=false;
		this.idMercancias=1;
		this.idImportaciones=1;
	}

	/**
	 * Constructor de copia
	 * @param obj Objeto que copiar
	 */
	public Reinos(Reinos obj) {
		super(obj);
		this.poblacion=obj.getPoblacion();
		this.importacionMercancia=obj.getImportacionMercancia();
		this.dineroTotal=obj.getDineroTotal();
		this.sublevaciones=obj.isSublevaciones();
	}


	/**
	 * M�todo que se encarga de calcular la cantidad de kg que se producen en un determinado lugar, dependiendo de su nombre
	 * @param product nombre del Producto del que queremos calcular su producci�n
	 * @throws Exception envio de Excepcion
	 */

	public void calcularProduccionMensual(Productos product) throws Exception {
		Random rnd = new Random();
		int random;
		int random2;

		random=rnd.nextInt((10000-5000)+5000)+10000;

		switch (product.getNombre()) {
		case Patata: 
			/**
			 * La f�rmula de los alimentos se basa en un n�mero de kg determinados que asumimos que una persona corriente consume de media al mes
			 *  de un determinado producto por el n�mero de personas de su pa�s determinado mas un n�mero aleatorio con el que se podr� comerciar con el resto de Pa�ses.
			 */
			product.setCantidad((4*this.poblacion)+random);
			//El objetivo es meter los datos de la produccion integra en la base de datos produccion total
			cosumoProductos(product);
			break;
		case Tomate:
			product.setCantidad((2*this.poblacion)+random);
			cosumoProductos(product);
			break;
//		case Cacao:
//			product.setCantidad((1*this.poblacion)+random);
//			cosumoProductos(product);
//			break;
		case Maiz:
			product.setCantidad((6*this.poblacion)+random);
			cosumoProductos(product);
			break;
		case Trigo:
			product.setCantidad((5*this.poblacion)+random);
			cosumoProductos(product);
			break;
		case Arroz:
			product.setCantidad((3*this.poblacion)+random);
			cosumoProductos(product);
			break;
		case Uvas:
			product.setCantidad((1*this.poblacion)+random);
			cosumoProductos(product);
			break;
		case Hierro:
			random2=rnd.nextInt((200-10)+10)+200;
			product.setCantidad(random2);
			cosumoProductos(product);
			break;
//		case Algodon:
//			product.setCantidad((1*this.poblacion)+random);
//			cosumoProductos(product);
//			break;		
		case Oro:
			random2=rnd.nextInt((50-10)+10)+50;
			product.setCantidad(random2);
			cosumoProductos(product);
			break;
		case Plata:
			random2=rnd.nextInt((50-10)+10)+50;
			product.setCantidad(random2);
			cosumoProductos(product);
			break;
		case Tabaco:
			product.setCantidad((1*this.poblacion)+random);
			cosumoProductos(product);
			break;
		case Cafe:
			product.setCantidad((3*this.poblacion)+random);
			cosumoProductos(product);
			break;
		default:
			throw new Exception("No existe "+product.getNombre());
		}
	}


	/**
	 * M�todo para ver las importaciones de cada zona
	 */
	public void verMercanciasImportacion() {
		Iterator iterador = this.importacionMercancia.keySet().iterator();
		int key;

		while(iterador.hasNext()) {
			key=(int) iterador.next();
			System.out.println("Exportacion "+this.importacionMercancia.get(key).getOrigen()+" numero "+key+" con "+this.importacionMercancia.get(key)+" a "+this.getNombre());
		}
	}
/**
 * M�todo encargado de recorrer la lista de las mercanc�as y mostrar su contenido
 */
	public void verMercancias() {
		Iterator iterador = this.mercancia.keySet().iterator();
		int key;

		while(iterador.hasNext()) {
			key=(int) iterador.next();
			System.out.println("Mercancia numero "+key+ " contenido "+this.mercancia.get(key));
		}
	}

	public String verproduccionMensual() {
		return this.getNombre()+" ";	
	}

	public void crearMercancia(ProductoNombre producto,int cantidad)throws Exception {

	}

	/**M�todo encargado de traspasar las importaciones de una flota al pa�s al que se les ha mandado 
	 * 
	 * @param barcos Par�metro encargado de pasar la flota que ha sido envidada por otro pa�s
	 */
	public void llegadaImpotacion(Flota barcos) {
		int key;
		Iterator iterador = barcos.getArrayMercancias().keySet().iterator();

		while(iterador.hasNext()) {
			key=(int) iterador.next();
			this.importacionMercancia.put(this.idImportaciones, barcos.getArrayMercancias().get(key));
			this.setIdImportaciones(this.getIdImportaciones()+1);
		}

		barcos.getArrayMercancias().clear();
		barcos.setPesoTodasMercancias(0);
		this.comprobarImportaciones();
	}

	/**
	 * M�todo encargado de recorrer  las importaciones y comprobar si �stas, contienen productos demandados
	 */
	private void comprobarImportaciones() {
		Iterator iterador = this.importacionMercancia.keySet().iterator();
		int key;

		while(iterador.hasNext()) {
			key=(int) iterador.next();
			for(int i=0;i<this.productosDemandados.length;i++) {
				if(this.productosDemandados[i]!=null) {
					if(this.productosDemandados[i].equals(this.importacionMercancia.get(key).getProducto().getNombre())) {
						this.productosDemandados[i]=null;
						this.importacionMercancia.remove(key);
					}
				}
			}
		}
	}

	/**
	 * M�todo encargado de calcular de forma autom�tica el consumo de productos dentro de un reino
	 * @param product par�metro encargado de pasar el producto del que se quiere calcular el consumo 
	 * @throws SQLException 
	 */
	private void cosumoProductos(Productos product) throws SQLException {
		int newCantidad;
		int produccionPrincipal;
	

		if(product.getNombre()==ProductoNombre.Cafe || product.getNombre()==ProductoNombre.Tabaco) {
			//se consume el 25% de lo generado
			newCantidad=(int) (product.getCantidad()*0.25);
			produccionPrincipal=product.getCantidad();
			product.setCantidad(product.getCantidad()-newCantidad);
		}else {
			//consume el 60% de lo generado
			newCantidad=(int) (product.getCantidad()*0.60);
			produccionPrincipal=product.getCantidad();
			product.setCantidad(product.getCantidad()-newCantidad);
		}
		
		//insertamos en la base de datos los primeros contenidos

//		IntroducirDatos.insertarDatosProduccion(PanelControl.getContadorTurnos(), this.getNombre(), product.getNombre().toString(), produccionPrincipal, newCantidad, product.getCantidad());
	}

	/**
	 * M�todo encargado de establecer las sublevaciones de cada reino
	 */
	public void sublevaciones() {

	}

	/**
	 * M�todo encargado de recorrer la lista de "ProductosDemandamos" y mostrar los mismos de cada Region
	 */
	public void verProductosDemandados() {
		for(int i=0;i<this.productosDemandados.length;i++) {
			if(this.productosDemandados[i]!=null) {
				System.out.println(this.productosDemandados[i]);
			}
		}
	}

	/**
	 * M�todo encargado de calcular cu�les de los productos que no producen van a demandar
	 * @return retorna el true si estan todas las producciones satisfechas
	 */
	public boolean comprobarProductosDemandados() {
		Iterator iterador = this.importacionMercancia.keySet().iterator();
		int key;
		int contador=0;

		while(iterador.hasNext()) {
			key=(int) iterador.next();
			if(productosDemandados.length!=0) {
				for(int i=0;i<this.productosDemandados.length;i++) {
					if(this.productosDemandados[i]!=null) {
						//comparo dos enums con ==  debido a que equals da nullpointedException
						if(this.importacionMercancia.get(iterador).getProducto().getNombre()==this.productosDemandados[i]) {
							contador++;
							this.productosDemandados[i]=null;
						}
					}
				}
			}
		}

		if(contador==this.productosDemandados.length) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * M�todo encargado de reemplazar las posibles demandas de un reino que tiene las sublevaciones a "true"
	 */
	public void zonaNoDisponibleDemanda() {
		this.productosDemandados=new ProductoNombre[0];
	}
	
	
	//getter

	public int getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(int poblacion) {
		this.poblacion = poblacion;
	}

	public LinkedHashMap<Integer, Mercancia> getImportacionMercancia() {
		return importacionMercancia;
	}

	public void setImportacionMercancia(LinkedHashMap<Integer, Mercancia> importacionMercancia) {
		this.importacionMercancia = importacionMercancia;
	}

	public LinkedHashMap<Integer, Mercancia> getMercancia() {
		return mercancia;
	}

	public void setMercancia(LinkedHashMap<Integer, Mercancia> mercancia) {
		this.mercancia = mercancia;
	}

	public double getDineroTotal() {
		return dineroTotal;
	}

	public void setDineroTotal(double dineroTotal) {
		this.dineroTotal = dineroTotal;
	}

	public boolean isSublevaciones() {
		return sublevaciones;
	}

	public void setSublevaciones(boolean sublevaciones) {
		this.sublevaciones = sublevaciones;
	}

	public int getIdMercancias() {
		return idMercancias;
	}

	public void setIdMercancias(int idMercancias) {
		this.idMercancias = idMercancias;
	}

	public int getIdImportaciones() {
		return idImportaciones;
	}

	public void setIdImportaciones(int idImportaciones) {
		this.idImportaciones = idImportaciones;
	}

	public ProductoNombre[] getProductosDemandados() {
		return productosDemandados;
	}

	public void setProductosDemandados(ProductoNombre[] productosDemandados) {
		this.productosDemandados = productosDemandados;
	}
	

}
