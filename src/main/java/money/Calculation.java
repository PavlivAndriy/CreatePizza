package money;

import drinks.Drinks;
import drinks.DrinksBuilder;
import pizza.Pizza;
import pizza.PizzaBuilder;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

/**
 * Created by Andriy on 1/15/2016.
 */
public class Calculation {

    public static void setDiscount(String discount) {
        double price = Creator.getTotalPrice();
        LocalDate date = Creator.getDate();
        if (date.getDayOfYear() == 7
                | (date.getMonth().equals(Month.AUGUST) & date.getDayOfMonth() == 24)
                | (date.getDayOfYear() == 256)) {
            price *= 0.5;
            Creator.setTotalPrice(price);
            System.out.println("Your economy, because of hollidays: " + Creator.getTotalPrice());
            discount = "No";
        } else {
            System.out.println("Available economy days are: Jannuary 7th, August 24th, 256 day of year");
        }

        if (discount.equalsIgnoreCase("Yes")) {
            double discountPay = Creator.getTotalPrice() * 0.1;
            price *= 0.9;
            Creator.setTotalPrice(price);
            System.out.println("Your discount is: " + discountPay);
        } else if (discount.equalsIgnoreCase("No")) {
            price *= 1;
            Creator.setTotalPrice(price);
        }

    }

    public static void check() {
        LocalDate date = Creator.getDate();
        double price = Creator.getTotalPrice();
        if (date.getMonth().equals(Month.SEPTEMBER) & date.getDayOfMonth() < 8 & date.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
            price *= 1;
            Creator.setTotalPrice(price);
            System.out.println("Today you don't need to pay tips");
        } else {
            double tips = 0.05 * Creator.getTotalPrice();
            price *= 1.05;
            Creator.setTotalPrice(price);
            System.out.println("Today your pay for tips is: +" + tips);
        }
    }

    public static void weekends() {
        LocalDate date = Creator.getDate();
        double price = Creator.getTotalPrice();
        if (date.getDayOfWeek().equals(DayOfWeek.FRIDAY)
                | date.getDayOfWeek().equals(DayOfWeek.SATURDAY)
                | date.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            double weekendsPay = 0.05 * Creator.getTotalPrice();
            price *= 1.05;
            Creator.setTotalPrice(price);
            System.out.println("Today there are weekends, and additional payment is: +" + weekendsPay);
        }

    }

    public static void storeInfo() {
        pizzaCost();
        drinksCost();
        Creator.setTotalPrice(Creator.getDrinksPrice() + Creator.getPizzaPrice());
        if (Creator.getTotalPrice() == 0) {
            System.err.println("Thank you for visiting our store");
        } else {
            Creator.finalPrice();
            System.out.println("YOUR FINAL PRICE IS: " + Creator.getTotalPrice());
        }
    }

    public static void pizzaCost() {

        for (int i = 0; i < PizzaBuilder.getCount(); i++) {
            double pizza = 0;
            pizza += Creator.getPizzas().get(i).getPrice();
            Creator.setPizzaPrice(pizza);
        }
        for (Pizza p : Creator.getPizzas()) {
            System.out.println(p);
        }
    }

    public static void drinksCost() {

        for (int i = 0; i < DrinksBuilder.getCount(); i++) {
            double drinks = 0;
            drinks += Creator.getDrinks().get(i).getPrice();
            Creator.setDrinksPrice(drinks);
        }
        for (Drinks dr : Creator.getDrinks()) {
            System.out.println(dr);
        }
    }
}
