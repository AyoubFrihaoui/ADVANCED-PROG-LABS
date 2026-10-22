package exo2;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class TestNomEtudiantsBuilder {
    
    @Test
    public void testSimple() throws IOException {
        List<String> nomsAttendus = Arrays.asList("Turing", "Lovelace", "Wirth");
        ArrayListEtudiantBuilder builder = new ArrayListEtudiantBuilder();
        StringReader reader = new StringReader(FichierUtil.dataEtudiants);
        LecteurFichierEtudiants lecteur = new LecteurFichierEtudiants(builder);
        lecteur.lireFichier(reader);
        List<String> resultat = builder.getNomsEtudiants();
        assertEquals(nomsAttendus, resultat);
    }
}
