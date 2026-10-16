package exo2; 

import java.util.ArrayList;
import java.util.List;

public class Etudiant {
    private int numero;
    private String nom, prenom;
    private List<Double> notes;


    public Etudiant(int numero, String nom, String prenom) {
        this.numero = numero;
        this.nom = nom;
        this.prenom = prenom;
        this.notes = new ArrayList<>();
    }

    public int getNumero() {
        return this.numero;
    }

    public String getNom() {
        return this.nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public List<Double> getNotes() {
        return new ArrayList<>(this.notes);
    }
    
    public void addNote(double note) {
        notes.add(note);
    }



}
