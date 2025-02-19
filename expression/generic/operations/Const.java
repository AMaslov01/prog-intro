package expression.generic.operations;

import expression.generic.MyExpression;


public class Const<T> implements MyExpression<T> {
    private final T c;
    public Const (T c) {
        this.c = c;
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return this.c;
    }
}
