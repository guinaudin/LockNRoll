package model;

import model.board.Board;
import model.player.Player;

public class BoardModel {
    private Board board;
    private Player player;
    //private EventListenerList listeners;

    public BoardModel() {
        player = new Player();
        board = new Board();
        board.rollDices();
    }
    
    public BoardModel(Board board) {
        this.board = board;
        //board.rollDices();
        //listeners = new EventListenerList();
    }
    
    public void makeTurn() {
        //caseTest(board, player)
        
        //victory test
        //rolldices
    }

    public Board getBoard() {
        return board;
    }
    
    public void setBoard(Board board) {
        this.board = board;
        //this.fireBoardChanged();
    }
    
    /*public void addBoardListener(BoardListener listener){
        listeners.add(BoardListener.class, listener);
    }
    
    public void removeBoardListener(BoardListener l){
        listeners.remove(BoardListener.class, l);
    }
 
    public void fireBoardChanged(){
        BoardListener[] listenerList = (BoardListener[])listeners.getListeners(BoardListener.class);

        for(BoardListener listener : listenerList){
            listener.boardChanged(new BoardChangedEvent(this, getBoard()));
        }
    }*/
}
