package fip1;

import static org.junit.Assert.*;
import org.junit.Test;


public class TestQ5 {
    
    @Test
    public void testConcat() {        
        MaListe<String> l1 = new MaListe<>();
        l1.ajouter("a");
        MaListe<String> l2 = new MaListe<>();
        l2.ajouter("b");
        MaListe<String> l3 = MaListeHelper.concat(l1, l2);
        assertEquals("a b", l3.toString());
    }



}
