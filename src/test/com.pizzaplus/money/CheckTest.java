package money;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.Month;

/**
 * Created by Andriy on 1/29/2016.
 */
public class CheckTest {
    @Test(groups = {"discount"})
    public void testDiscountHollidays(){
        System.err.println("Testing testDiscountHollidays");
        Data.totalPrice = 100;
        Data.date = LocalDate.of(2016, 01, 07);
        Check.setDiscount("yes");
        Assert.assertEquals(50.0, Data.totalPrice);
    }

    @Test(groups = {"discount"})
    public void testDiscount(){
        System.err.println("Testing testDiscount");
        Data.totalPrice = 100;
        Data.date = LocalDate.of(2016, 02, 07);
        Check.setDiscount("yes");
        Assert.assertEquals(90.0, Data.totalPrice);
    }

    @Test(groups = {"discount"})
    public void testCheck(){
        System.err.println("Testing testCheck");
        Data.totalPrice = 100;
        Data.date = LocalDate.of(2016, 02, 07);
        Check.check();
        Assert.assertEquals(105.0, Data.totalPrice);
    }

    @Test(groups = {"discount"})
    public void testWeekends(){
        System.err.println("Testing testWeekends");
        Data.totalPrice = 100;
        Data.date = LocalDate.of(2016, Month.JANUARY, 23);
        Check.weekends();
        Assert.assertEquals(105.0, Data.totalPrice);
    }
}
