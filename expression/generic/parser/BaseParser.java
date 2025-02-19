package expression.generic.parser;

import expression.exceptions.ParsingException;

public abstract class BaseParser {
    public CharSource charSource;
    private char ch;
    protected static final char EOF = 0;

    public BaseParser(CharSource charSource) {
        this.charSource = charSource;
        advance();
    }

    protected CharSource getCharSource(){
        return this.charSource;
    }

    protected char take() {
        final char result = ch;
        advance();
        return result;
    }

    protected boolean take(final char expected) {
        if (test(expected)) {
            advance();
            return true;
        }
        return false;
    }

    protected boolean test(char expected) {
        return ch == expected;
    }

    protected void expect(char expected) throws ParsingException {
        if (!take(expected)) {
            throw new ParsingException("Expected '" + expected + "', found '" + ((ch==EOF)?"EOF":ch) + "'");
        }
    }

    protected char cur() {
        return ch;
    }

    void advance() {
        ch = charSource != null && charSource.hasNext() ? charSource.next() : EOF;
    }
}
