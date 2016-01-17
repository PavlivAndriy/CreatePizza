package pizza;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * Created by Andriy on 1/14/2016.
 */
public class DrinksBuilder {
    public String n;
    public double s;
    public int p = 0;
    public static int count = 0;


    public DrinksBuilder makeName(String n){
        this.n = n;
        count++;
        return this;
    }

    public DrinksBuilder makeSize(double s){
        this.s = s;
        if (s == 0.5){
            p /= s;
        } else if (s == 1){
            p /= s;
        } else if (s == 1.5){
            p *= s;
        } else if (s == 2){
            p *= s;
        } else {
            System.err.println("Please choose from following sizes: 0.5L, 1L, 1.5L, 2L");
        }
        return this;
    }


    public DrinksBuilder makePrice(){
        LocalDate date = LocalDate.now();
        if (n.equals("Beer")){
            p = 30;
        } else if (n.equals("Vine")){
            p = 50;
        } else if (n.equals("Coca-cola") | n.equals("Fanta") | n.equals("Sprite") | n.equals("Pepsi")){
            p = 20;
        } else if (n.equals("Juice")){
            p = 25;
        }  else if (n.equals("Coffee")){
            p = 21;
        } else if (n.equals("Coffee") & date.getDayOfWeek().equals(DayOfWeek.MONDAY)){
            p = 0;
        } else {
            System.err.println("There are not this kind of drink. Please choose from following : Beer, Vine, Coca-cola, Fanta" +
                    "Sprite, Pepsi, Coffee, Juice");
        }
        return this;
    }

    public Drinks build(){
        Drinks drinks = new Drinks();
        drinks.setName(n);
        drinks.setPrice(p);
        drinks.setSize(s);
        drinks.setCount(count);
        return drinks;
    }

}
