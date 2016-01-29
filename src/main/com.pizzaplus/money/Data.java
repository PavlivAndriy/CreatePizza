package money;


import drinks.Drinks;
import drinks.DrinksBuilder;
import drinks.DrinksNames;
import drinks.DrinksSize;
import pizza.Pizza;
import pizza.PizzaBuilder;
import pizza.PizzasAddons;
import pizza.PizzasNames;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;


/**
 * Created by Andriy on 1/12/2016.
 */
public class Data {
    public static double pizzaPrice;
    public static double drinksPrice;
    public static double totalPrice = 0;
    public static ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
    public static ArrayList<Drinks> drinks = new ArrayList<Drinks>();
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static LocalDate date = LocalDate.now();
    public static int adds;

    public static void storeInfo() {
        totalPrice = pizzaPrice + drinksPrice;
        if (totalPrice == 0) {
            System.err.println("Thank you for visiting our store");
        } else {
            finalPrice();
            System.out.println("Your final price is: " + totalPrice);
        }
    }

    public static void finalPrice() {
        System.out.println("Please enter the date when you want to buy pizza or drinks in format : Year-month-day");
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
                    pizzaBuilder = pizzaBuilder.makeName(PizzasNames.valueOf(reader.readLine()));
                    System.out.println("2. Enter the size of pizza: Available sizes of pizza are: 30 and 50");
                    try {

                        pizzaBuilder = pizzaBuilder.makeInfo().makeSize(Integer.parseInt(reader.readLine())).makePrice();
                        System.out.println("3. If you want some addons, please enter the number of addons: ");
                        adds = Integer.parseInt(reader.readLine());
                    } catch (Exception e) {
                        System.err.println("Please type int number.");
                        System.exit(0);
                    }

                    if (adds > 0) {
                        System.out.println("4.  Enter the name of addons. Available addons are: Cheese, Sausage, Spice, Fruits, Tomato");
                        for (int k = 0; k < adds; k++) {
                            pizzaBuilder = pizzaBuilder.add(PizzasAddons.valueOf(reader.readLine()));
                        }
                    } else {
                        System.out.println("Your pizza is without addons");
                    }
                    pizzas.add(pizzaBuilder.build());
                    System.out.println(pizzas.get(j));
                }
            } else {
                System.err.println("As we see you don't want pizza, your number of pizzas is: " + n);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Please type int number of pizzas");
            System.exit(0);
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
                    drinksBuilder = drinksBuilder.makeName(DrinksNames.valueOf(reader.readLine())).makePrice();
                    System.out.println("2. Enter the size of drink: Please choose from following sizes: 0.5, 1, 1.5, 2");
                    try {
                        drinksBuilder = drinksBuilder.makeSize(DrinksSize.valueOf(reader.readLine()));
                    } catch (Exception e) {
                        System.err.println("Please type double number.");
                        System.exit(0);
                    }
                    drinks.add(drinksBuilder.build());
                    System.out.println(drinks);
                }
            } else {
                System.err.println("As we see you don't want drinks, your number of drinks is :" + d);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Please type int number of drinks.");
            System.exit(0);
        }

        for (int i = 0; i < DrinksBuilder.count; i++) {
            drinksPrice += drinks.get(i).price;
        }
        for (Drinks dr : drinks) {
            System.out.println(dr);
        }
    }
}
