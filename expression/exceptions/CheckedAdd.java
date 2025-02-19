package expression.exceptions;

import expression.Add;
import expression.TripleExpression;

public class CheckedAdd extends Add {

    public CheckedAdd(TripleExpression first, TripleExpression second) {
        super(first, second);
    }

    public int operate(int a, int b) {
        if(Integer.MAX_VALUE - a < b && a > 0 || a < 0 && b < Integer.MIN_VALUE - a){
            throw new OverflowException("Sum causes overflow: " + a + " + " + b);
        }
        return super.operate(a, b);
    }
}
