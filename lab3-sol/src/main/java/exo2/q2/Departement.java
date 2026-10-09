package exo2.q2;

import java.util.ArrayList;
import java.util.List;

/** SOLUTION (Ex2 Q2) — same pattern one level up: own director + services. */
public class Departement implements AvecDirecteur {
    private String nom;
    private Employe directeur;
    private List<Service> services;

    public Departement(String nom, Employe directeur, List<Service> services) {
        this.nom = nom;
        this.directeur = directeur;
        this.services = services;
    }

    public String getNom() {
        return this.nom;
    }

    @Override
    public Employe getDirecteur() {
        return this.directeur;
    }

    public List<Service> getServices() {
        return this.services;
    }

    public List<Employe> getDirecteurs() {
        List<Employe> directeurs = new ArrayList<>();
        directeurs.add(directeur);
        for (Service s : services) {
            directeurs.addAll(s.getDirecteurs());
        }
        return directeurs;
    }
}
