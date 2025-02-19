package expression;

public class Tetra2  extends AbstractUnaryOperation implements TripleExpression, ToMiniString {

    public Tetra2(TripleExpression term) {
        super(term);
    }

    @Override
    public String toString() {
        return "²(" + term.toString() + ")";
    }

    @Override
    public String toMiniString() {
        if((term instanceof Const || term instanceof Variable  || term instanceof Tetra2 || term instanceof Negate)){
            return "²" + term.toMiniString();
        }
        return "²(" + term.toMiniString() + ")";
    }

    public int operate(int value) {
        int num = value;
        for(int i = 1 ; i < value; i ++){
            num *= value;
        }
        return num;
    }

}
