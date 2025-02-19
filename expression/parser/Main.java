package expression.parser;

import expression.Expression;
import expression.TripleExpression;

public class Main {
    public static void main(String[] args) {
        ExpressionParser expressionParser = new ExpressionParser();
        System.out.println(expressionParser.parse("(-4 + -30)\n").evaluate(0, 0, 0));
    }
}
