package exo2.q1;

import java.util.ArrayList;
import java.util.List;

public class Departement {
    private String nom;
    private List<Service> services;

    public Departement(String nom, List<Service> services) {
        this.nom = nom;
        this.services = services;
    }

    public String getNom() {
        return this.nom;
    }

    public List<Service> getServices() {
        return this.services;
    }

    /**
     * SOLUTION (Law of Demeter): a department only talks to its DIRECT parts —
     * its own services — and delegates the rest downwards.
     */
    public List<Employe> getDirecteursEquipes() {
        List<Employe> directeurs = new ArrayList<>();
        for (Service s : services) {
            directeurs.addAll(s.getDirecteursEquipes());
        }
        return directeurs;
    }


}
