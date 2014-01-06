package model;

import model.dice.Die;

/**Classe analysant les combinaisons de 4 dés*/
public class CombinationTest {
    private int color;
    private int value;
    
    public CombinationTest() {

    }
    
    /**Détection de la combinaison même nombre et même couleur*/
    public boolean testSameColorSameNumber(Die[] combination) {
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
    
    /**Détection de la combinaison tous les nombres et même couleur*/
    public boolean testSameColorEachNumber(Die[] combination) {
        return this.testSameColor(combination) && this.testEachNumber(combination);
    }
    
    /**Détection de la combinaison toutes les couleurs et même nombre*/
    public boolean testEachColorSameNumber(Die[] combination) {
        return this.testEachColor(combination) && this.testSameNumber(combination);
    }
    
    /**Détection de la combinaison toutes les couleurs et tous les nombres*/
    public boolean testEachColorEachNumber(Die[] combination) {
        return this.testEachColor(combination) && this.testEachNumber(combination);
    }
    
    /**Détection de la combinaison avec une paire de même couleur et même nombre*/
    public boolean testTwoPair(Die[] combination) {
        int i = 0;
        int colorBis = 0;
        int valueBis = 0;
        Die[] pairNumber1 = new Die[2]; 
        Die[] pairNumber2 = new Die[2];
        
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
    
    /**Détection de la combinaison avec une paire de couleur*/
    public boolean testPairColor(Die[] combination) {
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
    
    /**Détection de la combinaison avec une paire de nombre*/
    public boolean testPairNumber(Die[] combination) {
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
    
    /**Détection de la combinaison avec une paire de nombre différents et de même couleur*/
    public boolean testPairColorPairNumber(Die[] combination) {
        int i = 0;
        int colorBis = 0;
        int valueBis = 0;
        Die[] pairNumber1 = new Die[2]; 
        Die[] pairNumber2 = new Die[2];
        
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
                if(valueBis == combination[i].getValue())
                    pairNumber1[1] = combination[i];
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
        
        return pairNumber1[1] != null && pairNumber2[1] != null && pairNumber1[0] != null && pairNumber2[0] != null;
    }
    
    /**Détection de la combinaison ayant même couleur*/
    public boolean testSameColor(Die[] combination) {
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
    
    /**Détection de la combinaison ayant même nombre*/
    public boolean testSameNumber(Die[] combination) {
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
    
    /**Détection de la combinaison ayant tous les nombres*/
    public boolean testEachNumber(Die[] combination) {
        int i = -1;
        int j;
        int cpt;
        
        do{
            i++;
            //reinitialisation des variables de la boucle interne
            cpt = 0;
            j = 0;
            
            //boucle tant que on n'a pas verifié l'ensemble des numeros precedents
            while(i > j)
            {             
                //si le numero est different du jieme precedent numero
                if(combination[i].getValue() != combination[j].getValue())
                    cpt++;
                //on verifie le numero suivant
                j++;
            } 
        }while(cpt == i && i < 3);
        
        return cpt == i && i == 3;
    }
    
    /**Détection de la combinaison ayant toutes les couleurs*/
    public boolean testEachColor(Die[] combination) {
        int i = -1;
        int j;
        int cpt;
        
        do{
            i++;
            //reinitialisation des variables de la boucle interne
            cpt = 0;
            j = 0;
            
            //boucle tant que on n'a pas verifié l'ensemble des numeros precedents
            while(i > j)
            {             
                //si le numero est different du jieme precedent numero
                if(combination[i].getColor() != combination[j].getColor())
                    cpt++;
                //on verifie le numero suivant*/
                j++;
            } 
        }while(cpt == i && i < 3);
        
        return cpt == i && i == 3;
    }
}
