package markup;

import java.util.List;

public abstract class AbstractList extends AbstractClass implements ListItemInterface {
    public AbstractList(List<ListItem> listItems) {
        super(listItems);
    }
}
