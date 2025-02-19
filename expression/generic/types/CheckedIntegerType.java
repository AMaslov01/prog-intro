package expression.generic.types;

import expression.exceptions.NegativeModulusException;
import expression.exceptions.OverflowException;
import expression.exceptions.ZeroDivisionException;

public class CheckedIntegerType implements Type<Integer>{

    @Override
    public Integer add(Integer a, Integer b) {
        if(Integer.MAX_VALUE - a < b && a > 0 || a < 0 && b < Integer.MIN_VALUE - a){
            throw new OverflowException("Sum causes overflow: " + a + " + " + b);
        }
        return a + b;
    }

    @Override
    public Integer subtract(Integer a, Integer b) {
        if(a < Integer.MIN_VALUE + b && b > 0|| b < 0 && a > Integer.MAX_VALUE + b){
            throw new OverflowException("Subtract causes overflow: " + a + " - " + b);
        }
        return a - b;
    }

    @Override
    public Integer multiply(Integer a, Integer b) {
        if (a > 0 && b > 0 && a > Integer.MAX_VALUE / b ||
                a > 0 && b < 0 && b < Integer.MIN_VALUE / a ||
                a < 0 && b > 0 && a < Integer.MIN_VALUE / b ||
                a < 0 && b < 0 && a < Integer.MAX_VALUE / b) {
            throw new OverflowException("Multiplication causes overflow: " + a + " * " + b);
        }
        return a * b;
    }

    @Override
    public Integer mod(Integer a, Integer b) {
        if(b == 0){
            throw new NegativeModulusException("Negative modulus: " + a + " % " + b);
        }
        return a % b;
    }

    @Override
    public Integer square(Integer a) {
//        return multiply(a, a);
        if(46340 < a || a < -46340){
            throw new OverflowException("Overflow reached during Squaring: " + a + " * " + a);
        }
        return a * a;
    }

    @Override
    public Integer abs(Integer a) {
        if(a == Integer.MIN_VALUE){
            throw new OverflowException("Cannot abs MIN_VALUE: " + a);
        }
        return (a < 0) ? -a : a;
    }

    @Override
    public Integer divide(Integer a, Integer b) {
        if(a == Integer.MIN_VALUE && b == -1){
            throw new OverflowException("Division cause overflow: " + a + " / " + b);
        }
        if(b == 0){
            throw new ZeroDivisionException("Zero division encountered: " + a + " / " + b);
        }
        return a / b;
    }

    @Override
    public Integer negate(Integer a) {
        if(a == Integer.MIN_VALUE){
            throw new OverflowException("Cannot negate min value: " + super.toString());
        }
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
