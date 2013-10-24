package Controller;

import Model.Board.Board;
import Model.BoardModel;
import View.JFrameBoard;

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
