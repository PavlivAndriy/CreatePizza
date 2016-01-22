package pizza;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

/**
 * Created by Andriy on 1/15/2016.
 */
public class Check {


    public static void setDiscount(String discount) {
        LocalDate date = Main.date;
        if (date.getDayOfYear() == 7
                | (date.getMonth().equals(Month.AUGUST) & date.getDayOfMonth() == 24)
                | (date.getDayOfYear() == 256)) {
            Main.totalPrice *= 0.5;
            System.out.println("Your economy, because of hollidays: " + Main.totalPrice);
            discount = "No";
        } else {

        }

        if (discount.equalsIgnoreCase("Yes")) {
            double discountPay = Main.totalPrice * 0.1;
            Main.totalPrice *= 0.9;
            System.out.println("Your discount is: " + discountPay);
        } else if (discount.equalsIgnoreCase("No")) {
            Main.totalPrice *= 1;
        }

    }


    public static void check() {
        LocalDate date = Main.date;

        if (date.getMonth().equals(Month.SEPTEMBER) & date.getDayOfMonth() < 8 & date.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
            Main.totalPrice *= 1;
            System.out.println("Today you don't need to pay tips");
        } else {
            double tips = 0.05 * Main.totalPrice;
            Main.totalPrice *= 1.05;
            System.out.println("Today your pay for tips is: +" + tips);
        }
    }

    public static void weekends() {
        LocalDate date = Main.date;

        if (date.getDayOfWeek().equals(DayOfWeek.FRIDAY)
                & date.getDayOfWeek().equals(DayOfWeek.SATURDAY)
                & date.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            double weekendsPay = 0.05 * Main.totalPrice;
            Main.totalPrice *= 1.05;
            System.out.println("Today there are weekends, and additional payment is: +" + weekendsPay);
        }

    }
}
