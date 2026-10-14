package tp04.exo2;

public class Main {
    public static void main(String[] args) {
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
        Element image= new Element("img");
        image.setAttributeValue("src", "toto.png");
        p.addChild(image);
        div.addChild(p);
        div.addChild(new TextNode("autre texte..."));

        // Displaying the text it contains
        System.out.println("Document text");
        System.out.println(html.textContent());
        System.out.println();
        System.out.println("Document XML code");
        System.out.println(html.toXML());
    }
}
