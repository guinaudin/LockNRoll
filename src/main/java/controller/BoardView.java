package controller;

import model.board.BoardListener;

public abstract class BoardView implements BoardListener {
    private BoardController controller = null;
 
    public BoardView(BoardController controller){
            super();

            this.controller = controller;
    }

    public final BoardController getController(){
            return controller;
    }

    public abstract void display();
    public abstract void close();
}
