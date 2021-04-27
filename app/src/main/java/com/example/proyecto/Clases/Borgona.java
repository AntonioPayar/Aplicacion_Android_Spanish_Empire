package com.example.proyecto.Clases;

import java.util.Random;


/**
 * Clase hija de Europa
 * @author Grupo
 *
 */
public class Borgona extends Europa{

	
        private MateriasPrimas recoleccionHierro;
        /**
    	 * atributo que almacena la cantidad de hierro producida en el Reino
    	 */
        private Alimentos recoleccionArroz;
        /**
    	 * atributo que almacena la cantidad de arroz producida en el Reino
    	 */
        private Alimentos recoleccionTomates;
        /**
    	 * atributo que almacena la cantidad de tomates producida en el Reino
    	 */
        private Alimentos recoleccionPatatas;
        /**
    	 * atributo que almacena la cantidad de patatas producida en el Reino
    	 */
        
        
        /**
         * Constructor donde se pasar�n por par�metros los atributos anteriores
         * @param nombre Informa sobre el nombre
         * @param continente Informa sobre el continente que se encuentra
         * @param poblacion Informa sobre la cantidad de poblaci�n que vive en el reino
         * @param territorio Informa sobre el lugar donde est� el Reino
         * @throws Exception env�a excepcion si no se encuentra el Producto
         */
        public Borgona(String nombre,String continente,int poblacion,String territorio) throws Exception {
            super(nombre,continente, poblacion ,territorio, 1566, 1147, 0, 1776, 9210,8186,10134,12248);
            this.recoleccionHierro= new MateriasPrimas(ProductoNombre.Hierro, 0, 0, 0, 0, 0, 10);
            calcularProduccionMensual(this.recoleccionHierro);
            this.recoleccionArroz= new Alimentos(ProductoNombre.Arroz, 0, 0, 0, 0, "Gram�neas", 0);
            calcularProduccionMensual(this.recoleccionArroz);
            this.recoleccionTomates= new Alimentos(ProductoNombre.Tomate, 0, 0, 0, 0, "Fruta ", 0);
            calcularProduccionMensual(recoleccionTomates);
            this.recoleccionPatatas= new Alimentos(ProductoNombre.Patata, 0, 0, 0, 0, "Tub�rculo ", 0);
            calcularProduccionMensual(recoleccionPatatas);
            this.calcularProductosDemandados();
        }

        /**
         * Constructor de copia
         * @param a que hay que copiar
         */
        public Borgona(Borgona a) {
            super(a);
            this.recoleccionArroz=a.getRecoleccionArroz();
            this.recoleccionHierro=a.getRecoleccionHierro();
            this.recoleccionTomates=a.getRecoleccionTomates();
            this.recoleccionPatatas=a.getRecoleccionPatatas();
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
    		case Tomate:

    			if(this.recoleccionTomates.getCantidad()>=cantidad) {

    				this.recoleccionTomates.setCantidad(this.recoleccionTomates.getCantidad()-cantidad);
    				newProduct= new Alimentos(recoleccionTomates);
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

    			if(this.recoleccionPatatas.getCantidad()>=cantidad) {

    				this.recoleccionPatatas.setCantidad(this.recoleccionPatatas.getCantidad()-cantidad);
    				newProduct= new Alimentos(recoleccionPatatas);
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
    			}while(productoNombre==ProductoNombre.Hierro || productoNombre==ProductoNombre.Arroz|| productoNombre==ProductoNombre.Patata || productoNombre==ProductoNombre.Tomate);			
    			this.getProductosDemandados()[i]=productoNombre;
    		}	
    	}
               
    	/**
         * M�todo que devuelve la producci�n mensual obtenida de los productos
         * @return Produccion Mensual total de Borgo�a
         */
    	public String  verproduccionMensual() {
    		return super.verproduccionMensual()+"Produccion de : "+this.recoleccionHierro.toString() + "/ Produccion de de : "+ this.recoleccionArroz.toString()+ "/ Produccion de de : "+ this.recoleccionPatatas.toString()+ "/ Produccion de de : "+ this.recoleccionTomates.toString();
    	}

		public MateriasPrimas getRecoleccionHierro() {
			return recoleccionHierro;
		}

		public void setRecoleccionHierro(MateriasPrimas recoleccionHierro) {
			this.recoleccionHierro = recoleccionHierro;
		}

		public Alimentos getRecoleccionArroz() {
			return recoleccionArroz;
		}

		public void setRecoleccionArroz(Alimentos recoleccionArroz) {
			this.recoleccionArroz = recoleccionArroz;
		}

		public Alimentos getRecoleccionTomates() {
			return recoleccionTomates;
		}

		public void setRecoleccionTomates(Alimentos recoleccionTomates) {
			this.recoleccionTomates = recoleccionTomates;
		}

		public Alimentos getRecoleccionPatatas() {
			return recoleccionPatatas;
		}

		public void setRecoleccionPatatas(Alimentos recoleccionPatatas) {
			this.recoleccionPatatas = recoleccionPatatas;
		}

		@Override
		public String toString() {
			return super.toString();
		}
    
        

}
