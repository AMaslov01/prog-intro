package expression.generic;

public interface MyExpression<T>{
    T evaluate(T x, T y, T z);
}
