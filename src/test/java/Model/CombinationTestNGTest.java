package Model;

import Model.Dice.Dice;
import Model.Dice.DiceTypes;
import Model.Dice.GameDice;
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
}