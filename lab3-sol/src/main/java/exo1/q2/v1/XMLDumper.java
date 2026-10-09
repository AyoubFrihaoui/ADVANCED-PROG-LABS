package exo1.q2.v1;

public class XMLDumper {
    private StringBuilder builder = new StringBuilder();

    public void baliseOpen(String nomBalise) {
        builder.append("<" + nomBalise + ">");
    } 

    public void texte(String texte) {
        builder.append(texte.replace("&", "&amp;").replace("<", "&lt;"));
    }

    public void baliseFerme(String nomBalise) {
        builder.append("</" + nomBalise + ">");
    }

    public String getResult() {
        return builder.toString();
    }
}


