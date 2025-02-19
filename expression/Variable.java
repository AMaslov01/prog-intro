package expression;

import expression.exceptions.ParsingException;

import java.util.Map;
import java.util.Objects;

public class Variable implements Expression, TripleExpression, LongMapExpression{
    String variable;
    public Variable(String var) {
        this.variable = var;
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public String toString() {
        return variable;
    }

    @Override
    public String toMiniString(){
        return variable;
    }
    @Override
    public boolean equals(Object expression) {
        if (this == expression) return true;
        if (expression == null || getClass() != expression.getClass()) {
            return false;
        }
        Variable other = (Variable) expression;
        return Objects.equals(this.variable, other.variable);
    }

    @Override
    public int hashCode() {
        return this.variable.hashCode();
    }

    @Override
    public int evaluate(int x, int y, int z) throws ParsingException {
        return switch (variable.charAt(variable.length() - 1)) {
            case ('x') -> x;
            case ('y') -> y;
            case ('z') -> z;
            default -> throw new ParsingException("Unexpected variable name: " + variable);
        };
    }

    @Override
    public long evaluateL(Map<String, Long> variables) {
        if (!variables.containsKey(variable)) {
            throw new IllegalArgumentException("Unknown variable: " + variable);
        }
        return variables.get(variable);
    }
}
