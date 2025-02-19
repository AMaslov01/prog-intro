package game;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        System.out.println("Choose rhombus(r) or basic(b): ");
        final String ch = scanner.next();

        boolean isRhombus = false;
        switch (ch) {
            case "r":
                isRhombus = true;
                break;
            case "b":
                break;
            default:
                System.out.println("Wrong input!");
                return;
        }
        // :NOTE: class FIXED
        final GameDimensions dimensions = new GameDimensions(scanner);
        final int m = dimensions.getM();
        final int n = dimensions.getN();
        final int k = dimensions.getK();

        if (k > Math.max(m, n)) {
            System.out.println("Wrong input details: this game is unplayable");
            return;
        }
        if(isRhombus && m != n){
            System.out.println("Rhombus must have equal sides!");
            return;
        }
        // :NOTE: isRhombus FIXED
        final Game game = new Game(true, new HumanPlayer(), new HumanPlayer());
        final int result = isRhombus
                ? game.play(new TicTacToeRhombusBoard(m, n, k))
                : game.play(new TicTacToeBoard(m, n, k));
        System.out.println("Game result: " + result);
    }
}
