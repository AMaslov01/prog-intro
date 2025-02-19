package game;

import java.util.Random;

public class RandomPlayer implements Player {
    int losses;
    private final Random random;
    int id;

    public RandomPlayer(final Random random) {
        this.random = random;
    }

    public RandomPlayer() {
        this(new Random());
        this.id = -1;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public void setID(int id) {
        this.id = id;
    }


    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
            int r = random.nextInt(position.getCells().length);
            int c = random.nextInt(position.getCells()[0].length);
            final Move move = new Move(r, c, cell);
            return move;
        }
    }

    @Override
    public int getLosses() {
        return losses;
    }
}
