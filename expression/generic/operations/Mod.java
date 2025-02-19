package expression.generic.operations;

import expression.generic.MyExpression;
import expression.generic.types.*;

public class Mod<T> extends AbstractBinaryOperation<T> implements MyExpression<T> {

    public Mod(MyExpression<T> first, MyExpression<T> second, Type<T> type) {
        super(first, second, type);
    }

    @Override
    protected T operate(T a, T b) {
        return type.mod(a, b);
    }
}
