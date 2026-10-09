package exo2.q2;

import java.util.List;

/** SOLUTION (Ex2 Q2) — a team now also has its own director via the interface. */
public class Equipe implements AvecDirecteur {
    private String nom;
    private Employe directeur;
    private List<Employe> employes;

    public Equipe(String nom, Employe directeur, List<Employe> employes) {
        this.nom = nom;
        this.directeur = directeur;
        this.employes = employes;
    }

    public String getNom() {
        return this.nom;
    }

    @Override
    public Employe getDirecteur() {
        return this.directeur;
    }

    public List<Employe> getEmployes() {
        return this.employes;
    }
}
