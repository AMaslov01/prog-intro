package markup;

import java.util.List;

public class Emphasis extends Markup implements Markdown{
    public Emphasis(List<Markdown> markdownDocBooks) {
        super(markdownDocBooks);
    }

    @Override
    public String getMarkdownSymbol(){
        return "*";
    }

    @Override
    public String getDocBookBeginningSymbol() {
        return "<emphasis>";
    }

    @Override
    public String getDocBookEndingSymbol() {
        return "</emphasis>";
    }
}
