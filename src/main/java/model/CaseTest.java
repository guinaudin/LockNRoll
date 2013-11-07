package model;

import model.board.Board;
import model.dice.Dice;
import model.dice.GameDice;
import model.player.Player;

public class CaseTest {
    private Board board;
    private Player player;
    private Dice[][] diceBoard;
    private Dice[] combination;
    private CombinationTest combinationTest;
    
    private Boolean[][] clearLines;
    private Boolean[][] clearColumns;
    private Boolean[][] clearDiagonals;
    private Boolean[][] clearCorners;
    private Boolean[][] clearCubes;
    
    public CaseTest(Board board, Player player) {
        this.board = board;
        this.player = player;
        diceBoard = new Dice[Constant.SIZE][Constant.SIZE];
        diceBoard = board.getDiceBoard();
        clearLines = new Boolean[Constant.SIZE][Constant.SIZE];
        clearColumns = new Boolean[Constant.SIZE][Constant.SIZE];
        clearDiagonals = new Boolean[Constant.SIZE][Constant.SIZE];
        clearCorners = new Boolean[Constant.SIZE][Constant.SIZE];
        clearCubes = new Boolean[Constant.SIZE][Constant.SIZE];
        combinationTest = new CombinationTest();
        combination = new Dice[Constant.SIZE];
        
        for(int i = 0; i < Constant.SIZE; i++)
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
        //reset UI
    }
    
    private void clearBoard() {
        for(int i = 0; i < Constant.SIZE; i++) {
            for(int j = 0; j < Constant.SIZE; j++) {
                if(this.clearLines[i][j] || this.clearColumns[i][j] || this.clearDiagonals[i][j] || this.clearCorners[i][j] || this.clearCubes[i][j])
                    diceBoard[i][j] = new GameDice();
            }
        }
    }
    
    public Boolean[][] findLineCombinations() {
        int j = 0;
        boolean clear = false;
        Boolean[][] clearLineDiceBoard = new Boolean[Constant.SIZE][Constant.SIZE];
        
        for(int k = 0; k < Constant.SIZE; k++)
            for(int l = 0; l < Constant.SIZE; l++)
                clearLineDiceBoard[l][k] = false;
        
        for(int i = 0; i < Constant.SIZE; i++) {
            j = 0;
            
            for(int k = 0; k < Constant.SIZE; k++)
                combination[k] = null;
            
            do {
                if(diceBoard[i][j].getLocked()) {
                    combination[j] = diceBoard[i][j];
                }
                else
                    j = Constant.SIZE;
                if(combination[Constant.SIZE - 1] != null) {
                    
                    clear = this.calculateScore(combination);
                    
                    if(clear) {
                        //Save the combination we have to clear
                        for(int k = 0; k < Constant.SIZE; k++) {
                            for(int l = 0; l < Constant.SIZE; l++) {
                                if(k == i)
                                    clearLineDiceBoard[k][l] = true;
                            }
                        }
                    }
                }
                j++;
            }while(j < Constant.SIZE);
        }
        
        return clearLineDiceBoard;
    }
    
    public Boolean[][] findColumnCombinations() {
        int j = 0;
        boolean clear = false;
        Boolean[][] clearColumnDiceBoard = new Boolean[Constant.SIZE][Constant.SIZE];
        
        for(int k = 0; k < Constant.SIZE; k++)
            for(int l = 0; l < Constant.SIZE; l++)
                clearColumnDiceBoard[l][k] = false;

        for(int i = 0; i < Constant.SIZE; i++) {
            j = 0;
            
            for(int k = 0; k < Constant.SIZE; k++)
                combination[k] = null;
            
            do {
                if(diceBoard[j][i].getLocked() == true) {
                    combination[j] = diceBoard[j][i];
                }
                else
                    j = Constant.SIZE;
                if(combination[Constant.SIZE - 1] != null) {
                    clear = this.calculateScore(combination);
                    
                    if(clear) {
                        //Save the combination we have to clear
                        for(int k = 0; k < Constant.SIZE; k++) {
                            for(int l = 0; l < Constant.SIZE; l++) {
                                if(k == i)
                                    clearColumnDiceBoard[l][k] = true;
                            }
                        }
                    }
                }
                j++;
            }while(j < Constant.SIZE);
        }
        
        return clearColumnDiceBoard;
    }
    
