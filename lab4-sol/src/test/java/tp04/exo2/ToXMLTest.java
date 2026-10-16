package tp04.exo2;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ToXMLTest {
    
    /**
     * Helper method to create a document.
     * @return
     */
    Node creerDocument() {
         // Building the XML document.
         Element html = new Element("html");
         Element body = new Element("body");
         html.addChild(body);
         Element h1 = new Element("h1");
         h1.addChild(new TextNode("Un exemple"));
         body.addChild(h1);
         Element div = new Element("div");
         body.addChild(div);
         Element p = new Element("p");
         p.addChild(new TextNode("texte..."));
         p.setAttributeValue("id", "p1");
         div.addChild(p);
         div.addChild(new TextNode("autre texte..."));
        return html;
    }

    @Test
    public void testNoeudTexte() {
        TextNode n = new TextNode("un texte");
        assertEquals("un texte", n.toXML());        
    }

    @Test
    public void testNoeudTexteAProteger() {
        TextNode n = new TextNode("2 < 3 & 4 > 2");
        assertEquals("2 &lt; 3 &amp; 4 &gt; 2", n.toXML());        
    }

    @Test
    public void testBaliseSimple() {
        Element e = new Element("balise");
        assertEquals("<balise></balise>", e.toXML());
    }

    @Test
    public void testBaliseComplexe1() {
        Element e = new Element("balise");
        e.addChild(new TextNode("texte"));
        assertEquals("<balise>texte</balise>", e.toXML());
    }

    @Test
    public void testUnAttribut() {
        Element e = new Element("balise");
        e.setAttributeValue("id", "val");
        assertEquals("<balise id=\"val\"></balise>", e.toXML());
    }


    @Test
    public void testDeuxAttribut() {
        Element e = new Element("balise");
        e.setAttributeValue("id", "val");
        e.setAttributeValue("class", "important");
        assertEquals("<balise class=\"important\" id=\"val\"></balise>", e.toXML());
    }

    @Test
    public void testProtectionAttribut() {
        Element e = new Element("balise");
        e.setAttributeValue("id", "<\">");
        assertEquals("<balise id=\"&lt;&quot;&gt;\"></balise>", e.toXML());
    }
    
    @Test
    public void testBaliseComplexe2() {
        Element e = new Element("balise");
        e.addChild(new TextNode("texte"));
        Element e1 = new Element("autre");
        e1.addChild(new TextNode("txt2"));
        e.addChild(e1);
        e.addChild(new TextNode("."));
        assertEquals("<balise>texte<autre>txt2</autre>.</balise>", e.toXML());
    }



    @Test
    public void testDocumentComplet() {
        Node node = creerDocument();        
        String attendu = "<html><body><h1>Un exemple</h1><div><p id=\"p1\">texte...</p>autre texte...</div></body></html>";
        assertEquals(attendu, node.toXML());
    }


}

