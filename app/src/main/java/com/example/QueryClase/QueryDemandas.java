package com.example.QueryClase;

public class QueryDemandas {

    private String descripcion;
    private String pais;
    private String realizada;
    private String partida;

    public QueryDemandas(String descripcion,String pais,String realizada,String partida){
        this.descripcion=descripcion;
        this.pais=pais;
        this.realizada=realizada;
        this.partida=partida;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getRealizada() {
        return realizada;
    }

    public void setRealizada(String realizada) {
        this.realizada = realizada;
    }

    public String getPartida() {
        return partida;
    }

    public void setPartida(String partida) {
        this.partida = partida;
    }
}
