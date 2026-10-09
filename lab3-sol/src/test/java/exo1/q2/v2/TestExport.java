package exo1.q2.v2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * SOLUTION tests — one traversal, two formats, {@code Catalogue} untouched.
 */
public class TestExport {

    private Catalogue catalogueSourisEtOrdi() {
        Catalogue cat = new Catalogue();
        cat.ajouterProduit(new Produit("souris", 30));
        cat.ajouterProduit(new Produit("ordi", 600));
        return cat;
    }

    @Test
    public void xmlExportContainsBothProducts() {
        String xml = catalogueSourisEtOrdi().exporter(new XMLDumper());
        assertTrue(xml.startsWith("<catalogue>"));
        assertTrue(xml.endsWith("</catalogue>"));
        assertTrue(xml.contains("<designation>souris</designation>"));
        assertTrue(xml.contains("<prix>30.0</prix>"));
        assertTrue(xml.contains("<designation>ordi</designation>"));
        assertTrue(xml.contains("<prix>600.0</prix>"));
    }

    @Test
    public void jsonExportHasExpectedShape() {
        String json = catalogueSourisEtOrdi().exporter(new JSONDumper());
        assertTrue(json.contains("\"@type\":\"catalogue\""));
        assertTrue(json.contains("\"@type\":\"produit\""));
        assertTrue(json.contains("\"@type\":\"designation\""));
        assertTrue(json.contains("\"@type\":\"prix\""));
        assertTrue(json.contains("souris"));
        assertTrue(json.contains("30.0"));
        assertTrue(json.contains("ordi"));
        assertTrue(json.contains("600.0"));
        // well-formed: balanced braces
        assertEquals(count(json, '{'), count(json, '}'));
    }

    @Test
    public void switchingFormatNeedsNoCatalogueChange() {
        Catalogue cat = catalogueSourisEtOrdi();
        String xml = cat.exporter(new XMLDumper());
        String json = cat.exporter(new JSONDumper());
        // same business object, two renderings — and both mention the products
        assertTrue(xml.contains("souris") && json.contains("souris"));
    }

    @Test
    public void emptyCatalogueExportsCleanly() {
        assertEquals("<catalogue></catalogue>", new Catalogue().exporter(new XMLDumper()));
        String json = new Catalogue().exporter(new JSONDumper());
        assertTrue(json.contains("\"@type\":\"catalogue\""));
    }

    private int count(String s, char c) {
        int n = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                n++;
            }
        }
        return n;
    }
}
