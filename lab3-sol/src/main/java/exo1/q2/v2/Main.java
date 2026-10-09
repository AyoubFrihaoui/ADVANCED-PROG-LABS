package exo1.q2.v2;

/**
 * SOLUTION — demo: one catalogue, two formats, {@code Catalogue} untouched.
 */
public class Main {

    public static void main(String[] args) {
        Catalogue cat = new Catalogue();
        cat.ajouterProduit(new Produit("ordi", 600));
        cat.ajouterProduit(new Produit("souris", 30));
        System.out.println("XML : " + cat.exporter(new XMLDumper()));
        System.out.println("JSON: " + cat.exporter(new JSONDumper()));
    }
}
