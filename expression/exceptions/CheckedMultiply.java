package expression.exceptions;

import expression.Multiply;
import expression.TripleExpression;

public class CheckedMultiply extends Multiply {

    public CheckedMultiply(TripleExpression first, TripleExpression second) {
        super(first, second);
    }

    public int operate(int a, int b) {
        if (a > 0 && b > 0 && a > Integer.MAX_VALUE / b ||
                a > 0 && b < 0 && b < Integer.MIN_VALUE / a ||
                a < 0 && b > 0 && a < Integer.MIN_VALUE / b ||
                a < 0 && b < 0 && a < Integer.MAX_VALUE / b) {
            throw new OverflowException("Multiplication causes overflow: " + a + " * " + b);
        }
        return super.operate(a, b);
    }

}
