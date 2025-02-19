package expression;

public class Gcd extends AbstractBinaryOperation {

    public Gcd(TripleExpression first, TripleExpression second) {
        super(first, second);
    }

    @Override
    String getSymbol() {
        return "gcd";
    }

    @Override
    protected int getPriority() {
        return 0;
    }

    @Override
    protected int operate(int a, int b){
        return (int)gcd(a, b);
    }

    @Override
    protected long operate(long a, long b){
        return gcd(a, b);
    }

    protected long gcd(long a, long b){
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return (a < 0) ? -a : a;
    }

}
