package observer;

import model.board.Board;
import model.player.Player;

public interface Observable {
    public void addObserver(Observer obs);
    public void removeObserver();
    public void notifyBoardObserver(Board board);
    public void notifyScoreObserver(Player player);
    public void notifyJokerObserver(Player player);
}
