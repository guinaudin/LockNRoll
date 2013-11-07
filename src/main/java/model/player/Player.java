package model.player;

public class Player {
    private String name;
    private int score;
    private int nbJokerDice;

    public Player() {

    }
    
    public void setScore(int score) {
        this.score = this.score + score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public int getNBJokerDice() {
        return nbJokerDice;
    }
}
