package fip1;

/**
 * SOLUTION — static helpers related to the "MaListe" class.
 */
public class MaListeHelper {

    /**
     * Q5/Q6: concatenate two lists into a new one.
     * Both sources are producers ({@code ? extends T}), so same-type calls
     * infer {@code T} from the arguments (TestQ5: {@code T = String}), while
     * mixed calls infer the common super-type (TestQ6: {@code T = Object}).
     *
     * @param <T> element type of the resulting list
     * @param l1 first list
     * @param l2 second list
     * @return a new list with all elements of l1 then l2
     */
    public static <T> MaListe<T> concat(MaListe<? extends T> l1, MaListe<? extends T> l2) {
        MaListe<T> resultat = new MaListe<>();
        resultat.ajouterListe(l1);
        resultat.ajouterListe(l2);
        return resultat;
    }
}
