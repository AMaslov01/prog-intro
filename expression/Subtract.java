package expression;

public class Subtract extends AbstractBinaryOperation implements Expression, TripleExpression, LongMapExpression{

    public Subtract(TripleExpression first, TripleExpression second) {
        super(first, second);
    }

    @Override
    protected int operate(int a, int b) {
        return a - b;
    }

    @Override
    protected long operate(long a, long b) {
        return a - b;
    }

    @Override
    protected String getSymbol() {
        return "-";
    }

    @Override
    protected int getPriority() {
        return 1;
    }
}
