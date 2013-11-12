package model.dice;

public class Die {
    protected int value;
    protected int color;
    private boolean locked;
    //private boolean selected;

    public Die() {
        value = 0;
        color = 0;
        locked = false;
        //selected = false;
    }
    
    public Die(int value,int color, boolean locked) {
        this.value = value;
        this.color = color;
        locked = true;
        //selected = false;
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
    
    /*public void setSelected(boolean selected) {
        this.selected = selected;
    }
    
    public boolean getSelected() {
        return selected;
    }*/
}
