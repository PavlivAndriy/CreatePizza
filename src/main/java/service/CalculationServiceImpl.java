package service;

import domain.Bill;
import domain.Data;
import domain.Drinks;
import domain.Pizza;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

public class CalculationServiceImpl implements CalculationService {
    private OrderCalculationImpl orderCreatorImpl = new OrderCalculationImpl();
    private double pizzaTotalPrice;
    private double drinksTotalPrice;
    private Bill bill = orderCreatorImpl.getBill();
    private LocalDate hollidayChristmas = Year.now().atMonth(Month.JANUARY).atDay(7);
    private LocalDate hollidayIndepenanceDay = Year.now().atMonth(Month.AUGUST).atDay(24);
    private LocalDate hollidayProgrammerDay = Year.now().atDay(256);

    public OrderCalculationImpl getOrderCreatorImpl() {
        return orderCreatorImpl;
    }

    public void setDiscount(String discount) {
        double price = bill.getTotalPrice();
        LocalDate date = orderCreatorImpl.getData().getDate();
        if ((date.equals(hollidayChristmas))
                | (date.equals(hollidayIndepenanceDay))
                | (date.equals(hollidayProgrammerDay))) {
            price *= 0.5;
            bill.setTotalPrice(price);
            System.out.println("Your economy, because of hollidays: " + bill.getTotalPrice() + " hrn");
            discount = "No";
        } else {
            System.out.println("Available economy days are: Jannuary 7th, August 24th, 256 day of year");
        }

        if (discount.equalsIgnoreCase("Yes")) {
            double discountPay = bill.getTotalPrice() * 0.1;
            price *= 0.9;
            bill.setTotalPrice(price);
            System.out.println("Your discount is: " + discountPay);
        } else if (discount.equalsIgnoreCase("No")) {
            price *= 1;
            bill.setTotalPrice(price);
        }

    }

    public void check() {
        LocalDate date = orderCreatorImpl.getData().getDate();
        double price = bill.getTotalPrice();
        if (date.getMonth().equals(Month.SEPTEMBER) & date.getDayOfMonth() < 8 & date.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
            price *= 1;
            bill.setTotalPrice(price);
            System.out.println("Today you don't need to pay tips");
        } else {
            double tips = 0.05 * bill.getTotalPrice();
            price *= 1.05;
            bill.setTotalPrice(price);
            System.out.println("Today your pay for tips is: +" + tips + " hrn");
        }
    }

    public void weekends() {
        LocalDate date = orderCreatorImpl.getData().getDate();
        double price = bill.getTotalPrice();
        if (date.getDayOfWeek().equals(DayOfWeek.FRIDAY)
                | date.getDayOfWeek().equals(DayOfWeek.SATURDAY)
                | date.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            double weekendsPay = 0.05 * bill.getTotalPrice();
            price *= 1.05;
            bill.setTotalPrice(price);
            System.out.println("Today there are weekends, and additional payment is: +" + weekendsPay + " hrn");
        }
    }

    public void storeInfo() {
        pizzaCost();
        drinksCost();
        bill.setTotalPrice(bill.getDrinksPrice() + bill.getPizzaPrice());
        if (bill.getTotalPrice() == 0) {
            System.err.println("Thank you for visiting our store");
        } else {
            orderCreatorImpl.finalPrice();
            System.out.println(bill);
            check();
            weekends();
            setDiscount(orderCreatorImpl.getDiscount());
            System.out.println("YOUR FINAL PRICE IS: " + bill.getTotalPrice());
        }
    }

    public void pizzaCost() {
        for (int i = 0; i < orderCreatorImpl.getPizzaBuilder().getCount(); i++) {
            pizzaTotalPrice += bill.getPizzas().get(i).getPrice();
            bill.setPizzaPrice(pizzaTotalPrice);
        }
        for (Pizza p : bill.getPizzas()) {
            System.out.println(p);
        }
    }

    public void drinksCost() {
        for (int i = 0; i < orderCreatorImpl.getDrinksBuilder().getCount(); i++) {
            drinksTotalPrice += bill.getDrinks().get(i).getPrice();
            bill.setDrinksPrice(drinksTotalPrice);
        }
        for (Drinks dr : bill.getDrinks()) {
            System.out.println(dr);
        }
    }

    @Override
    public Bill buildBill(Data data) {
        storeInfo();
        return bill;
    }
}
