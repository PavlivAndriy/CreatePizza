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
public class Creator {
    private static double pizzaPrice;
    private static double drinksPrice;
    private static double totalPrice = 0;
    private static ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
    private static ArrayList<Drinks> drinks = new ArrayList<Drinks>();
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static LocalDate date = LocalDate.now();
    private static int adds;

    public static LocalDate getDate() {
        return date;
    }

    public static void setDate(LocalDate date) {
        Creator.date = date;
    }

    public static double getTotalPrice() {
        return totalPrice;
    }

    public static void setTotalPrice(double totalPrice) {
        Creator.totalPrice = totalPrice;
    }

    public static double getDrinksPrice() {
        return drinksPrice;
    }

    public static void setDrinksPrice(double drinksPrice) {
        Creator.drinksPrice = drinksPrice;
    }

    public static double getPizzaPrice() {
        return pizzaPrice;
    }

    public static void setPizzaPrice(double pizzaPrice) {
        Creator.pizzaPrice = pizzaPrice;
    }

    public static ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    public static ArrayList<Drinks> getDrinks() {
        return drinks;
    }

    public static void finalPrice() {
        System.out.println("Please enter the date when you want to buy pizza or drinks in format : Year-month-day");
        try {
            date = LocalDate.parse(reader.readLine());
            Calculation.check();
            System.out.println("Do you have a discount card?");
            String discount = reader.readLine();
            Calculation.setDiscount(discount);
            Calculation.weekends();
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
                    System.out.println("1. Enter name of pizza. Available pizzas are: CAPRICCIOSA, SALAMI, VEGETERIANA, MEXICANO, PAPPERONI");
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
                        System.out.println("4.  Enter the name of addons. Available addons are: CHEESE, SAUSAGE, SPICE, FRUITS, TOMATO");
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
                    System.out.println("1. Enter the name of drink. Please choose from following : BEER, VINE, COCACOLA, FANTA, SPRITE, JUICE, COFFEE, PEPSI");
                    drinksBuilder = drinksBuilder.makeName(DrinksNames.valueOf(reader.readLine())).makePrice();
                    System.out.println("2. Enter the size of drink: Please choose from following sizes: LOW(0.5), MID1(1), MID2(1.5), BIG(2)");
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
    }
}
