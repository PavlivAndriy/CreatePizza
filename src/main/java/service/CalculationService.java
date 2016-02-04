package service;

import domain.Drinks;
import domain.Pizza;
import io.Creator;
import io.Data;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

public class CalculationService {
    public static Creator creator = new Creator();
    public static Data data = new Data();
    public void setDiscount(String discount) {
        double price = data.getTotalPrice();
        LocalDate date = data.getDate();
        if ((date.getDayOfYear() == 7)
                | (date.getMonth().equals(Month.AUGUST) & date.getDayOfMonth() == 24)
                | (date.getDayOfYear() == 256)) {
            price *= 0.5;
            data.setTotalPrice(price);
            System.out.println("Your economy, because of hollidays: " + data.getTotalPrice());
            discount = "No";
        } else {
            System.out.println("Available economy days are: Jannuary 7th, August 24th, 256 day of year");
        }

        if (discount.equalsIgnoreCase("Yes")) {
            double discountPay = data.getTotalPrice() * 0.1;
            price *= 0.9;
            data.setTotalPrice(price);
            System.out.println("Your discount is: " + discountPay);
        } else if (discount.equalsIgnoreCase("No")) {
            price *= 1;
            data.setTotalPrice(price);
        }

    }

    public void check() {
        LocalDate date = data.getDate();
        double price = data.getTotalPrice();
        if (date.getMonth().equals(Month.SEPTEMBER) & date.getDayOfMonth() < 8 & date.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
            price *= 1;
            data.setTotalPrice(price);
            System.out.println("Today you don't need to pay tips");
        } else {
            double tips = 0.05 * data.getTotalPrice();
            price *= 1.05;
            data.setTotalPrice(price);
            System.out.println("Today your pay for tips is: +" + tips);
        }
    }

    public void weekends() {
        LocalDate date = data.getDate();
        double price = data.getTotalPrice();
        if (date.getDayOfWeek().equals(DayOfWeek.FRIDAY)
                | date.getDayOfWeek().equals(DayOfWeek.SATURDAY)
                | date.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            double weekendsPay = 0.05 * data.getTotalPrice();
            price *= 1.05;
            data.setTotalPrice(price);
            System.out.println("Today there are weekends, and additional payment is: +" + weekendsPay);
        }
    }

    public void storeInfo() {

        pizzaCost();
        drinksCost();
        data.setTotalPrice(data.getDrinksPrice() + data.getPizzaPrice());
        if (data.getTotalPrice() == 0) {
            System.err.println("Thank you for visiting our store");
        } else {
            creator.finalPrice();
            System.out.println("YOUR FINAL PRICE IS: " + data.getTotalPrice());
        }
    }

    public void pizzaCost() {
        for (int i = 0; i < Pizza.PizzaBuilder.getCount(); i++) {
            double pizzas = 0;
            pizzas += data.getPizzas().get(i).getPrice();
            data.setPizzaPrice(pizzas);
        }
        for (Pizza p : data.getPizzas()) {
            System.out.println(p);
        }
    }

    public void drinksCost() {
        for (int i = 0; i < Drinks.DrinksBuilder.getCount(); i++) {
            double drinksCount = 0;
            drinksCount += data.getDrinks().get(i).getPrice();
            data.setDrinksPrice(drinksCount);
        }
        for (Drinks dr : data.getDrinks()) {
            System.out.println(dr);
        }
    }
}
