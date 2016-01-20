package pizza;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
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
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static LocalDate date = LocalDate.now();

    public static void main(String[] args) throws IOException {

        makePizza();
        makeDrinks();
        totalPrice = pizzaPrice + drinksPrice;

        finalPrice();
        System.out.println("Your final price is: " + totalPrice);
    }


    public static void finalPrice() {
        System.out.println("Please enter the date when you want to buy pizza in format : Year-month-day");
        try {
            date = LocalDate.parse(reader.readLine());
            Check.check();
            System.out.println("Do you have a discount card?");
            String discount = reader.readLine();
            Check.setDiscount(discount);
            Check.weekends();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void makePizza() {
        System.out.println("Please enter how many pizzas do you want:");
        int n = 0;
        try {
            n = Integer.parseInt(reader.readLine());


            if (n > 0) {
                for (int j = 0; j < n; j++) {
                    PizzaBuilder pizzaBuilder = new PizzaBuilder();
                    System.out.println("Please choose your pizza.");
                    System.out.println("1. Enter name of pizza. Available pizzas are: Capricciosa, Salami, Vegeteriana, Mexicano, Papperoni");
                    pizzaBuilder = pizzaBuilder.makeName(reader.readLine());
                    System.out.println("2. Enter the size of pizza: Available sizes of pizza are: 30 and 50");
                    pizzaBuilder = pizzaBuilder.makeInfo().makeSize(Integer.parseInt(reader.readLine())).makePrice();
                    System.out.println("3. If you want some addons, please enter the number of addons: ");
                    int adds = Integer.parseInt(reader.readLine());
                    System.out.println("4.  Enter the name of addons. Available addons are: Cheese, Sausage, Spice, Fruits, Tomato");
                    for (int k = 0; k < adds; k++) {
                        pizzaBuilder = pizzaBuilder.add(reader.readLine());
                    }
                    pizzas.add(pizzaBuilder.build());
                    System.out.println(pizzas.get(j));
                }
            } else {
                System.err.println("Please enter the number > 0");
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < PizzaBuilder.count; i++) {
            pizzaPrice += pizzas.get(i).price;
        }
        for (Pizza p : pizzas) {
            System.out.println(p);
        }
    }

    public static void makeDrinks() {
        System.out.println("Please enter how many drinks do you want:");
        int d = 0;
        try {
            d = Integer.parseInt(reader.readLine());
            if (d > 0) {
                for (int j = 0; j < d; j++) {
                    DrinksBuilder drinksBuilder = new DrinksBuilder();
                    System.out.println("Please choose your drinks.");
                    System.out.println("1. Enter the name of drink. Please choose from following : Beer, Vine, Coca-cola, Fanta, Sprite, Pepsi, Coffee, Juice");
                    drinksBuilder = drinksBuilder.makeName(reader.readLine()).makePrice();
                    System.out.println("2. Enter the size of drink: Please choose from following sizes: 0.5, 1, 1.5, 2");
                    drinksBuilder = drinksBuilder.makeSize(Double.parseDouble(reader.readLine()));
                    drinks.add(drinksBuilder.build());
                    System.out.println(drinks);
                }
            } else {
                System.err.println("Please enter the number > 0");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < DrinksBuilder.count; i++) {
            drinksPrice += drinks.get(i).price;
        }
        for (Drinks dr : drinks) {
            System.out.println(dr);
        }
    }
}
