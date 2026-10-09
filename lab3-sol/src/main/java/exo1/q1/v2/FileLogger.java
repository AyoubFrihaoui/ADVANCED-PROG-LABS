package exo1.q1.v2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.io.Writer;
import java.time.LocalDateTime;

/**
 * SOLUTION — the original file behavior, adapted to the {@link Logger}
 * interface. {@code IOException} is wrapped in {@code UncheckedIOException}
 * so callers are not forced to handle it.
 */
public class FileLogger implements Logger {
    private final Writer writer;

    public FileLogger(File f) {
        try {
            writer = new FileWriter(f);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    public void log(String message) {
        try {
            writer.write(LocalDateTime.now().toString());
            writer.write(" ");
            writer.write(message);
            writer.write('\n');
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    public void end() {
        try {
            writer.close();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
