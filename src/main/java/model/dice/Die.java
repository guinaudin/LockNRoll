package model.dice;

/**Classe définissant le dé*/
public class Die {
    protected int value;
    protected int color;
    private boolean locked;
    
    public Die(int value,int color, boolean locked) {
        this.value = value;
        this.color = color;
        this.locked = locked;
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
