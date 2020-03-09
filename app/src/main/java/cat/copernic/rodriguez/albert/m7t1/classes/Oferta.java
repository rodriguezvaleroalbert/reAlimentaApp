package cat.copernic.rodriguez.albert.m7t1.classes;

import androidx.annotation.NonNull;

public class Oferta {
    private int idOferta;
    private String titolOferta;
    private String descripcioOferta;
    private String horariRecogida;
    private String idNegoci;

    public Oferta() {
    }

    public Oferta(int idOferta, String titolOferta, String descripcioOferta, String horariRecogida, String idNegoci) {
        this.idOferta = idOferta;
        this.titolOferta = titolOferta;
        this.descripcioOferta = descripcioOferta;
        this.horariRecogida = horariRecogida;
        this.idNegoci = idNegoci;
    }

    public int getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(int idOferta) {
        this.idOferta = idOferta;
    }

    public String getTitolOferta() {
        return titolOferta;
    }

    public void setTitolOferta(String titolOferta) {
        this.titolOferta = titolOferta;
    }

    public String getDescripcioOferta() {
        return descripcioOferta;
    }

    public void setDescripcioOferta(String descripcioOferta) {
        this.descripcioOferta = descripcioOferta;
    }

    public String getHorariRecogida() {
        return horariRecogida;
    }

    public void setHorariRecogida(String horariRecogida) {
        this.horariRecogida = horariRecogida;
    }

    public String getIdNegoci() {
        return idNegoci;
    }

    public void setIdNegoci(String idNegoci) {
        this.idNegoci = idNegoci;
    }

    @Override
    public String toString() {
        return "Oferta{" +
                "idOferta=" + idOferta +
                ", titolOferta='" + titolOferta + '\'' +
                ", descripcioOferta='" + descripcioOferta + '\'' +
                ", horariRecogida='" + horariRecogida + '\'' +
                ", idNegoci='" + idNegoci + '\'' +
                '}';
    }
}
