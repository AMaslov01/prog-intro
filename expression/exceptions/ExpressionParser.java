package expression.exceptions;

import expression.*;

public class ExpressionParser implements TripleParser {

    @Override
    public TripleExpression parse(final String expression) {
        return parse(new StringSource(expression));
    }

    public static TripleExpression parse(final CharSource charSource) {
        return new MathParser(charSource).checkExpression();
    }

    private static class MathParser extends BaseParser {
        public MathParser(final CharSource charSource) {
            super(charSource);
        }

        private TripleExpression checkExpression() {
            final TripleExpression result = parseExpression();
            skipWhiteSpace();
            if (cur() != EOF) {
                throw new ParsingException("Expected: EOF, Found: " + cur());
            }
            return result;
        }

        private TripleExpression parseExpression() {
            skipWhiteSpace();
            final TripleExpression result = parseMinPriority();
            skipWhiteSpace();
            return result;
        }

        private TripleExpression parseMinPriority() {
            TripleExpression result = parseLowPriority();
            while (true) {
                if (take('g')) {
                    expect("cd");
                    if(Character.isDigit(cur())){
                        throw new ParsingException("Illegal gcd call");
                    }
                    result = new CheckedGcd(result, parseLowPriority());
                } else if (take('l')) {
                    expect("cm");
                    if(Character.isDigit(cur())){
                        throw new ParsingException("Illegal gcd call");
                    }
                    result = new CheckedLcm(result, parseLowPriority());
                } else {
                    return result;
                }
            }
        }

        private TripleExpression parseLowPriority() {
            TripleExpression result = parseMiddlePriority();
            while (true) {
                if (take('+')) {
                    result = new CheckedAdd(result, parseMiddlePriority());
                } else if (take('-')) {
                    result = new CheckedSubtract(result, parseMiddlePriority());
                } else {
                    return result;
                }
            }
        }

        private TripleExpression parseMiddlePriority() {
            TripleExpression result = parsePreTopPriority();
            while (true) {
                if (take('*')) {
                    result = new CheckedMultiply(result, parsePreTopPriority());
                } else if (take('/')) {
                    result = new CheckedDivide(result, parsePreTopPriority());
                } else {
                    return result;
                }
            }
        }

        private TripleExpression parsePreTopPriority() {
            TripleExpression result = parseUnary();
            while (true) {
                skipWhiteSpace();
                if (match("**")) {
                    result = new Pow(result, parseUnary());
                } else if (match("//")) {
                    result = new Log(result, parseUnary());
                } else {
                    return result;
                }
            }
        }

        private TripleExpression parseUnary() {
            skipWhiteSpace();
            TripleExpression result = null;
            if (take('²')) {
                return new CheckedTetra2(parseUnary());
            } else if (take('-')) {
                if (isDigit(cur())) {
                    result = parseNumber(true);
                } else {
                    result = new CheckedNegate(parseUnary());
                }
            }
            if(result == null){
                result = parseTopPriority();
            }
            return parseSquare(result);
        }


        private TripleExpression parseTopPriority() {
            if (take('(')) {
                final TripleExpression result = parseExpression();
                expect(')');
                return result;
            } else if (isDigit(cur())) {
                return parseNumber(false);
            } else if (isVariable(cur())) {
                return parseVariable();
            } else {
                throw new ParsingException("Expected: Var, Const or Expression, Found: " + ((cur()==EOF)?"EOF":cur()));
            }
        }

        private Const parseNumber(final boolean negative) {
            final Const result;
            final StringBuilder sb = new StringBuilder();
            if (negative) {
                sb.append('-');
            }
            while (isDigit(cur())) {
                sb.append(take());
            }
            if (Character.isLetter(cur())) {
                throw new ParsingException("Unexpected Character: " + cur() + " after '" + sb + "'");
            }                                                 
            try {
                result = new Const(Integer.parseInt(sb.toString()));
            } catch (final NumberFormatException e) {
                throw new ParsingException("Illegal argument encountered: " + sb);
            }
            return result;
        }

        private Variable parseVariable() {
            final StringBuilder sb = new StringBuilder();
            while (isVariable(cur())) {
                sb.append(take());
            }
            return new CheckedVariable(sb.toString());
        }

        private void skipWhiteSpace() {
            while (true) {
                if (Character.isWhitespace(cur())) {
                    take();
                } else {
                    break;
                }
            }
        }

        private TripleExpression parseSquare(TripleExpression result) {
            while (take('²')) {
                result = new Square(result);
                skipWhiteSpace();
            }
            return result;
        }

        private boolean isDigit(final char c) {
            return '0' <= c && c <= '9';
        }

        private boolean isVariable(final char c) {
            return Character.isLetter(c) || Character.isDigit(c);
        }

        private boolean match(final String expression) {
            final int pos = getCharSource().getPos();
            if (getCharSource().getPos() - 1 + expression.length() < getCharSource().getString().length()
                    && getCharSource().getString().substring(pos - 1).startsWith(expression)) {
                int i = 0;
                while (i < expression.length()) {
                    i++;
                    take();
                }
                return true;
            }
            return false;
        }

        private void expect(final String expected){
            for(char ch: expected.toCharArray()){
                expect(ch);
            }
        }
    }


}
