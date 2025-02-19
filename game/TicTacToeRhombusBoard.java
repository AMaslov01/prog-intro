package game;

import java.util.Arrays;

public class TicTacToeRhombusBoard extends AbstractBoard implements Board{
    // :NOTE: move to parent
    private final BoardPositionRhombus boardPositionRhombus;

    @Override
    public Position returnPosition() {
        return boardPositionRhombus;
    }

    public TicTacToeRhombusBoard(int m, int n, int k) {
        super(m, n, k);
        this.boardPositionRhombus = new BoardPositionRhombus(new Cell[m][n]);
        for (Cell[] row : boardPositionRhombus.getCells()) {
            Arrays.fill(row, Cell.E);
        }
    }
}
