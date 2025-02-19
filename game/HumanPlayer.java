package game;

import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class HumanPlayer implements Player {
    private final PrintStream out;
    private final Scanner in;

    // :NOTE: access
    int losses;
    int id;

    public HumanPlayer(final PrintStream out, final Scanner in) {
        this.out = out;
        this.in = in;
    }

    public HumanPlayer() {
        this(System.out, new Scanner(System.in));
    }

    @Override
    public void setID(int id) {
        this.id = id;
    }

    @Override
    public int getID() {
        return id;
    }


    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
            // :NOTE: show position
            //out.println("Position");
            out.println(cell + "'s move");
            out.println("Enter row and column");
            int x, y;
            try {
                x = in.nextInt();
                y = in.nextInt();
            } catch (NoSuchElementException ignored) {
                in.nextLine(); // :NOTE: oops
                System.out.println("Invalid input. Please use integers...");
                continue;
            }
            Move move = new Move(x, y, cell);
            // :NOTE: Board shape
            if(position instanceof BoardPositionRhombus){
                move = ((BoardPositionRhombus)position).convertRhombus(move);
            }
            if (position.isValid(move)) {
                return move;
            }
            out.println("Move is invalid");
        }
    }

    @Override
    public int getLosses() {
        return losses;
    }

}