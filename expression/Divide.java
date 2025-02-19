package expression;

public class Divide extends AbstractBinaryOperation implements Expression, TripleExpression, LongMapExpression {
    public Divide(TripleExpression first, TripleExpression second) {
        super(first, second);
    }

    @Override
    protected int operate(int a, int b) {
        return a / b;
    }

    @Override
    long operate(long a, long b) {
        return a / b;
    }

    @Override
    protected String getSymbol() {
        return "/";
    }

    @Override
    protected int getPriority() {
        return 2;
    }
}
