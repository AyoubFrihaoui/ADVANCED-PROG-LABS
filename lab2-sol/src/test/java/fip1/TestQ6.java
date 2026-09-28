package fip1;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestQ6 {
    
    @Test
    public void testConcat() {        
        MaListe<Integer> l1 = new MaListe<>();
        l1.ajouter(4);
        MaListe<String> l2 = new MaListe<>();
        l2.ajouter("b");
        MaListe<Object> l3 = MaListeHelper.concat(l1, l2);
        assertEquals("4 b", l3.toString());
    }
}
