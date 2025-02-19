package expression.generic.operations;

import expression.generic.MyExpression;
import expression.generic.types.*;

public abstract class AbstractUnaryOperation<T> implements MyExpression<T> {
    protected MyExpression<T> term;
    protected Type<T> type;

    public AbstractUnaryOperation(MyExpression<T> term, Type<T> type) {
        this.term = term;
        this.type = type;
    }

    abstract T operate(T x);

    @Override
    public T evaluate(T x, T y, T z) {
        return operate(term.evaluate(x, y, z));
    }
}
