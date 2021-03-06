package model;

import model.board.Board;
import model.dice.Die;
import model.player.Player;

/**Classe chargé de détecter les combinaisons de dés pour les analyser*/
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
    
    /**On recherche tous les tpes de combinaisons(lines, colonnes, diago, coins et cubes)*/
    public void findCombinations(Board board, Player player) {
        this.player = player;
        this.board = board;
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
    
    /**On vide le plateau des combinaisons effacables*/
    private void clearBoard() {
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                if(clearLineDiceBoard[i][j] || clearColumnDiceBoard[i][j] 
                || clearDiagonalDiceBoard[i][j] || clearCornerDiceBoard[i][j] || clearCubeDiceBoard[i][j]) {
                    board.setBoardDie(new Die(0, 0, false), i, j);
                    board.setUnlockedPlaces(board.getUnlockedPlaces()+1);
                }
            }
        }
    }
    
    /**On fait une analyse de toutes les lignes*/
    public Boolean[][] findLineCombinations() {
        int j = 0;
        boolean clear;
        int nbLockedDie = 0;
        clearLineDiceBoard = new Boolean[4][4];
        
        for(int k = 0; k < 4; k++)
            for(int l = 0; l < 4; l++)
                clearLineDiceBoard[l][k] = false;
        
        for(int i = 0; i < 4; i++) {
            j = 0;
            nbLockedDie = 0;
            
            for(int k = 0; k < 4; k++)
                combination[k] = null;
            
            do {
                if(diceBoard[i][j].getColor() != 0) {
                    combination[j] = diceBoard[i][j];
                    if(combination[j].getLocked())
                        nbLockedDie++;
                }
                else
                    j = 4;
                
                if(combination[3] != null && nbLockedDie < 4) {
                    clear = this.calculateScore(combination);
                    
                    if(clear) {
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
    
    /**On fait une analyse de toutes les colonnes*/
    public Boolean[][] findColumnCombinations() {
        int j = 0;
        int nbLockedDie = 0;
        boolean clear = false;
        clearColumnDiceBoard = new Boolean[4][4];
        
        for(int k = 0; k < 4; k++)
            for(int l = 0; l < 4; l++)
                clearColumnDiceBoard[l][k] = false;

        for(int i = 0; i < 4; i++) {
            j = 0;
            nbLockedDie = 0;
            
            for(int k = 0; k < 4; k++)
                combination[k] = null;
            
            do {
                if(diceBoard[j][i].getColor() != 0) {
                    combination[j] = diceBoard[j][i];
                    if(combination[j].getLocked())
                        nbLockedDie++;
                }
                else
                    j = 4;
                if(combination[3] != null && nbLockedDie < 4) {
                    clear = this.calculateScore(combination);
                    
                    if(clear) {
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
    
    /**On fait une analyse de toutes les diagonales*/
    public Boolean[][] findDiagonalCombinations() {
        int j = 0;
        boolean clear = false;
        clearDiagonalDiceBoard = new Boolean[4][4];
            
        for(int k = 0; k < 4; k++)
            combination[k] = null;
        
        for(int k = 0; k < 4; k++)
            for(int l = 0; l < 4; l++)
                clearDiagonalDiceBoard[k][l] = false;
        
        if(diceBoard[0][0].getLocked() && diceBoard[1][1].getLocked() &&
           diceBoard[2][2].getLocked() && diceBoard[3][3].getLocked()) {
            
        }
        else if(diceBoard[0][0].getColor() != 0 && diceBoard[1][1].getColor() != 0 &&
           diceBoard[2][2].getColor() != 0 && diceBoard[3][3].getColor() != 0) {
            combination[0] = diceBoard[0][0];
            combination[1] = diceBoard[1][1];
            combination[2] = diceBoard[2][2];
            combination[3] = diceBoard[3][3];
        }
 
        if(combination[3] != null) {
            clear = this.calculateScore(combination);

            if(clear) {
                //clear the combination on the board
                clearDiagonalDiceBoard[0][0] = true;
                clearDiagonalDiceBoard[1][1] = true;
                clearDiagonalDiceBoard[2][2] = true;
                clearDiagonalDiceBoard[3][3] = true;
            }
        }
        
        for(int k = 0; k < 4; k++)
            combination[k] = null;
        
        if(diceBoard[0][3].getLocked() && diceBoard[1][2].getLocked() &&
           diceBoard[2][1].getLocked() && diceBoard[3][0].getLocked()) {
            
        }
        else if(diceBoard[0][3].getColor() != 0 && diceBoard[1][2].getColor() != 0 &&
           diceBoard[2][1].getColor() != 0 && diceBoard[3][0].getColor() != 0) {
            combination[0] = diceBoard[0][3];
            combination[1] = diceBoard[1][2];
            combination[2] = diceBoard[2][1];
            combination[3] = diceBoard[3][0];
        }
 
        if(combination[3] != null) {
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
    
    /**On fait une analyse des 4 coins*/
    public Boolean[][] findCornerCombinations() {
        int j = 0;
        boolean clear = false;
        clearCornerDiceBoard = new Boolean[4][4];
            
        for(int k = 0; k < 4; k++)
            combination[k] = null;
        
        for(int k = 0; k < 4; k++)
            for(int l = 0; l < 4; l++)
                clearCornerDiceBoard[k][l] = false;
        
        if(diceBoard[0][0].getLocked() && diceBoard[0][3].getLocked() &&
           diceBoard[3][0].getLocked() && diceBoard[3][3].getLocked()) {
            
        }
        else if(diceBoard[0][0].getColor() != 0 && diceBoard[0][3].getColor() != 0 &&
           diceBoard[3][0].getColor() != 0 && diceBoard[3][3].getColor() != 0) {
            combination[0] = diceBoard[0][0];
            combination[1] = diceBoard[0][3];
            combination[2] = diceBoard[3][0];
            combination[3] = diceBoard[3][3];
        }
 
        if(combination[3] != null) {
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
    
    /**On fait une analyse de tous les cubes*/
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
                
                if(diceBoard[i][j].getLocked() && diceBoard[i+1][j].getLocked() &&
                   diceBoard[i][j+1].getLocked() && diceBoard[i+1][j+1].getLocked()) {

                }
                else if(diceBoard[i][j].getColor() != 0 && diceBoard[i+1][j].getColor() != 0 
                && diceBoard[i][j+1].getColor() != 0 && diceBoard[i+1][j+1].getColor() != 0) {
                    combination[0] = diceBoard[i][j];
                    combination[1] = diceBoard[i+1][j];
                    combination[2] = diceBoard[i][j+1];
                    combination[3] = diceBoard[i+1][j+1];
                }
                
                if(combination[3] != null) {
                    clear = this.calculateScore(combination);
                
                    if(clear) {
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
    
    /**Calcul du score du joueur*/
    private boolean calculateScore(Die[] combination) {
        boolean clear = false;
        
        //Combination cleared
        if(combinationTest.testSameColorSameNumber(combination)) {
            player.setScore(player.getScore() + 400);
            clear = true;
        }
        else if(combinationTest.testSameColorEachNumber(combination)) {
            player.setScore(player.getScore() + 200);
            clear = true;
        }
        else if(combinationTest.testEachColorSameNumber(combination)) {
            player.setScore(player.getScore() + 200);
            clear = true;
        }
        else if(combinationTest.testEachColorEachNumber(combination)) {
            player.setScore(player.getScore() + 100);
            clear = true;
        }
        
        //Combination not cleared
        else if(combinationTest.testTwoPair(combination)) {
            player.setScore(player.getScore() + 60); 
            if(player.getNbBombJoker() < 2) {
                player.setNbBombJoker(player.getNbBombJoker() + 1);
                if(player.getBombJokerDie(0).getValue() != 5)
                    player.setBombJokerDie(new Die(5,5, false), 0);
                else
                    player.setBombJokerDie(new Die(5,5, false), 1);
            }
        }
        else if(combinationTest.testSameColor(combination)) {
            player.setScore(player.getScore() + 40);
        }
        else if(combinationTest.testSameNumber(combination)) {
            player.setScore(player.getScore() + 40);
        }
        else if(combinationTest.testPairColorPairNumber(combination)) {
            player.setScore(player.getScore() + 20);
        }
        else if(combinationTest.testEachColor(combination)) {
            player.setScore(player.getScore() + 10);
        }
        else if(combinationTest.testEachNumber(combination)) {
            player.setScore(player.getScore() + 10);
        }
        else if(combinationTest.testPairColor(combination)) {
            player.setScore(player.getScore() + 5);
        }
        else if(combinationTest.testPairNumber(combination)) {
            player.setScore(player.getScore() + 5);
        }
        
        return clear;
    }
}
