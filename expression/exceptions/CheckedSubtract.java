package expression.exceptions;

import expression.Subtract;
import expression.TripleExpression;

public class CheckedSubtract extends Subtract {
    public CheckedSubtract(TripleExpression first, TripleExpression second) {
        super(first, second);
    }
    public int operate(int a, int b) {
        if(a < Integer.MIN_VALUE + b && b > 0|| b < 0 && a > Integer.MAX_VALUE + b){
            throw new OverflowException("Subtract causes overflow: " + a + " - " + b);
        }
        return super.operate(a, b);
    }
}
