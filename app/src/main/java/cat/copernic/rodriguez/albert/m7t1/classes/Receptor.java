package cat.copernic.rodriguez.albert.m7t1.classes;

import androidx.annotation.NonNull;

public class Receptor extends Usuari{

    private String nomReceptor;

    public Receptor(String mailUsuari, String contrasenyaUsuari, int tipusUsuari, String nomReceptor) {
        super(mailUsuari, contrasenyaUsuari, tipusUsuari);
        this.nomReceptor = nomReceptor;
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
                ", nomReceptor='" + nomReceptor + '\'' +
                '}';
    }
}
