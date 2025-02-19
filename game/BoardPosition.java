package game;

import java.util.Map;


public class BoardPosition extends AbstractPosition implements Position{
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.'
    );
    
    public BoardPosition(Cell[][] cells) {
        super(cells);
    }

    @Override
    public String toString() {
        int maxDecLength = checkLength(this.getCells().length);
        final StringBuilder sb = new StringBuilder(" ".repeat(maxDecLength));
        sb.append(" ");
        for (int i = 0; i < this.getCells()[0].length; i ++){
            sb.append(i);
            sb.append(" ".repeat(maxDecLength + 1 - checkLength(i)));
        }
        for (int r = 0; r < this.getCells().length; r++) {
            sb.append("\n");
            sb.append(r);
            sb.append(" ");
            sb.append(" ".repeat(maxDecLength - checkLength(r)));
            for (int c = 0; c < this.getCells()[0].length; c++) {
                sb.append(SYMBOLS.get(this.getCells()[r][c]));
                sb.append(" ".repeat(maxDecLength));
            }
        }
        return sb.toString();
    }
}
