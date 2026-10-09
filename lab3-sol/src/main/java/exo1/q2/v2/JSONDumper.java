package exo1.q2.v2;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * SOLUTION — JSON rendering of the same traversal.
 *
 * <p>Each node becomes {@code {"@type":"name","contenu":[...]}} and each text
 * leaf becomes {@code {"texte":"..."}}. Commas between siblings are tracked
 * with a per-level "first item?" flag stack — the same technique the handout
 * suggests for a YAML exporter (track write depth in an instance variable).
 */
public class JSONDumper implements Dumper {
    private final StringBuilder builder = new StringBuilder();
    private final Deque<Boolean> firstInLevel = new ArrayDeque<>();

    @Override
    public void openTag(String name) {
        separateIfNeeded();
        builder.append("{\"@type\":\"").append(escape(name)).append("\",\"contenu\":[");
        firstInLevel.push(Boolean.TRUE);
    }

    @Override
    public void text(String content) {
        separateIfNeeded();
        builder.append("{\"texte\":\"").append(escape(content)).append("\"}");
    }

    @Override
    public void closeTag(String name) {
        builder.append("]}");
        firstInLevel.pop();
    }

    @Override
    public String getResult() {
        return builder.toString();
    }

    /** Emits a ',' between siblings; the first item of a level gets none. */
    private void separateIfNeeded() {
        if (!firstInLevel.isEmpty()) {
            if (firstInLevel.peek()) {
                firstInLevel.pop();
                firstInLevel.push(Boolean.FALSE);
            } else {
                builder.append(',');
            }
        }
    }

    private String escape(String s) {
        return s.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n");
    }
}
