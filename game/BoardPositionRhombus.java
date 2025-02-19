package game;

import java.util.Map;

public class BoardPositionRhombus extends AbstractPosition implements Position {
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.'
    );

    public BoardPositionRhombus(final Cell[][] cells) {
        super(cells);
    }

    @Override
    public String toString() {
        final int m = cells.length;
        final StringBuilder sb = new StringBuilder();
        final int max = checkLength(2 * m - 1);
        for (int r = 0; r < 2 * m - 1; r++) {
            sb.append(r).append(" ").append(" ".repeat(max - checkLength(r)));
            sb.append(" ".repeat(Math.abs(m - r - 1)));
            int row;
            if(r < m){
                row = r;
                for (int c = 0; c < calculateCells(r, m) + 1; c++) {
                    sb.append(SYMBOLS.get(this.getCells()[row][c]));
                    sb.append(" ");
                    row--;
                }
            } else{
                int col = r  + 1 - m;
                row = m - 1;
                for(int c = 0; c < calculateCells(r, m); c++) {
                    sb.append(SYMBOLS.get(this.getCells()[row][col]));
                    sb.append(" ");
                    col++;
                    row --;
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public Move convertRhombus(final Move move){
        int rowInSquare = 0;
        int colInSquare = 0;
        final Cell turn = move.getValue();
        while((rowInSquare + colInSquare) != move.getRow()){
            if(rowInSquare < cells.length - 1){
                rowInSquare ++;
            } else{
                colInSquare ++;
            }
        }
        for(int i = 1; i <= move.getColumn(); i++){
            rowInSquare--;
            colInSquare++;
        }
        return new Move(rowInSquare, colInSquare, turn);
    }

    private int calculateCells(int r, final int m){
        if(r / m >= 1){
            final int cnt = r;
            r = m;
            for(int i = 0; i <= cnt - m; i ++){
                r --;
            }
        }
        return r;
    }
}
