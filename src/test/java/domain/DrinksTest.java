package domain;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Andriy on 1/29/2016.
 */
public class DrinksTest {

    @Test(groups = {"drinks"})
    public void testMakePriceDrinks() {
        Drinks drinks = new Drinks();
        Drinks.DrinksBuilder drinksBuilder = new Drinks.DrinksBuilder();
        System.err.println("Testing makePriceDrinks");
        drinksBuilder.makeName(DrinksNames.BEER);
        drinksBuilder.makePrice();
        Assert.assertEquals(30, drinksBuilder.getPrice());
    }

    @Test(groups = {"drinks"})
    public void testMakeSizeDrinks() {
        Drinks drinks = new Drinks();
        Drinks.DrinksBuilder drinksBuilder = new Drinks.DrinksBuilder();
        System.err.println("Testing makeSizeDrinks");
        drinksBuilder.makeSize(DrinksSize.BIG);
        Assert.assertEquals(DrinksSize.BIG, drinksBuilder.getDrinksSize());
    }
}
