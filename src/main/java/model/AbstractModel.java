package model;

import observer.Observable;
import observer.Observer;
import java.util.ArrayList;
import model.board.Board;
import model.dice.Die;
import model.player.Player;

/**Classe abstraite définissant les méthodes abstraites du modèle*/
public abstract class AbstractModel implements Observable {
    private ArrayList<Observer> listObserver = new ArrayList<Observer>();
    
    /**Démarrage d'un nouveau jeu*/
    public abstract void startNewGame();
    /**Méthode appeler après clic sur le bouton Roll*/
    public abstract void makeTurn();
    /**Lancement aléatoire des dés dans la colonne de tirage*/
    public abstract void rollDice();
    
    /**sélectionner un dé sur le plateau*/
    public abstract Die selectBoardDie(int posX, int posY);
    /**sélectionner un dé sur la colonne de tirage*/
    public abstract Die selectRolledDie(int posX);
    /**sélectionner le joker bomb*/
    public abstract Die selectBombJoker(int posX);
    
    /**Déplacement d'un dé provenant du plateau sur le plateau*/
    public abstract void moveBoardDie(Die dice, int posX, int posY, int selectedPosX, int selectedPosY);
    /**Déplacement d'un dé provenant du plateau sur la colonne de tirage*/
    public abstract void moveBoardDie(Die dice, int posX, int selectedPosX, int selectedPosY);
    /**Déplacement d'un dé provenant de la colonne de tirage sur le lateau*/
    public abstract void moveRolledDie(Die die, int posX, int posY, int selectedPosX);
    /**Déplacement du joker bomb sur le plateau*/
    public abstract void moveBombJoker(Die die, int posX, int posY, int selectedPosX);
    /**Activation du joker pour faire un nouveau tirage de dé aléatoire*/
    public abstract boolean activateCleanRollJoker(int posX);
    
    /**On ajoute un observateur*/
    @Override
    public void addObserver(Observer obs) {
        this.listObserver.add(obs);
    }
    
    /**On retire un observateur*/
    @Override
    public void removeObserver() {
        listObserver = new ArrayList<Observer>();
    }
    
    /**Mise à jour des vues des observateurs sur le plateau de jeu et la colonne de tirage*/
    @Override
    public void notifyBoardObserver(Board board) {
        for(Observer obs : listObserver) {
             obs.updateBoardDice(board);
             obs.updateRolledDice(board);
        }
    }
    
    /**Mise à jour des vues des observateurs sur le score du joueur*/
    @Override
    public void notifyScoreObserver(Player player, Board board) {
        for(Observer obs : listObserver) {
             obs.updateScore(player, board);
        }
    }
    
    /**Mise à jour des vues des observateurs sur les joker que possède le joueur*/
    @Override
    public void notifyJokerObserver(Player player) {
        for(Observer obs : listObserver) {
             obs.updateBombJoker(player);
             obs.updateCleanRollJoker(player);
        }
    }
}
