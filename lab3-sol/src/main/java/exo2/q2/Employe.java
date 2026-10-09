package exo2.q2;

/** SOLUTION (Ex2 Q2) — same employee as q1, plus a full-name helper. */
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

    public String getNomComplet() {
        return prenom + " " + nom;
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
