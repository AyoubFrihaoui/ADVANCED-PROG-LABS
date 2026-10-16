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

   /**
    * SOLUTION — a text node renders as its escaped text
    * (the Composite "leaf" case: no children, no tags).
    */
   @Override
   public String toXML() {
      return XMLHelper.protectText(text);
   }
}
