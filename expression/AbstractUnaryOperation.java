package expression;

import java.util.Objects;

public abstract class AbstractUnaryOperation implements TripleExpression, ToMiniString{
    protected TripleExpression term;

    public AbstractUnaryOperation(TripleExpression term) {
        this.term = term;
    }

    abstract int operate(int value);

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        AbstractUnaryOperation other = (AbstractUnaryOperation) obj;
        return Objects.equals(term, other.term);
    }

    @Override
    public int hashCode() {
        return Objects.hash(term);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return operate(term.evaluate(x, y, z));
    }
}
