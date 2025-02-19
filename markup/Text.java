package markup;

public class Text implements Markdown{
    private final StringBuilder stringBuilder;

    public Text(String string){
        this.stringBuilder = new StringBuilder(string);
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        sb.append(this.stringBuilder);
    }

    @Override
    public void toDocBook(StringBuilder sb) {
        sb.append(this.stringBuilder);
    }
}
