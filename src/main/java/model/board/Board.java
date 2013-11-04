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
            rolledDice[i] = null;
            for(int j = 0; j < Constant.SIZE; j++) {
                diceBoard[i][j] = null;
            }
        }
    }
    
    public void rollDices() {
        if(unlockedPlaces > 4) {
            for(int i = 0; i < Constant.SIZE; i++) {
                rolledDice[i] = new GameDice();
            }
        }
        else {
            for(int i = 0; i < unlockedPlaces; i++) {
                rolledDice[i] = new GameDice((int)Math.round(Math.random() * 3 + 1),(int)Math.round(Math.random() * 3 + 1));
            }
        }
    }
    
    public int getUnlockedPlaces() {
        return unlockedPlaces;
    }
    
    public Dice[][] getDiceBoard() {
        return diceBoard;
    }
    
    public void setDiceBoard(Dice[][] diceBoard) {
        this.diceBoard = diceBoard;
    }
}