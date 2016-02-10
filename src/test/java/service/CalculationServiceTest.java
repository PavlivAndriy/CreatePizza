package service;


import io.Data;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.Month;

public class CalculationServiceTest {
    private CalculationService calculationService = new CalculationService();
    private Data data = calculationService.getCreator().getData();

    @Test(groups = {"discount"})
    public void testDiscountHollidays() {
        System.err.println("Testing testDiscountHollidays");
        data.setTotalPrice(100);
        data.setDate(LocalDate.of(2016, 01, 07));
        calculationService.setDiscount("yes");
        Assert.assertEquals(50.0, data.getTotalPrice());
    }

    @Test(groups = {"discount"})
    public void testDiscount() {
        System.err.println("Testing testDiscount");
        data.setTotalPrice(100);
        data.setDate(LocalDate.of(2016, 02, 07));
        calculationService.setDiscount("yes");
        Assert.assertEquals(90.0, data.getTotalPrice());
    }

    @Test(groups = {"discount"})
    public void testCheck() {
        System.err.println("Testing testCheck");
        data.setTotalPrice(100);
        data.setDate(LocalDate.of(2016, 02, 07));
        calculationService.check();
        Assert.assertEquals(105.0, data.getTotalPrice());
    }

    @Test(groups = {"discount"})
    public void testWeekends() {

        System.err.println("Testing testWeekends");
        data.setTotalPrice(100);
        data.setDate(LocalDate.of(2016, Month.JANUARY, 01));
        calculationService.weekends();
        Assert.assertEquals(105.0, data.getTotalPrice());
    }
}
