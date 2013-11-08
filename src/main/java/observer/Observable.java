package observer;

import model.board.Board;

public interface Observable {
    public void addObserver(Observer obs);
    public void removeObserver();
    public void notifyObserver(Board board);
}
