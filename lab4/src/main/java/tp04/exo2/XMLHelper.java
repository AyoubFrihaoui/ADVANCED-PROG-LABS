package tp04.exo2;

/**
 * Helper class to produce more-or-less correct XML.
 */
public class XMLHelper {

    /**
     * Escapes the minimum set of special characters in XML text.
     */
    public static String protectText(String text) {
        return text.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");        
    }

    public static String protectAttributeValue(String value) {
        return protectText(value).replace("\"", "&quot;");        
    }
}
