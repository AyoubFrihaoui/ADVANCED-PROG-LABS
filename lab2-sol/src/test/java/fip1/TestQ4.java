package fip1;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestQ4 {
    
    @Test
    public void testQ4() {
        MaListe<String> liste1 = new MaListe<>();
        liste1.ajouter("un");
        liste1.ajouter("deux");
        MaListe<Object> liste2 = new MaListe<>();
        liste2.ajouter(Integer.valueOf(3));

        liste1.ajouterDansListe(liste2);
        assertEquals("3 un deux", liste2.toString());
    }


    @Test
    public void testQ4bis() {
        // Test sur le cas simple :
        MaListe<String> liste1 = new MaListe<>();
        liste1.ajouter("un");
        liste1.ajouter("deux");
        MaListe<String> liste2 = new MaListe<>();
        liste2.ajouter("début");

        liste1.ajouterDansListe(liste2);
        assertEquals("début un deux", liste2.toString());
    }
}
