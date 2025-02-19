package expression;

public class Log extends AbstractBinaryOperation implements Expression, TripleExpression, LongMapExpression {

    public Log(TripleExpression first, TripleExpression second) {
        super(first, second);
    }

    @Override
    protected int operate(int a, int b) {
        return (int)log(a, b);
    }

    @Override
    protected long operate(long a, long b) {
        return log(a, b);
    }

    @Override
    protected String getSymbol() {
        return "//";
    }

    @Override
    protected int getPriority() {
        return 3;
    }

    public static long log(long a, long b) {
        if(b == 1 && (a == -1 || a == 1) || a == 0  && b <= 0 || a < 0 && b != 0){
            return 0;
        }
        if(a == 0){
            return Integer.MIN_VALUE;
        }
        if(b == 1){
            return Integer.MAX_VALUE;
        }
        if(a == b){
            return 1;
        }
        if(b <= 0 || b >= a){
            return 0;
        }
        long res = 0;
        while (a >= b) {
            a /= b;
            res++;
        }
        return res;
    }
}