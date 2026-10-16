package tp04.exo2;

import java.util.Collections;
import java.util.List;

/**
 * Parent class for all the parts of an XML document.
 */
public abstract class Node {

    /**
     * Returns all the children of this node.
     * @return a list of nodes.
     */
    public List<Node> getChildren() {
        return Collections.emptyList();
    }

    /**
     * Returns the concatenation of all the text contained in a Node (including its children).
     * @return
     */
    public abstract String textContent();

    /**
     * Returns the XML code of the Node.
     * @return
     */
    public abstract String toXML();
}
