package pizza;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

/**
 * Created by Andriy on 1/15/2016.
 */
public class Check {



    public static void setDiscount(boolean discount) {
        LocalDate date = LocalDate.now();
        if (date.getDayOfYear() == 7
                | (date.getMonth().equals(Month.AUGUST) & date.getDayOfMonth() == 24)
                | (date.getDayOfYear() == 256)){
            Main.totalPrice *= 0.5;
            discount = false;
        }

        if (discount == true){
            Main.totalPrice *= 0.9;
        } else if(discount == false){
            Main.totalPrice *= 1;
        }

    }


    public static void check(){
        LocalDate date = LocalDate.now();

        if (date.getMonth().equals(Month.SEPTEMBER) & date.getDayOfMonth() < 8 & date.getDayOfWeek().equals(DayOfWeek.SATURDAY)){
            Main.totalPrice *= 1;
        } else {
            Main.totalPrice *= 1.05;
        }
    }

    public static void weekends(){
        LocalDate date = LocalDate.now();

        if (date.getDayOfWeek().equals(DayOfWeek.FRIDAY)
                & date.getDayOfWeek().equals(DayOfWeek.SATURDAY)
                & date.getDayOfWeek().equals(DayOfWeek.SUNDAY)){

            Main.totalPrice *= 1.05;
        }

    }
}
