package expression;

import java.util.Map;
import java.util.Objects;

public class Const implements Expression, TripleExpression, LongMapExpression, ToMiniString{
    protected long c;
    public Const(int c) {
        this.c = c;
    }

    public Const(long c) {
        this.c = c;
    }

    @Override
    public int evaluate(int x) {
        return (int)this.c;
    }

    @Override
    public String toString() {
        return Long.toString(this.c);
    }

    @Override
    public String toMiniString(){
        return Long.toString(this.c);
    }

    @Override
    public boolean equals(Object expression) {
        if (this == expression) return true;
        if (expression == null || getClass() != expression.getClass()) {
            return false;
        }
        Const other = (Const) expression;
        return this.c == other.c;
    }

    public long getC() {
        return c;
    }

    @Override
    public int hashCode() {
        return Objects.hash((int)this.c);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return (int)this.c;
    }

    @Override
    public long evaluateL(Map<String, Long> variables) {
        return c;
    }
}
