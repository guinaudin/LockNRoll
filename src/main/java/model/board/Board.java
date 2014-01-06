package model.board;
import model.dice.Die;

/**Classe définissant le plateau de jeu soient les 16 cases de jeu et les 4 cases pour tirer les dés aléatoirement*/
public class Board {
    private Die[] rolledDice;
    private Die[][] diceBoard;
    private int unlockedPlaces;

    public Board() {
        unlockedPlaces = 4 * 4;
        diceBoard = new Die[4][4];
        rolledDice = new Die[4];

        //Initialisation du plateau et des 4 cases pour lancer les dés
        for(int i = 0; i < 4; i++) {
            rolledDice[i] = new Die(0, 0, false);
            for(int j = 0; j < 4; j++) {
                diceBoard[i][j] = new Die(0, 0, false);
            }
        }
    }
    
    /**Lancement des dés aléaoirement*/
    public void rollDice() {
        //On lance entre 1 et 4 dés selon le nombre de palces restantes
        for(int i = 0; i < 4; i++) {
            if(i < unlockedPlaces)
                rolledDice[i] = new Die((int)Math.round(Math.random() * 3 + 1),(int)Math.round(Math.random() * 3 + 1), false);
            else 
                rolledDice[i] = new Die(0, 0, false);
        }
    }
    
    /**Blocage des dés sur le plateau*/
    public void lockDice() {
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                if(!diceBoard[i][j].getLocked() && diceBoard[i][j].getColor() != 0)
                    diceBoard[i][j].setLocked(true);
            }
        }
    }
    
    public int getUnlockedPlaces() {
        return unlockedPlaces;
    }
    
    public void setUnlockedPlaces(int unlockedPlaces) {
        this.unlockedPlaces = unlockedPlaces;
    }
    
    public Die[][] getDiceBoard() {
        return diceBoard;
    }
    
    public Die[] getRolledDice() {
        return rolledDice;
    }
    
    public Die getRolledDie(int posX) {
        return rolledDice[posX];
    }
    
    public Die getBoardDie(int posX, int posY) {
        return diceBoard[posX][posY];
    }
    
    public void setDiceBoard(Die[][] diceBoard) {
        this.diceBoard = diceBoard;
    }
    
    public void setBoardDie(Die die, int posX, int posY) {
        diceBoard[posX][posY] = die;
    }
    
    public void setRolledDie(Die die, int posX) {
        rolledDice[posX] = die;
    }
}