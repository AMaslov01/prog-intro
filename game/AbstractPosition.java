package game;

public abstract class AbstractPosition implements Position{
    protected Cell[][] cells;

    public AbstractPosition(Cell[][] cells) {
        this.cells = cells;
    }

    @Override
    public Cell[][] getCells() {
        return cells;
    }

    @Override
    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < cells.length
                && 0 <= move.getColumn() && move.getColumn() < cells[0].length
                && cells[move.getRow()][move.getColumn()] == Cell.E;
    }

    @Override
    public boolean isValidForDebut(Move move, int i, int m, int n){
        int horizontal = (- n % 2) + 2 * (i + 1);
        int vertical = (-m % 2) + 2 * (i + 1);
        int left = (n - horizontal) / 2;
        int right = left + horizontal - 1;
        int top = (m - vertical) / 2;
        int bottom = top + vertical - 1;
        int x = move.getRow();
        int y = move.getColumn();
        return x >= left && x <= right && y >= top && y <= bottom;
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    @Override
    public int checkLength(int num){
        int cnt = 0;
        if(num == 0){
            return 1;
        }
        while(num > 0){
            num = num / 10;
            cnt ++;
        }
        return cnt;
    }
}
