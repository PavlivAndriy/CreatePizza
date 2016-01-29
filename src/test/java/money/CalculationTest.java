package money;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.Month;

/**
 * Created by Andriy on 1/29/2016.
 */
public class CalculationTest {
    @Test(groups = {"discount"})
    public void testDiscountHollidays() {
        System.err.println("Testing testDiscountHollidays");
        Creator.setTotalPrice(100);
        Creator.setDate(LocalDate.of(2016, 01, 07));
        Calculation.setDiscount("yes");
        Assert.assertEquals(50.0, Creator.getTotalPrice());
    }

    @Test(groups = {"discount"})
    public void testDiscount() {
        System.err.println("Testing testDiscount");
        Creator.setTotalPrice(100);
        Creator.setDate(LocalDate.of(2016, 02, 07));
        Calculation.setDiscount("yes");
        Assert.assertEquals(90.0, Creator.getTotalPrice());
    }

    @Test(groups = {"discount"})
    public void testCheck() {
        System.err.println("Testing testCheck");
        Creator.setTotalPrice(100);
        Creator.setDate(LocalDate.of(2016, 02, 07));
        Calculation.check();
        Assert.assertEquals(105.0, Creator.getTotalPrice());
    }

    @Test(groups = {"discount"})
    public void testWeekends() {
        System.err.println("Testing testWeekends");
        Creator.setTotalPrice(100);
        Creator.setDate(LocalDate.of(2016, Month.JANUARY, 23));
        Calculation.weekends();
        Assert.assertEquals(105.0, Creator.getTotalPrice());
    }
}
