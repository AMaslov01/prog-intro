package expression.generic.operations;

import expression.exceptions.ParsingException;
import expression.generic.MyExpression;

public class Variable<T> implements MyExpression<T> {
    // :NOTE: access
    String name;
    public Variable(final String var) {
        this.name = var;
    }

    @Override
    public T evaluate(final T x, final T y, final T z) {
        return switch (name){
            case ("x") -> x;
            case ("y") -> y;
            case ("z") -> z;
            default ->  throw new ParsingException("Unexpected variable name: " + name);
        };
    }
}
