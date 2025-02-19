package expression.parser;

public abstract class BaseParser {
    public CharSource charSource;
    private char ch;
    private static final char EOF = 0;

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

    protected void expect(char expected) {
        if (!take(expected)) {
            throw new IllegalArgumentException("Expected '" + expected + "', found '" + ch + "'");
        }
    }

    protected char cur() {
        return ch;
    }

    private void advance() {
        ch = charSource != null && charSource.hasNext() ? charSource.next() : EOF;
    }
}
