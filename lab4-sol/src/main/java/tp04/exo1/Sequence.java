package tp04.exo1;
/**
 * A sequence of Strings, possibly infinite.
 */
public interface Sequence {
    /**
     * Returns the next line of the sequence, or null
     * if it is finished.
     * @return a string, possibly null.
     */
    String ligneSuivante();
}
