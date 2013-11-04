package model;

import model.dice.Dice;
import model.dice.DiceTypes;
import model.dice.GameDice;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CombinationTestNGTest {
    private CombinationTest CT;
    private Dice[] combination;
    
    public CombinationTestNGTest() {
    }

    @BeforeClass
    void setUpClass() {
        CT = new CombinationTest();
        combination = new Dice[4];
    }

    @AfterClass
    void tearDownClass()
    {
        CT = null;
        for(int i = 0; i < combination.length; i++)
            combination[i] = null;
    }
    
    /**################################################################
    #                       SameColorSameNumber                       #
    ################################################################**/

    @DataProvider(name = "CorrectSameColorSameNumber")
    public Object[][] createCorrectSameColorSameNumberCombination() {
        return new Object[][] {
            { new Dice[] {
                new GameDice(1, DiceTypes.Color.BLUE.getInt()), 
                new GameDice(1, DiceTypes.Color.BLUE.getInt()),
                new GameDice(1, DiceTypes.Color.BLUE.getInt()), 
                new GameDice(1, DiceTypes.Color.BLUE.getInt()) },
            },
                
            { new Dice[] {
                new GameDice(2, DiceTypes.Color.YELLOW.getInt()), 
                new GameDice(2, DiceTypes.Color.YELLOW.getInt()),
                new GameDice(2, DiceTypes.Color.YELLOW.getInt()), 
                new GameDice(2, DiceTypes.Color.YELLOW.getInt()) },
            },
        };
    }
    
    @DataProvider(name = "IncorrectSameColorSameNumber")
    public Object[][] createIncorrectSameColorSameNumberCombination() {
        return new Object[][] {
            { new Dice[] {
                new GameDice(1, DiceTypes.Color.BLUE.getInt()), 
                new GameDice(1, DiceTypes.Color.BLUE.getInt()),
                new GameDice(1, DiceTypes.Color.BLUE.getInt()), 
                new GameDice(2, DiceTypes.Color.BLUE.getInt()) },
            },
                
            { new Dice[] {
                new GameDice(2, DiceTypes.Color.YELLOW.getInt()), 
                new GameDice(2, DiceTypes.Color.YELLOW.getInt()),
                new GameDice(2, DiceTypes.Color.YELLOW.getInt()), 
                new GameDice(2, DiceTypes.Color.BLUE.getInt()) },
            },
        };
    }
    
    @Test(dataProvider = "CorrectSameColorSameNumber")
    void testCorrectSameColorSameNumberCombination(Dice[] combination) {
        Assert.assertEquals(CT.testSameColorSameNumber(combination), true);
    }
    
    @Test(dataProvider = "IncorrectSameColorSameNumber")
    void testIncorrectSameColorSameNumberCombination(Dice[] combination) {
        Assert.assertEquals(CT.testSameColorSameNumber(combination), false);
    }
    
    /**################################################################
    #                       SameColorEAchNumber                       #
    ################################################################**/
    
    @DataProvider(name = "CorrectSameColorEachNumber")
    public Object[][] createCorrectSameColorEachNumberCombination() {
        return new Object[][] {
            { new Dice[] {
                new GameDice(1, DiceTypes.Color.BLUE.getInt()), 
                new GameDice(4, DiceTypes.Color.BLUE.getInt()),
                new GameDice(3, DiceTypes.Color.BLUE.getInt()), 
                new GameDice(2, DiceTypes.Color.BLUE.getInt()) },
            },
                
            { new Dice[] {
                new GameDice(1, DiceTypes.Color.YELLOW.getInt()), 
                new GameDice(2, DiceTypes.Color.YELLOW.getInt()),
                new GameDice(3, DiceTypes.Color.YELLOW.getInt()), 
                new GameDice(4, DiceTypes.Color.YELLOW.getInt()) },
            },
        };
    }
    
    @DataProvider(name = "IncorrectSameColorEachNumber")
    public Object[][] createIncorrectSameColorEachNumberCombination() {
        return new Object[][] {
            { new Dice[] {
                new GameDice(1, DiceTypes.Color.BLUE.getInt()), 
                new GameDice(2, DiceTypes.Color.BLUE.getInt()),
                new GameDice(3, DiceTypes.Color.BLUE.getInt()), 
                new GameDice(2, DiceTypes.Color.BLUE.getInt()) },
            },
                
            { new Dice[] {
                new GameDice(1, DiceTypes.Color.YELLOW.getInt()), 
                new GameDice(2, DiceTypes.Color.YELLOW.getInt()),
                new GameDice(4, DiceTypes.Color.YELLOW.getInt()), 
                new GameDice(3, DiceTypes.Color.BLUE.getInt()) },
            },
        };
    }   
    
    @Test(dataProvider = "CorrectSameColorEachNumber")
    void testCorrectSameColorEachNumberCombination(Dice[] combination) {
        Assert.assertEquals(CT.testSameColorEachNumber(combination), true);
    }
    
    @Test(dataProvider = "IncorrectSameColorEachNumber")
    void testIncorrectSameColorEachNumberCombination(Dice[] combination) {
        Assert.assertEquals(CT.testSameColorEachNumber(combination), false);
    }
    
    /**################################################################
    #                       EachColorSameNumber                       #
    ################################################################**/
    
    @DataProvider(name = "CorrectEachColorSameNumber")
    public Object[][] createCorrectEachColorSameNumberCombination() {
        return new Object[][] {
            { new Dice[] {
                new GameDice(3, DiceTypes.Color.BLUE.getInt()), 
                new GameDice(3, DiceTypes.Color.GREEN.getInt()),
                new GameDice(3, DiceTypes.Color.YELLOW.getInt()), 
                new GameDice(3, DiceTypes.Color.RED.getInt()) },
            },
                
            { new Dice[] {
                new GameDice(1, DiceTypes.Color.RED.getInt()), 
                new GameDice(1, DiceTypes.Color.BLUE.getInt()),
                new GameDice(1, DiceTypes.Color.YELLOW.getInt()), 
                new GameDice(1, DiceTypes.Color.GREEN.getInt()) },
            },
        };
    }
    
    @DataProvider(name = "IncorrectEachColorSameNumber")
    public Object[][] createIncorrectEachColorSameNumberCombination() {
        return new Object[][] {
            { new Dice[] {
                new GameDice(2, DiceTypes.Color.RED.getInt()), 
                new GameDice(2, DiceTypes.Color.BLUE.getInt()),
                new GameDice(2, DiceTypes.Color.YELLOW.getInt()), 
                new GameDice(2, DiceTypes.Color.BLUE.getInt()) },
            },
                
            { new Dice[] {
                new GameDice(3, DiceTypes.Color.YELLOW.getInt()), 
                new GameDice(3, DiceTypes.Color.GREEN.getInt()),
                new GameDice(3, DiceTypes.Color.RED.getInt()), 
                new GameDice(4, DiceTypes.Color.BLUE.getInt()) },
            },
        };
    }

    @Test(dataProvider = "CorrectEachColorSameNumber")
    void testCorrectEachColorSameNumberCombination(Dice[] combination) {
        Assert.assertEquals(CT.testEachColorSameNumber(combination), true);
    }
    
    @Test(dataProvider = "IncorrectSameColorEachNumber")
    void testIncorrectEachColorSameNumberCombination(Dice[] combination) {
        Assert.assertEquals(CT.testEachColorSameNumber(combination), false);
    }
    
    /**################################################################
    #                       EachColorEachNumber                       #
    ################################################################**/
    
    @DataProvider(name = "CorrectEachColorEachNumber")
    public Object[][] createCorrectEachColorEachNumberCombination() {
        return new Object[][] {
            { new Dice[] {
                new GameDice(2, DiceTypes.Color.BLUE.getInt()), 
                new GameDice(3, DiceTypes.Color.GREEN.getInt()),
                new GameDice(4, DiceTypes.Color.YELLOW.getInt()), 
                new GameDice(1, DiceTypes.Color.RED.getInt()) },
            },
                
            { new Dice[] {
                new GameDice(1, DiceTypes.Color.RED.getInt()), 
                new GameDice(2, DiceTypes.Color.BLUE.getInt()),
                new GameDice(4, DiceTypes.Color.YELLOW.getInt()), 
                new GameDice(3, DiceTypes.Color.GREEN.getInt()) },
            },
        };
    }
    
    @DataProvider(name = "IncorrectEachColorEachNumber")
    public Object[][] createIncorrectEachColorEachNumberCombination() {
        return new Object[][] {
            { new Dice[] {
                new GameDice(2, DiceTypes.Color.RED.getInt()), 
                new GameDice(1, DiceTypes.Color.BLUE.getInt()),
                new GameDice(3, DiceTypes.Color.YELLOW.getInt()), 
                new GameDice(2, DiceTypes.Color.BLUE.getInt()) },
            },
                
            { new Dice[] {
                new GameDice(3, DiceTypes.Color.YELLOW.getInt()), 
                new GameDice(1, DiceTypes.Color.GREEN.getInt()),
                new GameDice(2, DiceTypes.Color.RED.getInt()), 
                new GameDice(4, DiceTypes.Color.GREEN.getInt()) },
            },
        };
    }

    @Test(dataProvider = "CorrectEachColorEachNumber")
    void testCorrectEachColorEachNumberCombination(Dice[] combination) {
        Assert.assertEquals(CT.testEachColorEachNumber(combination), true);
    }
    
    @Test(dataProvider = "IncorrectEachColorEachNumber")
    void testIncorrectEachColorEachNumberCombination(Dice[] combination) {
        Assert.assertEquals(CT.testEachColorEachNumber(combination), false);
    }
    
    /**################################################################
    #                             Two Pair                            #
    ################################################################**/
    
    @DataProvider(name = "CorrectTwoPair")
    public Object[][] createCorrectTwoPairCombination() {
        return new Object[][] {
            { new Dice[] {
                new GameDice(2, DiceTypes.Color.BLUE.getInt()), 
                new GameDice(3, DiceTypes.Color.GREEN.getInt()),
                new GameDice(3, DiceTypes.Color.GREEN.getInt()), 
                new GameDice(2, DiceTypes.Color.BLUE.getInt()) },
            },
                
            { new Dice[] {
                new GameDice(1, DiceTypes.Color.RED.getInt()), 
                new GameDice(1, DiceTypes.Color.RED.getInt()),
                new GameDice(4, DiceTypes.Color.YELLOW.getInt()), 
                new GameDice(4, DiceTypes.Color.YELLOW.getInt()) },
            },
        };
    }
    
    @DataProvider(name = "IncorrectTwoPair")
    public Object[][] createIncorrectTwoPairCombination() {
        return new Object[][] {
            { new Dice[] {
                new GameDice(2, DiceTypes.Color.RED.getInt()), 
                new GameDice(2, DiceTypes.Color.RED.getInt()),
                new GameDice(3, DiceTypes.Color.YELLOW.getInt()), 
                new GameDice(3, DiceTypes.Color.BLUE.getInt()) },
            },
                
            { new Dice[] {
                new GameDice(3, DiceTypes.Color.YELLOW.getInt()), 
                new GameDice(4, DiceTypes.Color.GREEN.getInt()),
                new GameDice(2, DiceTypes.Color.RED.getInt()), 
                new GameDice(4, DiceTypes.Color.GREEN.getInt()) },
            },
        };
    }

    @Test(dataProvider = "CorrectTwoPair")
    void testCorrectTwoPairCombination(Dice[] combination) {
        Assert.assertEquals(CT.testTwoPair(combination), true);
    }
    
    @Test(dataProvider = "IncorrectTwoPair")
    void testIncorrectTwoPairCombination(Dice[] combination) {
        Assert.assertEquals(CT.testTwoPair(combination), false);
    }
    
    /**################################################################
    #                       PairColorPairNumber                       #
    ################################################################**/
    
    @DataProvider(name = "CorrectPairColorPairNumber")
    public Object[][] createCorrectPairColorPairNumberCombination() {
        return new Object[][] {
            { new Dice[] {
                new GameDice(2, DiceTypes.Color.BLUE.getInt()), 
                new GameDice(3, DiceTypes.Color.GREEN.getInt()),
                new GameDice(2, DiceTypes.Color.GREEN.getInt()), 
                new GameDice(3, DiceTypes.Color.BLUE.getInt()) },
            },
                
            { new Dice[] {
                new GameDice(1, DiceTypes.Color.RED.getInt()), 
                new GameDice(4, DiceTypes.Color.RED.getInt()),
                new GameDice(4, DiceTypes.Color.YELLOW.getInt()), 
                new GameDice(1, DiceTypes.Color.YELLOW.getInt()) },
            },
        };
    }
    
    @DataProvider(name = "IncorrectPairColorPairNumber")
    public Object[][] createIncorrectPairColorPairNumberCombination() {
        return new Object[][] {
            { new Dice[] {
                new GameDice(2, DiceTypes.Color.RED.getInt()), 
                new GameDice(2, DiceTypes.Color.RED.getInt()),
                new GameDice(3, DiceTypes.Color.YELLOW.getInt()), 
                new GameDice(3, DiceTypes.Color.BLUE.getInt()) },
            },
                
            { new Dice[] {
                new GameDice(3, DiceTypes.Color.YELLOW.getInt()), 
                new GameDice(4, DiceTypes.Color.GREEN.getInt()),
                new GameDice(2, DiceTypes.Color.RED.getInt()), 
                new GameDice(4, DiceTypes.Color.GREEN.getInt()) },
            },
        };
    }

    @Test(dataProvider = "CorrectPairColorPairNumber")
    void testCorrectPairColorPairNumberCombination(Dice[] combination) {
        Assert.assertEquals(CT.testPairColorPairNumber(combination), true);
    }
    
    @Test(dataProvider = "IncorrectPairColorPairNumber")
    void testIncorrectPairColorPairNumberCombination(Dice[] combination) {
        Assert.assertEquals(CT.testPairColorPairNumber(combination), false);
    }
    
    /**################################################################
    #                            PairColor                            #
    ################################################################**/
    
    @DataProvider(name = "CorrectPairColor")
    public Object[][] createCorrectPairColorCombination() {
        return new Object[][] {
            { new Dice[] {
                new GameDice(2, DiceTypes.Color.BLUE.getInt()), 
                new GameDice(3, DiceTypes.Color.GREEN.getInt()),
                new GameDice(2, DiceTypes.Color.GREEN.getInt()), 
                new GameDice(3, DiceTypes.Color.BLUE.getInt()) },
            },
                
            { new Dice[] {
                new GameDice(1, DiceTypes.Color.RED.getInt()), 
                new GameDice(4, DiceTypes.Color.RED.getInt()),
                new GameDice(4, DiceTypes.Color.YELLOW.getInt()), 
                new GameDice(1, DiceTypes.Color.YELLOW.getInt()) },
            },
        };
    }
    
    @DataProvider(name = "IncorrectPairColor")
    public Object[][] createIncorrectPairColorCombination() {
        return new Object[][] {
            { new Dice[] {
                new GameDice(2, DiceTypes.Color.RED.getInt()), 
                new GameDice(2, DiceTypes.Color.RED.getInt()),
                new GameDice(3, DiceTypes.Color.YELLOW.getInt()), 
                new GameDice(3, DiceTypes.Color.BLUE.getInt()) },
            },
                
            { new Dice[] {
                new GameDice(3, DiceTypes.Color.YELLOW.getInt()), 
                new GameDice(4, DiceTypes.Color.GREEN.getInt()),
                new GameDice(2, DiceTypes.Color.RED.getInt()), 
                new GameDice(4, DiceTypes.Color.GREEN.getInt()) },
            },
        };
    }

    @Test(dataProvider = "CorrectPairColor")
    void testCorrectPairColorCombination(Dice[] combination) {
        Assert.assertEquals(CT.testPairColor(combination), true);
    }
    
    @Test(dataProvider = "IncorrectPairColor")
    void testIncorrectPairColorCombination(Dice[] combination) {
        Assert.assertEquals(CT.testPairColor(combination), false);
    }
    
    /**################################################################
    #                            PairNumber                            #
    ################################################################**/
    
    @DataProvider(name = "CorrectPairNumber")
    public Object[][] createCorrectPairNumberCombination() {
        return new Object[][] {
            { new Dice[] {
                new GameDice(2, DiceTypes.Color.BLUE.getInt()), 
                new GameDice(3, DiceTypes.Color.GREEN.getInt()),
                new GameDice(2, DiceTypes.Color.GREEN.getInt()), 
                new GameDice(3, DiceTypes.Color.BLUE.getInt()) },
            },
                
            { new Dice[] {
                new GameDice(1, DiceTypes.Color.RED.getInt()), 
                new GameDice(4, DiceTypes.Color.RED.getInt()),
                new GameDice(4, DiceTypes.Color.YELLOW.getInt()), 
                new GameDice(1, DiceTypes.Color.YELLOW.getInt()) },
            },
        };
    }
    
    @DataProvider(name = "IncorrectPairNumber")
    public Object[][] createIncorrectPairNumberCombination() {
        return new Object[][] {
            { new Dice[] {
                new GameDice(2, DiceTypes.Color.RED.getInt()), 
                new GameDice(2, DiceTypes.Color.RED.getInt()),
                new GameDice(3, DiceTypes.Color.YELLOW.getInt()), 
                new GameDice(4, DiceTypes.Color.BLUE.getInt()) },
            },
                
            { new Dice[] {
                new GameDice(3, DiceTypes.Color.YELLOW.getInt()), 
                new GameDice(4, DiceTypes.Color.GREEN.getInt()),
                new GameDice(2, DiceTypes.Color.RED.getInt()), 
                new GameDice(4, DiceTypes.Color.GREEN.getInt()) },
            },
        };
    }

    @Test(dataProvider = "CorrectPairNumber")
    void testCorrectPairNumberCombination(Dice[] combination) {
        Assert.assertEquals(CT.testPairNumber(combination), true);
    }
    
    @Test(dataProvider = "IncorrectPairNumber")
    void testIncorrectPairNumberCombination(Dice[] combination) {
        Assert.assertEquals(CT.testPairNumber(combination), false);
    }
}