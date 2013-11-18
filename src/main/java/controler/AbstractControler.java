package controler;

import model.AbstractModel;
import model.dice.Die;

public abstract class AbstractControler {
    protected AbstractModel abstractModel;
    
    public AbstractControler(AbstractModel abstractModel) {
        this.abstractModel = abstractModel;
    }
    
    public void startNewGame() {
        abstractModel.startNewGame();
    }
    
    public Die selectRolledDie(int posX) {
        return abstractModel.selectRolledDie(posX);
    }
    
    public Die selectBombJoker(int posX) {
        return abstractModel.selectBombJoker(posX);
    }
    
    public Die selectBoardDie(int posX, int posY) {
        return abstractModel.selectBoardDie(posX, posY);
    }
    
    public void moveBoardDie(Die die, int posX, int posY, int selectedPosX, int selectedPosY) {
        abstractModel.moveBoardDie(die, posX, posY, selectedPosX, selectedPosY);
    }
    
    public void moveBoardDie(Die die, int posX, int selectedPosX, int selectedPosY) {
        abstractModel.moveBoardDie(die, posX, selectedPosX, selectedPosY);
    }
    
    public void moveRolledDie(Die die, int posX, int posY, int selectedPosX) {
        abstractModel.moveRolledDie(die, posX, posY, selectedPosX);
    }
    
    public void moveBombJoker(Die die, int posX, int posY, int selectedPosX) {
        abstractModel.moveBombJoker(die, posX, posY, selectedPosX);
    }
    
    public void makeTurn() {
         abstractModel.makeTurn();
    }
}
