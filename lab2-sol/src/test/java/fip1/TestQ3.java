package fip1;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestQ3 {

    @Test
    public void testQ3() {
        MaListe<String> liste1 = new MaListe<>();
        liste1.ajouter("un");
        liste1.ajouter("deux");
        MaListe<Object> liste2 = new MaListe<>();
        liste2.ajouter(Integer.valueOf(3));

        liste2.ajouterListe(liste1);
        assertEquals("3 un deux", liste2.toString());
    }
}
