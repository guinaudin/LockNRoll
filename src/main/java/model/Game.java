package model;

import model.board.Board;
import model.dice.Die;
import model.player.Player;

public class Game extends AbstractModel{
    private Board board;
    private Player player;

    public Game() {
        player = new Player();
        board = new Board();
        board.rollDice();
    }
    
    public Game(Board board) {
        this.board = board;
        //board.rollDices();
    }
    
    public void startNewGame() {
        player = new Player();
        board = new Board();
        board.rollDice();
        
        notifyObserver(board);
    }
    
    public void makeTurn() {
        //caseTest(board, player)
        
        //victory test
        //rolldices
        board.rollDice();
        
        notifyObserver(board);
    }

    public Die selectBoardDie(int posX, int posY) {
        return new Die(board.getBoardDie(posX, posY).getValue(), board.getBoardDie(posX, posY).getColor(), board.getBoardDie(posX, posY).getLocked());
    }
    
    public Die selectRolledDie(int posX) {
        return new Die(board.getRolledDie(posX).getValue(), board.getRolledDie(posX).getColor(), board.getRolledDie(posX).getLocked());
    }
    
    public void moveRolledDie(Die die, int posX, int posY, int selectedPosX) {
        board.getBoardDie(posX, posY).setColor(die.getColor());
        board.getBoardDie(posX, posY).setValue(die.getValue());
        board.setRolledDie(new Die(0, 0, true), selectedPosX);
        
        //On met à jour 
        notifyObserver(board);
    }
    
    public void moveBoardDie(Die die, int posX, int posY, int selectedPosX, int selectedPosY) {
        board.setBoardDie(die, posX, posY);
        board.setBoardDie(new Die(0, 0, true), selectedPosX, selectedPosY);
        
        //On met à jour 
        notifyObserver(board);
    }
    
    public void moveBoardDie(Die die, int posX, int selectedPosX, int selectedPosY) {
        board.setRolledDie(die, posX);
        board.setBoardDie(new Die(0, 0, true), selectedPosX, selectedPosY);
        
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
