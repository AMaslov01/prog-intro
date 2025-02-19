package md2html;

public class ParagraphHtml extends AbstractHtml{
    public ParagraphHtml(String string){
        super(string);
    }

    public String convertParagraph(){
        return "<p>" +
                string +
                "</p>" +
                System.lineSeparator();
    }
}
