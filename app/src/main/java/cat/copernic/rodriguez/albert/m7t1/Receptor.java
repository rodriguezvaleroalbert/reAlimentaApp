package cat.copernic.rodriguez.albert.m7t1;

import androidx.annotation.NonNull;

public class Receptor {

    private int idUsuari;
    private String nomReceptor;

    public Receptor(String nomReceptor) {
        this.nomReceptor = nomReceptor;
    }

    public int getIdUsuari() {
        return idUsuari;
    }

    public void setIdUsuari(int idUsuari) {
        this.idUsuari = idUsuari;
    }

    public String getNomReceptor() {
        return nomReceptor;
    }

    public void setNomReceptor(String nomReceptor) {
        this.nomReceptor = nomReceptor;
    }

    @NonNull
    @Override
    public String toString() {
        return "Receptor{" +
                "idUsuari=" + idUsuari +
                ", nomReceptor='" + nomReceptor + '\'' +
                '}';
    }
}
