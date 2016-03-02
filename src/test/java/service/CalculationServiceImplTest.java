package service;


import domain.Bill;
import domain.Data;
import domain.Drinks;
import domain.Pizza;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

public class CalculationServiceImplTest {

    @Test(groups = {"discount"})
    public void testDiscountHollidays() {
        System.err.println("Testing testDiscountHollidays");
        CalculationServiceImpl calculationService = new CalculationServiceImpl();
        Data data = new Data();
        data.setPizzas(new ArrayList());
        data.getPizzas().add(new Pizza());
        data.getPizzas().get(0).setPrice(100);
        data.setDate(LocalDate.of(2016, 01, 07));
        data.setDiscount("no");
        Bill bill = calculationService.buildBill(data);
        Assert.assertEquals(52.5, bill.getTotalPrice());
    }

    @Test(groups = {"discount"})
    public void testDiscount() {
        System.err.println("Testing testDiscount");
        CalculationServiceImpl calculationService = new CalculationServiceImpl();
        Data data = new Data();
        data.setDrinks(new ArrayList());
        data.getDrinks().add(new Drinks());
        data.getDrinks().get(0).setPrice(100);
        data.setDate(LocalDate.of(2016, 02, 9));
        data.setDiscount("yes");
        Bill bill = calculationService.buildBill(data);
        Assert.assertEquals(94.5, bill.getTotalPrice());
    }

    @Test(groups = {"discount"})
    public void testCheck() {
        System.err.println("Testing testCheck");
        CalculationServiceImpl calculationService = new CalculationServiceImpl();
        Data data = new Data();
        data.setPizzas(new ArrayList());
        data.getPizzas().add(new Pizza());
        data.getPizzas().get(0).setPrice(100);
        data.setDate(LocalDate.of(2016, 8, 24));
        data.setDiscount("yes");
        Bill bill = calculationService.buildBill(data);
        Assert.assertEquals(52.5, bill.getTotalPrice());
    }

    @Test(groups = {"discount"})
    public void testWeekends() {

        System.err.println("Testing testWeekends");
        CalculationServiceImpl calculationService = new CalculationServiceImpl();
        Data data = new Data();
        data.setPizzas(new ArrayList());
        data.getPizzas().add(new Pizza());
        data.getPizzas().get(0).setPrice(50);
        data.setDrinks(new ArrayList());
        data.getDrinks().add(new Drinks());
        data.getDrinks().get(0).setPrice(50);
        data.setDate(LocalDate.of(2016, Month.MARCH, 06));
        data.setDiscount("No");
        Bill bill = calculationService.buildBill(data);
        Assert.assertEquals(110.25, bill.getTotalPrice());
    }

    @Test(groups = {"discount"})
    public void testNoPrice() {

        System.err.println("Testing testNoPrice");
        CalculationServiceImpl calculationService = new CalculationServiceImpl();
        Data data = new Data();
        data.setDrinks(new ArrayList());
        data.getDrinks().add(new Drinks());
        data.getDrinks().get(0).setPrice(0);
        data.setDate(LocalDate.of(2016, Month.SEPTEMBER, 03));
        data.setDiscount("No");
        Bill bill = calculationService.buildBill(data);
        Assert.assertEquals(0.0, bill.getDrinksPrice());
    }

    @Test(groups = {"discount"})
    public void testPrice() {

        System.err.println("Testing testPrice");
        CalculationServiceImpl calculationService = new CalculationServiceImpl();
        Data data = new Data();
        data.setDate(LocalDate.of(2016, Month.SEPTEMBER, 3));
        data.setDiscount("No");
        data.setDrinks(new ArrayList());
        data.getDrinks().add(new Drinks());
        data.getDrinks().get(0).setPrice(50);
        data.getDrinks().add(new Drinks());
        data.getDrinks().get(1).setPrice(50);
        Bill bill = calculationService.buildBill(data);
        Assert.assertEquals(105.0, bill.getTotalPrice());
    }
}
