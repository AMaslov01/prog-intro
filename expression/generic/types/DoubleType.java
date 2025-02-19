package expression.generic.types;

public class DoubleType implements Type<Double>{
    
    @Override
    public Double add(Double a, Double b) {
        return a + b;
    }

    @Override
    public Double subtract(Double a, Double b) {
        return a - b;
    }

    @Override
    public Double multiply(Double a, Double b) {
        return a * b;
    }

    @Override
    public Double divide(Double a, Double b) {
        return a / b;
    }

    @Override
    public Double negate(Double a) {
        return -a;
    }

    @Override
    public Double parse(int c) {
        return (double) c;
    }

    @Override
    public Double mod(Double a, Double b) {
        return a % b;
    }

    @Override
    public Double square(Double a) {
        return a * a;
    }

    @Override
    public Double abs(Double a) {
        if(a == -0){
            return 0.0;
        }
        return (a < 0) ? -a : a;
    }

    @Override
    public Double parse(String variable) {
        return Double.valueOf(variable);
    }

}
