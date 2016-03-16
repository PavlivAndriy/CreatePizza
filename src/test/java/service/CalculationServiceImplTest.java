package service;


import domain.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.LocalDate;
import java.time.Month;

public class CalculationServiceImplTest {

    @Test(groups = {"discount"}, description = "Testing Christmas Day")
    public void testChristmasDay() {
        CalculationServiceImpl calculationService = new CalculationServiceImpl();
        PriceService priceService = new PriceServiceImpl();
        Data data = new Data();
        data.addPizza(new Pizza.PizzaBuilder().setPizzaName(PizzasNames.CAPRICCIOSA)
                .setPizzaDescription()
                .setPizzaSize(50)
                .setPizzaAddons(PizzasAddons.CHEESE)
                .setPizzaAddons(PizzasAddons.CHEESE)
                .build());
        priceService.getPricesForPizza(data.getPizzas().get(0));
        data.setDate(LocalDate.of(2016, Month.JANUARY, 07));
        Bill bill = calculationService.buildBill(data);
        Assert.assertEquals(52.5, bill.getTotalPrice());
    }

    @Test(groups = {"discount"}, description = "Testing discount 10%")
    public void testDiscount() {
        CalculationServiceImpl calculationService = new CalculationServiceImpl();
        PriceService priceService = new PriceServiceImpl();
        Data data = new Data();
        data.addDrinks(new Drinks.DrinksBuilder()
                .setDrinksNames(DrinksNames.VINE)
                .setDrinksSize(DrinksSize.BIG)
                .build());
        priceService.getPricesForDrinks(data.getDrinks().get(0));
        data.setDate(LocalDate.of(2016, 02, 9));
        data.setDiscount(true);
        Bill bill = calculationService.buildBill(data);
        Assert.assertEquals(94.5, bill.getTotalPrice());
    }

    @Test(groups = {"discount"}, description =  "Testting Independence Day discount")
    public void testCheck() {
        CalculationServiceImpl calculationService = new CalculationServiceImpl();
        PriceService priceService = new PriceServiceImpl();
        Data data = new Data();
        data.addPizza(new Pizza.PizzaBuilder()
                .setPizzaName(PizzasNames.CAPRICCIOSA)
                .setPizzaDescription()
                .setPizzaSize(50)
                .setPizzaAddons(PizzasAddons.CHEESE)
                .setPizzaAddons(PizzasAddons.CHEESE)
                .build());
        priceService.getPricesForPizza(data.getPizzas().get(0));
        data.setDate(LocalDate.of(2016, 8, 24));
        data.setDiscount(true);
        Bill bill = calculationService.buildBill(data);
        Assert.assertEquals(52.5, bill.getTotalPrice());
    }

    @Test(groups = {"discount"}, description = "Testing weekends pay without discount")
    public void testWeekends() {
        PriceService priceService = new PriceServiceImpl();
        CalculationServiceImpl calculationService = new CalculationServiceImpl();
        Data data = new Data();
        data.addDrinks(new Drinks.DrinksBuilder()
                .setDrinksNames(DrinksNames.VINE)
                .setDrinksSize(DrinksSize.BIG)
                .build());
        priceService.getPricesForDrinks(data.getDrinks().get(0));
        data.setDate(LocalDate.of(2016, Month.MARCH, 06));
        Bill bill = calculationService.buildBill(data);
        Assert.assertEquals(110.25, bill.getTotalPrice());
    }

    @Test(groups = {"discount"}, description = "Testing without prices")
    public void testNoPrice() {
        CalculationServiceImpl calculationService = new CalculationServiceImpl();
        Data data = new Data();
        data.addDrinks(new Drinks.DrinksBuilder()
                .setDrinksNames(DrinksNames.VINE)
                .setDrinksSize(DrinksSize.BIG)
                .build());
        data.getDrinks().get(0).setPrice(0);
        data.setDate(LocalDate.of(2016, Month.SEPTEMBER, 12));
        Bill bill = calculationService.buildBill(data);
        Assert.assertEquals(0.0, bill.getDrinksPrice());
    }

    @Test(groups = {"discount"}, description = "Testing first Saturday of September")
    public void testPrice() {
        CalculationServiceImpl calculationService = new CalculationServiceImpl();
        PriceService priceService = new PriceServiceImpl();
        Data data = new Data();
        data.setDate(LocalDate.of(2016, Month.SEPTEMBER, 3));
        data.setDiscount(false);
        data.addDrinks(new Drinks.DrinksBuilder()
                .setDrinksNames(DrinksNames.VINE)
                .setDrinksSize(DrinksSize.BIG)
                .build());
        priceService.getPricesForDrinks(data.getDrinks().get(0));
        Bill bill = calculationService.buildBill(data);
        Assert.assertEquals(105.0, bill.getTotalPrice());
    }
}
