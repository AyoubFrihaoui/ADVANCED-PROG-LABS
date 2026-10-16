package tp04.exo1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

/** SOLUTION tests (Q2) — reference tests the students write themselves. */
public class GrepSequenceTest {

    private Sequence lignes() {
        return new ArraySequence(new String[] {
            "le chemin de la maison", "la route", "un petit chemin", "rien" });
    }

    @Test
    public void testFiltre() {
        Sequence seq = new GrepSequence(lignes(), "chemin");
        assertEquals("le chemin de la maison", seq.ligneSuivante());
        assertEquals("un petit chemin", seq.ligneSuivante());
        assertNull(seq.ligneSuivante());
    }

    @Test
    public void testAucuneLigne() {
        Sequence seq = new GrepSequence(lignes(), "autoroute");
        assertNull(seq.ligneSuivante());
        assertNull(seq.ligneSuivante());
    }

    @Test
    public void testToutesLesLignes() {
        Sequence seq = new GrepSequence(
                new ArraySequence(new String[] { "a", "b" }), "");
        assertEquals("a", seq.ligneSuivante());
        assertEquals("b", seq.ligneSuivante());
        assertNull(seq.ligneSuivante());
    }
}
