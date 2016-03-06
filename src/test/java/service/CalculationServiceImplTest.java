package service;


import domain.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

public class CalculationServiceImplTest {

    @Test(groups = {"discount"}, description = "Testing Christmas Day")
    public void testChristmasDay() {
        CalculationServiceImpl calculationService = new CalculationServiceImpl();
        Data data = new Data();
        data.setPizzas(new ArrayList());
        data.getPizzas().add(new Pizza.PizzaBuilder().makeName(PizzasNames.CAPRICCIOSA)
                .makeInfo().makeSize(50).makePrice().add(PizzasAddons.CHEESE).add(PizzasAddons.CHEESE).build());
        data.setDate(LocalDate.of(2016, Month.JANUARY, 07));
        data.setDiscount("no");
        Bill bill = calculationService.buildBill(data);
        Assert.assertEquals(52.5, bill.getTotalPrice());
    }

    @Test(groups = {"discount"}, description = "Testing discount 10%")
    public void testDiscount() {
        CalculationServiceImpl calculationService = new CalculationServiceImpl();
        Data data = new Data();
        data.setDrinks(new ArrayList());
        data.getDrinks().add(new Drinks.DrinksBuilder().makeName(DrinksNames.VINE)
                .makePrice().makeSize(DrinksSize.BIG).build());
        data.setDate(LocalDate.of(2016, 02, 9));
        data.setDiscount("yes");
        Bill bill = calculationService.buildBill(data);
        Assert.assertEquals(94.5, bill.getTotalPrice());
    }

    @Test(groups = {"discount"}, description =  "Testting Independence Day discount")
    public void testCheck() {
        CalculationServiceImpl calculationService = new CalculationServiceImpl();
        Data data = new Data();
        data.setPizzas(new ArrayList());
        data.getPizzas().add(new Pizza.PizzaBuilder().makeName(PizzasNames.CAPRICCIOSA)
                .makeInfo().makeSize(50).makePrice().add(PizzasAddons.CHEESE).add(PizzasAddons.CHEESE).build());
        data.setDate(LocalDate.of(2016, 8, 24));
        data.setDiscount("yes");
        Bill bill = calculationService.buildBill(data);
        Assert.assertEquals(52.5, bill.getTotalPrice());
    }

    @Test(groups = {"discount"}, description = "Testing weekends pay without discount")
    public void testWeekends() {
        CalculationServiceImpl calculationService = new CalculationServiceImpl();
        Data data = new Data();
        data.setPizzas(new ArrayList());
        data.getPizzas().add(new Pizza.PizzaBuilder().makeName(PizzasNames.CAPRICCIOSA)
                .makeInfo().makeSize(50).makePrice().add(PizzasAddons.CHEESE).add(PizzasAddons.CHEESE).build());
        data.setDrinks(new ArrayList());
        data.getDrinks().add(new Drinks.DrinksBuilder().makeName(DrinksNames.VINE)
                .makePrice().makeSize(DrinksSize.BIG).build());
        data.setDate(LocalDate.of(2016, Month.MARCH, 06));
        data.setDiscount("No");
        Bill bill = calculationService.buildBill(data);
        Assert.assertEquals(110.25, bill.getTotalPrice());
    }

    @Test(groups = {"discount"}, description = "Testing without prices")
    public void testNoPrice() {
        CalculationServiceImpl calculationService = new CalculationServiceImpl();
        Data data = new Data();
        data.setDrinks(new ArrayList());
        data.getDrinks().add(new Drinks.DrinksBuilder().makeName(DrinksNames.VINE)
                .makePrice().makeSize(DrinksSize.BIG).build());
        data.getDrinks().get(0).setPrice(0);
        data.setDate(LocalDate.of(2016, Month.SEPTEMBER, 12));
        data.setDiscount("No");
        Bill bill = calculationService.buildBill(data);
        Assert.assertEquals(0.0, bill.getDrinksPrice());
    }

    @Test(groups = {"discount"}, description = "Testing first Saturday of September")
    public void testPrice() {
        CalculationServiceImpl calculationService = new CalculationServiceImpl();
        Data data = new Data();
        data.setDate(LocalDate.of(2016, Month.SEPTEMBER, 3));
        data.setDiscount("No");
        data.setDrinks(new ArrayList());
        data.getDrinks().add(new Drinks.DrinksBuilder().makeName(DrinksNames.VINE)
                .makePrice().makeSize(DrinksSize.BIG).build());
        Bill bill = calculationService.buildBill(data);
        Assert.assertEquals(105.0, bill.getTotalPrice());
    }
}
