package cat.copernic.rodriguez.albert.m7t1.classes;

public class Usuari {
    private String mailUsuari;
    private int tipusUsuari;

    public Usuari() {
    }

    public Usuari(String mailUsuari, int tipusUsuari) {
        this.mailUsuari = mailUsuari;
        this.tipusUsuari = tipusUsuari;
    }

    public Usuari(int idUsuari, String mailUsuari, int tipusUsuari) {
        this.mailUsuari = mailUsuari;
        this.tipusUsuari = tipusUsuari;
    }

    public String getMailUsuari() {
        return mailUsuari;
    }

    public void setMailUsuari(String mailUsuari) {
        this.mailUsuari = mailUsuari;
    }

    public int getTipusUsuari() {
        return tipusUsuari;
    }

    public void setTipusUsuari(int tipusUsuari) {
        this.tipusUsuari = tipusUsuari;
    }

    @Override
    public String toString() {
        return "Usuari{" +
                ", mailUsuari='" + mailUsuari + '\'' +
                ", tipusUsuari=" + tipusUsuari +
                '}';
    }
}
