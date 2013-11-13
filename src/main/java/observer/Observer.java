package observer;

import model.board.Board;

public interface Observer {
    public void updateBoardDice(Board board);
    public void updateRolledDice(Board board);
    public void updateScore(int score);
}
