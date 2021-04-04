package com.example.proyecto.Clases;

import com.example.proyecto.Alimentos;
import com.example.proyecto.MateriasPrimas;
import com.example.proyecto.Mercancia;
import com.example.proyecto.Productos;

import java.util.Random;


/**
 * Clase hija de Europa
 * @author Grupo
 *
 */
public class Castilla extends Europa {
	
	private Alimentos recoleccionTrigo;
	/**
	 * atributo que almacena la cantidad de trigo producida en el Reino
	 */
	private Alimentos recoleccionUvas;
	/**
	 * atributo que almacena la cantidad de uvas producida en el Reino
	 */
	private MateriasPrimas recoleccionHierro;
	/**
	 * atributo que almacena la cantidad de hierro producida en el Reino
	 */
	
	   /**
     * Constructor donde se pasar�n por par�metros los atributos anteriores
     * @param nombre Informa sobre el nombre
     * @param continente Informa sobre el continente que se encuentra
     * @param poblacion Informa sobre la cantidad de poblaci�n que vive en el reino
     * @param territorio Informa sobre el lugar donde est� el Reino
     * @throws Exception env�a Excepcion si no encuentra el Producto
     */
	public Castilla(String nombre,String continente, int poblacion,String territorio) throws Exception {
		super(nombre,continente, poblacion,territorio, 621,	2375, 1784, 0, 9020,7258,9123,10927);
		this.recoleccionTrigo= new Alimentos(ProductoNombre.Trigo, 0, 0, 0, 0, "cereal", 0);
		calcularProduccionMensual(this.recoleccionTrigo);
		this.recoleccionUvas= new Alimentos(ProductoNombre.Uvas, 0, 0, 0, 0, "fruta", 0);
		calcularProduccionMensual(this.recoleccionUvas);
		this.recoleccionHierro=new MateriasPrimas(ProductoNombre.Hierro, 0, 0, 0, 0, 0, 10);
		calcularProduccionMensual(this.recoleccionHierro);
		this.calcularProductosDemandados();
	}

	/**
	 * Constructor de copia
	 * @param obj Objeto de copia
	 */
	public Castilla(Castilla obj) {
		super(obj);
		this.recoleccionTrigo=obj.getRecoleccionTrigo();
		this.recoleccionUvas=obj.getRecoleccionUvas();
		this.recoleccionHierro=obj.getRecoleccionHierro();
	}

    /**
     * El usuario procede a crear mercanc�as de los productos  recolectados
     * @param producto nombre del producto
     * @param cantidad cantidad del producto
     * @throws Exception envia Excepcion si no encuentra el Producto
     */
	public void crearMercancia(ProductoNombre producto,int cantidad)throws Exception {
		Mercancia mercancia;
		Productos newProduct;

		switch (producto) {
		case Trigo:
			if(this.recoleccionTrigo.getCantidad()>=cantidad) {

				this.recoleccionTrigo.setCantidad(this.recoleccionTrigo.getCantidad()-cantidad);
				newProduct= new Alimentos(recoleccionTrigo);
				newProduct.setCantidad(cantidad);
				mercancia= new Mercancia("Trigo",this.getNombre());
				mercancia.anadirProducto(newProduct);
				this.getMercancia().put(this.getIdMercancias(), mercancia);	
				this.setIdMercancias(this.getIdMercancias()+1);
			}else {
				throw new IllegalArgumentException(this.getNombre()+" no tiene " + cantidad+" kg de "+producto);
			}
			break;
		case Uvas:
			if(this.recoleccionUvas.getCantidad()>=cantidad) {

				this.recoleccionUvas.setCantidad(this.recoleccionUvas.getCantidad()-cantidad);
				newProduct= new Alimentos(recoleccionUvas);
				newProduct.setCantidad(cantidad);
				mercancia= new Mercancia("Uvas",this.getNombre());
				mercancia.anadirProducto(newProduct);
				this.getMercancia().put(this.getIdMercancias(), mercancia);	
				this.setIdMercancias(this.getIdMercancias()+1);
			}else {
				throw new IllegalArgumentException(this.getNombre()+" no tiene " + cantidad+" kg de "+producto);
			}

			break;
		case Hierro:

			if(this.recoleccionHierro.getCantidad()>=cantidad) {

				this.recoleccionHierro.setCantidad(this.recoleccionHierro.getCantidad()-cantidad);
				newProduct= new MateriasPrimas(recoleccionHierro);
				newProduct.setCantidad(cantidad);
				mercancia= new Mercancia("Hierro",this.getNombre());
				mercancia.anadirProducto(newProduct);
				this.getMercancia().put(this.getIdMercancias(), mercancia);	
				this.setIdMercancias(this.getIdMercancias()+1);	
			}else {
				throw new IllegalArgumentException(this.getNombre()+" no tiene " + cantidad+" kg de "+producto);
			}
			break;
		default:
			throw new IllegalArgumentException("Este reino no produce " + producto);
		}

	}
	/**
	 * M�todo, encargado de calcular cu�les de los productos que no producen. van a demandar
	 */
	private void calcularProductosDemandados() {
		int valor;
		ProductoNombre productoNombre;
		
		for(int i=0;i<this.getProductosDemandados().length;i++) {
			do {
				valor = new Random().nextInt(ProductoNombre.values().length);
				productoNombre=ProductoNombre.values()[valor];
			}while(productoNombre==ProductoNombre.Trigo || productoNombre==ProductoNombre.Uvas || productoNombre==ProductoNombre.Hierro);			
			this.getProductosDemandados()[i]=productoNombre;
		}	
	}

	/**
     * M�todo que devuelve la producci�n mensual obtenida de los productos
     * @return devuelve la la Produccion Mensual de Castilla
     */
	public String  verproduccionMensual() {
		return super.verproduccionMensual()+"Produccion de : "+this.recoleccionTrigo.toString() + "/ Produccion de de : "+ this.recoleccionUvas.toString() + "/ Produccion de de : "+this.recoleccionHierro.toString();
	}

	public Alimentos getRecoleccionTrigo() {
		return recoleccionTrigo;
	}

	public void setRecoleccionTrigo(Alimentos recoleccionTrigo) {
		this.recoleccionTrigo = recoleccionTrigo;
	}

	public Alimentos getRecoleccionUvas() {
		return recoleccionUvas;
	}

	public void setRecoleccionUvas(Alimentos recoleccionUvas) {
		this.recoleccionUvas = recoleccionUvas;
	}

	public MateriasPrimas getRecoleccionHierro() {
		return recoleccionHierro;
	}

	public void setRecoleccionHierro(MateriasPrimas recoleccionHierro) {
		this.recoleccionHierro = recoleccionHierro;
	}

	@Override
	public String toString() {
		return super.toString();
	}	

}
