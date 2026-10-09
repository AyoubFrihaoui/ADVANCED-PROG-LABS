package exo2.q2;

import java.util.ArrayList;
import java.util.List;

/**
 * SOLUTION (Ex2 Q2) — a service has its own director AND its teams.
 * It only talks to its direct parts (Demeter): its director, its teams.
 */
public class Service implements AvecDirecteur {
    private String nom;
    private Employe directeur;
    private List<Equipe> equipes;

    public Service(String nom, Employe directeur, List<Equipe> equipes) {
        this.nom = nom;
        this.directeur = directeur;
        this.equipes = equipes;
    }

    public String getNom() {
        return this.nom;
    }

    @Override
    public Employe getDirecteur() {
        return this.directeur;
    }

    public List<Equipe> getEquipes() {
        return this.equipes;
    }

    /** Own director first, then each team's director (delegated downward). */
    public List<Employe> getDirecteurs() {
        List<Employe> directeurs = new ArrayList<>();
        directeurs.add(directeur);
        for (Equipe e : equipes) {
            directeurs.add(e.getDirecteur());
        }
        return directeurs;
    }
}
