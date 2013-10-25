package model;

import model.board.Board;
import model.dice.Dice;

public class CaseTest {
    private Board board;
    private Dice[][] diceBoard;
    
    public CaseTest(Board board) {
        this.board = board;
        
        //Besoin d'init le board
        for(int i = 0; i < Constant.SIZE; i++) {
            for(int j = 0; j < Constant.SIZE; j++) {
                diceBoard[i][j] = null;
            }
        }
    }
}
