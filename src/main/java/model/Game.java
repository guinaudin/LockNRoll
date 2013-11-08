package model;

import model.board.Board;
import model.dice.Dice;
import model.player.Player;

public class Game extends AbstractModel{
    private Board board;
    private Player player;

    public Game() {
        player = new Player();
        board = new Board();
        board.rollDices();
    }
    
    public Game(Board board) {
        this.board = board;
        //board.rollDices();
    }
    
    public void startNewGame() {
        player = new Player();
        board = new Board();
        board.rollDices();
        
        notifyObserver(board);
    }
    
    public void makeTurn() {
        //caseTest(board, player)
        
        //victory test
        //rolldices
        
        notifyObserver(board);
    }
    
    public void setDice(Dice dice, int posX, int posY) {
        board.setDice(dice, posX, posY);
        
        //On met à jour 
        notifyObserver(board);
    }

    public Board getBoard() {
        return board;
    }
    
    public void setBoard(Board board) {
        this.board = board;
    }
}
