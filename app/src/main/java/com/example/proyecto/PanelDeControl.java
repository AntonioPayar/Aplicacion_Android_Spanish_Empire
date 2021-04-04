package com.example.proyecto;

import com.example.proyecto.Clases.Aragon;
import com.example.proyecto.Clases.ProductoNombre;
import com.example.proyecto.Clases.Reinos;

import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;



public class PanelDeControl {

    private HashSet<String> zonasSinProductosDemandados ;
    private HashSet<String> zonasSinFlotaTrue ;
    private static int contadorTurnos;
    private ReinoCompleto espana ;
//    private Ventana_Principal principal;
//    private IntroducirDatos base;


    public PanelDeControl() throws Exception {
//		IntroducirDatos.conectar();
//		IntroducirDatos.borrar();
        this.espana= new ReinoCompleto();
        this.contadorTurnos=0;
        this.zonasSinProductosDemandados= new HashSet<String>();
        this.zonasSinFlotaTrue= new HashSet<String>();

        cambiarTruno();
    }

    /**
     * Método encargado de resetear los objetos de la clase
     * (se utilizará para cambiar de turnos y que cada zona pida nuevos productos y los genere)
     * @throws Exception
     */
    protected void constructor() throws Exception {

        this.espana= new ReinoCompleto();
    }


    /**
     * Método encargado de recargar los constructores y poner si alguna zona tiene "sublevaciones"
     * @throws Exception envía Excepcion
     */
    public void cambiarTruno() throws Exception {

        if(contadorTurnos!=0) {
            contadorTurnos++;

            pasarTurno(espana);

            constructor();

            recorrerLista(espana);

            recorrerListaFlotas();

            this.zonasSinFlotaTrue.clear();

//            this.principal.dispose();

        }else {
            contadorTurnos++;
        }

        System.out.println("Nueva España"+this.espana.getNuevaEspana().isSublevaciones());
        System.out.println("Nueva Granada"+this.espana.getNuevaGranda().isSublevaciones());
        System.out.println("Peru"+this.espana.getPeru().isSublevaciones());
        System.out.println("Plata"+this.espana.getPlata().isSublevaciones());
        System.out.println("Aragon"+this.espana.getAragon().isSublevaciones());
        System.out.println("Castilla"+this.espana.getCastilla().isSublevaciones());
        System.out.println("Austria"+this.espana.getAustria().isSublevaciones());
        System.out.println("Borgoña"+this.espana.getBorgona().isSublevaciones());

//        this.principal= new Ventana_Principal(this);
//		IntroducirDatos.insertarDatosProduccion("xqfeff", 3, 2, 3, this.contadorTurnos);
//        principal.setVisible(true);

        if(zonasSinProductosDemandados.size()==8) {
            System.out.println(" Has aguantado "+contadorTurnos+" turnos");

//            this.principal.dispose();
        }

    }


    /**
     * Método encargado de meter en una lista el nombre de las zonas en las que no se ha podido traspasar los productos que demandaban
     * @param espana paremetro de España
     */
    public void pasarTurno(ReinoCompleto espana) {
        zonasSinProductosDemandados.addAll(espana.pasarTurno());
//		zonasSinFlotaTrue.addAll(espana.comprobarFlotasRetornadas());
    }


