package tp04.exo2;

/**
 * A text node in an XML document.
 */
public class TextNode extends Node {
   private String text;

   public TextNode(String text) {
      this.text = text;
   }

   public String getText() {
       return text;
   }

   public void setText(String text) {
       this.text = text;
   }

   @Override
   public String textContent() {
      return text;
   }

   @Override
   public String toXML() {
       throw new UnsupportedOperationException("TODO: implement");
   }
   
   
}
