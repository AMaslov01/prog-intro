package expression.generic.parser;

import expression.exceptions.ParsingException;
import expression.generic.MyExpression;
import expression.generic.types.*;
import expression.generic.operations.*;

import java.util.Map;

public class ExpressionParser<T> {
    private final Type<T> type;

    public ExpressionParser(Type<T> type) {
        this.type = type;
    }

    public MyExpression<T> parse(final String expression) {
        return parse(new StringSource(expression));
    }

    public MyExpression<T> parse(final CharSource charSource) {
        return new MathParser<>(charSource, type).checkExpression();
    }

    private static class MathParser<T> extends BaseParser {
        private final Type<T> type;

        private static final Map<Character, Character> PARENTHESIS = Map.of(
                '(', ')',
                '[', ']',
                '{', '}'
        );

        public MathParser(final CharSource charSource, final Type<T> type) {
            super(charSource);
            this.type = type;
        }

        private MyExpression<T> checkExpression() {
            final MyExpression<T> result = parseExpression();
            skipWhiteSpace(); // :NOTE: ??
            if (cur() != EOF) {
                throw new ParsingException("Expected: EOF, Found: " + cur());
            }
            return result;
        }

        private MyExpression<T> parseExpression() {
            skipWhiteSpace();
            final MyExpression<T> result = parseLowPriority();
            skipWhiteSpace();
            return result;
        }

        private MyExpression<T> parseLowPriority() {
            MyExpression<T> result = parseMiddlePriority();
            while (true) {
                if (take('+')) {
                    result = new Add<>(result, parseMiddlePriority(), type);
                } else if (take('-')) {
                    result = new Subtract<>(result, parseMiddlePriority(), type);
                } else {
                    return result;
                }
            }
        }

        private MyExpression<T> parseMiddlePriority() {
            MyExpression<T> result = parseUnary();
            skipWhiteSpace();
            while (true) {
                if (take('*')) {
                    result = new Multiply<>(result, parseUnary(), type);
                } else if (take('/')) {
                    result = new Divide<>(result, parseUnary(), type);
                } else if (take('m')){
                    expect("od");
                    // :NOTE: 5mod5
                    result = new Mod<>(result, parseUnary(), type);
                }
                else {
                    return result;
                }
            }
        }

        private MyExpression<T> parseUnary() {
            skipWhiteSpace();
            if (take('-')) {
                if (isDigit(cur())) {
                    return parseNumber(true);
                } else {
                    return new Negate<>(parseUnary(), type);
                }
                // :NOTE: spaces
            } else if(take('a')){
                expect("bs");
                return new Abs<>(parseUnary(), type);
            } else if(take('s')){
                expect("quare");
                return new Square<>(parseUnary(), type);
            }
            return parseTopPriority();
        }

        private MyExpression<T> parseTopPriority() {
            // :NOTE: containsKey + get
            if (PARENTHESIS.containsKey(cur())) {
                final char opening = cur();
                take(cur());
                final MyExpression<T> result = parseExpression();
                expect(PARENTHESIS.get(opening));
                return result;
            } else if (isDigit(cur())) {
                return parseNumber(false);
            } else if (isVariable(cur())) {
                return parseVariable();
            } else {
                throw new ParsingException("Expected: Var, Const or Expression, Found: " + ((cur()==EOF)?"EOF":cur()));
            }
        }

        private Const<T> parseNumber(final boolean negative) {
            final Const<T> result;
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
                result = new Const<>(type.parse(sb.toString()));
            } catch (final NumberFormatException e) {
                throw new ParsingException("Illegal argument encountered: " + sb);
            }
            skipWhiteSpace();
            return result;
        }

        private Variable<T> parseVariable() {
            final StringBuilder sb = new StringBuilder();
            while (isVariable(cur())) {
                sb.append(take());
            }
            skipWhiteSpace();
            return new Variable<>(sb.toString());
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

        private boolean isDigit(final char c) {
            return '0' <= c && c <= '9';
        }

        private boolean isVariable(final char c) {
            return Character.isLetter(c) || Character.isDigit(c);
        }

        private void expect(final String expected){
            for(char ch: expected.toCharArray()){
                expect(ch);
            }
        }
    }
}