package exo1.q2.v2;

/** SOLUTION — the v1 XML rendering, adapted to the {@link Dumper} interface. */
public class XMLDumper implements Dumper {
    private StringBuilder builder = new StringBuilder();

    @Override
    public void openTag(String name) {
        builder.append("<" + name + ">");
    }

    @Override
    public void text(String content) {
        builder.append(content.replace("&", "&amp;").replace("<", "&lt;"));
    }

    @Override
    public void closeTag(String name) {
        builder.append("</" + name + ">");
    }

    @Override
    public String getResult() {
        return builder.toString();
    }
}
