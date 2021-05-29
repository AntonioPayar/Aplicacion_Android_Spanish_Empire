package com.example.QueryClase;

public class QueryMercancias {

    private String mercancia;
    private String pais;
    private String producion;
    private String cantidadSelec;
    private String turno;

    public QueryMercancias(String mercancia,String pais,String producion,String cantidadSelec,String turno){
        this.mercancia=mercancia;
        this.pais=pais;
        this.producion=producion;
        this.cantidadSelec=cantidadSelec;
        this.turno=turno;
    }

    public String getMercancia() {
        return mercancia;
    }

    public void setMercancia(String mercancia) {
        this.mercancia = mercancia;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getProducion() {
        return producion;
    }

    public void setProducion(String producion) {
        this.producion = producion;
    }

    public String getCantidadSelec() {
        return cantidadSelec;
    }

    public void setCantidadSelec(String cantidadSelec) {
        this.cantidadSelec = cantidadSelec;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }
}
