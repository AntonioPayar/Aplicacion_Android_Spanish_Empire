package com.example.proyecto.Clases;

import java.util.Random;


/**
 *
 * @author Grupo
 *
 */
public class NuevaGranada extends Virreinatos {


	private MateriasPrimas recoleccionOro;
	/**
	 * atributo que almacena la cantidad de oro producida en el Reino
	 */
	private MateriasPrimas recoleccionPlata;
	/**
	 * atributo que almacena la cantidad de plata producida en el Reino
	 */
	private MateriasPrimas recoleccionTabaco;
	/**
	 * atributo que almacena la cantidad de tabaco producida en el Reino
	 */
	private MateriasPrimas recoleccionCafe;
	/**
	 * atributo que almacena la cantidad de cafe producida en el Reino
	 */

	/**
	 * Constructor donde se pasar�n por par�metros los atributos anteriores
	 * @param nombre Informa sobre el nombre
	 * @param continente Informa sobre el continente que se encuentra
	 * @param poblacion Informa sobre la cantidad de poblaci�n que vive en el reino
	 * @throws Exception env�a una Excepcion si no encuetra el Producto
	 */
	public NuevaGranada(String nombre,String continente, int poblacion) throws Exception {
		super(nombre,continente, poblacion, 1970, 4707, 7258, 0, 4288,6829,8186,8949);
		this.recoleccionOro = new MateriasPrimas(ProductoNombre.Oro, 0, 0, 0, 0, 0, 10);
		calcularProduccionMensual(this.recoleccionOro);
		this.recoleccionPlata = new MateriasPrimas(ProductoNombre.Plata, 0, 0, 0, 0, 0, 10);
		calcularProduccionMensual(this.recoleccionPlata);
		this.recoleccionTabaco =new MateriasPrimas(ProductoNombre.Tabaco, 0, 0, 0, 0, 0, 10);
		calcularProduccionMensual(this.recoleccionTabaco);
		this.recoleccionCafe = new MateriasPrimas(ProductoNombre.Cafe, 0, 0, 0, 0, 0, 10);
		calcularProduccionMensual(this.recoleccionCafe);
		this.calcularProductosDemandados();
	}

	/**
	 * Contructor de copia
	 * @param nuevaGranada Objeto de copia
	 */
	public NuevaGranada(NuevaGranada nuevaGranada) {
		super(nuevaGranada);
		this.recoleccionOro = nuevaGranada.getRecoleccionOro();
		this.recoleccionPlata = nuevaGranada.getRecoleccionPlata();
		this.recoleccionTabaco = nuevaGranada.getRecoleccionTabaco();
		this.recoleccionCafe = nuevaGranada.getRecoleccionCafe();
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
			case Oro:
				if(this.recoleccionOro.getCantidad()>=cantidad) {

					this.recoleccionOro.setCantidad(this.recoleccionOro.getCantidad()-cantidad);
					newProduct= new MateriasPrimas(recoleccionOro);
					newProduct.setCantidad(cantidad);
					mercancia= new Mercancia("Oro",this.getNombre());
					mercancia.anadirProducto(newProduct);
					this.getMercancia().put(this.getIdMercancias(), mercancia);
					this.setIdMercancias(this.getIdMercancias()+1);
				}else {
					throw new IllegalArgumentException(this.getNombre()+" no tiene " + cantidad+" kg de "+producto);
				}
				break;
			case Plata:
				if(this.recoleccionPlata.getCantidad()>=cantidad) {

					this.recoleccionPlata.setCantidad(this.recoleccionPlata.getCantidad()-cantidad);
					newProduct= new MateriasPrimas(recoleccionPlata);
					newProduct.setCantidad(cantidad);
					mercancia= new Mercancia("Plata",this.getNombre());
					mercancia.anadirProducto(newProduct);
					this.getMercancia().put(this.getIdMercancias(), mercancia);
					this.setIdMercancias(this.getIdMercancias()+1);
				}else {
					throw new IllegalArgumentException(this.getNombre()+" no tiene " + cantidad+" kg de "+producto);
				}

				break;
			case Tabaco:

				if(this.recoleccionTabaco.getCantidad()>=cantidad) {

					this.recoleccionTabaco.setCantidad(this.recoleccionTabaco.getCantidad()-cantidad);
					newProduct= new MateriasPrimas(recoleccionTabaco);
					newProduct.setCantidad(cantidad);
					mercancia= new Mercancia("Tabaco",this.getNombre());
					mercancia.anadirProducto(newProduct);
					this.getMercancia().put(this.getIdMercancias(), mercancia);
					this.setIdMercancias(this.getIdMercancias()+1);
				}else {
					throw new IllegalArgumentException(this.getNombre()+" no tiene " + cantidad+" kg de "+producto);
				}
				break;
			case Cafe:

				if(this.recoleccionCafe.getCantidad()>=cantidad) {

					this.recoleccionCafe.setCantidad(this.recoleccionCafe.getCantidad()-cantidad);
					newProduct= new MateriasPrimas(recoleccionCafe);
					newProduct.setCantidad(cantidad);
					mercancia= new Mercancia("Cafe",this.getNombre());
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
			}while(productoNombre==ProductoNombre.Oro || productoNombre==ProductoNombre.Plata || productoNombre==ProductoNombre.Tabaco || productoNombre==ProductoNombre.Cafe);
			this.getProductosDemandados()[i]=productoNombre;
		}
	}

	public String  verproduccionMensual() {
		return super.verproduccionMensual()+"Produccion de : "+this.recoleccionOro.toString() + "/ Produccion de de : "+ this.recoleccionPlata.toString() + "/ Produccion de de : "+this.recoleccionTabaco.toString() + "/ Produccion de de : "+this.recoleccionCafe.toString();
	}

	public MateriasPrimas getRecoleccionOro() {
		return recoleccionOro;
	}

	public void setRecoleccionOro(MateriasPrimas recoleccionOro) {
		this.recoleccionOro = recoleccionOro;
	}

	public MateriasPrimas getRecoleccionPlata() {
		return recoleccionPlata;
	}

	public void setRecoleccionPlata(MateriasPrimas recoleccionPlata) {
		this.recoleccionPlata = recoleccionPlata;
	}

	public MateriasPrimas getRecoleccionTabaco() {
		return recoleccionTabaco;
	}

	public void setRecoleccionTabaco(MateriasPrimas recoleccionTabaco) {
		this.recoleccionTabaco = recoleccionTabaco;
	}

	public MateriasPrimas getRecoleccionCafe() {
		return recoleccionCafe;
	}

	public void setRecoleccionCafe(MateriasPrimas recoleccionCafe) {
		this.recoleccionCafe = recoleccionCafe;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}