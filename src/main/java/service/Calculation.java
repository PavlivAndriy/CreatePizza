package service;

import domain.Drinks;
import domain.Pizza;
import io.Creator;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

/**
 * Created by Andriy on 1/15/2016.
 */
public class Calculation {
    public static Creator creator = new Creator();

    public void setDiscount(String discount) {
        double price = creator.getTotalPrice();
        LocalDate date = creator.getDate();
        if (date.getDayOfYear() == 7
                | (date.getMonth().equals(Month.AUGUST) & date.getDayOfMonth() == 24)
                | (date.getDayOfYear() == 256)) {
            price *= 0.5;
            creator.setTotalPrice(price);
            System.out.println("Your economy, because of hollidays: " + creator.getTotalPrice());
            discount = "No";
        } else {
            System.out.println("Available economy days are: Jannuary 7th, August 24th, 256 day of year");
        }

        if (discount.equalsIgnoreCase("Yes")) {
            double discountPay = creator.getTotalPrice() * 0.1;
            price *= 0.9;
            creator.setTotalPrice(price);
            System.out.println("Your discount is: " + discountPay);
        } else if (discount.equalsIgnoreCase("No")) {
            price *= 1;
            creator.setTotalPrice(price);
        }

    }

    public void check() {
        LocalDate date = creator.getDate();
        double price = creator.getTotalPrice();
        if (date.getMonth().equals(Month.SEPTEMBER) & date.getDayOfMonth() < 8 & date.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
            price *= 1;
            creator.setTotalPrice(price);
            System.out.println("Today you don't need to pay tips");
        } else {
            double tips = 0.05 * creator.getTotalPrice();
            price *= 1.05;
            creator.setTotalPrice(price);
            System.out.println("Today your pay for tips is: +" + tips);
        }
    }

    public void weekends() {
        LocalDate date = creator.getDate();
        double price = creator.getTotalPrice();
        if (date.getDayOfWeek().equals(DayOfWeek.FRIDAY)
                | date.getDayOfWeek().equals(DayOfWeek.SATURDAY)
                | date.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            double weekendsPay = 0.05 * creator.getTotalPrice();
            price *= 1.05;
            creator.setTotalPrice(price);
            System.out.println("Today there are weekends, and additional payment is: +" + weekendsPay);
        }
    }

    public void storeInfo() {

        pizzaCost();
        drinksCost();
        creator.setTotalPrice(creator.getDrinksPrice() + creator.getPizzaPrice());
        if (creator.getTotalPrice() == 0) {
            System.err.println("Thank you for visiting our store");
        } else {
            creator.finalPrice();
            System.out.println("YOUR FINAL PRICE IS: " + creator.getTotalPrice());
        }
    }

    public void pizzaCost() {
        for (int i = 0; i < Pizza.PizzaBuilder.getCount(); i++) {
            double pizzas = 0;
            pizzas += creator.getPizzas().get(i).getPrice();
            creator.setPizzaPrice(pizzas);
        }
        for (Pizza p : creator.getPizzas()) {
            System.out.println(p);
        }
    }

    public void drinksCost() {
        for (int i = 0; i < Drinks.DrinksBuilder.getCount(); i++) {
            double drinksCount = 0;
            drinksCount += creator.getDrinks().get(i).getPrice();
            creator.setDrinksPrice(drinksCount);
        }
        for (Drinks dr : creator.getDrinks()) {
            System.out.println(dr);
        }
    }
}
