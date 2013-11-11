package model;

import model.dice.Dice;
import model.dice.GameDice;

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
    
    public boolean testPairColor(Dice[] combination) {
        int i = 0;
        int colorBis = 0;
        int numColor = 0;
        int numColorBis = 0;
        
        color = combination[i].getColor();
        numColor++;
        
        do {
            i++;
            if(color == combination[i].getColor())
                numColor++;
            else if(color != combination[i].getColor() && colorBis == 0) {
                colorBis = combination[i].getColor();
                numColorBis++;
            }
            else if(colorBis == combination[i].getColor())
                numColorBis++;
            else
                i = 4;
        }while(i < 3);

        return numColor == 2 && numColorBis == 2;
    }
    
    public boolean testPairNumber(Dice[] combination) {
        int i = 0;
        int valueBis = 0;
        int numValue = 0;
        int numValueBis = 0;
        
        value = combination[i].getValue();
        numValue++;
        
        do {
            i++;
            if(value == combination[i].getValue())
                numValue++;
            else if(value != combination[i].getValue() && valueBis == 0) {
                valueBis = combination[i].getValue();
                numValueBis++;
            }
            else if(valueBis == combination[i].getValue())
                numValueBis++;
            else
                i = 4;
        }while(i < 3);

        return numValue == 2 && numValueBis == 2;
    }
    
    public boolean testPairColorPairNumber(Dice[] combination) {
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
            
            if(colorBis == 0 && color != combination[i].getColor()) {
                colorBis = combination[i].getColor();
            }
            
            if(color == combination[i].getColor()) {
                if(value != combination[i].getValue() && pairNumber1[1] == null) { 
                    valueBis = combination[i].getValue();
                    pairNumber1[1] = combination[i];
                }
                else
                    i = 3;
            }
            else if(colorBis == combination[i].getColor()) {
                if(value == combination[i].getValue()) {
                    pairNumber2[0] = combination[i];
                }
                else if(valueBis == 0) {
                    valueBis = combination[i].getValue();
                    pairNumber2[1] = combination[i];
                }
                else if(valueBis == combination[i].getValue()) {
                    pairNumber2[1] = combination[i];
                }
                else
                    i = 3;
            }
            else
                i = 3;
        }while(i < 3);
        
        return pairNumber1[1] != null && pairNumber2[1] != null;
    }
    
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
        int i = -1;
        int j;
        int cpt;
        
        do{
            i++;
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
            //System.out.println("i : " + i + " cpt : " + cpt);
        }while(cpt == i && i < 3);
        
        return cpt == i && i == 3;
    }
    
    public boolean testEachColor(Dice[] combination) {
        int i = -1;
        int j;
        int cpt;
        
        do{
            i++;
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
        }while(cpt == i && i < 3);
        
        return cpt == i && i == 3;
    }
}
