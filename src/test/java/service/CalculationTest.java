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
    Calculation calculation = new Calculation();
    Creator creator = new Creator();
    @Test(groups = {"discount"})
    public void testDiscountHollidays() {
        System.err.println("Testing testDiscountHollidays");
        creator.setTotalPrice(100);
        creator.setDate(LocalDate.of(2016, 01, 07));
        calculation.setDiscount("yes");
        Assert.assertEquals(50.0, creator.getTotalPrice());
    }

    @Test(groups = {"discount"})
    public void testDiscount() {
        System.err.println("Testing testDiscount");
        creator.setTotalPrice(100);
        creator.setDate(LocalDate.of(2016, 02, 07));
        calculation.setDiscount("yes");
        Assert.assertEquals(90.0, creator.getTotalPrice());
    }

    @Test(groups = {"discount"})
    public void testCheck() {
        System.err.println("Testing testCheck");
        creator.setTotalPrice(100);
        creator.setDate(LocalDate.of(2016, 02, 07));
        calculation.check();
        Assert.assertEquals(105.0, creator.getTotalPrice());
    }

    @Test(groups = {"discount"})
    public void testWeekends() {

        System.err.println("Testing testWeekends");
        creator.setTotalPrice(100);
        creator.setDate(LocalDate.of(2016, Month.JANUARY, 23));
        calculation.weekends();
        Assert.assertEquals(100.0, creator.getTotalPrice());
    }
}
