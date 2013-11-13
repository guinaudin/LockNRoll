package observer;

import model.board.Board;

public interface Observable {
    public void addObserver(Observer obs);
    public void removeObserver();
    public void notifyBoardObserver(Board board);
    public void notifyScoreObserver(int score);
}
