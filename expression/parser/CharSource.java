package expression.parser;

public interface CharSource {
    boolean hasNext();
    char next();
    int getPos();
    String getString();
}
