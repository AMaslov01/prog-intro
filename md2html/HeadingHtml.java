package md2html;

public class HeadingHtml extends AbstractHtml{
    public HeadingHtml(String string){
        super(string);
    }

    public String convertHeading(int depth){
        return "<h" + depth + ">" +
                string.substring(depth + 1) +
                "</h" + depth + ">" +
                System.lineSeparator();
    }
}
