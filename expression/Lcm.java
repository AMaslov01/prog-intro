package expression;
public class Lcm extends AbstractBinaryOperation {

    public Lcm(TripleExpression first, TripleExpression second) {
        super(first, second);
    }

    @Override
    protected String getSymbol() {
        return "lcm";
    }

    @Override
    protected int getPriority() {
        return 0;
    }

    protected long gcd(long a, long b){
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return (a < 0) ? -a : a;
    }
    protected long lcm(long a, long b){
        if(a == 0 || b == 0){
            return 0;
        }
        long gcd = gcd(a, b);
        return (gcd == 0) ? 0 : a * b / gcd;
    }

    @Override
    protected int operate(int a, int b) {
        return (int) lcm(a, b);
    }

    @Override
    protected long operate(long a, long b) {
        return lcm(a, b);
    }
}
