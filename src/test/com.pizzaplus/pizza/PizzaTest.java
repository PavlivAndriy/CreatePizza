package pizza;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Andriy on 1/29/2016.
 */
public class PizzaTest {
    @Test(groups = {"pizzas"})
    public void testMakePricePizza(){
        PizzaBuilder pizzaBuilder = new PizzaBuilder();
        System.err.println("Testing testMakePricePizza");
        pizzaBuilder.pizzasNames = PizzasNames.Capricciosa;
        pizzaBuilder.size = 50;
        pizzaBuilder.makePrice();
        Assert.assertEquals(80, pizzaBuilder.price);
    }

    @Test(groups = {"pizzas"})
    public void testMakeInfoPizza(){
        PizzaBuilder pizzaBuilder = new PizzaBuilder();
        System.err.println("Testing testMakeInfoPizza");
        pizzaBuilder.pizzasNames = PizzasNames.Capricciosa;
        pizzaBuilder.makeInfo();
        Assert.assertEquals(": Cheese + Salami + Papper + Ham + Mushrooms", pizzaBuilder.info);
    }
}
