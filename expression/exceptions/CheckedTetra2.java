package expression.exceptions;

import expression.Tetra2;
import expression.TripleExpression;

public class CheckedTetra2 extends Tetra2 {

    public CheckedTetra2(TripleExpression exp) {
        super(exp);
    }

    public int operate(int value) {
        if (value <= 0) {
            throw new ParsingException("Cannot use such argument as power: " + term.toString());
        }
        if (value >= 10) {
            throw new OverflowException("Overflow caused: " + term.toString());
        }
        return super.operate(value);
    }
}
