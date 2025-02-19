package markup;

import java.util.List;

public class Strikeout extends Markup implements Markdown{
    public Strikeout(List <Markdown> markdownDocBooks) {
        super(markdownDocBooks);
    }

    @Override
    public String getMarkdownSymbol(){
        return "~";
    }

    @Override
    public String getDocBookBeginningSymbol() {
        return "<emphasis role='strikeout'>";
    }

    @Override
    public String getDocBookEndingSymbol() {
        return "</emphasis>";
    }
}