    /**
     * Método encargado de recorrer la lista de zonas en la que no se han satisfecho las demandas"zonasSinProductosDemandados", y una vez pasado el turno, poner las sublevaciones a "true"
     * y las posibles demandas, que estos Reinos tuviesen, cancelarlas.
     * @param espana parametro de Reino de España
     */
    public void recorrerLista(ReinoCompleto espana) {
        Iterator it= zonasSinProductosDemandados.iterator();
        String nombreZona;

        while(it.hasNext()) {
            nombreZona=(String) it.next();

            switch (nombreZona.toUpperCase()) {
                case "PERU":
                    /**
                     * Una vez sabemos las zonas donde no se ha podido satisfacer la demanda de productos en anteriores turnos, se procede a poner las
                     * sublevaciones a "true" y quitar las posibles demandas de productos que tuviesen
                     */
                    espana.getPeru().setSublevaciones(true);
                    recorrer(espana.getPeru());
                    break;
                case "PLATA":
                    espana.getPlata().setSublevaciones(true);
                    recorrer(espana.getPlata());
                    break;
                case "CASTILLA":
                    espana.getCastilla().setSublevaciones(true);
                    recorrer(espana.getCastilla());
                    break;
                case "NUEVA GRANADA":
                    espana.getNuevaGranda().setSublevaciones(true);
                    recorrer(espana.getNuevaGranda());
                    break;
                case "NUEVA ESPAÑA":
                    espana.getNuevaEspana().setSublevaciones(true);
                    recorrer(espana.getNuevaEspana());
                    break;
                case "ARAGON":
                    espana.getAragon().setSublevaciones(true);
                    recorrer(espana.getAragon());
                    break;
                case "AUSTRIA":
                    espana.getAustria().setSublevaciones(true);
                    recorrer(espana.getAustria());
                    break;
                case "BORGOÑA":
                    espana.getBorgona().setSublevaciones(true);
                    recorrer(espana.getBorgona());
                    break;
                default:
                    throw new IllegalArgumentException(nombreZona+" no esta disponible");
            }
        }
    }

    /**
     * Método encargado de recorrer la lista de las zonas donde no se ha retornado la "Flota" y poner estas a "false" en los países correspondientes.
     */
    public void recorrerListaFlotas() {
        Iterator it= zonasSinFlotaTrue.iterator();
        String nombreZona;
        String palabra[];

        while(it.hasNext()) {
            nombreZona=(String) it.next();

            palabra=this.StringToken(nombreZona);

            switch (palabra[0].toUpperCase()) {
                case "PERU":
                    espana.getPeru().getFlota().setDisponible(false);
                    espana.getPeru().getFlota().setDestino(Integer.parseInt(palabra[1]));

                    break;
                case "PLATA":
                    espana.getPlata().getFlota().setDisponible(false);
                    espana.getPlata().getFlota().setDestino(Integer.parseInt(palabra[1]));

                    break;
                case "CASTILLA":
                    espana.getCastilla().getFlota().setDisponible(false);
                    espana.getCastilla().getFlota().setDestino(Integer.parseInt(palabra[1]));

                    break;
                case "NUEVA GRANADA":
                    espana.getNuevaGranda().getFlota().setDisponible(false);
                    espana.getNuevaEspana().getFlota().setDestino(Integer.parseInt(palabra[1]));

                    break;
                case "NUEVA ESPAÑA":
                    espana.getNuevaEspana().getFlota().setDisponible(false);
                    espana.getNuevaEspana().getFlota().setDestino(Integer.parseInt(palabra[1]));

                    break;
                case "ARAGON":
                    espana.getAragon().getFlota().setDisponible(false);
                    espana.getAragon().getFlota().setDestino(Integer.parseInt(palabra[1]));

                    break;
                case "AUSTRIA":
                    espana.getAustria().getFlota().setDisponible(false);
                    espana.getAustria().getFlota().setDestino(Integer.parseInt(palabra[1]));

                    break;
                case "BORGOÑA":
                    espana.getBorgona().getFlota().setDisponible(false);
                    espana.getBorgona().getFlota().setDestino(Integer.parseInt(palabra[1]));

                    break;
                default:
                    throw new IllegalArgumentException(nombreZona+" no esta disponible");
            }
        }
    }

    /**
     * Método hermano de RecorreListaFlotas se encarga de separar del String generado el nombre del reino que no tiene la flota disponible y la distancia a
     * la que ésta se encuentra
     * @param palabra parámetro que tiene el nombre del Reino y la distancia de su "Flota" unidos
     * @return retorna una array de String donde la primera posición es el Reino y la Segunda la distancia
     */
    private String[] StringToken(String palabra) {
        StringTokenizer st = new StringTokenizer(palabra,",");
        String division []= new String [2];
        int i=0;

        while(st.hasMoreTokens()) {
            division[i]=st.nextToken();
            i++;
        }
        return division;
    }

