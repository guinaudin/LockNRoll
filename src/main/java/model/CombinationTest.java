package Model;

import Model.Dice.Dice;
import Model.Dice.GameDice;

public class CombinationTest {
    private int color;
    private int value;
    
    public CombinationTest() {

    }
    
    public boolean testSameColorSameNumber(Dice[] combination) {
        int i = 0;
        
        color = combination[i].getColor();
        value = combination[i].getValue();
        
        do {
            //System.out.print("color(i) = " + color + " (color(i+1) = " + combination[i+1].getColor());
            //System.out.println(" value(i) = " + value + " (value(i+1) = " + combination[i+1].getValue() + "\n");
            
            if(color == combination[i+1].getColor() && value == combination[i+1].getValue())
                i++;
            else
                i = 4;
        }while(i < 3);

        return i == 3;
    }
    
    public boolean testSameColorEachNumber(Dice[] combination) {
        return this.testSameColor(combination) && this.testEachNumber(combination);
    }
    
    public boolean testEachColorSameNumber(Dice[] combination) {
        return this.testEachColor(combination) && this.testSameNumber(combination);
    }
    
    public boolean testEachColorEachNumber(Dice[] combination) {
        return this.testEachColor(combination) && this.testEachNumber(combination);
    }
    
    public boolean testTwoPair(Dice[] combination) {
        int i = 0;
        int colorBis = 0;
        int valueBis = 0;
        Dice[] pairNumber1 = new GameDice[2]; 
        Dice[] pairNumber2 = new GameDice[2];
        
        for(int j = 0; j < 2; j++) {
            pairNumber1[j] = null;
            pairNumber2[j] = null;
        }
        
        color = combination[i].getColor();
        value = combination[i].getValue();
        
        pairNumber1[0] = combination[i];
        
        do {
            i++;
            if(color == combination[i].getColor() && value == combination[i].getValue())
                pairNumber1[1] = combination[i];
            else if(pairNumber2[0] == null) {
                valueBis = combination[i].getValue();
                colorBis = combination[i].getColor();
                pairNumber2[0] = combination[i];
            }
            else if(colorBis == combination[i].getColor() && valueBis == combination[i].getValue())
                pairNumber2[1] = combination[i];
            else
                i = 3;
        }while(i < 3);
        
        return pairNumber1[1] != null && pairNumber2[1] != null;
    }
    
    /*public boolean testPairColorPairNumber(Dice[] combination) {
        int i = 0;
        int colorBis = 0;
        int valueBis = 0;
        Dice[] pairNumber1 = new GameDice[2]; 
        Dice[] pairNumber2 = new GameDice[2];
        
        for(int j = 0; j < 2; j++) {
            pairNumber1[j] = null;
            pairNumber2[j] = null;
        }
        
        color = combination[i].getColor();
        value = combination[i].getValue();
        
        pairNumber1[0] = combination[i];
        System.out.println("pairNumber1[0] " + value + " " + color);
        
        do {
            i++;
            if(color == combination[i].getColor() && value != combination[i].getValue() && pairNumber1[1] == null)
                valueBis = combination[i].getValue();
                pairNumber1[1] = combination[i];
            else if(color != combination[i].getColor() && (value == combination[i].getValue() || valueBis == combination[i].getValue())) {
                colorBis = combination[i].getColor();
                pairNumber2[0] = combination[i];
            }
            else if(colorBis == combination[i].getColor() && (value == combination[i].getValue()))
                pairNumber2[1] = combination[i];
            else
                i = 3;
        }while(i < 3);
        
        return pairNumber1[1] != null && pairNumber2[1] != null;
    }*/
    
    public boolean testSameColor(Dice[] combination) {
        int i = 0;
        
        color = combination[i].getColor();
        
        do {
            if(color == combination[i+1].getColor())
                i++;
            else
                i = 4;
        }while(i < 3);

        return i == 3;
    }
    
    public boolean testSameNumber(Dice[] combination) {
        int i = 0;
        
        value = combination[i].getValue();
        
        do {
            if(value == combination[i+1].getValue())
                i++;
            else
                i = 4;
        }while(i < 3);
        
        return i == 3;
    }
    
    public boolean testEachNumber(Dice[] combination) {
        int i = 0;
        int j;
        int cpt;
        
        do{   
            /**reinitialisation des variables de la boucle interne*/
            cpt = 0;
            j = 0;
            
            /**boucle tant que on n'a pas verifié l'ensemble des numeros precedents*/
            while(i > j)
            {
                /**si le numero est different du jieme precedent numero*/
                if(combination[i].getValue() != combination[j].getValue())
                    cpt++;
                /**on verifie le numero suivant*/
                j++;
            }
            
            i++;
        }while(cpt == i || i < 4);
        
        return cpt == 3;
    }
    
    public boolean testEachColor(Dice[] combination) {
        int i = 0;
        int j;
        int cpt;
        
        do{
            /**reinitialisation des variables de la boucle interne*/
            cpt = 0;
            j = 0;
            
            /**boucle tant que on n'a pas verifié l'ensemble des numeros precedents*/
            while(i > j)
            {             
                /**si le numero est different du jieme precedent numero*/
                if(combination[i].getColor() != combination[j].getColor())
                    cpt++;
                /**on verifie le numero suivant*/
                j++;
            }

            i++;
        }while(cpt == i || i < 4);
        
        return cpt == 3;
    }
}
