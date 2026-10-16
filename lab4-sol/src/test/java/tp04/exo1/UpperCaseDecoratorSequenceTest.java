package tp04.exo1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

/** SOLUTION tests (Q1) — reference tests the students write themselves. */
public class UpperCaseDecoratorSequenceTest {

    @Test
    public void testUppercase() {
        Sequence seq = new UpperCaseDecoratorSequence(
                new ArraySequence(new String[] { "un", "deux" }));
        assertEquals("UN", seq.ligneSuivante());
        assertEquals("DEUX", seq.ligneSuivante());
        assertNull(seq.ligneSuivante());
    }

    @Test
    public void testEmptyStaysEmpty() {
        Sequence seq = new UpperCaseDecoratorSequence(
                new ArraySequence(new String[] {}));
        assertNull(seq.ligneSuivante());
    }

    @Test
    public void testDecoratorsStack() {
        // grep first, then uppercase: composition instead of subclasses
        Sequence seq = new UpperCaseDecoratorSequence(
                new GrepSequence(
                        new ArraySequence(new String[] { "le chemin", "la route", "un chemin" }),
                        "chemin"));
        assertEquals("LE CHEMIN", seq.ligneSuivante());
        assertEquals("UN CHEMIN", seq.ligneSuivante());
        assertNull(seq.ligneSuivante());
    }
}
