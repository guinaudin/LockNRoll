package view;

import model.board.Board;
import model.dice.Die;
import model.dice.DieTypes;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MainViewNGTest {
    private Die[][] diceBoard;
    private Board board;
    private MainView mainView;
    
    public MainViewNGTest() {
        board = new Board();
    }
    
    @BeforeClass
    void setUpClass() {
        diceBoard = new Die[][] {
            {new Die(0, 0, false),
            new Die(1, DieTypes.Color.BLUE.getInt(), true),
            new Die(1, DieTypes.Color.BLUE.getInt(), true),
            new Die(1, DieTypes.Color.BLUE.getInt(), true)},

            {new Die(1, DieTypes.Color.BLUE.getInt(), true),
            new Die(2, DieTypes.Color.BLUE.getInt(), true),
            new Die(3, DieTypes.Color.BLUE.getInt(), true),
            new Die(4, DieTypes.Color.BLUE.getInt(), true)},

            {new Die(1, DieTypes.Color.BLUE.getInt(), true),
            new Die(1, DieTypes.Color.GREEN.getInt(), true),
            new Die(1, DieTypes.Color.RED.getInt(), true),
            new Die(1, DieTypes.Color.YELLOW.getInt(), true)},

            {new Die(1, DieTypes.Color.BLUE.getInt(), true),
            new Die(2, DieTypes.Color.GREEN.getInt(), true),
            new Die(3, DieTypes.Color.RED.getInt(), true),
            new Die(4, DieTypes.Color.YELLOW.getInt(), true)}
        };
        
        board.setDiceBoard(diceBoard);
    }

    @AfterClass
    void tearDownClass() {
        mainView = null;
        board = null;
        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 4; j++)
                diceBoard[i][j] = null;
    }
    
    /**################################################################
    #                           MoveRolledDice                        #
    ################################################################**/

    @DataProvider(name = "MoveRolledDice")
    public Object[][] createMoveRolledDice() {
        return new Object[][] {
            {new Integer(1)},
            {new Integer(2)},
            {new Integer(3)},
            {new Integer(4)},
        };
    }
    
    @Test(dataProvider = "MoveRolledDice")
    void testCorrectSameColorSameNumberCombination(Die[] combination) {
        //mainView.moveRolledDie(Die selectedDie, int posX, int posY, int selectedPosX) {
        //Assert.assertEquals(CT.testSameColorSameNumber(combination), true);
    }
    
    @Test(dataProvider = "MoveRolledDice")
    void testIncorrectSameColorSameNumberCombination(Die[] combination) {
        //Assert.assertEquals(CT.testSameColorSameNumber(combination), false);
    }
}