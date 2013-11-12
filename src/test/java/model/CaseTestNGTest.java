/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.board.Board;
import model.dice.Die;
import model.dice.DieTypes;
import model.player.Player;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CaseTestNGTest {
    private CaseTest caseTest;
    private Die[][] diceBoard;
    private Board board;
    
    public CaseTestNGTest() {
    }
    
    @BeforeClass
    public void setUpClass() {
        board = new Board();
        diceBoard = new Die[4][4];
    }
    
    @AfterClass
    void tearDownClass()
    {
        board = null;
        caseTest = null;
        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 4; j++)
                diceBoard[i][j] = null;
    }
    
    /**################################################################
    #                     ClearAllLineCombination                     #
    ################################################################**/
    
    @DataProvider(name = "ClearAllLineCombinations")
    public Object[][] createClearAllLineCombinations() {
        return new Object[][] {
        { 
            new Die[][] {
                {new Die(1, DieTypes.Color.BLUE.getInt(), true),
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
                new Die(4, DieTypes.Color.YELLOW.getInt(), true)},
            }
        },   
        };
    }   
    
    @Test(dataProvider = "ClearAllLineCombinations")
    void testClearAllLineCombinations(Die[][] diceBoard) {
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
            new Die[][] {
                {new Die(1, DieTypes.Color.BLUE.getInt(), true),
                new Die(1, DieTypes.Color.BLUE.getInt(), true),
                new Die(1, DieTypes.Color.BLUE.getInt(), true),
                new Die(1, DieTypes.Color.BLUE.getInt(), true)},

                {new Die(1, DieTypes.Color.BLUE.getInt(), true),
                new Die(2, DieTypes.Color.BLUE.getInt(), true),
                new Die(1, DieTypes.Color.GREEN.getInt(), true),
                new Die(2, DieTypes.Color.GREEN.getInt(), true)},

                {new Die(1, DieTypes.Color.BLUE.getInt(), true),
                new Die(3, DieTypes.Color.BLUE.getInt(), true),
                new Die(1, DieTypes.Color.RED.getInt(), true),
                new Die(3, DieTypes.Color.RED.getInt(), true)},

                {new Die(1, DieTypes.Color.BLUE.getInt(), true),
                new Die(4, DieTypes.Color.BLUE.getInt(), true),
                new Die(1, DieTypes.Color.YELLOW.getInt(), true),
                new Die(4, DieTypes.Color.YELLOW.getInt(), true)},
            }
        },   
        };
    }   
    
    @Test(dataProvider = "ClearAllColumnCombinations")
    void testClearAllColumnCombinations(Die[][] diceBoard) {
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
            new Die[][] {
                {new Die(1, DieTypes.Color.BLUE.getInt(), true),
                new Die(1, DieTypes.Color.BLUE.getInt(), true),
                new Die(1, DieTypes.Color.BLUE.getInt(), true),
                new Die(1, DieTypes.Color.BLUE.getInt(), true)},

                {new Die(1, DieTypes.Color.BLUE.getInt(), true),
                new Die(1, DieTypes.Color.BLUE.getInt(), true),
                new Die(2, DieTypes.Color.GREEN.getInt(), true),
                new Die(2, DieTypes.Color.GREEN.getInt(), true)},

                {new Die(1, DieTypes.Color.BLUE.getInt(), true),
                new Die(3, DieTypes.Color.RED.getInt(), true),
                new Die(1, DieTypes.Color.BLUE.getInt(), true),
                new Die(3, DieTypes.Color.RED.getInt(), true)},

                {new Die(4, DieTypes.Color.YELLOW.getInt(), true),
                new Die(4, DieTypes.Color.BLUE.getInt(), true),
                new Die(1, DieTypes.Color.YELLOW.getInt(), true),
                new Die(1, DieTypes.Color.BLUE.getInt(), true)},
            }
        },   
        };
    }   
    
    @Test(dataProvider = "ClearAllDiagonalCombinations")
    void testClearAllDiagonalCombinations(Die[][] diceBoard) {
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
            new Die[][] {
                {new Die(1, DieTypes.Color.BLUE.getInt(), true),
                new Die(1, DieTypes.Color.RED.getInt(), true),
                new Die(3, DieTypes.Color.BLUE.getInt(), true),
                new Die(1, DieTypes.Color.BLUE.getInt(), true)},

                {new Die(1, DieTypes.Color.BLUE.getInt(), true),
                new Die(1, DieTypes.Color.BLUE.getInt(), true),
                new Die(2, DieTypes.Color.GREEN.getInt(), true),
                new Die(4, DieTypes.Color.BLUE.getInt(), true)},

                {new Die(1, DieTypes.Color.BLUE.getInt(), true),
                new Die(3, DieTypes.Color.RED.getInt(), true),
                new Die(2, DieTypes.Color.BLUE.getInt(), true),
                new Die(3, DieTypes.Color.RED.getInt(), true)},

                {new Die(1, DieTypes.Color.BLUE.getInt(), true),
                new Die(4, DieTypes.Color.BLUE.getInt(), true),
                new Die(1, DieTypes.Color.YELLOW.getInt(), true),
                new Die(1, DieTypes.Color.BLUE.getInt(), true)},
            }
        },   
        };
    }   
    
    @Test(dataProvider = "ClearAllCornerCombinations")
    void testClearAllCornerCombinations(Die[][] diceBoard) {
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
            new Die[][] {
                {new Die(1, DieTypes.Color.BLUE.getInt(), true),
                new Die(1, DieTypes.Color.RED.getInt(), true),
                new Die(1, DieTypes.Color.GREEN.getInt(), true),
                new Die(1, DieTypes.Color.RED.getInt(), true)},

                {new Die(1, DieTypes.Color.GREEN.getInt(), true),
                new Die(1, DieTypes.Color.YELLOW.getInt(), true),
                new Die(1, DieTypes.Color.BLUE.getInt(), true),
                new Die(1, DieTypes.Color.YELLOW.getInt(), true)},

                {new Die(1, DieTypes.Color.BLUE.getInt(), true),
                new Die(1, DieTypes.Color.RED.getInt(), true),
                new Die(1, DieTypes.Color.GREEN.getInt(), true),
                new Die(1, DieTypes.Color.RED.getInt(), true)},

                {new Die(1, DieTypes.Color.GREEN.getInt(), true),
                new Die(1, DieTypes.Color.YELLOW.getInt(), true),
                new Die(1, DieTypes.Color.BLUE.getInt(), true),
                new Die(1, DieTypes.Color.YELLOW.getInt(), true)},
            }
        },   
        };
    }   
    
    @Test(dataProvider = "ClearAllCubeCombinations")
    void testClearAllCubeCombinations(Die[][] diceBoard) {
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
            new Die[][] {
                {new Die(3, DieTypes.Color.BLUE.getInt(), true),
                new Die(1, DieTypes.Color.BLUE.getInt(), true),
                new Die(1, DieTypes.Color.GREEN.getInt(), true),
                new Die(2, DieTypes.Color.YELLOW.getInt(), true)},

                {new Die(2, DieTypes.Color.YELLOW.getInt(), true),
                new Die(1, DieTypes.Color.YELLOW.getInt(), true),
                new Die(1, DieTypes.Color.BLUE.getInt(), true),
                new Die(2, DieTypes.Color.YELLOW.getInt(), true)},

                {new Die(1, DieTypes.Color.BLUE.getInt(), true),
                new Die(1, DieTypes.Color.RED.getInt(), true),
                new Die(1, DieTypes.Color.GREEN.getInt(), true),
                new Die(2, DieTypes.Color.BLUE.getInt(), true)},

                {new Die(1, DieTypes.Color.GREEN.getInt(), true),
                new Die(3, DieTypes.Color.YELLOW.getInt(), true),
                new Die(1, DieTypes.Color.YELLOW.getInt(), true),
                new Die(4, DieTypes.Color.YELLOW.getInt(), true)},
            }
        },   
        };
    } 
    
    @Test(dataProvider = "ClearMiddleCubeCombinations")
    void testClearMiddleCubeCombinations(Die[][] diceBoard) {
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
            new Die[][] {
                {new Die(3, DieTypes.Color.BLUE.getInt(), true),
                new Die(1, DieTypes.Color.BLUE.getInt(), true),
                new Die(1, DieTypes.Color.GREEN.getInt(), true),
                new Die(2, DieTypes.Color.YELLOW.getInt(), true)},

                {new Die(2, DieTypes.Color.BLUE.getInt(), true),
                new Die(4, DieTypes.Color.BLUE.getInt(), true),
                new Die(2, DieTypes.Color.YELLOW.getInt(), true),
                new Die(2, DieTypes.Color.YELLOW.getInt(), true)},

                {new Die(1, DieTypes.Color.BLUE.getInt(), true),
                new Die(3, DieTypes.Color.RED.getInt(), true),
                new Die(1, DieTypes.Color.GREEN.getInt(), true),
                new Die(1, DieTypes.Color.BLUE.getInt(), true)},

                {new Die(1, DieTypes.Color.GREEN.getInt(), true),
                new Die(3, DieTypes.Color.YELLOW.getInt(), true),
                new Die(1, DieTypes.Color.YELLOW.getInt(), true),
                new Die(1, DieTypes.Color.RED.getInt(), true)},
            }
        },   
        };
    }
    
    @Test(dataProvider = "ClearMiddleBottomRightUpperLeftCubeCombinations")
    void testClearMiddleBottomRightUpperLeftCubeCombinations(Die[][] diceBoard) {
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
            new Die[][] {
                {new Die(2, DieTypes.Color.BLUE.getInt(), true),
                new Die(1, DieTypes.Color.BLUE.getInt(), true),
                new Die(1, DieTypes.Color.BLUE.getInt(), true),
                new Die(1, DieTypes.Color.BLUE.getInt(), true)},

                {new Die(2, DieTypes.Color.BLUE.getInt(), true),
                new Die(1, DieTypes.Color.GREEN.getInt(), true),
                new Die(3, DieTypes.Color.RED.getInt(), true),
                new Die(4, DieTypes.Color.YELLOW.getInt(), true)},

                {new Die(1, DieTypes.Color.BLUE.getInt(), true),
                new Die(1, DieTypes.Color.RED.getInt(), true),
                new Die(1, DieTypes.Color.BLUE.getInt(), true),
                new Die(3, DieTypes.Color.RED.getInt(), true)},

                {new Die(4, DieTypes.Color.YELLOW.getInt(), true),
                new Die(1, DieTypes.Color.YELLOW.getInt(), true),
                new Die(1, DieTypes.Color.YELLOW.getInt(), true),
                new Die(1, DieTypes.Color.BLUE.getInt(), true)},
            }
        },   
        };
    }   
    
    @Test(dataProvider = "ClearSecondLineSecondColumnCombinations")
    void testClearSecondLineSecondColumnCombinations(Die[][] diceBoard) {
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
            new Die[][] {
                {new Die(2, DieTypes.Color.BLUE.getInt(), true),
                new Die(1, DieTypes.Color.BLUE.getInt(), true),
                new Die(1, DieTypes.Color.BLUE.getInt(), true),
                new Die(2, DieTypes.Color.BLUE.getInt(), true)},

                {new Die(3, DieTypes.Color.RED.getInt(), true),
                new Die(1, DieTypes.Color.GREEN.getInt(), true),
                new Die(2, DieTypes.Color.BLUE.getInt(), true),
                new Die(4, DieTypes.Color.YELLOW.getInt(), true)},

                {new Die(1, DieTypes.Color.BLUE.getInt(), true),
                new Die(2, DieTypes.Color.BLUE.getInt(), true),
                new Die(1, DieTypes.Color.BLUE.getInt(), true),
                new Die(3, DieTypes.Color.RED.getInt(), true)},

                {new Die(2, DieTypes.Color.BLUE.getInt(), true),
                new Die(1, DieTypes.Color.YELLOW.getInt(), true),
                new Die(1, DieTypes.Color.YELLOW.getInt(), true),
                new Die(2, DieTypes.Color.BLUE.getInt(), true)},
            }
        },   
        };
    }   
    
    @Test(dataProvider = "ClearSecondLineCornerSecondDiagoalCombinations")
    void testClearSecondLineCornerSecondDiagoalCombinations(Die[][] diceBoard) {
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
            new Die[][] {
                {new Die(1, DieTypes.Color.GREEN.getInt(), true),
                new Die(1, DieTypes.Color.BLUE.getInt(), true),
                new Die(1, DieTypes.Color.BLUE.getInt(), true),
                new Die(2, DieTypes.Color.BLUE.getInt(), true)},

                {new Die(3, DieTypes.Color.RED.getInt(), true),
                new Die(3, DieTypes.Color.GREEN.getInt(), true),
                new Die(4, DieTypes.Color.BLUE.getInt(), true),
                new Die(4, DieTypes.Color.YELLOW.getInt(), true)},

                {new Die(1, DieTypes.Color.BLUE.getInt(), true),
                new Die(2, DieTypes.Color.BLUE.getInt(), true),
                new Die(4, DieTypes.Color.GREEN.getInt(), true),
                new Die(4, DieTypes.Color.RED.getInt(), true)},

                {new Die(2, DieTypes.Color.BLUE.getInt(), true),
                new Die(2, DieTypes.Color.YELLOW.getInt(), true),
                new Die(2, DieTypes.Color.RED.getInt(), true),
                new Die(2, DieTypes.Color.GREEN.getInt(), true)},
            }
        },   
        };
    }   
    
    @Test(dataProvider = "ClearRightMiddleCubeFirstDiagoalLastLineCombinations")
    void testClearRightMiddleCubeFirstDiagoalLastLineCombinations(Die[][] diceBoard) {
        board.setDiceBoard(diceBoard);
        caseTest = new CaseTest(board, new Player());
        
        System.out.println("@Test - Clear : Middle Right Cube, First Diagoal, Last Line\nO = Free place\nX = Dice\n");
        caseTest.findCombinations();
        this.printBoard();
        System.out.println();
    }

    private void printBoard() {
        diceBoard = board.getDiceBoard();
        
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                if(diceBoard[i][j].getColor() == 0 && diceBoard[i][j].getValue() == 0)
                    System.out.print("O");
                else
                    System.out.print("X");
            }
            System.out.println("");
        }
    }
    
    private void printClearedBoard(Boolean[][] clearedBoard) {
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                if(clearedBoard[i][j])
                    System.out.print("O");
                else
                    System.out.print("X");
            }
            System.out.println("");
        }
    }
}