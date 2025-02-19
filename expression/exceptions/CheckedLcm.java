package expression.exceptions;

import expression.Lcm;
import expression.TripleExpression;

public class CheckedLcm extends Lcm {

    public CheckedLcm(TripleExpression first, TripleExpression second) {
        super(first, second);
    }

    public int operate(int a, int b){
        if(a == 0 || b == 0){
            return super.operate(a, b);
        }
        if((int)super.gcd(a, b) != 0){
            int aDivGcd = a/(int)super.gcd(a, b);
            if(overflowCheck(aDivGcd, b)){
                throw new OverflowException("Lcm causes overflow: " + a + " lcm " + b);
            }
        }
        if(a == Integer.MIN_VALUE && b == -1 || a == -1 && b == Integer.MIN_VALUE){
            throw new OverflowException("Lcm causes overflow: " + a + " lcm " + b);
        }
        return super.operate(a, b);
    }

    public static boolean overflowCheck(int x, int y) {
        if (x > 0 && y > 0) {
            return x > Integer.MAX_VALUE / y;
        } else if (x < 0 && y < 0) {
            return x < Integer.MAX_VALUE / y;
        } else if (x > 0 && y < 0) {
            return y < Integer.MIN_VALUE / x;
        } else {
            return x < Integer.MIN_VALUE / y;
        }
    }


}
