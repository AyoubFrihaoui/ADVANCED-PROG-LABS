package exo1.q1.v2;

/**
 * SOLUTION — logging abstraction.
 *
 * <p>Key design decision: NO checked exceptions. The whole point of the
 * exercise is that business code ({@code LogicielDeCalcul}) must not be
 * polluted by {@code throws IOException} just because one particular logger
 * happens to write to a file. Implementations that can fail with an
 * {@code IOException} wrap it in an {@code UncheckedIOException}.
 */
public interface Logger {
    /** Records one message. */
    void log(String message);

    /** Releases resources (file handle, …). No-op when there is nothing to release. */
    void end();
}
