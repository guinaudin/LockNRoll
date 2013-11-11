package model.dice;

public class Dice {
    protected int value;
    protected int color;
    private boolean locked;

    public Dice() {
        value = 0;
        color = 0;
        locked = false;
    }
    
    public Dice(int value,int color) {
        this.value = value;
        this.color = color;
        locked = true;
    }
    
    public boolean getLocked() {
        return locked;
    }
    
    public int getColor() {
        return color;
    }
    
    public int getValue() {
        return value;
    }
    
    public void setColor(int color) {
        this.color = color;
    }
    
    public void setValue(int value) {
        this.value = value;
    }
    
    public void setLocked(boolean locked) {
        this.locked = locked;
    }
}
