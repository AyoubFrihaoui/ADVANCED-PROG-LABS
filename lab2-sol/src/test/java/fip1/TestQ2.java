package fip1;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestQ2 {

    @Test
    public void testQ2V1() {
        MaListe<String> liste1 = new MaListe<>();
        MaListe<String> liste2 = new MaListe<>();
        liste1.ajouter("un");
        liste1.ajouter("deux");
        liste2.ajouter("trois");
        liste1.ajouterListe(liste2);
        assertEquals("un deux trois", liste1.toString());
        assertEquals("trois", liste2.toString());
    }

    @Test
    public void testQ2V2() {
        MaListe<Integer> liste1 = new MaListe<>();
        MaListe<Integer> liste2 = new MaListe<>();
        liste1.ajouter(1);
        liste1.ajouter(2);
        liste2.ajouter(3);
        liste1.ajouterListe(liste2);
        assertEquals("1 2 3", liste1.toString());
    }
}
