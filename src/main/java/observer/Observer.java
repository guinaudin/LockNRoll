package observer;

import model.board.Board;
import model.player.Player;

public interface Observer {
    public void updateBoardDice(Board board);
    public void updateRolledDice(Board board);
    public void updateScore(Player player, Board board);
    public void updateBombJoker(Player player);
    public void updateCleanRollJoker(Player player);
}
