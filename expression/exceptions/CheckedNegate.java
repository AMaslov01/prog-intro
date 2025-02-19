package expression.exceptions;

import expression.Negate;
import expression.TripleExpression;

public class CheckedNegate extends Negate {

    public CheckedNegate(TripleExpression term) {
        super(term);
    }

    public int operate(int value){
        if(value == Integer.MIN_VALUE){
            throw new OverflowException("Cannot negate min value: " + super.toString());
        }
        return super.operate(value);
    }
}
