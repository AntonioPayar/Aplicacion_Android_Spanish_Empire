package com.example.QueryClase;

public class QueryFlotasEnviadas {

    private String flota;
    private String almacenada;
    private String partida;
    private String turno;
    private String destino;
    private String distancia;

    public QueryFlotasEnviadas(String flota,String almacenada,String partida,String turno,String destino,String distancia){
        this.flota=flota;
        this.almacenada=almacenada;
        this.partida=partida;
        this.turno=turno;
        this.destino=destino;
        this.distancia=distancia;
    }

    public String getFlota() {
        return flota;
    }

    public void setFlota(String flota) {
        this.flota = flota;
    }

    public String getAlmacenada() {
        return almacenada;
    }

    public void setAlmacenada(String almacenada) {
        this.almacenada = almacenada;
    }

    public String getPartida() {
        return partida;
    }

    public void setPartida(String partida) {
        this.partida = partida;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getDistancia() {
        return distancia;
    }

    public void setDistancia(String distancia) {
        this.distancia = distancia;
    }
}
