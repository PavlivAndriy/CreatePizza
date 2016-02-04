package io;


import domain.*;

import java.io.IOException;
import java.time.LocalDate;


public class Creator {
    private Data data = new Data();
    private String discount;
    private Pizza.PizzaBuilder pizzaBuilder = new Pizza.PizzaBuilder();
    private Drinks.DrinksBuilder drinksBuilder = new Drinks.DrinksBuilder();

    public Pizza.PizzaBuilder getPizzaBuilder() {
        return pizzaBuilder;
    }

    public Drinks.DrinksBuilder getDrinksBuilder() {
        return drinksBuilder;
    }

    public String getDiscount() {
        return discount;
    }

    public Data getData() {
        return data;
    }

    public void finalPrice() {
        System.out.println("Please enter the date when you want to buy pizza or drinks in format : Year-month-day");
        try {
            data.setDate(LocalDate.parse(data.getReader().readLine()));
            //calculationService.check();
            System.out.println("Do you have a discount card?");
            discount = data.getReader().readLine();
            //calculationService.setDiscount(discount);
            //calculationService.weekends();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void makePizza() {
        System.out.println("Please enter how many pizzas do you want:");
        int pizzaCount;
        try {
            pizzaCount = Integer.parseInt(data.getReader().readLine());

            if (pizzaCount > 0) {
                for (int j = 0; j < pizzaCount; j++) {

                    System.out.println("Please choose your pizza.");
                    System.out.println("1. Enter name of pizza. Available pizzas are: CAPRICCIOSA, SALAMI, VEGETERIANA, MEXICANO, PAPPERONI");
                    pizzaBuilder = pizzaBuilder.makeName(PizzasNames.valueOf(data.getReader().readLine()));
                    System.out.println("2. Enter the size of pizza: Available sizes of pizza are: 30 and 50");
                    try {
                        pizzaBuilder = pizzaBuilder.makeInfo().makeSize(Integer.parseInt(data.getReader().readLine())).makePrice();
                        System.out.println("3. If you want some addons, please enter the number of addons: ");
                        data.setAddons(Integer.parseInt(data.getReader().readLine()));
                    } catch (Exception e) {
                        System.err.println("Please type int number.");
                        System.exit(0);
                    }

                    if (data.getAddons() > 0) {
                        System.out.println("4.  Enter the name of addons. Available addons are: CHEESE, SAUSAGE, SPICE, FRUITS, TOMATO");
                        for (int k = 0; k < data.getAddons(); k++) {
                            pizzaBuilder = pizzaBuilder.add(PizzasAddons.valueOf(data.getReader().readLine()));
                        }
                    } else {
                        System.out.println("Your pizza is without addons");
                    }
                    data.getPizzas().add(pizzaBuilder.build());
                    System.out.println(data.getPizzas().get(j));
                }
            } else {
                System.err.println("As we see you don't want pizza, your number of pizzas is: " + pizzaCount);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Please type int number of pizzas");
            System.exit(0);
        }

    }

    public void makeDrinks() {
        System.out.println("Please enter how many drinks do you want:");
        int drinksCount;
        try {
            drinksCount = Integer.parseInt(data.getReader().readLine());
            if (drinksCount > 0) {
                for (int j = 0; j < drinksCount; j++) {

                    System.out.println("Please choose your drinks.");
                    System.out.println("1. Enter the name of drink. Please choose from following : BEER, VINE, COCACOLA, FANTA, SPRITE, JUICE, COFFEE, PEPSI");
                    drinksBuilder = drinksBuilder.makeName(DrinksNames.valueOf(data.getReader().readLine())).makePrice();
                    System.out.println("2. Enter the size of drink: Please choose from following sizes: LOW(0.5L), MID1(1L), MID2(1.5L), BIG(2L)");
                    try {
                        drinksBuilder = drinksBuilder.makeSize(DrinksSize.valueOf(data.getReader().readLine()));
                    } catch (Exception e) {
                        System.err.println("Please type double number.");
                        System.exit(0);
                    }
                    data.getDrinks().add(drinksBuilder.build());
                    System.out.println(data.getDrinks());
                }
            } else {
                System.err.println("As we see you don't want drinks, your number of drinks is :" + drinksCount);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Please type int number of drinks.");
            System.exit(0);
        }
    }
}
