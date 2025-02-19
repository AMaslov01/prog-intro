package expression.generic.types;

import expression.exceptions.NegativeModulusException;
import expression.exceptions.ZeroDivisionException;

public class LongType implements Type<Long>{

    @Override
    public Long add(Long a, Long b) {
        return a + b;
    }

    @Override
    public Long subtract(Long a, Long b) {
        return a - b;
    }

    @Override
    public Long multiply(Long a, Long b) {
        return a * b;
    }

    @Override
    public Long mod(Long a, Long b) {
        if(b == 0){
            throw new NegativeModulusException("Negative modulus: " + a + " % " + b);
        }
        return a % b;
    }

    @Override
    public Long square(Long a) {
        return a * a;
    }

    @Override
    public Long abs(Long a) {
        return (a < 0) ? -a : a;
    }

    @Override
    public Long divide(Long a, Long b) {
        if(b == 0){
            throw new ZeroDivisionException("Zero division encountered: " + a + " / " + b);
        }
        return a / b;
    }

    @Override
    public Long negate(Long a) {
        return -a;
    }

    @Override
    public Long parse(int c) {
        return (long)c;
    }

    @Override
    public Long parse(String variable) {
        return Long.valueOf(variable);
    }
}
