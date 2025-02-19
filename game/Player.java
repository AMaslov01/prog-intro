package game;

public interface Player {
    Move move(Position position, Cell cell);
    int getLosses();
    void setID(int id);
    int getID();
}
