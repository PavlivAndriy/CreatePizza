package io;


import domain.*;
import service.Calculation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;


public class Creator {
    private static ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
    private static ArrayList<Drinks> drinks = new ArrayList<Drinks>();
    private static Calculation calculation = new Calculation();
    private double pizzaPrice;
    private double drinksPrice;
    private double totalPrice = 0;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private LocalDate date = LocalDate.now();
    private int adds;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getDrinksPrice() {
        return drinksPrice;
    }

    public void setDrinksPrice(double drinksPrice) {
        this.drinksPrice = drinksPrice;
    }

    public double getPizzaPrice() {
        return pizzaPrice;
    }

    public void setPizzaPrice(double pizzaPrice) {
        this.pizzaPrice = pizzaPrice;
    }

    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    public ArrayList<Drinks> getDrinks() {
        return drinks;
    }

    public void finalPrice() {
        System.out.println("Please enter the date when you want to buy pizza or drinks in format : Year-month-day");
        try {
            date = LocalDate.parse(reader.readLine());
            calculation.check();
            System.out.println("Do you have a discount card?");
            String discount = reader.readLine();
            calculation.setDiscount(discount);
            calculation.weekends();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void makePizza() {
        System.out.println("Please enter how many pizzas do you want:");
        int pizzaCount = 0;
        try {
            pizzaCount = Integer.parseInt(reader.readLine());

            if (pizzaCount > 0) {
                for (int j = 0; j < pizzaCount; j++) {
                    Pizza.PizzaBuilder pizzaBuilder = new Pizza.PizzaBuilder();
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
                System.err.println("As we see you don't want pizza, your number of pizzas is: " + pizzaCount);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Please type int number of pizzas");
            System.exit(0);
        }

    }

    public void makeDrinks() {
        System.out.println("Please enter how many drinks do you want:");
        int drinksCount = 0;
        try {
            drinksCount = Integer.parseInt(reader.readLine());
            if (drinksCount > 0) {
                for (int j = 0; j < drinksCount; j++) {
                    Drinks.DrinksBuilder drinksBuilder = new Drinks.DrinksBuilder();
                    System.out.println("Please choose your drinks.");
                    System.out.println("1. Enter the name of drink. Please choose from following : BEER, VINE, COCACOLA, FANTA, SPRITE, JUICE, COFFEE, PEPSI");
                    drinksBuilder = drinksBuilder.makeName(DrinksNames.valueOf(reader.readLine())).makePrice();
                    System.out.println("2. Enter the size of drink: Please choose from following sizes: LOW(0.5L), MID1(1L), MID2(1.5L), BIG(2L)");
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
                System.err.println("As we see you don't want drinks, your number of drinks is :" + drinksCount);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Please type int number of drinks.");
            System.exit(0);
        }
    }
}
