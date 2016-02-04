package service;


import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.Month;

/**
 * Created by Andriy on 1/29/2016.
 *//*
public class CalculationServiceTest {
    public static CalculationService calculationService = new CalculationService();
    @Test(groups = {"discount"})
    public void testDiscountHollidays() {
        System.err.println("Testing testDiscountHollidays");
        CalculationService.data.setTotalPrice(100);
        CalculationService.data.setDate(LocalDate.of(2016, 01, 07));
        calculationService.setDiscount("yes");
        Assert.assertEquals(50.0, CalculationService.data.getTotalPrice());
    }

    @Test(groups = {"discount"})
    public void testDiscount() {
        System.err.println("Testing testDiscount");
        CalculationService.data.setTotalPrice(100);
        CalculationService.data.setDate(LocalDate.of(2016, 02, 07));
        calculationService.setDiscount("yes");
        Assert.assertEquals(90.0, CalculationService.data.getTotalPrice());
    }

    @Test(groups = {"discount"})
    public void testCheck() {
        System.err.println("Testing testCheck");
        CalculationService.data.setTotalPrice(100);
        CalculationService.data.setDate(LocalDate.of(2016, 02, 07));
        calculationService.check();
        Assert.assertEquals(105.0, CalculationService.data.getTotalPrice());
    }

    @Test(groups = {"discount"})
    public void testWeekends() {

        System.err.println("Testing testWeekends");
        CalculationService.data.setTotalPrice(100);
        CalculationService.data.setDate(LocalDate.of(2016, Month.JANUARY, 23));
        calculationService.weekends();
        Assert.assertEquals(105.0, CalculationService.data.getTotalPrice());
    }
}*/
