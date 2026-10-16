package tp04.exo1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

/** SOLUTION tests (Q3) — reference tests the students write themselves. */
public class LineNumberSequenceTest {

    @Test
    public void testNumeros() {
        LineNumberSequence seq = new LineNumberSequence(
                new ArraySequence(new String[] { "a", "b", "c" }));
        assertEquals(-1, seq.getLineNumber()); // nothing read yet
        assertEquals("a", seq.ligneSuivante());
        assertEquals(0, seq.getLineNumber());
        assertEquals("b", seq.ligneSuivante());
        assertEquals(1, seq.getLineNumber());
        assertEquals("c", seq.ligneSuivante());
        assertEquals(2, seq.getLineNumber());
        assertNull(seq.ligneSuivante());
        assertEquals(2, seq.getLineNumber()); // counter stays at last line
    }

    @Test
    public void testVide() {
        LineNumberSequence seq = new LineNumberSequence(
                new ArraySequence(new String[] {}));
        assertNull(seq.ligneSuivante());
        assertEquals(-1, seq.getLineNumber());
    }
}
