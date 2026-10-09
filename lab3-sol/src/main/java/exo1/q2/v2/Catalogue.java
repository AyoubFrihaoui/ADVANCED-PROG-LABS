package exo1.q2.v2;

import java.util.HashSet;
import java.util.Set;

/**
 * SOLUTION — the catalogue only knows the {@link Dumper} interface.
 * Switching from XML to JSON (or YAML) changes the caller, never this class.
 */
public class Catalogue {
    private Set<Produit> produits;

    public Catalogue() {
        produits = new HashSet<>();
    }

    public void ajouterProduit(Produit p) {
        produits.add(p);
    }

    /**
     * Exports the catalogue through any dumper. The traversal is written
     * once, here; each format decides how nodes and text are rendered.
     */
    public String exporter(Dumper dumper) {
        dumper.openTag("catalogue");
        for (Produit p : produits) {
            dumper.openTag("produit");
            dumper.openTag("designation");
            dumper.text(p.getDesignation());
            dumper.closeTag("designation");
            dumper.openTag("prix");
            dumper.text(Double.toString(p.getPrix()));
            dumper.closeTag("prix");
            dumper.closeTag("produit");
        }
        dumper.closeTag("catalogue");
        return dumper.getResult();
    }
}
