package exo2.q1;

import java.util.ArrayList;
import java.util.List;

public class Service {
    private String nom;
    private List<Equipe> equipes;


    public Service(String nom, List<Equipe> equipes) {
        this.nom = nom;
        this.equipes = equipes;
    }

    public String getNom() {
        return this.nom;
    }

    public List<Equipe> getEquipes() {
        return this.equipes;
    }

    /**
     * SOLUTION (Law of Demeter): a service only talks to its DIRECT parts —
     * its own teams — and asks each team for its director. Callers no longer
     * dig through teams themselves.
     */
    public List<Employe> getDirecteursEquipes() {
        List<Employe> directeurs = new ArrayList<>();
        for (Equipe e : equipes) {
            directeurs.add(e.getDirecteur());
        }
        return directeurs;
    }

}
