package markup;

import java.util.List;

public class ListItem extends AbstractClass implements DocBook{
    public ListItem(List<ListItemInterface> listItems) {
        super(listItems);
    }
    public String getDocBookBeginningSymbol() {
        return "<listitem>";
    }
    public String getDocBookEndingSymbol() {
        return "</listitem>";
    }
}
