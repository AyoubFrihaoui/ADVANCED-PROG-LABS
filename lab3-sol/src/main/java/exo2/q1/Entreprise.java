package exo2.q1;

import java.util.ArrayList;
import java.util.List;

public class Entreprise {
    private String nom;
    private List<Departement> departements;

    public Entreprise(String nom, List<Departement> departements) {
        this.nom = nom;
        this.departements = departements;
    }

    public String getNom() {
        return this.nom;
    }

    public List<Departement> getDepartements() {
        return this.departements;
    }

    public List<Employe> listeDirecteursDepartement() {
        return listeDirecteursEquipe();
    }

    /**
     * SOLUTION (Law of Demeter): the company only talks to its DIRECT parts —
     * its own departments. The old triple loop
     * (departments → services → teams → director) is gone: each level
     * delegates to the next one down, so a hierarchy change only touches one
     * class instead of every client of the chain.
     */
    public List<Employe> listeDirecteursEquipe() {
        ArrayList<Employe> directeurs = new ArrayList<>();
        for (Departement d : departements) {
            directeurs.addAll(d.getDirecteursEquipes());
        }
        return directeurs;
    }

}
