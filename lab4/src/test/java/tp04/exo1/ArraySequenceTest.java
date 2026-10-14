package tp04.exo1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Test;

public class ArraySequenceTest {
    
    @Test
    public void testSequenceVide() {
        String [] t = {};
        Sequence seq = new ArraySequence(t);
        assertSame(null, seq.ligneSuivante());
    }

    /*
     * Checks that calling ligneSuivante several times
     * after the end of the sequence does not crash.
     */
    @Test
    public void testSequenceVideBis() {
        String [] t = {};
        Sequence seq = new ArraySequence(t);
        assertSame(null, seq.ligneSuivante());
        assertSame(null, seq.ligneSuivante());
    }

    @Test
    public void testSimple() {
        String [] t= {"un", "deux", "trois"};
        Sequence seq = new ArraySequence(t);
        assertEquals("un", seq.ligneSuivante());
        assertEquals("deux", seq.ligneSuivante());
        assertEquals("trois", seq.ligneSuivante());
        assertSame(null,seq.ligneSuivante());
    }
    


}
