package cat.copernic.rodriguez.albert.m7t1;

import androidx.annotation.NonNull;

public class Accedeix {
    private int idOferta;
    private int idUsuari;

    public Accedeix(int idOferta, int idUsuari) {
        this.idOferta = idOferta;
        this.idUsuari = idUsuari;
    }

    public int getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(int idOferta) {
        this.idOferta = idOferta;
    }

    public int getIdUsuari() {
        return idUsuari;
    }

    public void setIdUsuari(int idUsuari) {
        this.idUsuari = idUsuari;
    }

    @NonNull
    @Override
    public String toString() {
        return "Accedeix{" +
                "idOferta=" + idOferta +
                ", idUsuari=" + idUsuari +
                '}';
    }
}
