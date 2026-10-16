package tp04.exo1;

/**
 * A sequence backed by a String array.
 */
public class ArraySequence implements Sequence {
    private String[] representation;
    private int position;

    public ArraySequence(String[] representation) {
        this.representation = representation.clone();
    }

    @Override
    public String ligneSuivante() {
        if (position >= representation.length) {
            return null;
        } else {
            // Could be done in one line, but
            // let's stay readable.
            String resultat = representation[position];
            position++;
            return resultat;
        }
    }
    
}
