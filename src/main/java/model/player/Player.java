package model.player;

import java.io.Serializable;
import model.dice.Die;

public class Player implements Serializable {
    private String name;
    private int score;
    private boolean[] cleanRollJokerActivated;
    private boolean[] cleanRollJoker;
    private int[] jokerScoreArray;
    private Die[] bombJoker;
    private int actualJokerScore;
    private int nbCleanRollJoker;
    private int nbBombJoker;

    public Player() {
        score = 0;
        nbCleanRollJoker = 0;
        nbBombJoker = 0;
        cleanRollJoker = new boolean[] {false, false};
        bombJoker = new Die[] {new Die(0, 0, false), new Die(0, 0, false)};
        cleanRollJokerActivated = new boolean[] {false, false};
        name = "";
        actualJokerScore = 0;
        jokerScoreArray = new int[] {250, 750, 1500, 2500};
    }
    
    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }
    
    public boolean getCleanRollJokerActivated(int position) {
        return cleanRollJokerActivated[position];
    }
    
    public void setCleanRollJokerActivated(boolean cleanRollJokerActivated, int position) {
        this.cleanRollJokerActivated[position] = cleanRollJokerActivated;
    }
    
    public boolean getCleanRollJoker(int position) {
        return cleanRollJoker[position];
    }
    
    public void setCleanRollJoker(boolean cleanRollJoker, int position) {
        this.cleanRollJoker[position] = cleanRollJoker;
    }
   
    public int getNbCleanRollJoker() {
        return nbCleanRollJoker;
    }
    
    public void setNbCleanRollJoker(int nbCleanRollJoker) {
        this.nbCleanRollJoker = nbCleanRollJoker;
    }

    public int getActualJokerScore() {
        return actualJokerScore;
    }
    
    public void setActualJokerScore(int actualJokerScore) {
        this.actualJokerScore = actualJokerScore;
    }
    
    public int[] getJokerScoreArray() {
        return jokerScoreArray;
    }
    
    public Die[] getBombJoker() {
        return bombJoker;
    }
    
    public Die getBombJokerDie(int posX) {
        return bombJoker[posX];
    }
    
    public void setBombJokerDie(Die die, int posX) {
        bombJoker[posX] = die;
    }
    
    public int getJokerScore(int jokerScore) {
        return jokerScoreArray[jokerScore];
    }
    
    public int getNbBombJoker() {
        return nbBombJoker;
    }
    
    public void setNbBombJoker(int nbBombJoker) {
        this.nbBombJoker = nbBombJoker;
    }
    
    public void setTwoLastJokerScore(int jokerScore) {
        this.jokerScoreArray[3] = this.jokerScoreArray[3] + jokerScore;
        this.jokerScoreArray[2] = this.jokerScoreArray[2] + jokerScore;
    }
}
