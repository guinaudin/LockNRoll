package controller;

import model.AbstractModel;
import model.dice.Dice;

public abstract class AbstractControler {
    protected AbstractModel abstractModel;
    
    public AbstractControler(AbstractModel abstractModel) {
        this.abstractModel = abstractModel;
    }
    
    public void startNewGame() {
        abstractModel.startNewGame();
    }
    
    public void setDice(Dice dice, int posX, int posY) {
         abstractModel.setDice(dice, posX, posY);
    }
    
    public void makeTurn() {
         abstractModel.makeTurn();
    }
}
