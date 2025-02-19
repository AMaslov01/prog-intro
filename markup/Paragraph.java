package markup;

import java.util.List;

public class Paragraph extends AbstractClass implements ListItemInterface{
    public Paragraph(List<Markdown> elements){
        super(elements);
    }

    @Override
    public String getDocBookBeginningSymbol() {
        return "<para>";
    }

    @Override
    public String getDocBookEndingSymbol() {
        return "</para>";
    }

    public void toMarkdown(StringBuilder stringBuilder){
        for(DocBook element: elements){
            ((Markdown)element).toMarkdown(stringBuilder);
        }
    }
}
