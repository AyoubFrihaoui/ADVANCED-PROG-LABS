package exo1.q1.v2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * SOLUTION — a logger that keeps messages in memory.
 * This is what makes the computation unit-testable: no file is created,
 * and the test can assert on the recorded messages.
 */
public class MemoryLogger implements Logger {
    private final List<String> messages = new ArrayList<>();

    @Override
    public void log(String message) {
        messages.add(message);
    }

    @Override
    public void end() {
        // nothing to release
    }

    /** Returns an unmodifiable view of the recorded messages, in order. */
    public List<String> getMessages() {
        return Collections.unmodifiableList(messages);
    }
}
