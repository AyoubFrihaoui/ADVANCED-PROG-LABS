package tp04.exo1;

/**
 * SOLUTION (Q2) — decorator keeping only lines containing a given text.
 * Non-matching lines are skipped inside the loop; exhaustion of the inner
 * sequence yields {@code null} as usual.
 */
public class GrepSequence implements Sequence {
    private final Sequence inner;
    private final String motif;

    public GrepSequence(Sequence inner, String motif) {
        this.inner = inner;
        this.motif = motif;
    }

    @Override
    public String ligneSuivante() {
        String line;
        while ((line = inner.ligneSuivante()) != null) {
            if (line.contains(motif)) {
                return line;
            }
        }
        return null;
    }
}
