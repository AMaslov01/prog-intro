package expression.generic.types;

import expression.exceptions.NegativeModulusException;
import expression.exceptions.ZeroDivisionException;

import java.math.BigInteger;

public class BigIntegerType implements Type<BigInteger>{

    @Override
    public BigInteger abs(BigInteger a) {
        return a.abs();
    }

    @Override
    public BigInteger mod(BigInteger a, BigInteger b) {
        if(b.signum() != 1){
            throw new NegativeModulusException("Negative modulus: " + a + " % " + b);
        }
        return a.mod(b);
    }

    @Override
    public BigInteger square(BigInteger a) {
        return a.multiply(a);
    }

    @Override
    public BigInteger add(BigInteger a, BigInteger b) {
        return a.add(b);
    }

    @Override
    public BigInteger subtract(BigInteger a, BigInteger b) {
        return a.subtract(b);
    }

    @Override
    public BigInteger multiply(BigInteger a, BigInteger b) {
        return a.multiply(b);
    }

    @Override
    public BigInteger divide(BigInteger a, BigInteger b) {
        if(b.compareTo(BigInteger.ZERO) == 0){
            throw new ZeroDivisionException("Zero division encountered: " + a + " / " + b);
        }
        return a.divide(b);
    }

    @Override
    public BigInteger negate(BigInteger a) {
        return a.negate();
    }

    @Override
    public BigInteger parse(int c) {
        return BigInteger.valueOf(c);
    }

    @Override
    public BigInteger parse(String variable) {
        return new BigInteger(variable);
    }

}
