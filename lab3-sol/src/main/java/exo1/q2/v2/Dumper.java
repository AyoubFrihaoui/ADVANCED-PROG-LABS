package exo1.q2.v2;

/**
 * SOLUTION — export abstraction.
 *
 * <p>{@code Catalogue} only talks to this interface, never to a concrete
 * format. Adding JSON, YAML, … means writing a new implementation, with
 * zero changes to {@code Catalogue} (Open/Closed Principle).
 */
public interface Dumper {
    /** Opens a named node (e.g. {@code <produit>} in XML). */
    void openTag(String name);

    /** Writes a text leaf. */
    void text(String content);

    /** Closes the most recently opened node. */
    void closeTag(String name);

    /** Returns the finished document. */
    String getResult();
}
