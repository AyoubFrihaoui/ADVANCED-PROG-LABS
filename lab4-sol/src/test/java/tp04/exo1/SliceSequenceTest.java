package tp04.exo1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

/** SOLUTION tests (Q4) — reference tests the students write themselves. */
public class SliceSequenceTest {

    private Sequence lettres() {
        return new ArraySequence(new String[] { "a", "b", "c", "d", "e" });
    }

    @Test
    public void testMilieu() {
        Sequence seq = new SliceSequence(lettres(), 1, 3);
        assertEquals("b", seq.ligneSuivante());
        assertEquals("c", seq.ligneSuivante());
        assertNull(seq.ligneSuivante());
    }

    @Test
    public void testDebut() {
        Sequence seq = new SliceSequence(lettres(), 0, 2);
        assertEquals("a", seq.ligneSuivante());
        assertEquals("b", seq.ligneSuivante());
        assertNull(seq.ligneSuivante());
    }

    @Test
    public void testVide() {
        Sequence seq = new SliceSequence(lettres(), 2, 2);
        assertNull(seq.ligneSuivante());
    }

    @Test
    public void testAuDelaDeLaFin() {
        Sequence seq = new SliceSequence(lettres(), 10, 20);
        assertNull(seq.ligneSuivante());
    }

    @Test
    public void testFinOuverte() {
        Sequence seq = new SliceSequence(lettres(), 3, 100);
        assertEquals("d", seq.ligneSuivante());
        assertEquals("e", seq.ligneSuivante());
        assertNull(seq.ligneSuivante());
    }
}
