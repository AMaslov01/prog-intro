package expression;

public class Pow extends AbstractBinaryOperation implements Expression, TripleExpression, LongMapExpression, ToMiniString {

    public Pow(TripleExpression first, TripleExpression second) {
        super(first, second);
    }

    @Override
    protected int operate(int a, int b) {
        return (int)pow(a, b);
    }

    @Override
    protected long operate(long a, long b) {
        return pow(a, b);
    }

    @Override
    protected String getSymbol() {
        return "**";
    }

    @Override
    protected int getPriority() {
        return 3;
    }


    private long pow(long a, long b){
        if(b == 1){
            return a;
        } else if(b < 0 || b == 0 || a == 1){
            return 1;
        }else if(a == 0){
            return 0;
        } else if(a == -1){
            return (b % 2 == 0) ? 1:-1;
        } else{
            long result = 1;
            long currentBase = a;

            while (b > 0) {
                if ((b & 1) == 1) {
                    result *= currentBase;
                }
                currentBase *= currentBase;
                b >>= 1;
            }

            return result;
        }
    }
}