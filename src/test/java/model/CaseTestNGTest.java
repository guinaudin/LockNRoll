/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.board.Board;
import model.dice.Dice;
import model.dice.DiceTypes;
import model.dice.GameDice;
import org.testng.Assert;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CaseTestNGTest {
    private CaseTest caseTest;
    private Dice[][] diceBoard;
    private Board board;
    
    public CaseTestNGTest() {
    }

    @BeforeClass
    void setUpClass() {
        board = new Board();
        
        diceBoard[0][0].setColor(DiceTypes.Color.BLUE.getInt());
        diceBoard[0][0].setValue(1);
        diceBoard[0][0].setLocked(true);
        
        diceBoard[0][1].setColor(DiceTypes.Color.BLUE.getInt());
        diceBoard[0][1].setValue(1);
        diceBoard[0][1].setLocked(true);
        
        diceBoard[0][2].setColor(DiceTypes.Color.BLUE.getInt());
        diceBoard[0][2].setValue(1);
        diceBoard[0][2].setLocked(true);
        
        diceBoard[0][3].setColor(DiceTypes.Color.BLUE.getInt());
        diceBoard[0][3].setValue(1);
        diceBoard[0][3].setLocked(true);
        
        diceBoard[1][0].setColor(DiceTypes.Color.BLUE.getInt());
        diceBoard[1][0].setValue(1);
        diceBoard[1][0].setLocked(true);
        
        diceBoard[1][1].setColor(DiceTypes.Color.BLUE.getInt());
        diceBoard[1][1].setValue(1);
        diceBoard[1][1].setLocked(true);
        
        diceBoard[1][2].setColor(DiceTypes.Color.BLUE.getInt());
        diceBoard[1][2].setValue(1);
        diceBoard[1][2].setLocked(true);
        
        diceBoard[1][3].setColor(DiceTypes.Color.BLUE.getInt());
        diceBoard[1][3].setValue(1);
        diceBoard[1][3].setLocked(true);
        
        diceBoard[2][0].setColor(DiceTypes.Color.BLUE.getInt());
        diceBoard[2][0].setValue(1);
        diceBoard[2][0].setLocked(true);
        
        diceBoard[2][1].setColor(DiceTypes.Color.BLUE.getInt());
        diceBoard[2][1].setValue(1);
        diceBoard[2][1].setLocked(true);
        
        diceBoard[2][2].setColor(DiceTypes.Color.BLUE.getInt());
        diceBoard[2][2].setValue(1);
        diceBoard[2][2].setLocked(true);
        
        diceBoard[2][3].setColor(DiceTypes.Color.BLUE.getInt());
        diceBoard[2][3].setValue(1);
        diceBoard[2][3].setLocked(true);
        
        diceBoard[3][0].setColor(DiceTypes.Color.BLUE.getInt());
        diceBoard[3][0].setValue(1);
        diceBoard[3][0].setLocked(true);
        
        diceBoard[3][1].setColor(DiceTypes.Color.BLUE.getInt());
        diceBoard[3][1].setValue(1);
        diceBoard[3][1].setLocked(true);
        
        diceBoard[3][2].setColor(DiceTypes.Color.BLUE.getInt());
        diceBoard[3][2].setValue(1);
        diceBoard[3][2].setLocked(true);
       
        diceBoard[3][3].setColor(DiceTypes.Color.BLUE.getInt());
        diceBoard[3][3].setValue(1);
        diceBoard[3][3].setLocked(true);
        
        board.setDiceBoard(diceBoard);
    }

    @AfterClass
    void tearDownClass()
    {
        board = null;
        for(int i = 0; i < Constant.SIZE; i++)
            for(int j = 0; j < Constant.SIZE; j++)
                diceBoard[i][j] = null;
    }
   
    @Test
    void testFindCombination() {
        //Assert.assertEquals(CaseTest.findCombination(), true);
    }
}