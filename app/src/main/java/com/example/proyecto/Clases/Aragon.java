package com.example.proyecto.Clases;

import java.util.Random;

/**
 * Clase hija de Europa
 * @author Grupo
 *
 */
public class Aragon extends Europa {
	
	private Alimentos recoleccionTrigo;
	/**
	 * atributo que almacena la cantidad de trigo producida en el Reino
	 */
	private Alimentos recoleccionUvas;
	/**
	 * atributo que almacena la cantidad de uvas producidas en el Reino
	 */
	private Alimentos recoleccionMaiz;
	/**
	 * atributo que almacena la cantidad de Ma�z producida en el Reino
	 */
	private Alimentos recoleccionArroz;
	/**
	 * atributo que almacena la cantidad de Arroz producida en el Reino
	 */

	/**
     * Constructor donde se pasar�n por par�metros los atributos anteriores
     * @param nombre Informa sobre el nombre
     * @param continente Informa sobre el continente que se encuentra
     * @param poblacion Informa sobre la cantidad de poblaci�n que vive en el reino
     * @param territorio Informa sobre el lugar donde est� el Reino
     * @throws Exception env�a Excepcion si no se encuentra el Producto
     */
	public Aragon(String nombre,String continente,int poblacion,String territorio ) throws Exception {
		super(nombre,continente, poblacion,territorio,0, 1778, 1543, 624, 9483,6829,9628,11379);
		this.recoleccionTrigo= new Alimentos(ProductoNombre.Trigo, 0, 0, 0, 0, "Cereal", 0);
		calcularProduccionMensual(this.recoleccionTrigo);
		this.recoleccionUvas= new Alimentos(ProductoNombre.Uvas, 0, 0, 0, 0, "Fruta", 0);
		calcularProduccionMensual(this.recoleccionUvas);
		this.recoleccionMaiz = new Alimentos(ProductoNombre.Maiz, 0, 0, 0, 0, "Semilla", 0);
		calcularProduccionMensual(recoleccionMaiz);
		this.recoleccionArroz = new Alimentos(ProductoNombre.Arroz, 0, 0, 0, 0, "Cereal", 0);
		calcularProduccionMensual(recoleccionArroz);
		this.calcularProductosDemandados();
	}

	/**
	 * Constructor de copia
	 * @param a  copiar
	 */
	public Aragon(Aragon a) {
		super(a);
		this.recoleccionTrigo=a.getRecoleccionTrigo();
		this.recoleccionUvas=a.getRecoleccionUvas();
		this.recoleccionArroz=a.getRecoleccionArroz();
		this.recoleccionMaiz=a.getRecoleccionMaiz();
	}

	/**
     * El usuario procede a crear mercanc�as de los productos  recolectados
     * @param producto nombre del producto
     * @param cantidad cantidad del producto
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
		case Arroz:
			if(this.recoleccionArroz.getCantidad()>=cantidad) {

				this.recoleccionArroz.setCantidad(this.recoleccionArroz.getCantidad()-cantidad);
				newProduct= new Alimentos(recoleccionArroz);
				newProduct.setCantidad(cantidad);
				mercancia= new Mercancia("Arroz",this.getNombre());
				mercancia.anadirProducto(newProduct);
				this.getMercancia().put(this.getIdMercancias(), mercancia);	
				this.setIdMercancias(this.getIdMercancias()+1);
			}else {
				throw new IllegalArgumentException(this.getNombre()+" no tiene " + cantidad+" kg de "+producto);
			}
			break;
		case Maiz:
			if(this.recoleccionMaiz.getCantidad()>=cantidad) {

				this.recoleccionMaiz.setCantidad(this.recoleccionMaiz.getCantidad()-cantidad);
				newProduct= new Alimentos(recoleccionMaiz);
				newProduct.setCantidad(cantidad);
				mercancia= new Mercancia("Maiz",this.getNombre());
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
	 * M�todo, encargado de calcular cu�les de los producotos que no producen, van a demandar
	 */
	private void calcularProductosDemandados() {
		int valor;
		ProductoNombre productoNombre;
		
		for(int i=0;i<this.getProductosDemandados().length;i++) {
			do {
				valor = new Random().nextInt(ProductoNombre.values().length);
				productoNombre=ProductoNombre.values()[valor];
			}while(productoNombre==ProductoNombre.Uvas || productoNombre==ProductoNombre.Trigo);			
			this.getProductosDemandados()[i]=productoNombre;
		}	
	}

	/**
     * M�todo que devuelve la producci�n mensual obtenida de los productos
     * @return devuelve la producci�n total de Reino
     */
	public String  verproduccionMensual() {
		return super.verproduccionMensual()+"Produccion de : "+this.recoleccionTrigo.toString() + "/ Produccion de de : "+ this.recoleccionUvas.toString()+ "/ Produccion de de : "+ this.recoleccionMaiz.toString()+ "/ Produccion de de : "+ this.recoleccionArroz.toString();
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

	public Alimentos getRecoleccionMaiz() {
		return recoleccionMaiz;
	}

	public void setRecoleccionMaiz(Alimentos recoleccionMaiz) {
		this.recoleccionMaiz = recoleccionMaiz;
	}

	public Alimentos getRecoleccionArroz() {
		return recoleccionArroz;
	}

	public void setRecoleccionArroz(Alimentos recoleccionArroz) {
		this.recoleccionArroz = recoleccionArroz;
	}

	@Override
	public String toString() {
		return super.toString();
	}



}
