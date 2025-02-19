package expression.generic.operations;

import expression.generic.MyExpression;
import expression.generic.types.*;

public class Subtract<T> extends AbstractBinaryOperation<T> implements MyExpression<T> {

    public Subtract(MyExpression<T> first, MyExpression<T> second, Type<T> type) {
        super(first, second, type);
    }

    @Override
    protected T operate(T a, T b) {
        return type.subtract(a, b);
    }
}
