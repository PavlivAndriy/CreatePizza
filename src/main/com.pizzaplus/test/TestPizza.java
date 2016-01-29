package test;

import drinks.DrinksBuilder;
import drinks.DrinksNames;
import money.Check;
import money.Data;
import org.testng.annotations.Test;
import org.testng.Assert;
import pizza.PizzaBuilder;
import pizza.PizzasNames;

import java.time.LocalDate;
import java.time.Month;

/**
 * Created by Andriy on 1/28/2016.
 */
public class TestPizza {

    @Test(groups = {"drinks", "integration"})
    public void testMakePriceDrinks(){
        DrinksBuilder drinksBuilder = new DrinksBuilder();
        System.err.println("Testing makePriceDrinks");
        drinksBuilder.drinksNames = DrinksNames.Beer;
        drinksBuilder.makePrice();
        Assert.assertEquals(30, drinksBuilder.price);
    }

    @Test(groups = {"pizzas", "integration"})
    public void testMakePricePizza(){
        PizzaBuilder pizzaBuilder = new PizzaBuilder();
        System.err.println("Testing testMakePricePizza");
        pizzaBuilder.pizzasNames = PizzasNames.Capricciosa;
        pizzaBuilder.size = 50;
        pizzaBuilder.makePrice();
        Assert.assertEquals(80, pizzaBuilder.price);
    }

    @Test(groups = {"discount", "integration"})
    public void testDiscountHollidays(){
        System.err.println("Testing testDiscountHollidays");
        Data.totalPrice = 100;
        Data.date = LocalDate.of(2016, 01, 07);
        Check.setDiscount("yes");
        Assert.assertEquals(50.0, Data.totalPrice);
    }

    @Test(groups = {"discount", "integration"})
    public void testDiscount(){
        System.err.println("Testing testDiscount");
        Data.totalPrice = 100;
        Data.date = LocalDate.of(2016, 02, 07);
        Check.setDiscount("yes");
        Assert.assertEquals(90.0, Data.totalPrice);
    }

    @Test(groups = {"discount", "integration"})
    public void testCheck(){
        System.err.println("Testing testCheck");
        Data.totalPrice = 100;
        Data.date = LocalDate.of(2016, 02, 07);
        Check.check();
        Assert.assertEquals(105.0, Data.totalPrice);
    }

    @Test(groups = {"discount", "integration"})
    public void testWeekends(){
        System.err.println("Testing testWeekends");
        Data.totalPrice = 100;
        Data.date = LocalDate.of(2016, Month.JANUARY, 23);
        Check.weekends();
        Assert.assertEquals(105.0, Data.totalPrice);
    }



}
