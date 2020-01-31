package cat.copernic.rodriguez.albert.m7t1;

public class Indret {

    //Variables membres de la classe
    private String nom;
    private String info;

    //Constructor
    public Indret(String nom, String info) {
        this.nom = nom;
        this.info = info;
    }

    //Getters i Setters

    public String getNom() {
        return nom;
    }

    public String getInfo() {
        return info;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
