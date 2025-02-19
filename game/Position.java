package game;

public interface Position {
    boolean isValid(Move move);
    Cell getCell(int r, int c);
    Cell [][] getCells();
    boolean isValidForDebut(Move move, int i, int m, int n);
    int checkLength(int num);
}
