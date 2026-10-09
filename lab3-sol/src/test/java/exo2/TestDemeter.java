package exo2;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

/**
 * SOLUTION tests — Law of Demeter refactoring (q1) and generalization (q2).
 */
public class TestDemeter {

    // ---- Ex2 Q1: delegation chain gives the same answer as the old loop ----

    private exo2.q1.Entreprise companyQ1() {
        exo2.q1.Employe wirth = new exo2.q1.Employe("Niklaus", "Wirth");
        exo2.q1.Employe torvalds = new exo2.q1.Employe("Linus", "Torvalds");
        exo2.q1.Equipe progWeb = new exo2.q1.Equipe("Programmation", wirth, Collections.emptyList());
        exo2.q1.Equipe systeme = new exo2.q1.Equipe("Systeme", torvalds, Collections.emptyList());
        exo2.q1.Service venteWeb = new exo2.q1.Service("Vente en ligne", Arrays.asList(progWeb, systeme));
        exo2.q1.Departement vente = new exo2.q1.Departement("vente", Collections.singletonList(venteWeb));
        return new exo2.q1.Entreprise("World Company", Collections.singletonList(vente));
    }

    @Test
    public void delegatedMethodFindsEveryTeamDirector() {
        List<String> noms = companyQ1().listeDirecteursEquipe().stream()
                .map(e -> e.getPrenom() + " " + e.getNom())
                .sorted()
                .collect(Collectors.toList());
        assertEquals(Arrays.asList("Linus Torvalds", "Niklaus Wirth"), noms);
    }

    @Test
    public void oldMethodNowDelegatesToNewOne() {
        exo2.q1.Entreprise e = companyQ1();
        assertEquals(e.listeDirecteursEquipe(), e.listeDirecteursDepartement());
    }

    @Test
    public void eachLevelOnlyTalksToItsChildren() {
        exo2.q1.Entreprise e = companyQ1();
        // department level sees both team directors without knowing about teams
        assertEquals(2, e.getDepartements().get(0).getDirecteursEquipes().size());
        // service level likewise
        assertEquals(2, e.getDepartements().get(0).getServices().get(0).getDirecteursEquipes().size());
    }

    // ---- Ex2 Q2: every entity can have a director ----

    private exo2.q2.Entreprise companyQ2() {
        exo2.q2.Employe boss = new exo2.q2.Employe("Ada", "Lovelace");
        exo2.q2.Employe chefVente = new exo2.q2.Employe("Grace", "Hopper");
        exo2.q2.Employe chefService = new exo2.q2.Employe("Alan", "Turing");
        exo2.q2.Employe wirth = new exo2.q2.Employe("Niklaus", "Wirth");
        exo2.q2.Equipe progWeb = new exo2.q2.Equipe("Programmation", wirth, Collections.emptyList());
        exo2.q2.Service venteWeb = new exo2.q2.Service("Vente en ligne", chefService,
                Collections.singletonList(progWeb));
        exo2.q2.Departement vente = new exo2.q2.Departement("vente", chefVente,
                Collections.singletonList(venteWeb));
        return new exo2.q2.Entreprise("World Company", boss, Collections.singletonList(vente));
    }

    @Test
    public void allDirectorsCollectedTopDown() {
        List<String> noms = companyQ2().getDirecteurs().stream()
                .map(exo2.q2.Employe::getNomComplet)
                .collect(Collectors.toList());
        assertEquals(
                Arrays.asList("Ada Lovelace", "Grace Hopper", "Alan Turing", "Niklaus Wirth"),
                noms);
    }

    @Test
    public void everyEntityExposesItsDirectorUniformly() {
        exo2.q2.Entreprise e = companyQ2();
        // the interface lets us treat all levels the same
        List<exo2.q2.AvecDirecteur> niveaux = Arrays.asList(
                e,
                e.getDepartements().get(0),
                e.getDepartements().get(0).getServices().get(0),
                e.getDepartements().get(0).getServices().get(0).getEquipes().get(0));
        List<String> noms = niveaux.stream()
                .map(d -> d.getDirecteur().getNomComplet())
                .collect(Collectors.toList());
        assertEquals(
                Arrays.asList("Ada Lovelace", "Grace Hopper", "Alan Turing", "Niklaus Wirth"),
                noms);
    }
}
