package markup;

import java.util.List;

public abstract class Markup extends AbstractClass implements Markdown {
    protected Markup(List<Markdown> elements) {
        super(elements);
    }

    protected abstract String getMarkdownSymbol();

    @Override
    public void toMarkdown(StringBuilder stringBuilder) {
        stringBuilder.append(getMarkdownSymbol());
        for (DocBook element : elements) {
            ((Markdown)element).toMarkdown(stringBuilder);
        }
        stringBuilder.append(getMarkdownSymbol());
    }
}
