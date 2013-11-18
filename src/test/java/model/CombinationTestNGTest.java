package model;

import model.dice.Die;
import model.dice.DieTypes;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CombinationTestNGTest {
    private CombinationTest CT;
    private Die[] combination;
    
    public CombinationTestNGTest() {
    }

    @BeforeClass
    void setUpClass() {
        CT = new CombinationTest();
        combination = new Die[4];
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
            { new Die[] {
                new Die(1, DieTypes.Color.BLUE.getInt(), true), 
                new Die(1, DieTypes.Color.BLUE.getInt(), true),
                new Die(1, DieTypes.Color.BLUE.getInt(), true), 
                new Die(1, DieTypes.Color.BLUE.getInt(), true) },
            },
                
            { new Die[] {
                new Die(2, DieTypes.Color.YELLOW.getInt(), true), 
                new Die(2, DieTypes.Color.YELLOW.getInt(), true),
                new Die(2, DieTypes.Color.YELLOW.getInt(), true), 
                new Die(2, DieTypes.Color.YELLOW.getInt(), true) },
            },
        };
    }
    
    @DataProvider(name = "IncorrectSameColorSameNumber")
    public Object[][] createIncorrectSameColorSameNumberCombination() {
        return new Object[][] {
            { new Die[] {
                new Die(1, DieTypes.Color.BLUE.getInt(), true), 
                new Die(1, DieTypes.Color.BLUE.getInt(), true),
                new Die(1, DieTypes.Color.BLUE.getInt(), true), 
                new Die(2, DieTypes.Color.BLUE.getInt(), true) },
            },
                
            { new Die[] {
                new Die(2, DieTypes.Color.YELLOW.getInt(), true), 
                new Die(2, DieTypes.Color.YELLOW.getInt(), true),
                new Die(2, DieTypes.Color.YELLOW.getInt(), true), 
                new Die(2, DieTypes.Color.BLUE.getInt(), true) },
            },
        };
    }
    
    @Test(dataProvider = "CorrectSameColorSameNumber")
    void testCorrectSameColorSameNumberCombination(Die[] combination) {
        Assert.assertEquals(CT.testSameColorSameNumber(combination), true);
    }
    
    @Test(dataProvider = "IncorrectSameColorSameNumber")
    void testIncorrectSameColorSameNumberCombination(Die[] combination) {
        Assert.assertEquals(CT.testSameColorSameNumber(combination), false);
    }
    
    /**################################################################
    #                       SameColorEachNumber                       #
    ################################################################**/
    
    @DataProvider(name = "CorrectSameColorEachNumber")
    public Object[][] createCorrectSameColorEachNumberCombination() {
        return new Object[][] {
            { new Die[] {
                new Die(1, DieTypes.Color.BLUE.getInt(), true), 
                new Die(4, DieTypes.Color.BLUE.getInt(), true),
                new Die(3, DieTypes.Color.BLUE.getInt(), true), 
                new Die(2, DieTypes.Color.BLUE.getInt(), true) },
            },
                
            { new Die[] {
                new Die(1, DieTypes.Color.YELLOW.getInt(), true), 
                new Die(2, DieTypes.Color.YELLOW.getInt(), true),
                new Die(3, DieTypes.Color.YELLOW.getInt(), true), 
                new Die(4, DieTypes.Color.YELLOW.getInt(), true) },
            },
        };
    }
    
    @DataProvider(name = "IncorrectSameColorEachNumber")
    public Object[][] createIncorrectSameColorEachNumberCombination() {
        return new Object[][] {
           { new Die[] {
                new Die(1, DieTypes.Color.BLUE.getInt(), true), 
                new Die(2, DieTypes.Color.BLUE.getInt(), true),
                new Die(3, DieTypes.Color.BLUE.getInt(), true), 
                new Die(2, DieTypes.Color.BLUE.getInt(), true) },
            },
                
            { new Die[] {
                new Die(2, DieTypes.Color.YELLOW.getInt(), true), 
                new Die(2, DieTypes.Color.YELLOW.getInt(), true),
                new Die(4, DieTypes.Color.YELLOW.getInt(), true), 
                new Die(3, DieTypes.Color.YELLOW.getInt(), true) },
            },
            
            { new Die[] {
                new Die(1, DieTypes.Color.RED.getInt(), true), 
                new Die(2, DieTypes.Color.GREEN.getInt(), true),
                new Die(3, DieTypes.Color.GREEN.getInt(), true), 
                new Die(4, DieTypes.Color.GREEN.getInt(), true) },
            },
        };
    }   
    
    @Test(dataProvider = "CorrectSameColorEachNumber")
    void testCorrectSameColorEachNumberCombination(Die[] combination) {
        Assert.assertEquals(CT.testSameColorEachNumber(combination), true);
    }
    
    @Test(dataProvider = "IncorrectSameColorEachNumber")
    void testIncorrectSameColorEachNumberCombination(Die[] combination) {
        Assert.assertEquals(CT.testSameColorEachNumber(combination), false);
    }
    
    /**################################################################
    #                       EachColorSameNumber                       #
    ################################################################**/
    
    @DataProvider(name = "CorrectEachColorSameNumber")
    public Object[][] createCorrectEachColorSameNumberCombination() {
        return new Object[][] {
            { new Die[] {
                new Die(3, DieTypes.Color.BLUE.getInt(), true), 
                new Die(3, DieTypes.Color.GREEN.getInt(), true),
                new Die(3, DieTypes.Color.YELLOW.getInt(), true), 
                new Die(3, DieTypes.Color.RED.getInt(), true) },
            },
                
            { new Die[] {
                new Die(1, DieTypes.Color.RED.getInt(), true), 
                new Die(1, DieTypes.Color.BLUE.getInt(), true),
                new Die(1, DieTypes.Color.YELLOW.getInt(), true), 
                new Die(1, DieTypes.Color.GREEN.getInt(), true) },
            },
        };
    }
    
    @DataProvider(name = "IncorrectEachColorSameNumber")
    public Object[][] createIncorrectEachColorSameNumberCombination() {
        return new Object[][] {
            { new Die[] {
                new Die(2, DieTypes.Color.BLUE.getInt(), true), 
                new Die(2, DieTypes.Color.RED.getInt(), true),
                new Die(2, DieTypes.Color.YELLOW.getInt(), true), 
                new Die(2, DieTypes.Color.BLUE.getInt(), true) },
            },
                
            { new Die[] {
                new Die(3, DieTypes.Color.YELLOW.getInt(), true), 
                new Die(3, DieTypes.Color.GREEN.getInt(), true),
                new Die(3, DieTypes.Color.RED.getInt(), true), 
                new Die(4, DieTypes.Color.BLUE.getInt(), true) },
            },
        };
    }

    @Test(dataProvider = "CorrectEachColorSameNumber")
    void testCorrectEachColorSameNumberCombination(Die[] combination) {
        Assert.assertEquals(CT.testEachColorSameNumber(combination), true);
    }
    
    @Test(dataProvider = "IncorrectSameColorEachNumber")
    void testIncorrectEachColorSameNumberCombination(Die[] combination) {
        Assert.assertEquals(CT.testEachColorSameNumber(combination), false);
    }
    
    /**################################################################
    #                                EachColor                        #
    ################################################################**/
    
    @DataProvider(name = "CorrectEachColor")
    public Object[][] createCorrectEachColorCombination() {
        return new Object[][] {
            { new Die[] {
                new Die(3, DieTypes.Color.BLUE.getInt(), true), 
                new Die(3, DieTypes.Color.RED.getInt(), true),
                new Die(3, DieTypes.Color.YELLOW.getInt(), true), 
                new Die(3, DieTypes.Color.GREEN.getInt(), true) },
            },
                
            { new Die[] {
                new Die(1, DieTypes.Color.RED.getInt(), true), 
                new Die(1, DieTypes.Color.BLUE.getInt(), true),
                new Die(1, DieTypes.Color.YELLOW.getInt(), true), 
                new Die(1, DieTypes.Color.GREEN.getInt(), true) },
            },
        };
    }
    
    @DataProvider(name = "IncorrectEachColor")
    public Object[][] createIncorrectEachColorCombination() {
        return new Object[][] {
            { new Die[] {
                new Die(2, DieTypes.Color.BLUE.getInt(), true), 
                new Die(2, DieTypes.Color.RED.getInt(), true),
                new Die(2, DieTypes.Color.YELLOW.getInt(), true), 
                new Die(2, DieTypes.Color.BLUE.getInt(), true) },
            },
                
            { new Die[] {
                new Die(3, DieTypes.Color.YELLOW.getInt(), true), 
                new Die(3, DieTypes.Color.GREEN.getInt(), true),
                new Die(3, DieTypes.Color.RED.getInt(), true), 
                new Die(4, DieTypes.Color.RED.getInt(), true) },
            },
        };
    }

    @Test(dataProvider = "CorrectEachColor")
    void testCorrectEachColorCombination(Die[] combination) {
        Assert.assertEquals(CT.testEachColor(combination), true);
    }
    
    @Test(dataProvider = "IncorrectEachColor")
    void testIncorrectEachColorCombination(Die[] combination) {
        Assert.assertEquals(CT.testEachColor(combination), false);
    }
    
    /**################################################################
    #                       EachColorEachNumber                       #
    ################################################################**/
    
    @DataProvider(name = "CorrectEachColorEachNumber")
    public Object[][] createCorrectEachColorEachNumberCombination() {
        return new Object[][] {
            { new Die[] {
                new Die(2, DieTypes.Color.BLUE.getInt(), true), 
                new Die(3, DieTypes.Color.GREEN.getInt(), true),
                new Die(4, DieTypes.Color.YELLOW.getInt(), true), 
                new Die(1, DieTypes.Color.RED.getInt(), true) },
            },
                
            { new Die[] {
                new Die(1, DieTypes.Color.RED.getInt(), true), 
                new Die(2, DieTypes.Color.BLUE.getInt(), true),
                new Die(4, DieTypes.Color.YELLOW.getInt(), true), 
                new Die(3, DieTypes.Color.GREEN.getInt(), true) },
            },
        };
    }
    
    @DataProvider(name = "IncorrectEachColorEachNumber")
    public Object[][] createIncorrectEachColorEachNumberCombination() {
        return new Object[][] {
            { new Die[] {
                new Die(2, DieTypes.Color.RED.getInt(), true), 
                new Die(1, DieTypes.Color.BLUE.getInt(), true),
                new Die(3, DieTypes.Color.YELLOW.getInt(), true), 
                new Die(2, DieTypes.Color.BLUE.getInt(), true) },
            },
                
            { new Die[] {
                new Die(3, DieTypes.Color.YELLOW.getInt(), true), 
                new Die(1, DieTypes.Color.GREEN.getInt(), true),
                new Die(2, DieTypes.Color.RED.getInt(), true), 
                new Die(4, DieTypes.Color.GREEN.getInt(), true) },
            },
        };
    }

    @Test(dataProvider = "CorrectEachColorEachNumber")
    void testCorrectEachColorEachNumberCombination(Die[] combination) {
        Assert.assertEquals(CT.testEachColorEachNumber(combination), true);
    }
    
    @Test(dataProvider = "IncorrectEachColorEachNumber")
    void testIncorrectEachColorEachNumberCombination(Die[] combination) {
        Assert.assertEquals(CT.testEachColorEachNumber(combination), false);
    }
    
    /**################################################################
    #                            EachNumber                           #
    ################################################################**/
    
    @DataProvider(name = "CorrectEachNumber")
    public Object[][] createCorrectEachNumberCombination() {
        return new Object[][] {
            { new Die[] {
                new Die(4, DieTypes.Color.GREEN.getInt(), true), 
                new Die(3, DieTypes.Color.BLUE.getInt(), true),
                new Die(2, DieTypes.Color.GREEN.getInt(), true), 
                new Die(1, DieTypes.Color.BLUE.getInt(), true) },
            },
                
            { new Die[] {
                new Die(3, DieTypes.Color.YELLOW.getInt(), true), 
                new Die(1, DieTypes.Color.GREEN.getInt(), true),
                new Die(2, DieTypes.Color.RED.getInt(), true), 
                new Die(4, DieTypes.Color.GREEN.getInt(), true) },
            },
        };
    }
    
    @DataProvider(name = "IncorrectEachNumber")
    public Object[][] createIncorrectEachNumberCombination() {
        return new Object[][] {
            { new Die[] {
                new Die(4, DieTypes.Color.GREEN.getInt(), true), 
                new Die(3, DieTypes.Color.BLUE.getInt(), true),
                new Die(3, DieTypes.Color.GREEN.getInt(), true), 
                new Die(1, DieTypes.Color.BLUE.getInt(), true) },
            },
                
            { new Die[] {
                new Die(3, DieTypes.Color.YELLOW.getInt(), true), 
                new Die(1, DieTypes.Color.GREEN.getInt(), true),
                new Die(1, DieTypes.Color.RED.getInt(), true), 
                new Die(4, DieTypes.Color.GREEN.getInt(), true) },
            },
        };
    }

    @Test(dataProvider = "CorrectEachNumber")
    void testCorrectEachNumberCombination(Die[] combination) {
        Assert.assertEquals(CT.testEachNumber(combination), true);
    }
    
    @Test(dataProvider = "IncorrectEachNumber")
    void testIncorrectEachNumberCombination(Die[] combination) {
        Assert.assertEquals(CT.testEachNumber(combination), false);
    }
    
    /**################################################################
    #                             Two Pair                            #
    ################################################################**/
    
    @DataProvider(name = "CorrectTwoPair")
    public Object[][] createCorrectTwoPairCombination() {
        return new Object[][] {
            { new Die[] {
                new Die(2, DieTypes.Color.BLUE.getInt(), true), 
                new Die(3, DieTypes.Color.GREEN.getInt(), true),
                new Die(3, DieTypes.Color.GREEN.getInt(), true), 
                new Die(2, DieTypes.Color.BLUE.getInt(), true) },
            },
                
            { new Die[] {
                new Die(1, DieTypes.Color.RED.getInt(), true), 
                new Die(1, DieTypes.Color.RED.getInt(), true),
                new Die(4, DieTypes.Color.YELLOW.getInt(), true), 
                new Die(4, DieTypes.Color.YELLOW.getInt(), true) },
            },
        };
    }
    
    @DataProvider(name = "IncorrectTwoPair")
    public Object[][] createIncorrectTwoPairCombination() {
        return new Object[][] {
            { new Die[] {
                new Die(2, DieTypes.Color.RED.getInt(), true), 
                new Die(2, DieTypes.Color.RED.getInt(), true),
                new Die(3, DieTypes.Color.YELLOW.getInt(), true), 
                new Die(3, DieTypes.Color.BLUE.getInt(), true) },
            },
                
            { new Die[] {
                new Die(3, DieTypes.Color.YELLOW.getInt(), true), 
                new Die(4, DieTypes.Color.GREEN.getInt(), true),
                new Die(2, DieTypes.Color.RED.getInt(), true), 
                new Die(4, DieTypes.Color.GREEN.getInt(), true) },
            },
        };
    }

    @Test(dataProvider = "CorrectTwoPair")
    void testCorrectTwoPairCombination(Die[] combination) {
        Assert.assertEquals(CT.testTwoPair(combination), true);
    }
    
    @Test(dataProvider = "IncorrectTwoPair")
    void testIncorrectTwoPairCombination(Die[] combination) {
        Assert.assertEquals(CT.testTwoPair(combination), false);
    }
    
    /**################################################################
    #                       PairColorPairNumber                       #
    ################################################################**/
    
    @DataProvider(name = "CorrectPairColorPairNumber")
    public Object[][] createCorrectPairColorPairNumberCombination() {
        return new Object[][] {
            { new Die[] {
                new Die(2, DieTypes.Color.BLUE.getInt(), true), 
                new Die(3, DieTypes.Color.GREEN.getInt(), true),
                new Die(2, DieTypes.Color.GREEN.getInt(), true), 
                new Die(3, DieTypes.Color.BLUE.getInt(), true) },
            },
                
            { new Die[] {
                new Die(1, DieTypes.Color.RED.getInt(), true), 
                new Die(4, DieTypes.Color.RED.getInt(), true),
                new Die(4, DieTypes.Color.YELLOW.getInt(), true), 
                new Die(1, DieTypes.Color.YELLOW.getInt(), true) },
            },
            
            { new Die[] {
                new Die(3, DieTypes.Color.RED.getInt(), true), 
                new Die(3, DieTypes.Color.GREEN.getInt(), true),
                new Die(2, DieTypes.Color.GREEN.getInt(), true), 
                new Die(2, DieTypes.Color.RED.getInt(), true) },
            },
        };
    }
    
    @DataProvider(name = "IncorrectPairColorPairNumber")
    public Object[][] createIncorrectPairColorPairNumberCombination() {
        return new Object[][] {
            { new Die[] {
                new Die(2, DieTypes.Color.RED.getInt(), true), 
                new Die(2, DieTypes.Color.RED.getInt(), true),
                new Die(3, DieTypes.Color.YELLOW.getInt(), true), 
                new Die(3, DieTypes.Color.BLUE.getInt(), true) },
            },
                
            { new Die[] {
                new Die(3, DieTypes.Color.YELLOW.getInt(), true), 
                new Die(4, DieTypes.Color.GREEN.getInt(), true),
                new Die(2, DieTypes.Color.RED.getInt(), true), 
                new Die(4, DieTypes.Color.GREEN.getInt(), true) },
            },
            
            { new Die[] {
                new Die(3, DieTypes.Color.RED.getInt(), true), 
                new Die(2, DieTypes.Color.GREEN.getInt(), true),
                new Die(2, DieTypes.Color.RED.getInt(), true), 
                new Die(4, DieTypes.Color.RED.getInt(), true) },
            },
        };
    }

    @Test(dataProvider = "CorrectPairColorPairNumber")
    void testCorrectPairColorPairNumberCombination(Die[] combination) {
        Assert.assertEquals(CT.testPairColorPairNumber(combination), true);
    }
    
    @Test(dataProvider = "IncorrectPairColorPairNumber")
    void testIncorrectPairColorPairNumberCombination(Die[] combination) {
        Assert.assertEquals(CT.testPairColorPairNumber(combination), false);
    }
    
    /**################################################################
    #                            PairColor                            #
    ################################################################**/
    
    @DataProvider(name = "CorrectPairColor")
    public Object[][] createCorrectPairColorCombination() {
        return new Object[][] {
            { new Die[] {
                new Die(2, DieTypes.Color.BLUE.getInt(), true), 
                new Die(3, DieTypes.Color.GREEN.getInt(), true),
                new Die(2, DieTypes.Color.GREEN.getInt(), true), 
                new Die(3, DieTypes.Color.BLUE.getInt(), true) },
            },
                
            { new Die[] {
                new Die(1, DieTypes.Color.RED.getInt(), true), 
                new Die(4, DieTypes.Color.RED.getInt(), true),
                new Die(4, DieTypes.Color.YELLOW.getInt(), true), 
                new Die(1, DieTypes.Color.YELLOW.getInt(), true) },
            },
        };
    }
    
    @DataProvider(name = "IncorrectPairColor")
    public Object[][] createIncorrectPairColorCombination() {
        return new Object[][] {
            { new Die[] {
                new Die(2, DieTypes.Color.RED.getInt(), true), 
                new Die(2, DieTypes.Color.RED.getInt(), true),
                new Die(3, DieTypes.Color.YELLOW.getInt(), true), 
                new Die(3, DieTypes.Color.BLUE.getInt(), true) },
            },
                
            { new Die[] {
                new Die(3, DieTypes.Color.YELLOW.getInt(), true), 
                new Die(4, DieTypes.Color.GREEN.getInt(), true),
                new Die(2, DieTypes.Color.RED.getInt(), true), 
                new Die(4, DieTypes.Color.GREEN.getInt(), true) },
            },
        };
    }

    @Test(dataProvider = "CorrectPairColor")
    void testCorrectPairColorCombination(Die[] combination) {
        Assert.assertEquals(CT.testPairColor(combination), true);
    }
    
    @Test(dataProvider = "IncorrectPairColor")
    void testIncorrectPairColorCombination(Die[] combination) {
        Assert.assertEquals(CT.testPairColor(combination), false);
    }
    
    /**################################################################
    #                            PairNumber                            #
    ################################################################**/
    
    @DataProvider(name = "CorrectPairNumber")
    public Object[][] createCorrectPairNumberCombination() {
        return new Object[][] {
            { new Die[] {
                new Die(2, DieTypes.Color.BLUE.getInt(), true), 
                new Die(3, DieTypes.Color.GREEN.getInt(), true),
                new Die(2, DieTypes.Color.GREEN.getInt(), true), 
                new Die(3, DieTypes.Color.BLUE.getInt(), true) },
            },
                
            { new Die[] {
                new Die(1, DieTypes.Color.RED.getInt(), true), 
                new Die(4, DieTypes.Color.RED.getInt(), true),
                new Die(4, DieTypes.Color.YELLOW.getInt(), true), 
                new Die(1, DieTypes.Color.YELLOW.getInt(), true) },
            },
        };
    }
    
    @DataProvider(name = "IncorrectPairNumber")
    public Object[][] createIncorrectPairNumberCombination() {
        return new Object[][] {
            { new Die[] {
                new Die(2, DieTypes.Color.RED.getInt(), true), 
                new Die(2, DieTypes.Color.RED.getInt(), true),
                new Die(3, DieTypes.Color.YELLOW.getInt(), true), 
                new Die(4, DieTypes.Color.BLUE.getInt(), true) },
            },
                
            { new Die[] {
                new Die(3, DieTypes.Color.YELLOW.getInt(), true), 
                new Die(4, DieTypes.Color.GREEN.getInt(), true),
                new Die(2, DieTypes.Color.RED.getInt(), true), 
                new Die(4, DieTypes.Color.GREEN.getInt(), true) },
            },
        };
    }

    @Test(dataProvider = "CorrectPairNumber")
    void testCorrectPairNumberCombination(Die[] combination) {
        Assert.assertEquals(CT.testPairNumber(combination), true);
    }
    
    @Test(dataProvider = "IncorrectPairNumber")
    void testIncorrectPairNumberCombination(Die[] combination) {
        Assert.assertEquals(CT.testPairNumber(combination), false);
    }
}