package com.example.QueryClase;

public class QueryProductos {

    private String pais;
    private String turno;
    private String producto;
    private String cantidad;
    private String partida;

    public QueryProductos(String pais,String turno,String producto,String cantidad,String partida){
        this.pais=pais;
        this.turno=turno;
        this.producto=producto;
        this.cantidad=cantidad;
        this.partida=partida;
    }

    public String getPais() {
        return pais;
    }

    public String getTurno() {
        return turno;
    }

    public String getProducto() {
        return producto;
    }

    public String getCantidad() {
        return cantidad;
    }

    public String getPartida() {
        return partida;
    }
}
