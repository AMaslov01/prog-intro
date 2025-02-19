package markup;

import java.util.List;

public class UnorderedList extends AbstractList implements ListItemInterface {

    public UnorderedList(List<ListItem> listItems){
        super(listItems);
    }

    @Override
    public String getDocBookBeginningSymbol() {
        return "<itemizedlist>";
    }

    @Override
    public String getDocBookEndingSymbol() {
        return "</itemizedlist>";
    }

}
