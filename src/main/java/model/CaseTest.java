package model;

import model.board.Board;
import model.dice.Die;
import model.player.Player;

public class CaseTest {
    private Board board;
    private Player player;
    private Die[][] diceBoard;
    private Die[] combination;
    private CombinationTest combinationTest;
    
    private Boolean[][] clearLines;
    private Boolean[][] clearColumns;
    private Boolean[][] clearDiagonals;
    private Boolean[][] clearCorners;
    private Boolean[][] clearCubes;
    private Boolean[][] clearLineDiceBoard;
    private Boolean[][] clearColumnDiceBoard;
    private Boolean[][] clearDiagonalDiceBoard;
    private Boolean[][] clearCornerDiceBoard;
    private Boolean[][] clearCubeDiceBoard;
    
    public CaseTest(Board board, Player player) {
        this.board = board;
        this.player = player;
        diceBoard = new Die[4][4];
        diceBoard = board.getDiceBoard();
        clearLines = new Boolean[4][4];
        clearColumns = new Boolean[4][4];
        clearDiagonals = new Boolean[4][4];
        clearCorners = new Boolean[4][4];
        clearCubes = new Boolean[4][4];
        combinationTest = new CombinationTest();
        combination = new Die[4];
        
        for(int i = 0; i < 4; i++)
            combination[i] = null;
    }
    
    public void findCombinations() {
        diceBoard = board.getDiceBoard();

        //Test Lines
        clearLines = this.findLineCombinations();
        //Test column
        clearColumns = this.findColumnCombinations();
        //Test diagonals
        clearDiagonals = this.findDiagonalCombinations();
        //Test corners
        clearCorners = this.findCornerCombinations();
        //Test cubes
        clearCubes = this.findCubeCombinations();
        //Clear board
        this.clearBoard();
    }
    
