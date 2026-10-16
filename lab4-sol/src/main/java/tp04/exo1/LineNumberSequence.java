package tp04.exo1;

/**
 * SOLUTION (Q3) — decorator remembering the number of the last line read.
 *
 * <p>Numbering is 0-based (first line read is number 0, consistent with the
 * {@code start}/{@code end} indices of {@code SliceSequence}); {@code -1}
 * means "nothing read yet". The counter only moves on a successful read, so
 * it stays put once the sequence is exhausted.
 */
public class LineNumberSequence implements Sequence {
    private final Sequence inner;
    private int lineNumber = -1;

    public LineNumberSequence(Sequence inner) {
        this.inner = inner;
    }

    @Override
    public String ligneSuivante() {
        String line = inner.ligneSuivante();
        if (line != null) {
            lineNumber++;
        }
        return line;
    }

    /** Number of the last line read, 0-based; -1 if none read yet. */
    public int getLineNumber() {
        return lineNumber;
    }
}
