package cat.copernic.rodriguez.albert.m7t1;

import androidx.annotation.NonNull;

public abstract class Usuari {
    private int idUsuari;
    private String mailUsuari;
    private String contrasenyaUsuari;
    private int tipusUsuari;

    Usuari(String mailUsuari, String contrasenyaUsuari, int tipusUsuari) {
        this.mailUsuari = mailUsuari;
        this.contrasenyaUsuari = contrasenyaUsuari;
        this.tipusUsuari = tipusUsuari;
    }

    public int getIdUsuari() {
        return idUsuari;
    }

    public void setIdUsuari(int idUsuari) {
        this.idUsuari = idUsuari;
    }

    public String getMailUsuari() {
        return mailUsuari;
    }

    public void setMailUsuari(String mailUsuari) {
        this.mailUsuari = mailUsuari;
    }

    public String getContrasenyaUsuari() {
        return contrasenyaUsuari;
    }

    public void setContrasenyaUsuari(String contrasenyaUsuari) {
        this.contrasenyaUsuari = contrasenyaUsuari;
    }

    public int getTipusUsuari() {
        return tipusUsuari;
    }

    public void setTipusUsuari(int tipusUsuari) {
        this.tipusUsuari = tipusUsuari;
    }

    @NonNull
    @Override
    public String toString() {
        return "cat.copernic.rodriguez.albert.m7t1.Usuari{" +
                "idUsuari=" + idUsuari +
                ", mailUsuari='" + mailUsuari + '\'' +
                ", contrasenyaUsuari='" + contrasenyaUsuari + '\'' +
                ", tipusUsuari=" + tipusUsuari +
                '}';
    }
}
