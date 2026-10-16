package tp04.exo1;

/**
 * SOLUTION (Q4) — decorator returning only lines {@code [start, end)}.
 *
 * <p>Implemented by REUSE (composition), as the handout suggests: a
 * {@link LineNumberSequence} does the counting, the constructor skips the
 * first {@code start} lines, and each call stops once the counter reaches
 * {@code end}. Stacking decorators instead of subclassing is the whole
 * point of the pattern.
 */
public class SliceSequence implements Sequence {
    private final LineNumberSequence numbered;
    private final int end;

    public SliceSequence(Sequence inner, int start, int end) {
        this.numbered = new LineNumberSequence(inner);
        this.end = end;
        for (int i = 0; i < start; i++) {
            if (numbered.ligneSuivante() == null) {
                break; // inner sequence shorter than start: stay exhausted
            }
        }
    }

    @Override
    public String ligneSuivante() {
        if (numbered.getLineNumber() + 1 >= end) {
            return null;
        }
        return numbered.ligneSuivante();
    }
}
