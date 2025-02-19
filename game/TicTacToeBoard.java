package game;

import java.util.Arrays;

public class TicTacToeBoard extends AbstractBoard implements Board{
    private BoardPosition boardPosition;

    @Override
    public Position returnPosition() {
        return boardPosition;
    }

    public TicTacToeBoard(int m, int n, int k) {
        super(m, n, k);
        this.boardPosition = new BoardPosition(new Cell[m][n]);
        for (Cell[] row : boardPosition.getCells()) {
            Arrays.fill(row, Cell.E);
        }
    }
}