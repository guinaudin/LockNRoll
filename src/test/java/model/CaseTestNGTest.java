/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.board.Board;
import model.dice.Dice;
import model.dice.DiceTypes;
import model.dice.GameDice;
import model.player.Player;
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
    public void setUpClass() {
        board = new Board();
        diceBoard = new Dice[Constant.SIZE][Constant.SIZE];
    }
    
    @AfterClass
    void tearDownClass()
    {
        board = null;
        caseTest = null;
        for(int i = 0; i < Constant.SIZE; i++)
            for(int j = 0; j < Constant.SIZE; j++)
                diceBoard[i][j] = null;
    }
    
    /**################################################################
    #                     ClearAllLineCombination                     #
    ################################################################**/
    
    @DataProvider(name = "ClearAllLineCombinations")
    public Object[][] createClearAllLineCombinations() {
        return new Object[][] {
        { 
            new GameDice[][] {
                {new GameDice(1, DiceTypes.Color.BLUE.getInt()),
                new GameDice(1, DiceTypes.Color.BLUE.getInt()),
                new GameDice(1, DiceTypes.Color.BLUE.getInt()),
                new GameDice(1, DiceTypes.Color.BLUE.getInt())},

                {new GameDice(1, DiceTypes.Color.BLUE.getInt()),
                new GameDice(2, DiceTypes.Color.BLUE.getInt()),
                new GameDice(3, DiceTypes.Color.BLUE.getInt()),
                new GameDice(4, DiceTypes.Color.BLUE.getInt())},

                {new GameDice(1, DiceTypes.Color.BLUE.getInt()),
                new GameDice(1, DiceTypes.Color.GREEN.getInt()),
                new GameDice(1, DiceTypes.Color.RED.getInt()),
                new GameDice(1, DiceTypes.Color.YELLOW.getInt())},

                {new GameDice(1, DiceTypes.Color.BLUE.getInt()),
                new GameDice(2, DiceTypes.Color.GREEN.getInt()),
                new GameDice(3, DiceTypes.Color.RED.getInt()),
                new GameDice(4, DiceTypes.Color.YELLOW.getInt())},
            }
        },   
        };
    }   
    
    @Test(dataProvider = "ClearAllLineCombinations")
    void testClearAllLineCombinations(Dice[][] diceBoard) {
        board.setDiceBoard(diceBoard);
        caseTest = new CaseTest(board, new Player());
        
        System.out.println("@Test - Clear : All Lines\nO = Free place\nX = Dice\n");
        this.printClearedBoard(caseTest.findLineCombinations());
        System.out.println();
    }
    
    /**################################################################
    #                     ClearAllColumnCombination                   #
    ################################################################**/
    
    @DataProvider(name = "ClearAllColumnCombinations")
    public Object[][] createClearAllColumnCombinations() {
        return new Object[][] {
        { 
            new GameDice[][] {
                {new GameDice(1, DiceTypes.Color.BLUE.getInt()),
                new GameDice(1, DiceTypes.Color.BLUE.getInt()),
                new GameDice(1, DiceTypes.Color.BLUE.getInt()),
                new GameDice(1, DiceTypes.Color.BLUE.getInt())},

                {new GameDice(1, DiceTypes.Color.BLUE.getInt()),
                new GameDice(2, DiceTypes.Color.BLUE.getInt()),
                new GameDice(1, DiceTypes.Color.GREEN.getInt()),
                new GameDice(2, DiceTypes.Color.GREEN.getInt())},

                {new GameDice(1, DiceTypes.Color.BLUE.getInt()),
                new GameDice(3, DiceTypes.Color.BLUE.getInt()),
                new GameDice(1, DiceTypes.Color.RED.getInt()),
                new GameDice(3, DiceTypes.Color.RED.getInt())},

                {new GameDice(1, DiceTypes.Color.BLUE.getInt()),
                new GameDice(4, DiceTypes.Color.BLUE.getInt()),
                new GameDice(1, DiceTypes.Color.YELLOW.getInt()),
                new GameDice(4, DiceTypes.Color.YELLOW.getInt())},
            }
        },   
        };
    }   
    
    @Test(dataProvider = "ClearAllColumnCombinations")
    void testClearAllColumnCombinations(Dice[][] diceBoard) {
        board.setDiceBoard(diceBoard);
        caseTest = new CaseTest(board, new Player());
        
        System.out.println("@Test - Clear : All Columns\nO = Free place\nX = Dice\n");
        this.printClearedBoard(caseTest.findColumnCombinations());
        System.out.println();
    }
    
    /**################################################################
    #                      ClearAllDiagonalCombination                #
    ################################################################**/
    
    @DataProvider(name = "ClearAllDiagonalCombinations")
    public Object[][] createClearAllDiagonalCombinations() {
        return new Object[][] {
        { 
            new GameDice[][] {
                {new GameDice(1, DiceTypes.Color.BLUE.getInt()),
                new GameDice(1, DiceTypes.Color.BLUE.getInt()),
                new GameDice(1, DiceTypes.Color.BLUE.getInt()),
                new GameDice(1, DiceTypes.Color.BLUE.getInt())},

                {new GameDice(1, DiceTypes.Color.BLUE.getInt()),
                new GameDice(1, DiceTypes.Color.BLUE.getInt()),
                new GameDice(2, DiceTypes.Color.GREEN.getInt()),
                new GameDice(2, DiceTypes.Color.GREEN.getInt())},

                {new GameDice(1, DiceTypes.Color.BLUE.getInt()),
                new GameDice(3, DiceTypes.Color.RED.getInt()),
                new GameDice(1, DiceTypes.Color.BLUE.getInt()),
                new GameDice(3, DiceTypes.Color.RED.getInt())},

                {new GameDice(4, DiceTypes.Color.YELLOW.getInt()),
                new GameDice(4, DiceTypes.Color.BLUE.getInt()),
                new GameDice(1, DiceTypes.Color.YELLOW.getInt()),
                new GameDice(1, DiceTypes.Color.BLUE.getInt())},
            }
        },   
        };
    }   
    
    @Test(dataProvider = "ClearAllDiagonalCombinations")
    void testClearAllDiagonalCombinations(Dice[][] diceBoard) {
        board.setDiceBoard(diceBoard);
        caseTest = new CaseTest(board, new Player());
        
        System.out.println("@Test - Clear : All Diagonals\nO = Free place\nX = Dice\n");
        this.printClearedBoard(caseTest.findDiagonalCombinations());
        System.out.println();
    }
    
    /**################################################################
    #                         ClearCornerCombination                  #
    ################################################################**/
    
    @DataProvider(name = "ClearAllCornerCombinations")
    public Object[][] createClearAllCornerCombinations() {
        return new Object[][] {
        { 
            new GameDice[][] {
                {new GameDice(1, DiceTypes.Color.BLUE.getInt()),
                new GameDice(1, DiceTypes.Color.RED.getInt()),
                new GameDice(3, DiceTypes.Color.BLUE.getInt()),
                new GameDice(1, DiceTypes.Color.BLUE.getInt())},

                {new GameDice(1, DiceTypes.Color.BLUE.getInt()),
                new GameDice(1, DiceTypes.Color.BLUE.getInt()),
                new GameDice(2, DiceTypes.Color.GREEN.getInt()),
                new GameDice(4, DiceTypes.Color.BLUE.getInt())},

                {new GameDice(1, DiceTypes.Color.BLUE.getInt()),
                new GameDice(3, DiceTypes.Color.RED.getInt()),
                new GameDice(2, DiceTypes.Color.BLUE.getInt()),
                new GameDice(3, DiceTypes.Color.RED.getInt())},

                {new GameDice(1, DiceTypes.Color.BLUE.getInt()),
                new GameDice(4, DiceTypes.Color.BLUE.getInt()),
                new GameDice(1, DiceTypes.Color.YELLOW.getInt()),
                new GameDice(1, DiceTypes.Color.BLUE.getInt())},
            }
        },   
        };
    }   
    
    @Test(dataProvider = "ClearAllCornerCombinations")
    void testClearAllCornerCombinations(Dice[][] diceBoard) {
        board.setDiceBoard(diceBoard);
        caseTest = new CaseTest(board, new Player());
        
        System.out.println("@Test - Clear : All Corners\nO = Free place\nX = Dice\n");
        this.printClearedBoard(caseTest.findCornerCombinations());
        System.out.println();
    }
    
    /**################################################################
    #                       ClearALlCubeCombination                   #
    ################################################################**/
    
    @DataProvider(name = "ClearAllCubeCombinations")
    public Object[][] createClearAllCubeCombinations() {
        return new Object[][] {
        { 
            new GameDice[][] {
                {new GameDice(1, DiceTypes.Color.BLUE.getInt()),
                new GameDice(1, DiceTypes.Color.RED.getInt()),
                new GameDice(1, DiceTypes.Color.GREEN.getInt()),
                new GameDice(1, DiceTypes.Color.RED.getInt())},

                {new GameDice(1, DiceTypes.Color.GREEN.getInt()),
                new GameDice(1, DiceTypes.Color.YELLOW.getInt()),
                new GameDice(1, DiceTypes.Color.BLUE.getInt()),
                new GameDice(1, DiceTypes.Color.YELLOW.getInt())},

                {new GameDice(1, DiceTypes.Color.BLUE.getInt()),
                new GameDice(1, DiceTypes.Color.RED.getInt()),
                new GameDice(1, DiceTypes.Color.GREEN.getInt()),
                new GameDice(1, DiceTypes.Color.RED.getInt())},

                {new GameDice(1, DiceTypes.Color.GREEN.getInt()),
                new GameDice(1, DiceTypes.Color.YELLOW.getInt()),
                new GameDice(1, DiceTypes.Color.BLUE.getInt()),
                new GameDice(1, DiceTypes.Color.YELLOW.getInt())},
            }
        },   
        };
    }   
    
    @Test(dataProvider = "ClearAllCubeCombinations")
    void testClearAllCubeCombinations(Dice[][] diceBoard) {
        board.setDiceBoard(diceBoard);
        caseTest = new CaseTest(board, new Player());
        
        System.out.println("@Test - Clear : All Cubes\nO = Free place\nX = Dice\n");
        this.printClearedBoard(caseTest.findCubeCombinations());
        System.out.println();
    }
    
    /**################################################################
    #                       ClearMiddleCubeCombination                   #
    ################################################################**/
    
    @DataProvider(name = "ClearMiddleCubeCombinations")
    public Object[][] createClearMiddleCubeCombinations() {
        return new Object[][] {
        { 
            new GameDice[][] {
                {new GameDice(3, DiceTypes.Color.BLUE.getInt()),
                new GameDice(1, DiceTypes.Color.BLUE.getInt()),
                new GameDice(1, DiceTypes.Color.GREEN.getInt()),
                new GameDice(2, DiceTypes.Color.YELLOW.getInt())},

                {new GameDice(2, DiceTypes.Color.YELLOW.getInt()),
                new GameDice(1, DiceTypes.Color.YELLOW.getInt()),
                new GameDice(1, DiceTypes.Color.BLUE.getInt()),
                new GameDice(2, DiceTypes.Color.YELLOW.getInt())},

                {new GameDice(1, DiceTypes.Color.BLUE.getInt()),
                new GameDice(1, DiceTypes.Color.RED.getInt()),
                new GameDice(1, DiceTypes.Color.GREEN.getInt()),
                new GameDice(2, DiceTypes.Color.BLUE.getInt())},

                {new GameDice(1, DiceTypes.Color.GREEN.getInt()),
                new GameDice(3, DiceTypes.Color.YELLOW.getInt()),
                new GameDice(1, DiceTypes.Color.YELLOW.getInt()),
                new GameDice(4, DiceTypes.Color.YELLOW.getInt())},
            }
        },   
        };
    } 
    
    @Test(dataProvider = "ClearMiddleCubeCombinations")
    void testClearMiddleCubeCombinations(Dice[][] diceBoard) {
        board.setDiceBoard(diceBoard);
        caseTest = new CaseTest(board, new Player());
        
        System.out.println("@Test - Clear : Middle Cube\nO = Free place\nX = Dice\n");
        this.printClearedBoard(caseTest.findCubeCombinations());
        System.out.println();
    }
    
    /**################################################################
    #           ClearMiddleBottomRightUpperLeftCubeCombination        #
    ################################################################**/
    
    @DataProvider(name = "ClearMiddleBottomRightUpperLeftCubeCombinations")
    public Object[][] createClearMiddleBottomRightUpperLeftCubeCombinations() {
        return new Object[][] {
        { 
            new GameDice[][] {
                {new GameDice(3, DiceTypes.Color.BLUE.getInt()),
                new GameDice(1, DiceTypes.Color.BLUE.getInt()),
                new GameDice(1, DiceTypes.Color.GREEN.getInt()),
                new GameDice(2, DiceTypes.Color.YELLOW.getInt())},

                {new GameDice(2, DiceTypes.Color.BLUE.getInt()),
                new GameDice(4, DiceTypes.Color.BLUE.getInt()),
                new GameDice(2, DiceTypes.Color.YELLOW.getInt()),
                new GameDice(2, DiceTypes.Color.YELLOW.getInt())},

                {new GameDice(1, DiceTypes.Color.BLUE.getInt()),
                new GameDice(3, DiceTypes.Color.RED.getInt()),
                new GameDice(1, DiceTypes.Color.GREEN.getInt()),
                new GameDice(1, DiceTypes.Color.BLUE.getInt())},

                {new GameDice(1, DiceTypes.Color.GREEN.getInt()),
                new GameDice(3, DiceTypes.Color.YELLOW.getInt()),
                new GameDice(1, DiceTypes.Color.YELLOW.getInt()),
                new GameDice(1, DiceTypes.Color.RED.getInt())},
            }
        },   
        };
    }
    
    @Test(dataProvider = "ClearMiddleBottomRightUpperLeftCubeCombinations")
    void testClearMiddleBottomRightUpperLeftCubeCombinations(Dice[][] diceBoard) {
        board.setDiceBoard(diceBoard);
        caseTest = new CaseTest(board, new Player());
        
        System.out.println("@Test - Clear : Middle, Bottom Right, Upper Left Cubes\nO = Free place\nX = Dice\n");
        this.printClearedBoard(caseTest.findCubeCombinations());
        System.out.println();
    }
    
    /**################################################################
    #              ClearSecondLineSecondColumnCombination             #
    ################################################################**/
    
    @DataProvider(name = "ClearSecondLineSecondColumnCombinations")
    public Object[][] createClearSecondLineSecondColumnCombinations() {
        return new Object[][] {
        { 
            new GameDice[][] {
                {new GameDice(2, DiceTypes.Color.BLUE.getInt()),
                new GameDice(1, DiceTypes.Color.BLUE.getInt()),
                new GameDice(1, DiceTypes.Color.BLUE.getInt()),
                new GameDice(1, DiceTypes.Color.BLUE.getInt())},

                {new GameDice(2, DiceTypes.Color.BLUE.getInt()),
                new GameDice(1, DiceTypes.Color.GREEN.getInt()),
                new GameDice(3, DiceTypes.Color.RED.getInt()),
                new GameDice(4, DiceTypes.Color.YELLOW.getInt())},

                {new GameDice(1, DiceTypes.Color.BLUE.getInt()),
                new GameDice(1, DiceTypes.Color.RED.getInt()),
                new GameDice(1, DiceTypes.Color.BLUE.getInt()),
                new GameDice(3, DiceTypes.Color.RED.getInt())},

                {new GameDice(4, DiceTypes.Color.YELLOW.getInt()),
                new GameDice(1, DiceTypes.Color.YELLOW.getInt()),
                new GameDice(1, DiceTypes.Color.YELLOW.getInt()),
                new GameDice(1, DiceTypes.Color.BLUE.getInt())},
            }
        },   
        };
    }   
    
    @Test(dataProvider = "ClearSecondLineSecondColumnCombinations")
    void testClearSecondLineSecondColumnCombinations(Dice[][] diceBoard) {
        board.setDiceBoard(diceBoard);
        caseTest = new CaseTest(board, new Player());
        
        System.out.println("@Test - Clear : Second Line, Second Column\nO = Free place\nX = Dice\n");
        caseTest.findCombinations();
        this.printBoard();
        System.out.println();
    }
    
    /**################################################################
    #         ClearSecondLineCornerSecondDiagoalCombination           #
    ################################################################**/
    
    @DataProvider(name = "ClearSecondLineCornerSecondDiagoalCombinations")
    public Object[][] createClearSecondLineCornerSecondDiagoalCombinations() {
        return new Object[][] {
        { 
            new GameDice[][] {
                {new GameDice(2, DiceTypes.Color.BLUE.getInt()),
                new GameDice(1, DiceTypes.Color.BLUE.getInt()),
                new GameDice(1, DiceTypes.Color.BLUE.getInt()),
                new GameDice(2, DiceTypes.Color.BLUE.getInt())},

                {new GameDice(3, DiceTypes.Color.RED.getInt()),
                new GameDice(1, DiceTypes.Color.GREEN.getInt()),
                new GameDice(2, DiceTypes.Color.BLUE.getInt()),
                new GameDice(4, DiceTypes.Color.YELLOW.getInt())},

                {new GameDice(1, DiceTypes.Color.BLUE.getInt()),
                new GameDice(2, DiceTypes.Color.BLUE.getInt()),
                new GameDice(1, DiceTypes.Color.BLUE.getInt()),
                new GameDice(3, DiceTypes.Color.RED.getInt())},

                {new GameDice(2, DiceTypes.Color.BLUE.getInt()),
                new GameDice(1, DiceTypes.Color.YELLOW.getInt()),
                new GameDice(1, DiceTypes.Color.YELLOW.getInt()),
                new GameDice(2, DiceTypes.Color.BLUE.getInt())},
            }
        },   
        };
    }   
    
    @Test(dataProvider = "ClearSecondLineCornerSecondDiagoalCombinations")
    void testClearSecondLineCornerSecondDiagoalCombinations(Dice[][] diceBoard) {
        board.setDiceBoard(diceBoard);
        caseTest = new CaseTest(board, new Player());
        
        System.out.println("@Test - Clear : Second Line, Corners, Second Diagonal\nO = Free place\nX = Dice\n");
        caseTest.findCombinations();
        this.printBoard();
        System.out.println();
    }
    
    /**################################################################
    #       ClearRightMiddleCubeFirstDiagoalLastLineCombination       #
    ################################################################**/
    
    @DataProvider(name = "ClearRightMiddleCubeFirstDiagoalLastLineCombinations")
    public Object[][] createClearRightMiddleCubeFirstDiagoalLastLineCombinations() {
        return new Object[][] {
        { 
            new GameDice[][] {
                {new GameDice(1, DiceTypes.Color.GREEN.getInt()),
                new GameDice(1, DiceTypes.Color.BLUE.getInt()),
                new GameDice(1, DiceTypes.Color.BLUE.getInt()),
                new GameDice(2, DiceTypes.Color.BLUE.getInt())},

                {new GameDice(3, DiceTypes.Color.RED.getInt()),
                new GameDice(3, DiceTypes.Color.GREEN.getInt()),
                new GameDice(4, DiceTypes.Color.BLUE.getInt()),
                new GameDice(4, DiceTypes.Color.YELLOW.getInt())},

                {new GameDice(1, DiceTypes.Color.BLUE.getInt()),
                new GameDice(2, DiceTypes.Color.BLUE.getInt()),
                new GameDice(4, DiceTypes.Color.GREEN.getInt()),
                new GameDice(4, DiceTypes.Color.RED.getInt())},

                {new GameDice(2, DiceTypes.Color.BLUE.getInt()),
                new GameDice(2, DiceTypes.Color.YELLOW.getInt()),
                new GameDice(2, DiceTypes.Color.RED.getInt()),
                new GameDice(2, DiceTypes.Color.GREEN.getInt())},
            }
        },   
        };
    }   
    
    @Test(dataProvider = "ClearRightMiddleCubeFirstDiagoalLastLineCombinations")
    void testClearRightMiddleCubeFirstDiagoalLastLineCombinations(Dice[][] diceBoard) {
        board.setDiceBoard(diceBoard);
        caseTest = new CaseTest(board, new Player());
        
        System.out.println("@Test - Clear : Middle Right Cube, First Diagoal, Last Line\nO = Free place\nX = Dice\n");
        caseTest.findCombinations();
        this.printBoard();
        System.out.println();
    }

    private void printBoard() {
        diceBoard = board.getDiceBoard();
        
        for(int i = 0; i < Constant.SIZE; i++) {
            for(int j = 0; j < Constant.SIZE; j++) {
                if(diceBoard[i][j].getColor() == 0 && diceBoard[i][j].getValue() == 0)
                    System.out.print("O");
                else
                    System.out.print("X");
            }
            System.out.println("");
        }
    }
    
    private void printClearedBoard(Boolean[][] clearedBoard) {
        for(int i = 0; i < Constant.SIZE; i++) {
            for(int j = 0; j < Constant.SIZE; j++) {
                if(clearedBoard[i][j])
                    System.out.print("O");
                else
                    System.out.print("X");
            }
            System.out.println("");
        }
    }
}