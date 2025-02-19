package game;

public abstract class AbstractBoard implements Board {
    private int m;
    private int n;
    private int k;
    public abstract Position returnPosition();

    private Cell turn;
    private int noOfMoves;

    public AbstractBoard(int m, int n, int k) {
        this.m = m;
        this.n = n;
        this.k = k;
        turn = Cell.X;
    }

    @Override
    public Position getPosition() {
        // :NOTE: ??
        return this.returnPosition();
    }

    @Override
    public Cell getTurn() {
        return turn;
    }

    @Override
    public Result makeMove(final Move move) {
        noOfMoves ++;
        if (!returnPosition().isValid(move)){
            return Result.LOSE;
        }
        // debut rule
        if (!returnPosition().isValidForDebut(move, noOfMoves, m , n)){
            return Result.LOSE_BY_DEBUT;
        }

        returnPosition().getCells()[move.getRow()][move.getColumn()] = move.getValue();

        int inDiag1 = 1;
        int inDiag2 = 1;
        int inRow = 1;
        int inCol = 1;
        int row = move.getRow();
        int col = move.getColumn();
        for(int i = 1; i < k; i ++){
            if(checkValidity(row + i, col + i) && returnPosition().getCells()[row + i][col + i] == turn){
                inDiag1 ++;
            }
            if(checkValidity(row - i, col - i) && returnPosition().getCells()[row - i][col - i] == turn){
                inDiag1++;
            }
            if(checkValidity(row + i, col - i) && returnPosition().getCells()[row + i][col - i] == turn){
                inDiag2 ++;
            }
            if(checkValidity(row - i, col + i) && returnPosition().getCells()[row - i][col + i] == turn){
                inDiag2++;
            }
            if(checkValidity(row, col + i) && returnPosition().getCells()[row][col + i] == turn){
                inRow ++;
            }
            if(checkValidity(row, col - i) && returnPosition().getCells()[row][col - i] == turn){
                inRow++;
            }
            if(checkValidity(row + i, col) && returnPosition().getCells()[row + i][col] == turn){
                inCol ++;
            }
            if(checkValidity(row - i, col) && returnPosition().getCells()[row - i][col] == turn){
                inCol++;
            }
        }
        if (inDiag1 == k || inDiag2 == k || inRow == k || inCol == k) {
            return Result.WIN;
        }
        if (noOfMoves == m*n) {
            return Result.DRAW;
        }

        turn = turn == Cell.X ? Cell.O : Cell.X;
        return Result.UNKNOWN;
    }



    private boolean checkValidity(int row, int col){
        return (row >= 0 && row < m && col >= 0 && col < n);
    }
}
