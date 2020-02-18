package cat.copernic.rodriguez.albert.m7t1;

import androidx.annotation.NonNull;

public class Negoci {
    private int idUsuari;
    private String nomNegoci;
    private String coordenadaNegoci;
    private String ubicacioNegoci;

    public Negoci(String nomNegoci, String coordenadaNegoci, String ubicacioNegoci) {
        this.nomNegoci = nomNegoci;
        this.coordenadaNegoci = coordenadaNegoci;
        this.ubicacioNegoci = ubicacioNegoci;
    }

    public int getIdUsuari() {
        return idUsuari;
    }

    public void setIdUsuari(int idUsuari) {
        this.idUsuari = idUsuari;
    }

    public String getNomNegoci() {
        return nomNegoci;
    }

    public void setNomNegoci(String nomNegoci) {
        this.nomNegoci = nomNegoci;
    }

    public String getCoordenadaNegoci() {
        return coordenadaNegoci;
    }

    public void setCoordenadaNegoci(String coordenadaNegoci) {
        this.coordenadaNegoci = coordenadaNegoci;
    }

    public String getUbicacioNegoci() {
        return ubicacioNegoci;
    }

    public void setUbicacioNegoci(String ubicacioNegoci) {
        this.ubicacioNegoci = ubicacioNegoci;
    }

    @NonNull
    @Override
    public String toString() {
        return "Negoci{" +
                "idUsuari=" + idUsuari +
                ", nomNegoci='" + nomNegoci + '\'' +
                ", coordenadaNegoci='" + coordenadaNegoci + '\'' +
                ", ubicacioNegoci='" + ubicacioNegoci + '\'' +
                '}';
    }
}
