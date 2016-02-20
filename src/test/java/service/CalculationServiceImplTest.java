package service;


import domain.Bill;
import domain.Data;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.Month;

public class CalculationServiceImplTest {
    private CalculationServiceImpl calculationServiceImpl = new CalculationServiceImpl();
    private Data data = calculationServiceImpl.getOrderCreatorImpl().getData();
    private Bill bill = calculationServiceImpl.getOrderCreatorImpl().getBill();

    @Test(groups = {"discount"})
    public void testDiscountHollidays() {
        System.err.println("Testing testDiscountHollidays");
        bill.setTotalPrice(100);
        data.setDate(LocalDate.of(2016, 01, 07));
        calculationServiceImpl.setDiscount("yes");
        Assert.assertEquals(50.0, bill.getTotalPrice());
    }

    @Test(groups = {"discount"})
    public void testDiscount() {
        System.err.println("Testing testDiscount");
        bill.setTotalPrice(100);
        data.setDate(LocalDate.of(2016, 02, 07));
        calculationServiceImpl.setDiscount("yes");
        Assert.assertEquals(90.0, bill.getTotalPrice());
    }

    @Test(groups = {"discount"})
    public void testCheck() {
        System.err.println("Testing testCheck");
        bill.setTotalPrice(100);
        data.setDate(LocalDate.of(2016, 02, 07));
        calculationServiceImpl.check();
        Assert.assertEquals(105.0, bill.getTotalPrice());
    }

    @Test(groups = {"discount"})
    public void testWeekends() {

        System.err.println("Testing testWeekends");
        bill.setTotalPrice(100);
        data.setDate(LocalDate.of(2016, Month.JANUARY, 01));
        calculationServiceImpl.weekends();
        Assert.assertEquals(105.0, bill.getTotalPrice());
    }
}
