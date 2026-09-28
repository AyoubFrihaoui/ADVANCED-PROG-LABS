package fip1;

import static org.junit.Assert.*;
import org.junit.Test;


public class TestQ1 {

    @Test
    public void question1P1() {
        MaListe<String> liste1 = new MaListe<>();
        liste1.ajouter("un");
        liste1.ajouter("deux");             
        assertEquals("un deux", liste1.toString());
    }

    @Test
    public void question1P2() {
        MaListe<Integer> liste1 = new MaListe<>();
        liste1.ajouter(3);
        liste1.ajouter(4);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < liste1.taille(); i++) {
            Integer valeur = liste1.element(i);
            builder.append(valeur);
        }
        assertEquals("34", builder.toString());
    }
    
    @Test
    public void testAjout1() {
        MaListe<Integer> l = new MaListe<>();
        l.ajouter(3);
        assertEquals(1, l.taille());
        assertEquals(Integer.valueOf(3), l.element(0)); 
        // Note: we are comparing Integer objects here
    }

    @Test
    public void testAjout2() {
        MaListe<Integer> l = new MaListe<>();
        l.ajouter(3);
        l.ajouter(100);
        assertEquals(2, l.taille());
        assertEquals(Integer.valueOf(3), l.element(0)); 
        assertEquals(Integer.valueOf(100), l.element(1)); 
    }
}
