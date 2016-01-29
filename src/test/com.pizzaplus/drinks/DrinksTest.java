package drinks;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Andriy on 1/29/2016.
 */
public class DrinksTest {

    @Test(groups = {"drinks"})
    public void testMakePriceDrinks(){
        DrinksBuilder drinksBuilder = new DrinksBuilder();
        System.err.println("Testing makePriceDrinks");
        drinksBuilder.drinksNames = DrinksNames.Beer;
        drinksBuilder.makePrice();
        Assert.assertEquals(30, drinksBuilder.price);
    }

    @Test(groups = {"drinks"})
    public void testMakeSizeDrinks(){
        DrinksBuilder drinksBuilder = new DrinksBuilder();
        System.err.println("Testing makeSizeDrinks");
        drinksBuilder.makeSize(DrinksSize.BIG);
        Assert.assertEquals(DrinksSize.BIG, drinksBuilder.drinksSize);
    }
}
