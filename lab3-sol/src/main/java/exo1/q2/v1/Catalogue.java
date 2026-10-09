package exo1.q2.v1;

import java.util.HashSet;

public class Catalogue {
    private HashSet<Produit> produits;

    public Catalogue() {
        produits = new HashSet<>();
    }

    public void ajouterProduit(Produit p) {
        produits.add(p);
    }

    public String xmlDumper() {
        XMLDumper xmlDumper = new XMLDumper();
        xmlDumper.baliseOpen("catalogue");
        for (Produit p: produits) {
            xmlDumper.baliseOpen("produit");
            xmlDumper.baliseOpen("designation");
            xmlDumper.texte(p.getDesignation());
            xmlDumper.baliseFerme("designation");
            xmlDumper.baliseOpen("prix");
            xmlDumper.texte(Double.toString(p.getPrix()));
            xmlDumper.baliseFerme("prix");
            xmlDumper.baliseFerme("produit");
        }
        xmlDumper.baliseFerme("catalogue");
        return xmlDumper.getResult();
    }
}
