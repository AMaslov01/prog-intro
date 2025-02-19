package game;

public class EmptyPlayer implements Player{
    int id;
    public EmptyPlayer() {
        this.id = -1;
    }


    @Override
    public Move move(Position position, Cell cell) {
        return null;
    }

    @Override
    public void setID(int id) {
        this.id = id;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public int getLosses() {
        return 0;
    }
}
