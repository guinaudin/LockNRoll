package model;

import observer.Observable;
import observer.Observer;
import java.util.ArrayList;
import model.board.Board;
import model.dice.Dice;

public abstract class AbstractModel implements Observable {
    private ArrayList<Observer> listObserver = new ArrayList<Observer>();
    
    public abstract void startNewGame();
    
    public abstract void makeTurn();
    
    public abstract void setDice(Dice dice, int posX, int posY);
    
    public void addObserver(Observer obs) {
        this.listObserver.add(obs);
    }

    public void removeObserver() {
        listObserver = new ArrayList<Observer>();
    }

    public void notifyObserver(Board board) {
        for(Observer obs : listObserver) {
             obs.updateBoardDice(board);
             obs.updateRolledDice(board);
        }
    }
}
