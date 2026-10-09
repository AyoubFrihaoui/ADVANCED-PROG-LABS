package exo2.q2;

import java.util.ArrayList;
import java.util.List;

/**
 * SOLUTION (Ex2 Q2) — the company only talks to its direct parts.
 * Collecting every director in the company is now a uniform delegation
 * chain through the {@link AvecDirecteur} abstraction.
 */
public class Entreprise implements AvecDirecteur {
    private String nom;
    private Employe directeur;
    private List<Departement> departements;

    public Entreprise(String nom, Employe directeur, List<Departement> departements) {
        this.nom = nom;
        this.directeur = directeur;
        this.departements = departements;
    }

    public String getNom() {
        return this.nom;
    }

    @Override
    public Employe getDirecteur() {
        return this.directeur;
    }

    public List<Departement> getDepartements() {
        return this.departements;
    }

    /** Every director in the company: own first, then each department's. */
    public List<Employe> getDirecteurs() {
        List<Employe> directeurs = new ArrayList<>();
        directeurs.add(directeur);
        for (Departement d : departements) {
            directeurs.addAll(d.getDirecteurs());
        }
        return directeurs;
    }
}
