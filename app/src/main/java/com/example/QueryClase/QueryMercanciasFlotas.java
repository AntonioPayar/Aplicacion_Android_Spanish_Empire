package com.example.QueryClase;

public class QueryMercanciasFlotas {

    private String flota_pais;
    private String mercancia;
    private String turno;
    private String partida;

    public QueryMercanciasFlotas(String flota_pais,String mercancia,String turno,String partida){
        this.flota_pais=flota_pais;
        this.mercancia=mercancia;
        this.turno=turno;
        this.partida=partida;
    }

    public String getFlota_pais() {
        return flota_pais;
    }

    public void setFlota_pais(String flota_pais) {
        this.flota_pais = flota_pais;
    }

    public String getMercancia() {
        return mercancia;
    }

    public void setMercancia(String mercancia) {
        this.mercancia = mercancia;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getPartida() {
        return partida;
    }

    public void setPartida(String partida) {
        this.partida = partida;
    }
}
