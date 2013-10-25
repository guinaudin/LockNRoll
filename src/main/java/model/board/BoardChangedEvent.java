package model.board;

import java.util.EventObject;

public class BoardChangedEvent extends EventObject {
    private Board newBoard;
    
    public BoardChangedEvent(Object source, Board newBoard) {
        super(source);
        this.newBoard = newBoard;
    }
    
    public Board getNewBoard() {
        return newBoard;
    }
}
