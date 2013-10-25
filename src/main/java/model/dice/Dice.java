package model.dice;

public abstract class Dice {
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
}
