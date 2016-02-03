package domain;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Andriy on 2/3/2016.
 */
public class PizzaTest {
    @Test(groups = {"pizzas"})
    public void testMakePricePizza() {
        Pizza.PizzaBuilder pizzaBuilder = new Pizza.PizzaBuilder();
        System.err.println("Testing testMakePricePizza");
        pizzaBuilder.makeName(PizzasNames.CAPRICCIOSA);
        pizzaBuilder.setSize(50);
        pizzaBuilder.makePrice();
        Assert.assertEquals(80, pizzaBuilder.getPrice());
    }

    @Test(groups = {"pizzas"})
    public void testMakeInfoPizza() {
        Pizza.PizzaBuilder pizzaBuilder = new Pizza.PizzaBuilder();
        System.err.println("Testing testMakeInfoPizza");
        pizzaBuilder.makeName(PizzasNames.CAPRICCIOSA);
        pizzaBuilder.makeInfo();
        Assert.assertEquals(": Cheese + Salami + Papper + Ham + Mushrooms", pizzaBuilder.getInfo());
    }
}
