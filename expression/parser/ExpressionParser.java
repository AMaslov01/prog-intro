package expression.parser;

import expression.*;

public class ExpressionParser implements TripleParser {

    @Override
    public TripleExpression parse(final String expression) {
        return parse(new StringSource(expression));
    }

    public TripleExpression parse(final CharSource charSource) {
        return new MathParser(charSource).parseExpression();
    }

    private static class MathParser extends BaseParser {


        public MathParser(final CharSource charSource) {
            super(charSource);

        }

        private TripleExpression parseExpression() {
            skipWhiteSpace();
            final TripleExpression result = parseLowPriority();
            skipWhiteSpace();
            return result;
        }

        private TripleExpression parseLowPriority() {
            TripleExpression result = parseMiddlePriority();
            while (true) {
                skipWhiteSpace();
                if (take('+')) {
                    result = new Add(result, parseMiddlePriority());
                } else if (take('-')) {
                    result = new Subtract(result, parseMiddlePriority());
                } else {
                    return result;
                }
            }
        }

        private TripleExpression parseMiddlePriority() {
            TripleExpression result = parsePreTopPriority();
            while (true) {
                skipWhiteSpace();
                if (take('*')) {
                    result = new Multiply(result, parsePreTopPriority());
                } else if (take('/')) {
                    result = new Divide(result, parsePreTopPriority());
                } else {
                    return result;
                }
            }
        }

        private TripleExpression parsePreTopPriority(){
            TripleExpression result = checkUnary();
            while (true) {
                skipWhiteSpace();
                if (match("**")) {
                    result = new Pow(result, checkUnary());
                } else if (match("//")) {
                    result = new Log(result, checkUnary());
                } else {
                    return result;
                }
                skipWhiteSpace();
            }
        }

        private TripleExpression checkUnary() {
            skipWhiteSpace();
            TripleExpression result;
             if(take('-')) {
                if(isDigit(cur())){
                    result = parseNumber(true);
                } else{
                    skipWhiteSpace();
                    result = new Negate(checkUnary());
                }
                 return checkSquare(result);
            }
            result = parseTopPriority();
            return checkSquare(result);
        }


        private TripleExpression parseTopPriority() {
            skipWhiteSpace();
            TripleExpression result;
            if (take('(')) {
                result = parseExpression();
                expect(')');
            } else if (isDigit(cur())) {
                result = parseNumber(false);
            } else if (isVariable(cur())) {
                result = parseVariable();
            } else {
                throw new IllegalArgumentException("Unexpected character: " + cur());
            }
            skipWhiteSpace();
            return result;
        }

        private Const parseNumber(final boolean negative) {
            final StringBuilder sb = new StringBuilder();
            if (negative){
                sb.append('-');
            }
            while (isDigit(cur())) {
                sb.append(take());
            }
            return new Const(Integer.parseInt(sb.toString()));
        }

        private Variable parseVariable() {
            final StringBuilder sb = new StringBuilder();
            while (isVariable(cur())) {
                char character = take();
                sb.append(character);
            }
            return new Variable(sb.toString());
        }

        private void skipWhiteSpace() {
            while (true) {
                if(Character.isWhitespace(cur())) {
                    take();
                } else {
                    break;
                }
            }
        }

        private TripleExpression checkSquare(TripleExpression result){
            while(take('²')){
                result = new Square(result);

                skipWhiteSpace();
            }
            return result;
        }

        private boolean isDigit(final char c) {
            return '0' <= c && c <= '9';
        }

        private boolean isVariable(final char c) {
            return Character.isAlphabetic(c);
        }

        private boolean match(final String expression){
            final int pos = getCharSource().getPos();
            if(getCharSource().getPos() - 1 + expression.length() < getCharSource().getString().length()
                    && getCharSource().getString().substring(pos - 1).startsWith(expression)){
                int i = 0;
                while(i < expression.length()){
                    i ++;
                    take();
                }
                return true;
            }
            return false;
        }

    }


}
