package expression.generic.operations;
import expression.generic.MyExpression;
import expression.generic.types.*;

public class Abs<T> extends AbstractUnaryOperation<T> implements MyExpression<T> {

    public Abs(MyExpression<T> term, Type<T> type) {
        super(term, type);
    }

    @Override
    T operate(T x) {
        return type.abs(x);
    }
}