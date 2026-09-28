package fip1;

/**
 * SOLUTION — a minimal generic list backed by an internal Object array.
 *
 * <p>Because of type erasure we cannot create a {@code T[]}, so elements are
 * stored in an {@code Object[]} and cast back to {@code T} when reading
 * (safe: only {@code T} instances are ever stored).
 *
 * <p>Wildcards follow PECS (Producer Extends, Consumer Super):
 * <ul>
 *   <li>{@code ajouterListe} reads from the other list (producer) →
 *       {@code MaListe&lt;? extends T&gt;} (Q3).</li>
 *   <li>{@code ajouterDansListe} writes into the destination list (consumer) →
 *       {@code MaListe&lt;? super T&gt;} (Q4).</li>
 * </ul>
 */
public class MaListe<T> {

    private static final int CAPACITE_INITIALE = 10;

    private Object[] elements;
    private int taille;

    public MaListe() {
        this.elements = new Object[CAPACITE_INITIALE];
        this.taille = 0;
    }

    /** Q1: append an element at the end, growing the array when full. */
    public void ajouter(T element) {
        if (taille == elements.length) {
            Object[] nouveau = new Object[elements.length * 2];
            System.arraycopy(elements, 0, nouveau, 0, taille);
            elements = nouveau;
        }
        elements[taille++] = element;
    }

    /** Q1: current size. */
    public int taille() {
        return taille;
    }

    /** Q1: element at index i. */
    @SuppressWarnings("unchecked")
    public T element(int i) {
        if (i < 0 || i >= taille) {
            throw new IndexOutOfBoundsException("index " + i + ", size " + taille);
        }
        return (T) elements[i];
    }

    /**
     * Q2/Q3: append all elements of another list.
     * {@code ? extends T} lets a {@code MaListe<Object>} absorb a
     * {@code MaListe<String>} (TestQ3) while same-type calls (TestQ2) still compile.
     */
    public void ajouterListe(MaListe<? extends T> autre) {
        for (int i = 0; i < autre.taille(); i++) {
            ajouter(autre.element(i));
        }
    }

    /**
     * Q4: append all elements of this list into the destination list.
     * {@code ? super T} lets a {@code MaListe<String>} pour into a
     * {@code MaListe<Object>}; same-type calls (TestQ4bis) also compile since
     * {@code ? super T} includes {@code T} itself.
     */
    public void ajouterDansListe(MaListe<? super T> destination) {
        for (int i = 0; i < taille; i++) {
            destination.ajouter(element(i));
        }
    }

    /** Space-separated elements, e.g. "un deux". Required by every test. */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < taille; i++) {
            if (i > 0) {
                builder.append(' ');
            }
            builder.append(elements[i]);
        }
        return builder.toString();
    }
}
