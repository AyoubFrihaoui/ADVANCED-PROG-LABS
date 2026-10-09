package exo2.q1;

public class Employe {


    public Employe(String prenom, String nom) {
        this.prenom = prenom;
        this.nom = nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public String getNom() {
        return this.nom;
    }

    @Override
    public String toString() {
        return "{" +
            " prenom='" + getPrenom() + "'" +
            ", nom='" + getNom() + "'" +
            "}";
    }

    private String prenom;
    private String nom;
}
