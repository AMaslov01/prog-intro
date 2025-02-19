package markup;

import java.util.List;

public class Strong extends Markup implements Markdown{
    public Strong(List<Markdown> markdownDocBooks) {
        super(markdownDocBooks);
    }

    @Override
    public String getMarkdownSymbol(){
        return "__";
    }

    @Override
    public String getDocBookBeginningSymbol() {
        return "<emphasis role='bold'>";
    }

    @Override
    public String getDocBookEndingSymbol() {
        return "</emphasis>";
    }
}
