package exo1.q1.v2;

/**
 * SOLUTION — a logger that does nothing. Useful to disable logging
 * (e.g. in performance runs) without touching the business code.
 */
public class NullLogger implements Logger {
    @Override
    public void log(String message) {
        // intentionally empty
    }

    @Override
    public void end() {
        // intentionally empty
    }
}
