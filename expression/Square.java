package expression;

public class Square  implements TripleExpression, ToMiniString {
    TripleExpression exp;
    public Square(TripleExpression exp) {
        this.exp = exp;
    }

    public String toString() {
        return "(" + exp.toString() + ")²";
    }
    public String toMiniString() {
        if((exp instanceof Const || exp instanceof Variable  || exp instanceof Square)){
            return exp.toMiniString() + "²";
        }
        return "(" + exp.toMiniString() + ")²";
    }
    @Override
    public int evaluate(int x, int y, int z) {
        return exp.evaluate(x, y, z) * exp.evaluate(x, y, z);
    }

}
