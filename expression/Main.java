package expression;

public class Main {
    public static void main(String[] args) {
        //System.out.println(new Variable("x").equals(new Variable("x")));
       // System.err.println(args[0]);
        System.out.println(new Multiply(
                new Subtract(
                        new Variable("x"),
                        new Const(1)),
                new Subtract(
                        new Variable("x"),
                        new Const(1)
                )).evaluate(Integer.parseInt(args[0])));
    }
}
