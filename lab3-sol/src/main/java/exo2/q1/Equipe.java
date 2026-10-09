package exo2.q1;

import java.util.List;

public class Equipe {
    String nom;
    Employe directeur;    
    List<Employe> employes;


    public Equipe(String nom, Employe directeur, List<Employe> employes) {
        this.nom = nom;
        this.directeur = directeur;
        this.employes = employes;
    }

    public String getNom() {
        return this.nom;
    }

    public Employe getDirecteur() {
        return this.directeur;
    }

    public List<Employe> getEmployes() {
        return this.employes;
    }

    
}
