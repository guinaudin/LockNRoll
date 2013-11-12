package model.board;
import model.dice.Die;

public class Board {
    private Die[] rolledDice;
    private Die[][] diceBoard;
    private int unlockedPlaces;

    public Board() {
        unlockedPlaces = 4 * 4;
        diceBoard = new Die[4][4];
        rolledDice = new Die[4];

        //Besoin d'init le board
        for(int i = 0; i < 4; i++) {
            rolledDice[i] = new Die(0, 0, false);
            for(int j = 0; j < 4; j++) {
                diceBoard[i][j] = new Die(0, 0, false);
            }
        }
    }
    
    public void rollDice() {
        for(int i = 0; i < 4; i++) {
            if(i < unlockedPlaces)
                rolledDice[i] = new Die((int)Math.round(Math.random() * 3 + 1),(int)Math.round(Math.random() * 3 + 1), false);
            else 
                rolledDice[i] = new Die(0, 0, false);
        }
    }
    
    public int getUnlockedPlaces() {
        return unlockedPlaces;
    }
    
    public Die[][] getDiceBoard() {
        return diceBoard;
    }
    
    public Die[] getRolledDice() {
        return rolledDice;
    }
    
    public Die getRolledDie(int posX) {
        return rolledDice[posX];
    }
    
    public Die getBoardDie(int posX, int posY) {
        return diceBoard[posX][posY];
    }
    
    public void setDiceBoard(Die[][] diceBoard) {
        this.diceBoard = diceBoard;
    }
    
    public void setBoardDie(Die die, int posX, int posY) {
        diceBoard[posX][posY] = die;
        System.out.println(diceBoard[posX][posY].getColor() + " " + diceBoard[posX][posY].getValue());
    }
    
    public void setRolledDie(Die die, int posX) {
        rolledDice[posX] = die;
    }
}