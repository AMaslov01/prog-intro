package expression.generic.operations;

import expression.generic.MyExpression;
import expression.generic.types.*;

public abstract class AbstractBinaryOperation<T> implements MyExpression<T> {
    protected MyExpression<T> first;
    protected MyExpression<T> second;
    protected Type<T> type;

    public AbstractBinaryOperation(MyExpression<T> first, MyExpression<T> second, Type<T> type) {
        this.first = first;
        this.second = second;
        this.type = type;
    }
    abstract T operate(T x, T y);

    @Override
    public T evaluate(T x, T y, T z) {
        return operate(first.evaluate(x, y, z), second.evaluate(x, y, z));
    }
}
