package model.player;

import model.dice.Die;

public class Player {
    private String name;
    private int score;
    private boolean[] cleanRollJokerActivated;
    private boolean[] cleanRollJoker;
    private int[] jokerScoreArray;
    private Die[] bombJoker;
    private int actualJokerScore;
    private int nbCleanRollJoker;

    public Player() {
        score = 0;
        nbCleanRollJoker = 0;
        cleanRollJoker = new boolean[] {false, false};
        bombJoker = new Die[] {null, null};
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
    
    public int getJokerScore(int jokerScore) {
        return jokerScoreArray[jokerScore];
    }
}