    public Boolean[][] findDiagonalCombinations() {
        int j = 0;
        boolean clear = false;
        Boolean[][] clearDiagonalDiceBoard = new Boolean[Constant.SIZE][Constant.SIZE];
            
        for(int k = 0; k < Constant.SIZE; k++)
            combination[k] = null;
        
        for(int k = 0; k < Constant.SIZE; k++)
            for(int l = 0; l < Constant.SIZE; l++)
                clearDiagonalDiceBoard[k][l] = false;
           
        if(diceBoard[0][0].getLocked() && diceBoard[1][1].getLocked() &&
           diceBoard[2][2].getLocked() && diceBoard[3][3].getLocked()) {
            combination[0] = diceBoard[0][0];
            combination[1] = diceBoard[1][1];
            combination[2] = diceBoard[2][2];
            combination[3] = diceBoard[3][3];
        }
 
        if(combination[Constant.SIZE - 1] != null) {
            clear = this.calculateScore(combination);

            if(clear) {
                //clear the combination on the board
                clearDiagonalDiceBoard[0][0] = true;
                clearDiagonalDiceBoard[1][1] = true;
                clearDiagonalDiceBoard[2][2] = true;
                clearDiagonalDiceBoard[3][3] = true;
            }
        }
        
        for(int k = 0; k < Constant.SIZE; k++)
            combination[k] = null;
        
        if(diceBoard[0][3].getLocked() && diceBoard[1][2].getLocked() &&
           diceBoard[2][1].getLocked() && diceBoard[3][0].getLocked()) {
            combination[0] = diceBoard[0][3];
            combination[1] = diceBoard[1][2];
            combination[2] = diceBoard[2][1];
            combination[3] = diceBoard[3][0];
        }
 
        if(combination[Constant.SIZE - 1] != null) {
            clear = this.calculateScore(combination);

            if(clear) {
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
        Boolean[][] clearCornerDiceBoard = new Boolean[Constant.SIZE][Constant.SIZE];
            
        for(int k = 0; k < Constant.SIZE; k++)
            combination[k] = null;
        
        for(int k = 0; k < Constant.SIZE; k++)
            for(int l = 0; l < Constant.SIZE; l++)
                clearCornerDiceBoard[k][l] = false;
           
        if(diceBoard[0][0].getLocked() && diceBoard[0][3].getLocked() &&
           diceBoard[3][0].getLocked() && diceBoard[3][3].getLocked()) {
            combination[0] = diceBoard[0][0];
            combination[1] = diceBoard[0][3];
            combination[2] = diceBoard[3][0];
            combination[3] = diceBoard[3][3];
        }
 
        if(combination[Constant.SIZE - 1] != null) {
            clear = this.calculateScore(combination);

            if(clear) {
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
        Boolean[][] clearCubeDiceBoard = new Boolean[Constant.SIZE][Constant.SIZE];
            
        for(int k = 0; k < Constant.SIZE; k++)
            for(int l = 0; l < Constant.SIZE; l++)
                clearCubeDiceBoard[k][l] = false;
           
        for(int i = 0; i < Constant.SIZE - 1; i++) {
            for(int j = 0; j < Constant.SIZE - 1; j++) {
                for(int k = 0; k < Constant.SIZE; k++)
                    combination[k] = null;
                
                if(diceBoard[i][j].getLocked() && diceBoard[i+1][j].getLocked() && diceBoard[i][j+1].getLocked() && diceBoard[i+1][j+1].getLocked()) {
                    combination[0] = diceBoard[i][j];
                    combination[1] = diceBoard[i+1][j];
                    combination[2] = diceBoard[i][j+1];
                    combination[3] = diceBoard[i+1][j+1];
                }
                
                if(combination[Constant.SIZE - 1] != null) {
                    clear = this.calculateScore(combination);
                
                    if(clear) {
                        //Save the combination we have to clear
                        for(int k = 0; k < Constant.SIZE - 1; k++) {
                            for(int l = 0; l < Constant.SIZE - 1; l++) {
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
    
    private boolean calculateScore(Dice[] combination) {
        boolean clear = false;
        
        /*for(int k = 0; k < Constant.SIZE; k++) {
            System.out.print("color : " + combination[k].getColor());
            System.out.print(", value : " + combination[k].getValue());
            System.out.print(", locked : " + combination[k].getLocked());
            System.out.println("/n");
        }*/
        
        //Combination cleared
        if(combinationTest.testSameColorSameNumber(combination)) {
            player.setScore(400);
            clear = true;
        }
        else if(combinationTest.testSameColorEachNumber(combination)) {
            player.setScore(200);
            clear = true;
        }
        else if(combinationTest.testEachColorSameNumber(combination)) {
            player.setScore(200);
            clear = true;
        }
        else if(combinationTest.testEachColorEachNumber(combination)) {
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
