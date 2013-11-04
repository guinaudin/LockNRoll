package model;

import model.board.Board;
import model.dice.Dice;

public class CaseTest {
    private Board board;
    private Dice[][] diceBoard;
    private Dice[] combination;
    private CombinationTest combinationTest;
    
    public CaseTest(Board board) {
        this.board = board;
        combinationTest = new CombinationTest();
        combination = new Dice[Constant.SIZE];
        
        for(int i = 0; i < Constant.SIZE; i++)
            combination[i] = null;
    }
    
    public void findCombination() {
        int j = 0;
        boolean result;
        
        diceBoard = board.getDiceBoard();
        
        //Test Lines
        for(int i = 0; i < Constant.SIZE; i++) {
            j = 0;
            do {
                if(diceBoard[i][j].getLocked() == true) {
                    combination[j] = diceBoard[i][j];
                }
                else
                    j = 4;
                if(combination.length == 4) {
                    //appel des combination Test
                    // Launch tests
                    result = combinationTest.testEachColor(combination);
                }
                j++;
            }while(j < 3);
        }
    }
}
