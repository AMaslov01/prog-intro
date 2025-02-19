package expression.generic.types;

import expression.exceptions.ZeroDivisionException;

public class IntegerType implements Type<Integer>{

    @Override
    public Integer add(Integer a, Integer b) {
        return a + b;
    }

    @Override
    public Integer subtract(Integer a, Integer b) {
        return a - b;
    }

    @Override
    public Integer multiply(Integer a, Integer b) {
        return a * b;
    }

    @Override
    public Integer mod(Integer a, Integer b) {
        if(b == 0){
            throw new ZeroDivisionException("Zero Division while finding modulus: " + a + " % " + b);
        }
        return a % b;
    }

    @Override
    public Integer square(Integer a) {
        return a * a;
    }

    @Override
    public Integer abs(Integer a) {
        return (a < 0) ? -a : a;
    }

    @Override
    public Integer divide(Integer a, Integer b) {
        if(b == 0){
            throw new ZeroDivisionException("Zero division encountered: " + a + " / " + b);
        }
        return a / b;
    }

    @Override
    public Integer negate(Integer a) {
        return -a;
    }

    @Override
    public Integer parse(int c) {
        return c;
    }

    @Override
    public Integer parse(String variable) {
        return Integer.valueOf(variable);
    }
}
