package com.example.proyecto.Clases;

import com.example.proyecto.PanelDeControl;

import java.util.Random;

/**
 * 
 * @author Grupo
 *
 */
public class Plata extends Virreinatos {
	
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
	private Alimentos recoleccionPatata;
	/**
	 * atributo que almacena la cantidad de patata producida en el Reino
	 */
	
	/**
	 * Constructor donde se pasar�n por par�metros los atributos anteriores
	 * @param nombre Informa sobre el nombre
	 * @param continente Informa sobre el continente que se encuentra
	 * @param poblacion Informa sobre la cantidad de poblaci�n que vive en el reino
	 * @throws Exception env�a Excepcion si no encuentra alguna produccion
	 */
	public Plata(String nombre,String continente, int poblacion) throws Exception {
		super(nombre,continente, poblacion, 4190, 0, 10927, 4707, 7353,11379,12248,12727);
		this.recoleccionPlata = new MateriasPrimas(ProductoNombre.Plata, 0, 0, 0, 0, 0, 10);
		calcularProduccionMensual(this.recoleccionPlata);
		this.recoleccionTabaco = new MateriasPrimas(ProductoNombre.Tabaco, 0, 0, 0, 0, 0, 10);
		calcularProduccionMensual(this.recoleccionTabaco);
		this.recoleccionCafe = new MateriasPrimas(ProductoNombre.Cafe, 0, 0, 0, 0, 0, 10);
		calcularProduccionMensual(this.recoleccionCafe);
		this.recoleccionPatata =new Alimentos(ProductoNombre.Patata, 0, 0, 0, 0, "tub�rculo", 0);
		calcularProduccionMensual(this.recoleccionPatata);
		this.calcularProductosDemandados();
		addFlota();
		baseDatosDemandas();
	}

	protected void baseDatosDemandas(){
		if(this.getProductosDemandados().length>0){
			for(ProductoNombre products :this.getProductosDemandados()){
				PanelDeControl.database.insertDemandas(products,this.getNombre());
			}
		}
	}

	protected  void addFlota(){
		PanelDeControl.database.insertFLotas(this.getNombre());
	}

	/**
	 * Constructor de copia
	 * @param plata Objeto de copia
	 */
	public Plata(Plata plata) {
		super(plata);
		this.recoleccionPlata = plata.getRecoleccionPlata();
		this.recoleccionTabaco = plata.getRecoleccionTabaco();
		this.recoleccionCafe = plata.getRecoleccionCafe();
		this.recoleccionPatata = plata.getRecoleccionPatata();
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
		case Patata:

			if(this.recoleccionPatata.getCantidad()>=cantidad) {

				this.recoleccionPatata.setCantidad(this.recoleccionPatata.getCantidad()-cantidad);
				newProduct= new Alimentos(recoleccionPatata);
				newProduct.setCantidad(cantidad);
				mercancia= new Mercancia("Patata",this.getNombre());
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
	 * M�todo, encargado de calcular cu�les de los productos que no producen, van a demandar
	 */
	private void calcularProductosDemandados() {
		int valor;
		ProductoNombre productoNombre;

		for(int i=0;i<this.getProductosDemandados().length;i++) {
			do {
				valor = new Random().nextInt(ProductoNombre.values().length);
				productoNombre=ProductoNombre.values()[valor];
			}while(productoNombre==ProductoNombre.Plata || productoNombre==ProductoNombre.Tabaco || productoNombre==ProductoNombre.Cafe || productoNombre==ProductoNombre.Patata);			
			this.getProductosDemandados()[i]=productoNombre;
		}	
	}

	public String  verproduccionMensual() {
		return super.verproduccionMensual()+"Produccion de : "+this.recoleccionPlata.toString() + "/ Produccion de de : "+ this.recoleccionTabaco.toString() + "/ Produccion de de : "+this.recoleccionCafe.toString() + "/ Produccion de de : "+this.recoleccionPatata.toString();
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

	public Alimentos getRecoleccionPatata() {
		return recoleccionPatata;
	}

	public void setRecoleccionPatata(Alimentos recoleccionPatata) {
		this.recoleccionPatata = recoleccionPatata;
	}

	@Override
	public String toString() {
		return super.toString();
	}

}
