package game;

public class SequentialPlayer implements Player {
    int losses;
    int id;
    public SequentialPlayer() {
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
    //    Board board = (Board) position;
        //board.makeMove();
        for (int r = 0; r < position.getCells().length; r++) {
            for (int c = 0; c < position.getCells()[0].length; c++) {
                final Move move = new Move(r, c, cell);
                if (position.isValid(move)) {
                    return move;
                }
            }
        }
        throw new IllegalStateException("No valid moves");
    }

    @Override
    public int getLosses() {
        return losses;
    }
}
