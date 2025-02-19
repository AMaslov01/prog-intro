package expression;

import java.util.Objects;

public class Negate extends AbstractUnaryOperation implements TripleExpression,  ToMiniString {

    public Negate( TripleExpression term) {
        super(term);
    }

    @Override
    protected int operate(int value) {
        return -value;
    }

    @Override
    public String toMiniString() {
        if (term instanceof  Const || term instanceof  Variable
                || term instanceof Negate || term instanceof Square || term instanceof Tetra2) {
            return "- " + term.toMiniString();
        }
        return "-" + "(" + term.toMiniString() + ")";
    }

    @Override
    public String toString() {
        return "-(" + term.toString() + ")";
    }
}

