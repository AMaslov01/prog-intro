package expression.exceptions;

import expression.Divide;
import expression.TripleExpression;

public class CheckedDivide extends Divide {

    public CheckedDivide(TripleExpression first, TripleExpression second) {
        super(first, second);
    }

    public int operate(int a, int b){
        if(a == Integer.MIN_VALUE && b == -1){
            throw new OverflowException("Division cause overflow: " + a + " / " + b);
        } if(b == 0){
            throw new ZeroDivisionException("Zero division encountered: " + a + " / " + b);
        }
        return super.operate(a, b);
    }

}
