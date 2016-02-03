package service;

import io.Creator;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.Month;

/**
 * Created by Andriy on 1/29/2016.
 */
public class CalculationTest {
    public static Calculation calculation = new Calculation();
    @Test(groups = {"discount"})
    public void testDiscountHollidays() {
        System.err.println("Testing testDiscountHollidays");
        Calculation.creator.setTotalPrice(100);
        Calculation.creator.setDate(LocalDate.of(2016, 01, 07));
        calculation.setDiscount("yes");
        Assert.assertEquals(50.0, Calculation.creator.getTotalPrice());
    }

    @Test(groups = {"discount"})
    public void testDiscount() {
        System.err.println("Testing testDiscount");
        Calculation.creator.setTotalPrice(100);
        Calculation.creator.setDate(LocalDate.of(2016, 02, 07));
        calculation.setDiscount("yes");
        Assert.assertEquals(90.0, Calculation.creator.getTotalPrice());
    }

    @Test(groups = {"discount"})
    public void testCheck() {
        System.err.println("Testing testCheck");
        Calculation.creator.setTotalPrice(100);
        Calculation.creator.setDate(LocalDate.of(2016, 02, 07));
        calculation.check();
        Assert.assertEquals(105.0, Calculation.creator.getTotalPrice());
    }

    @Test(groups = {"discount"})
    public void testWeekends() {

        System.err.println("Testing testWeekends");
        Calculation.creator.setTotalPrice(100);
        Calculation.creator.setDate(LocalDate.of(2016, Month.JANUARY, 23));
        calculation.weekends();
        Assert.assertEquals(105.0, Calculation.creator.getTotalPrice());
    }
}
