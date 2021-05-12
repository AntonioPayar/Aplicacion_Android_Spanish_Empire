package com.example.proyecto.Clases;

import com.example.proyecto.PanelDeControl;

import java.util.Random;

/**
 * 
 * @author Grupo
 *
 */
public class Peru extends Virreinatos {

	
	private MateriasPrimas recoleccionOro;
	/**
	 * atributo que almacena la cantidad de oro producida en el Reino
	 */
	private Alimentos recoleccionMaiz;
	/**
	 * atributo que almacena la cantidad de maiz producida en el Reino
	 */
	private Alimentos recoleccionTomate;
	/**
	 * atributo que almacena la cantidad de tomates producida en el Reino
	 */
	private Alimentos recoleccionPatata;
	/**
	 * atributo que almacena la cantidad de patatas producida en el Reino
	 */
	
	//Constructores
	/**
	 * Constructor donde se pasar�n por par�metros los atributos anteriores
	 * @param nombre Informa sobre el nombre
	 * @param continente Informa sobre el continente que se encuentra
	 * @param poblacion Informa sobre la cantidad de poblaci�n que vive en el reino
	 * @throws Exception no se introducen otros objetos distintos 
	 */
	public Peru(String nombre,String continente, int poblacion) throws Exception {
		super(nombre,continente, poblacion, 0, 4163, 9123, 1970, 4717,9628,10134,10864);
		this.recoleccionOro = new MateriasPrimas(ProductoNombre.Oro, 0, 0, 0, 0, 0, 10);
		calcularProduccionMensual(this.recoleccionOro);
		this.recoleccionMaiz = new Alimentos(ProductoNombre.Maiz, 0, 0, 0, 0, "Gram�nea ", 0);
		calcularProduccionMensual(this.recoleccionMaiz);
		this.recoleccionTomate = new Alimentos(ProductoNombre.Tomate, 0, 0, 0, 0, "fruta", 0);
		calcularProduccionMensual(this.recoleccionTomate);
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
	 * Constructor de Copia
	 * @param peru Objeto de Copia
	 */
	public Peru(Peru peru) {
		super(peru);
		this.recoleccionOro = peru.getRecoleccionOro();
		this.recoleccionMaiz = peru.getRecoleccionMaiz();
		this.recoleccionTomate = peru.getRecoleccionTomate();
		this.recoleccionPatata = peru.getRecoleccionPatata();
	}

	//Metodos Publicos
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
		case Tomate:

			if(this.recoleccionTomate.getCantidad()>=cantidad) {

				this.recoleccionTomate.setCantidad(this.recoleccionTomate.getCantidad()-cantidad);
				newProduct= new Alimentos(recoleccionTomate);
				newProduct.setCantidad(cantidad);
				mercancia= new Mercancia("Tomate",this.getNombre());
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
			}while(productoNombre==ProductoNombre.Oro || productoNombre==ProductoNombre.Maiz || productoNombre==ProductoNombre.Tomate || productoNombre==ProductoNombre.Patata);			
			this.getProductosDemandados()[i]=productoNombre;
		}	
	}

	public String  verproduccionMensual() {
		return super.verproduccionMensual()+"Produccion de : "+this.recoleccionOro.toString() + "/ Produccion de de : "+ this.recoleccionMaiz.toString() + "/ Produccion de de : "+this.recoleccionTomate.toString() + "/ Produccion de de : "+this.recoleccionPatata.toString();
	}

	public MateriasPrimas getRecoleccionOro() {
		return recoleccionOro;
	}

	public void setRecoleccionOro(MateriasPrimas recoleccionOro) {
		this.recoleccionOro = recoleccionOro;
	}

	public Alimentos getRecoleccionMaiz() {
		return recoleccionMaiz;
	}

	public void setRecoleccionMaiz(Alimentos recoleccionMaiz) {
		this.recoleccionMaiz = recoleccionMaiz;
	}

	public Alimentos getRecoleccionTomate() {
		return recoleccionTomate;
	}

	public void setRecoleccionTomate(Alimentos recoleccionTomate) {
		this.recoleccionTomate = recoleccionTomate;
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
