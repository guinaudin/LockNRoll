package model.player;

import java.io.Serializable;

public class LeaderBoard implements Serializable {
    private String name;
    private int score;
    
    public LeaderBoard(String name, int score) {
        this.name = name;
        this.score = score;
    }
    
    public String getName() {
        return name;
    }
    
    public int getScore() {
        return score;
    }
}
