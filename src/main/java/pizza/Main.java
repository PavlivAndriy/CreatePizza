package pizza;


import java.util.ArrayList;


/**
 * Created by Andriy on 1/12/2016.
 */
public class Main {
    public static double pizzaPrice;
    public static double drinksPrice;
    public static double totalPrice = 0;
    public static ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
    public static ArrayList<Drinks> drinks = new ArrayList<Drinks>();

    public static void main(String[] args) {
        pizzas.add(new PizzaBuilder().makeName("Papperoni").makeInfo().makeSize(50).makePrice().add("Cheese").add("Sausage").build());
        pizzas.add(new PizzaBuilder().makeName("Salami").makeInfo().makeSize(30).makePrice().build());
        pizzas.add(new PizzaBuilder().makeName("Capricciosa").makeInfo().makeSize(50).makePrice().add("Tomato").add("Sausage").build());


        drinks.add(new DrinksBuilder().makeName("Coffee").makePrice().makeSize(1).build());
        drinks.add(new DrinksBuilder().makeName("Beer").makePrice().makeSize(1).build());
        drinks.add(new DrinksBuilder().makeName("Coca-cola").makePrice().makeSize(1).build());

        makePizza();
        makeDrinks();

        totalPrice = pizzaPrice + drinksPrice;

        finalPrice();
        System.out.println("Your final price is: " + totalPrice);
    }



    public static void finalPrice(){
        Check.check();
        Check.setDiscount(true);
        Check.weekends();

    }

    public static void makePizza(){
        for (int i =0; i < PizzaBuilder.count; i++){
            pizzaPrice += pizzas.get(i).price;
        }
        for (Pizza p : pizzas){
            System.out.println(p);
        }
    }

    public static void makeDrinks(){
        for (int i =0; i < DrinksBuilder.count; i++){
            drinksPrice += drinks.get(i).price;
        }
        for (Drinks d : drinks){
            System.out.println(d);
        }
    }
}
