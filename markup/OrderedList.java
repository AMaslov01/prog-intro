package markup;

import java.util.List;

public class OrderedList extends AbstractList implements ListItemInterface{

    public OrderedList(List<ListItem> listItems) {
        super(listItems);
    }

    @Override
    public String getDocBookBeginningSymbol() {
        return "<orderedlist>";
    }

    @Override
    public String getDocBookEndingSymbol() {
        return "</orderedlist>";
    }

}
