package service;

import domain.Drinks;
import domain.Pizza;
import io.Creator;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

public class CalculationService {
    private Creator creator = new Creator();
    private double pizzaTotalPrice;
    private double drinksTotalPrice;
    private LocalDate hollidayChristmas = Year.now().atMonth(Month.JANUARY).atDay(7);
    private LocalDate hollidayIndepenanceDay = Year.now().atMonth(Month.AUGUST).atDay(24);
    private LocalDate hollidayProgrammerDay = Year.now().atDay(256);

    public Creator getCreator() {
        return creator;
    }

    public void setDiscount(String discount) {
        double price = creator.getData().getTotalPrice();
        LocalDate date = creator.getData().getDate();
        if ((date == hollidayChristmas)
                | (date == hollidayIndepenanceDay)
                | (date == hollidayProgrammerDay)) {
            price *= 0.5;
            creator.getData().setTotalPrice(price);
            System.out.println("Your economy, because of hollidays: " + creator.getData().getTotalPrice());
            discount = "No";
        } else {
            System.out.println("Available economy days are: Jannuary 7th, August 24th, 256 day of year");
        }

        if (discount.equalsIgnoreCase("Yes")) {
            double discountPay = creator.getData().getTotalPrice() * 0.1;
            price *= 0.9;
            creator.getData().setTotalPrice(price);
            System.out.println("Your discount is: " + discountPay);
        } else if (discount.equalsIgnoreCase("No")) {
            price *= 1;
            creator.getData().setTotalPrice(price);
        }

    }

    public void check() {
        LocalDate date = creator.getData().getDate();
        double price = creator.getData().getTotalPrice();
        if (date.getMonth().equals(Month.SEPTEMBER) & date.getDayOfMonth() < 8 & date.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
            price *= 1;
            creator.getData().setTotalPrice(price);
            System.out.println("Today you don't need to pay tips");
        } else {
            double tips = 0.05 * creator.getData().getTotalPrice();
            price *= 1.05;
            creator.getData().setTotalPrice(price);
            System.out.println("Today your pay for tips is: +" + tips);
        }
    }

    public void weekends() {
        LocalDate date = creator.getData().getDate();
        double price = creator.getData().getTotalPrice();
        if (date.getDayOfWeek().equals(DayOfWeek.FRIDAY)
                | date.getDayOfWeek().equals(DayOfWeek.SATURDAY)
                | date.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            double weekendsPay = 0.05 * creator.getData().getTotalPrice();
            price *= 1.05;
            creator.getData().setTotalPrice(price);
            System.out.println("Today there are weekends, and additional payment is: +" + weekendsPay);
        }
    }

    public void storeInfo() {
        pizzaCost();
        drinksCost();
        creator.getData().setTotalPrice(creator.getData().getDrinksPrice() + creator.getData().getPizzaPrice());
        if (creator.getData().getTotalPrice() == 0) {
            System.err.println("Thank you for visiting our store");
        } else {
            creator.finalPrice();
            check();
            weekends();
            setDiscount(creator.getDiscount());
            System.out.println("YOUR FINAL PRICE IS: " + creator.getData().getTotalPrice());
        }
    }

    public void pizzaCost() {
        for (int i = 0; i < creator.getPizzaBuilder().getCount(); i++) {
            pizzaTotalPrice += creator.getData().getPizzas().get(i).getPrice();
            creator.getData().setPizzaPrice(pizzaTotalPrice);
        }
        for (Pizza p : creator.getData().getPizzas()) {
            System.out.println(p);
        }
    }

    public void drinksCost() {
        for (int i = 0; i < creator.getDrinksBuilder().getCount(); i++) {
            drinksTotalPrice += creator.getData().getDrinks().get(i).getPrice();
            creator.getData().setDrinksPrice(drinksTotalPrice);
        }
        for (Drinks dr : creator.getData().getDrinks()) {
            System.out.println(dr);
        }
    }
}
