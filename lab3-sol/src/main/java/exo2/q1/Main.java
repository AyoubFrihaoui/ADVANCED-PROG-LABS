package exo2.q1;

import java.util.Collections;

public class Main {
    
    public static void main(String[] args) {
        Employe wirth = new Employe("Niklaus", "Wirth");
        Equipe progWeb = new Equipe("Programmation", wirth, Collections.emptyList());
        Service venteWeb = new Service("Vente en ligne", Collections.singletonList(progWeb));
        Departement vente = new Departement("vente", Collections.singletonList(venteWeb));
        Entreprise e = new Entreprise("World Company", Collections.singletonList(vente));
        System.out.println(e.listeDirecteursDepartement());
        System.out.println(e.listeDirecteursEquipe()); // SOLUTION: same result, Demeter-compliant
    }
}
