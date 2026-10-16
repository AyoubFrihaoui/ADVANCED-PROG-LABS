package tp04.exo1;

/**
 * SOLUTION (Q1) — decorator returning every line in uppercase.
 *
 * <p>Note the pattern: same interface ({@link Sequence}), holds the decorated
 * object, delegates the read, transforms the result. {@code null}
 * (end of sequence) passes through untouched.
 */
public class UpperCaseDecoratorSequence implements Sequence {
    private final Sequence inner;

    public UpperCaseDecoratorSequence(Sequence inner) {
        this.inner = inner;
    }

    @Override
    public String ligneSuivante() {
        String line = inner.ligneSuivante();
        return line == null ? null : line.toUpperCase();
    }
}
