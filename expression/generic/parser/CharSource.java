package expression.generic.parser;

public interface CharSource {
    boolean hasNext();
    char next();
    int getPos();
    String getString();
}
