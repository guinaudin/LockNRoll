package model.dice;

public class DiceTypes {
    public enum Color { 
        YELLOW(1), GREEN(2), RED(3), BLUE(4);

        private int color;

        private Color(int color) {
            this.color = color;
        }

        public int getInt() {
            return color;
        } 
    }
};