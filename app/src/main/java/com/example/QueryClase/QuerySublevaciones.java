package com.example.QueryClase;

public class QuerySublevaciones {

    private String pais;
    private String hora;
    private String turno;
    private String partida;

    public QuerySublevaciones(String pais,String hora,String turno,String partida){
        this.pais=pais;
        this.hora=hora;
        this.turno=turno;
        this.partida=partida;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
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