    private void clearBoard() {
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                if(clearLineDiceBoard[i][j] || clearColumnDiceBoard[i][j] 
                || clearDiagonalDiceBoard[i][j] || clearCornerDiceBoard[i][j] || clearCubeDiceBoard[i][j]) {
                    board.setBoardDie(new Die(0, 0, false), i, j);
                    System.out.println("cleared " + i + ", " + j);
                }
            }
        }
    }
    
    public Boolean[][] findLineCombinations() {
        int j = 0;
        boolean clear = false;
        clearLineDiceBoard = new Boolean[4][4];
        
        for(int k = 0; k < 4; k++)
            for(int l = 0; l < 4; l++)
                clearLineDiceBoard[l][k] = false;
        
        for(int i = 0; i < 4; i++) {
            j = 0;
            
            for(int k = 0; k < 4; k++)
                combination[k] = null;
            
            do {
                if(diceBoard[i][j].getColor() != 0) {
                    combination[j] = diceBoard[i][j];
                }
                else
                    j = 4;
                if(combination[3] != null) {
                    System.out.println("line");
                    clear = this.calculateScore(combination);
                    
                    if(clear) {
                        System.out.println("Clear");
                        //Save the combination we have to clear
                        for(int k = 0; k < 4; k++) {
                            for(int l = 0; l < 4; l++) {
                                if(k == i)
                                    clearLineDiceBoard[k][l] = true;
                            }
                        }
                    }
                }
                j++;
            }while(j < 4);
        }
        
        return clearLineDiceBoard;
    }
    
    public Boolean[][] findColumnCombinations() {
        int j = 0;
        boolean clear = false;
        clearColumnDiceBoard = new Boolean[4][4];
        
        for(int k = 0; k < 4; k++)
            for(int l = 0; l < 4; l++)
                clearColumnDiceBoard[l][k] = false;

        for(int i = 0; i < 4; i++) {
            j = 0;
            
            for(int k = 0; k < 4; k++)
                combination[k] = null;
            
            do {
                if(diceBoard[i][j].getColor() != 0) {
                    combination[j] = diceBoard[j][i];
                }
                else
                    j = 4;
                if(combination[3] != null) {
                    System.out.println("Column");
                    clear = this.calculateScore(combination);
                    
                    if(clear) {
                        System.out.println("Clear");
                        //Save the combination we have to clear
                        for(int k = 0; k < 4; k++) {
                            for(int l = 0; l < 4; l++) {
                                if(k == i)
                                    clearColumnDiceBoard[l][k] = true;
                            }
                        }
                    }
                }
                j++;
            }while(j < 4);
        }
        
        return clearColumnDiceBoard;
    }
    
    public Boolean[][] findDiagonalCombinations() {
        int j = 0;
        boolean clear = false;
        clearDiagonalDiceBoard = new Boolean[4][4];
            
        for(int k = 0; k < 4; k++)
            combination[k] = null;
        
        for(int k = 0; k < 4; k++)
            for(int l = 0; l < 4; l++)
                clearDiagonalDiceBoard[k][l] = false;
           
        if(diceBoard[0][0].getColor() != 0 && diceBoard[1][1].getColor() != 0 &&
           diceBoard[2][2].getColor() != 0 && diceBoard[3][3].getColor() != 0) {
            combination[0] = diceBoard[0][0];
            combination[1] = diceBoard[1][1];
            combination[2] = diceBoard[2][2];
            combination[3] = diceBoard[3][3];
        }
 
        if(combination[3] != null) {
            System.out.println("Diago");
            clear = this.calculateScore(combination);

            if(clear) {
                System.out.println("Clear");
                //clear the combination on the board
                clearDiagonalDiceBoard[0][0] = true;
                clearDiagonalDiceBoard[1][1] = true;
                clearDiagonalDiceBoard[2][2] = true;
                clearDiagonalDiceBoard[3][3] = true;
            }
        }
        
        for(int k = 0; k < 4; k++)
            combination[k] = null;
        
        if(diceBoard[0][3].getColor() != 0 && diceBoard[1][2].getColor() != 0 &&
           diceBoard[2][1].getColor() != 0 && diceBoard[3][0].getColor() != 0) {
            combination[0] = diceBoard[0][3];
            combination[1] = diceBoard[1][2];
            combination[2] = diceBoard[2][1];
            combination[3] = diceBoard[3][0];
        }
 
        if(combination[3] != null) {
            System.out.println("Diago");
            clear = this.calculateScore(combination);

            if(clear) {
                System.out.println("Clear");
                //clear the combination on the board
                clearDiagonalDiceBoard[0][3] = true;
                clearDiagonalDiceBoard[1][2] = true;
                clearDiagonalDiceBoard[2][1] = true;
                clearDiagonalDiceBoard[3][0] = true;
            }
        }
        
        return clearDiagonalDiceBoard;
    }
    
    public Boolean[][] findCornerCombinations() {
        int j = 0;
        boolean clear = false;
        clearCornerDiceBoard = new Boolean[4][4];
            
        for(int k = 0; k < 4; k++)
            combination[k] = null;
        
        for(int k = 0; k < 4; k++)
            for(int l = 0; l < 4; l++)
                clearCornerDiceBoard[k][l] = false;
           
        if(diceBoard[0][0].getColor() != 0 && diceBoard[0][3].getColor() != 0 &&
           diceBoard[3][0].getColor() != 0 && diceBoard[3][3].getColor() != 0) {
            combination[0] = diceBoard[0][0];
            combination[1] = diceBoard[0][3];
            combination[2] = diceBoard[3][0];
            combination[3] = diceBoard[3][3];
        }
 
        if(combination[3] != null) {
            System.out.println("Corner");
            clear = this.calculateScore(combination);

            if(clear) {
                System.out.println("Clear");
                //clear the combination on the board
                clearCornerDiceBoard[0][0] = true;
                clearCornerDiceBoard[0][3] = true;
                clearCornerDiceBoard[3][0] = true;
                clearCornerDiceBoard[3][3] = true;
            }
        }
        
        return clearCornerDiceBoard;
    }
    
    public Boolean[][] findCubeCombinations() {
        boolean clear = false;
        clearCubeDiceBoard = new Boolean[4][4];
            
        for(int k = 0; k < 4; k++)
            for(int l = 0; l < 4; l++)
                clearCubeDiceBoard[k][l] = false;
           
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                for(int k = 0; k < 4; k++)
                    combination[k] = null;
                
                if(diceBoard[i][j].getColor() != 0 && diceBoard[i+1][j].getColor() != 0 
                && diceBoard[i][j+1].getColor() != 0 && diceBoard[i+1][j+1].getColor() != 0) {
                    combination[0] = diceBoard[i][j];
                    combination[1] = diceBoard[i+1][j];
                    combination[2] = diceBoard[i][j+1];
                    combination[3] = diceBoard[i+1][j+1];
                }
                
                if(combination[3] != null) {
                    System.out.println("Cube");
                    clear = this.calculateScore(combination);
                
                    if(clear) {
                        System.out.println("Clear");
                        //Save the combination we have to clear
                        for(int k = 0; k < 3; k++) {
                            for(int l = 0; l < 3; l++) {
                                if(k == i && l == j) {
                                    clearCubeDiceBoard[k][l] = true;
                                    clearCubeDiceBoard[k+1][l] = true;
                                    clearCubeDiceBoard[k][l+1] = true;
                                    clearCubeDiceBoard[k+1][l+1] = true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return clearCubeDiceBoard;
    }
    
    private boolean calculateScore(Die[] combination) {
        boolean clear = false;
        
        /*for(int k = 0; k < Constant.SIZE; k++) {
            System.out.print("color : " + combination[k].getColor());
            System.out.print(", value : " + combination[k].getValue());
            System.out.print(", locked : " + combination[k].getLocked());
            System.out.println("/n");
        }*/
        
        //Combination cleared
        if(combinationTest.testSameColorSameNumber(combination)) {
            System.out.println("bingo");
            player.setScore(400);
            clear = true;
        }
        else if(combinationTest.testSameColorEachNumber(combination)) {
            System.out.println("bingo");
            player.setScore(200);
            clear = true;
        }
        else if(combinationTest.testEachColorSameNumber(combination)) {
            System.out.println("bingo");
            player.setScore(200);
            clear = true;
        }
        else if(combinationTest.testEachColorEachNumber(combination)) {
            System.out.println("bingo");
            player.setScore(100);
            clear = true;
        }
        
        //Combination not cleared
        else if(combinationTest.testTwoPair(combination))
            player.setScore(60); 
        else if(combinationTest.testSameColor(combination))
            player.setScore(40);
        else if(combinationTest.testSameNumber(combination))
            player.setScore(40);
        else if(combinationTest.testPairColorPairNumber(combination))
            player.setScore(20);
        else if(combinationTest.testEachColor(combination))
            player.setScore(10);
        else if(combinationTest.testEachNumber(combination))
            player.setScore(10);
        else if(combinationTest.testPairColor(combination))
            player.setScore(5);
        else if(combinationTest.testPairNumber(combination))
            player.setScore(5);
        
        return clear;
    }
}
