package expression.exceptions;

import expression.parser.ExpressionParser;

public class Main {
    public static void main(String[] args) throws ParsingException, OverflowException {
        expression.exceptions.ExpressionParser expressionParser = new expression.exceptions.ExpressionParser();
        System.out.println(expressionParser.parse("(-4 + -30)\n").evaluate(1, 1, 1));
    }
}
