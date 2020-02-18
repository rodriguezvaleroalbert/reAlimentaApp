package cat.copernic.rodriguez.albert.m7t1;

import androidx.annotation.NonNull;

public class Negoci extends Usuari{
    private String nomNegoci;
    private String coordenadaNegoci;
    private String ubicacioNegoci;

    public Negoci(String mailUsuari, String contrasenyaUsuari, int tipusUsuari, String nomNegoci, String coordenadaNegoci, String ubicacioNegoci) {
        super(mailUsuari, contrasenyaUsuari, tipusUsuari);
        this.nomNegoci = nomNegoci;
        this.coordenadaNegoci = coordenadaNegoci;
        this.ubicacioNegoci = ubicacioNegoci;
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
                ", nomNegoci='" + nomNegoci + '\'' +
                ", coordenadaNegoci='" + coordenadaNegoci + '\'' +
                ", ubicacioNegoci='" + ubicacioNegoci + '\'' +
                '}';
    }
}
