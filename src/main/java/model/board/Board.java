package model.board;

import model.Constant;
import model.dice.Dice;
import model.dice.GameDice;

public class Board {
    private GameDice[] rolledDice;
    private Dice[][] diceBoard;
    private int unlockedPlaces;

    public Board() {
        unlockedPlaces = Constant.SIZE * Constant.SIZE;
        diceBoard = new Dice[Constant.SIZE][Constant.SIZE];
        rolledDice = new GameDice[Constant.SIZE];

        //Besoin d'init le board
        for(int i = 0; i < Constant.SIZE; i++) {
            rolledDice[i] = new GameDice(0, 0);
            for(int j = 0; j < Constant.SIZE; j++) {
                diceBoard[i][j] = new Dice(0, 0);
            }
        }
    }
    
    public void rollDice() {
        for(int i = 0; i < Constant.SIZE; i++) {
            if(i < unlockedPlaces)
                rolledDice[i] = new GameDice((int)Math.round(Math.random() * 3 + 1),(int)Math.round(Math.random() * 3 + 1));
            else 
                rolledDice[i] = new GameDice(0, 0);
        }
    }
    
    public int getUnlockedPlaces() {
        return unlockedPlaces;
    }
    
    public Dice[][] getDiceBoard() {
        return diceBoard;
    }
    
    public GameDice[] getRolledDice() {
        return rolledDice;
    }
    
    public void setDiceBoard(Dice[][] diceBoard) {
        this.diceBoard = diceBoard;
    }
    
    public void setDice(Dice dice, int posX, int posY) {
        diceBoard[posX][posY] = dice;
    }
}