package controller;

import model.board.Board;
import model.BoardModel;
import view.JFrameBoard;

public class BoardController {
    public BoardView mainView;

    private BoardModel model;

    public BoardController (BoardModel model){
        this.model = model;
        mainView = new JFrameBoard(this, model.getBoard());
        //this.addListenersToModel();
    }

    /*private void addListenersToModel() {
        model.addBoardListener(mainView);
    }*/

    public void displayViews(){
        mainView.display();
    }

    public void closeViews(){
        mainView.close();
    }

    public void notifyBoardChanged(Board board){
        model.setBoard(board);
    }
}
