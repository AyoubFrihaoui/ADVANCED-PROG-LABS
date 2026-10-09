package exo2.q2;

import java.util.Collections;

/** SOLUTION (Ex2 Q2) — demo of the generalized hierarchy. */
public class Main {

    public static void main(String[] args) {
        Employe boss = new Employe("Ada", "Lovelace");
        Employe chefVente = new Employe("Grace", "Hopper");
        Employe chefService = new Employe("Alan", "Turing");
        Employe wirth = new Employe("Niklaus", "Wirth");
        Equipe progWeb = new Equipe("Programmation", wirth, Collections.emptyList());
        Service venteWeb = new Service("Vente en ligne", chefService, Collections.singletonList(progWeb));
        Departement vente = new Departement("vente", chefVente, Collections.singletonList(venteWeb));
        Entreprise e = new Entreprise("World Company", boss, Collections.singletonList(vente));
        for (Employe d : e.getDirecteurs()) {
            System.out.println(e.getDirecteur());
            System.out.println(d.getNomComplet());
        }
    }
}
