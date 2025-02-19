package markup;

import java.util.List;

public abstract class AbstractClass {
    protected List<? extends DocBook> elements;

    protected AbstractClass(List<? extends DocBook> elements) {
        this.elements = elements;
    }

    protected abstract String getDocBookBeginningSymbol();

    protected abstract String getDocBookEndingSymbol();

    public void toDocBook(StringBuilder stringBuilder) {
        stringBuilder.append(getDocBookBeginningSymbol());
        for (DocBook element : elements) {
            element.toDocBook(stringBuilder);
        }
        stringBuilder.append(getDocBookEndingSymbol());
    }
}
