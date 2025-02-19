package expression.exceptions;

import expression.Gcd;
import expression.TripleExpression;

public class CheckedGcd extends Gcd {

    public CheckedGcd(TripleExpression first, TripleExpression second) {
        super(first, second);
    }

    public int operate(int a, int b){
        return super.operate(a, b);
    }
}
