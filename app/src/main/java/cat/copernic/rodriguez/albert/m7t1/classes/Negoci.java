package cat.copernic.rodriguez.albert.m7t1.classes;

import androidx.annotation.NonNull;

public class Negoci {
    private int idNegoci;
    private String nomNegoci;
    private String descripcioNegoci;
    private String ubicacioNegoci;
    private String idUsuari;

    public Negoci() {
    }

    public Negoci(int idNegoci, String nomNegoci, String descripcioNegoci, String ubicacioNegoci, String idUsuari) {
        this.idNegoci = idNegoci;
        this.nomNegoci = nomNegoci;
        this.descripcioNegoci = descripcioNegoci;
        this.ubicacioNegoci = ubicacioNegoci;
        this.idUsuari = idUsuari;
    }

    public int getIdNegoci() {
        return idNegoci;
    }

    public void setIdNegoci(int idNegoci) {
        this.idNegoci = idNegoci;
    }

    public String getNomNegoci() {
        return nomNegoci;
    }

    public void setNomNegoci(String nomNegoci) {
        this.nomNegoci = nomNegoci;
    }

    public String getDescripcioNegoci() {
        return descripcioNegoci;
    }

    public void setDescripcioNegoci(String descripcioNegoci) {
        this.descripcioNegoci = descripcioNegoci;
    }

    public String getUbicacioNegoci() {
        return ubicacioNegoci;
    }

    public void setUbicacioNegoci(String ubicacioNegoci) {
        this.ubicacioNegoci = ubicacioNegoci;
    }

    public String getIdUsuari() {
        return idUsuari;
    }

    public void setIdUsuari(String idUsuari) {
        this.idUsuari = idUsuari;
    }

    @Override
    public String toString() {
        return "Negoci{" +
                "idNegoci=" + idNegoci +
                ", nomNegoci='" + nomNegoci + '\'' +
                ", descripcioNegoci='" + descripcioNegoci + '\'' +
                ", ubicacioNegoci='" + ubicacioNegoci + '\'' +
                ", idUsuari='" + idUsuari + '\'' +
                '}';
    }
}