    /**
     * Método encargado de una vez el Reino está con sublevación "True " quitarles posibles demandas de siguientes turnos
     * @param reino
     */
    private void recorrer(Reinos reino) {
        for(int i=0;i<reino.getProductosDemandados().length;i++) {
            if(reino.getProductosDemandados()[i]!=null) {
//				reino.getProductosDemandados()[i]=null;
                reino.zonaNoDisponibleDemanda();
            }
        }
    }

    /**
     * Método encargado de crear Mercancías directamente desde el Panel de Control
     * @param pais parámetro encargado de introducir el Reino del que se quiere crear una Mercancía
     * @param cantidad parámetro encargado de introducir la cantidad de dicha mercancía
     * @param producto parámetro encargado de introducir el nombre del Producto que queremos crear como Mercancía
     * @throws Exception envía una Excepcion si no puede crear una Mercancia
     */
    public void crearMercancias(Reinos pais, int cantidad, ProductoNombre producto) throws Exception {
        String codigoPais;
        pais.crearMercancia(producto, cantidad);
        int turno;

//        turno= PanelControl.getContadorTurnos();

        if(pais.getNombre().equals("Nueva España")) {
            codigoPais="NE";
        }else if(pais.getNombre().equals("Nueva Granada")) {
            codigoPais="NG";
        }else {
            codigoPais=pais.getNombre().substring(0,2);
        }

        codigoPais=codigoPais+pais.getMercancia().size();

//		IntroducirDatos.insertarDatosMercancias(codigoPais.toUpperCase(),producto.toString(),cantidad,turno,pais.getNombre().toString());

        pais.verMercancias();
    }

    /**
     * Método encargado de introducir en "zonasSinFlotaTrue" los Reinos que no han devuelto sus flotas
     * @param nombre nombre del Reino
     */
    public void meterZonaSinFlota(String nombre) {
        this.zonasSinFlotaTrue.add(nombre);
    }

    /**
     * Método encargado de eliminar en "zonasSinFlotaTrue" los Reinos que no han devuelto sus flotas
     * @param nombre nombre del Reino
     */
    public void quitarReinoDeZonasSinFlota(String nombre) {

        if(this.zonasSinFlotaTrue.contains(nombre)) {
            this.zonasSinFlotaTrue.remove(nombre);
        }
    }

    /**
     * Iterador para ver las flotas que no van a ser "True" o devueltas el siguiente turno
     */
    public void iteradorZonasSinFLota() {
        Iterator it = this.zonasSinFlotaTrue.iterator();

        while(it.hasNext()) {
            System.out.println(it.next());
        }
    }

    //getter setter

    public HashSet<String> getZonasSinProductosDemandados() {
        return zonasSinProductosDemandados;
    }

    public void setZonasSinProductosDemandados(HashSet<String> zonasSinProductosDemandados) {
        this.zonasSinProductosDemandados = zonasSinProductosDemandados;
    }

    public HashSet<String> getZonasSinFlotaTrue() {
        return zonasSinFlotaTrue;
    }

    public void setZonasSinFlotaTrue(HashSet<String> zonasSinFlotaTrue) {
        this.zonasSinFlotaTrue = zonasSinFlotaTrue;
    }

    public static int getContadorTurnos() {
        return contadorTurnos;
    }

    public static void setContadorTurnos(int contadorTurnos) {
        PanelDeControl.contadorTurnos = contadorTurnos;
    }

    public ReinoCompleto getEspana() {
        return espana;
    }

    public void setEspana(ReinoCompleto espana) {
        this.espana = espana;
    }

//    public Ventana_Principal getPrincipal() {
//        return principal;
//    }

//    public void setPrincipal(Ventana_Principal principal) {
//        this.principal = principal;
//    }

//    public IntroducirDatos getBase() {
//        return base;
//    }

//    public void setBase(IntroducirDatos base) {
//        this.base = base;
//    }





}
