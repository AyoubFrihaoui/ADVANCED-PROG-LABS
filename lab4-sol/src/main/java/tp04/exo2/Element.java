package tp04.exo2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * An element in the XML sense of the term.
 * It represents a pair of opening and closing tags,
 * and everything inside.
 * It is a Composite in the pattern sense.
 */
public class Element extends Node{
    private String tag;
    private TreeMap<String,String> attributes = new TreeMap<>();
    private List<Node> children = new ArrayList<>();


    /**
     * Declares the element.
     * @param tag :
     */
    public Element(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public void addChild(Node newChild) {
        children.add(newChild);
    }

    public void removeChild(Node toRemove) {
        children.remove(toRemove);
    }

    @Override
    public List<Node> getChildren() {
        return new ArrayList<>(children);
    }

    public void setAttributeValue(String attributeName, String value) {
        attributes.put(attributeName, value);
    }

    public String getAttributeValue(String attributeName) {
        return attributes.get(attributeName);
    }

    /**
     * Returns the set of defined attribute names.
     * @return
     */
    public Set<String> getDefinedAttributes() {
        return new TreeSet<>(attributes.keySet());
    }

    @Override
    public String textContent() {
        StringBuilder builder = new StringBuilder();
        for (Node n : getChildren()) {
            builder.append(n.textContent());
        }
        return builder.toString();
    }

    /**
     * SOLUTION — renders the element and, recursively, its children.
     * Attributes come from a TreeMap, so iteration is already alphabetical
     * (deterministic output, as required). Values and text go through
     * XMLHelper escaping.
     */
    @Override
    public String toXML() {
        StringBuilder builder = new StringBuilder();
        builder.append('<').append(tag);
        for (Map.Entry<String, String> attribute : attributes.entrySet()) {
            builder.append(' ')
                   .append(attribute.getKey())
                   .append("=\"")
                   .append(XMLHelper.protectAttributeValue(attribute.getValue()))
                   .append('"');
        }
        builder.append('>');
        for (Node child : children) {
            builder.append(child.toXML());
        }
        builder.append("</").append(tag).append('>');
        return builder.toString();
    }
}
