package expression;

import java.util.Map;
import java.util.Objects;

public abstract class AbstractBinaryOperation implements Expression, TripleExpression, ToMiniString, LongMapExpression {
    protected TripleExpression first;
    protected TripleExpression second;

    public AbstractBinaryOperation(TripleExpression first, TripleExpression second) {
        this.first = first;
        this.second = second;
    }

    abstract String getSymbol();
    protected abstract int getPriority();

    abstract int operate(int a, int b);
    abstract long operate(long a, long b);

    @Override
    public int evaluate(int x) {
        return operate(((Expression)first).evaluate(x), ((Expression)second).evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return operate(first.evaluate(x, y, z), second.evaluate(x, y, z));
    }

    @Override
    public long evaluateL(Map<String, Long> variables) {
        return operate(((LongMapExpression)first).evaluateL(variables), ((LongMapExpression)second).evaluateL(variables));
    }


    @Override
    public String toString() {
        return "(" + first.toString() + " " + getSymbol() + " " + second.toString() + ")";
    }

    @Override
    public boolean equals(Object expression) {
        if (this == expression) return true;
        if (expression == null || getClass() != expression.getClass()) {
            return false;
        }
        AbstractBinaryOperation other = (AbstractBinaryOperation) expression;
        return Objects.equals(first, other.first) &&
                Objects.equals(second, other.second) &&
                Objects.equals(getSymbol(), other.getSymbol());
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second, getSymbol());
    }

    @Override
    public String toMiniString() {
        String first = this.first.toMiniString();
        String second = this.second.toMiniString();
        if (this.first instanceof AbstractBinaryOperation && ((AbstractBinaryOperation) this.first).getPriority() < this.getPriority()) {
            first = "(" + first + ")";
        }
        if (this.getSymbol().equals("**") || this.getSymbol().equals("//")) {
            if (this.second instanceof AbstractBinaryOperation) {
                second = "(" + second + ")";
            }
        } else if (this.second instanceof AbstractBinaryOperation secondOperation) {
            if (secondOperation.getPriority() < this.getPriority()
                    || (secondOperation.getPriority() == this.getPriority()
                    && ((this.getSymbol().equals("-") || this.getSymbol().equals("/")
                    || (this.getSymbol().equals("*") && secondOperation.getSymbol().equals("/")))
                    || (this.getSymbol().equals("gcd") && secondOperation.getSymbol().equals("lcm")
                    || this.getSymbol().equals("lcm") && secondOperation.getSymbol().equals("gcd"))))) {
                second = "(" + second + ")";
            }
        }
        return first + " " + getSymbol() + " " + second;
    }


}
