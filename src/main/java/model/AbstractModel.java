package model;

import observer.Observable;
import observer.Observer;
import java.util.ArrayList;
import model.board.Board;
import model.dice.Die;

public abstract class AbstractModel implements Observable {
    private ArrayList<Observer> listObserver = new ArrayList<Observer>();
    
    public abstract void startNewGame();
    
    public abstract void makeTurn();
    
    public abstract Die selectBoardDie(int posX, int posY);
    
    public abstract Die selectRolledDie(int posX);
    
    public abstract void moveBoardDie(Die dice, int posX, int posY, int selectedPosX, int selectedPosY);
    
    public abstract void moveBoardDie(Die dice, int posX, int selectedPosX, int selectedPosY);
    
    public abstract void moveRolledDie(Die die, int posX, int posY, int selectedPosX);
    
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
