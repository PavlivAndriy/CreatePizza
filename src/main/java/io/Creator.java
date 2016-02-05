package io;


import domain.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Creator {
    private Data data = new Data();
    private String discount;
    private Pizza.PizzaBuilder pizzaBuilder = new Pizza.PizzaBuilder();
    private Drinks.DrinksBuilder drinksBuilder = new Drinks.DrinksBuilder();
    private String readerText;
    private int countTries = 3;
    private int pizzaCount;
    private int drinksCount;

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

    public boolean regexPizzaSize(String s) {
        Pattern pattern = Pattern.compile("^[3,5]0$");
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }

    public boolean regexPizzaAddons(String s) {
        try {
            Pattern pattern = Pattern.compile(String.valueOf(PizzasAddons.valueOf(s)));
            Matcher matcher = pattern.matcher(s);
            return matcher.matches();
        } catch (IllegalArgumentException e) {
        }
        return false;
    }

    public boolean regexCount(String s) {
        Pattern pattern = Pattern.compile("[0-9]?[0-9]?");
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }

    public boolean regexPizzaName(String s) {
        try {
            Pattern pattern = Pattern.compile(String.valueOf(PizzasNames.valueOf(s)));
            Matcher matcher = pattern.matcher(s);
            return matcher.matches();
        } catch (IllegalArgumentException e) {
        }
        return false;
    }

    public boolean regexAddons(String s) {
        Pattern pattern = Pattern.compile("[0-9]");
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }

    public boolean regexDrinksName(String s) {
        try {
            Pattern pattern = Pattern.compile(String.valueOf(DrinksNames.valueOf(s)));
            Matcher matcher = pattern.matcher(s);
            return matcher.matches();
        } catch (IllegalArgumentException e) {
        }
        return false;
    }

    public boolean regexDrinksSize(String s) {
        try {
            Pattern pattern = Pattern.compile(String.valueOf(DrinksSize.valueOf(s)));
            Matcher matcher = pattern.matcher(s);
            return matcher.matches();
        } catch (IllegalArgumentException e) {
        }
        return false;
    }

    public int testPizzaCount() {
        System.out.println("Please enter how many pizzas do you want:");
        try {
            readerText = data.getReader().readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (regexCount(readerText)) {
            pizzaCount = Integer.parseInt(readerText);
            countTries = 3;
            return 1;
        } else {
            countTries--;
            System.err.println("You can choose from 0 to 99 pizzas. You have " + countTries + " tries left ");
            if (countTries == 0) {
                System.exit(0);
            } else {
                return testPizzaCount();
            }
            return 0;
        }

    }

    public PizzasNames testPizzasNamesEnum() {
        try {
            System.out.println("Please choose your pizza.");
            System.out.println("1. Enter name of pizza. Available pizzas are: CAPRICCIOSA, SALAMI, VEGETERIANA, MEXICANO, PAPPERONI");
            readerText = data.getReader().readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (regexPizzaName(readerText)) {
            pizzaBuilder = pizzaBuilder.makeName(PizzasNames.valueOf(readerText));
            countTries = 3;
            return null;
        } else {
            countTries--;
            System.err.println("You have " + countTries + " tries left ");
            if (countTries == 0) {
                System.exit(0);
            } else {
                return testPizzasNamesEnum();
            }
            return null;
        }

    }

    public int testPizzaSize() {
        System.out.println("2. Enter the size of pizza: Available sizes of pizza are: 30 and 50");
        try {
            readerText = data.getReader().readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (regexPizzaSize(readerText)) {
            pizzaBuilder = pizzaBuilder.makeInfo().makeSize(Integer.parseInt(readerText)).makePrice();
            countTries = 3;
            return 1;
        } else {
            countTries--;
            System.err.println("You have " + countTries + " tries left ");
            if (countTries == 0) {
                System.exit(0);
            } else {
                return testPizzaSize();
            }
            return 0;
        }
    }

    public int testPizzaAddonsCount() {
        System.out.println("3. If you want some addons, please enter the number of addons: ");
        try {
            readerText = data.getReader().readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (regexAddons(readerText)) {
            data.setAddons(Integer.parseInt(readerText));
            countTries = 3;
            return 1;
        } else {
            countTries--;
            System.err.println("You can choose from 0 to 9 addons. You have " + countTries + " tries left ");
            if (countTries == 0) {
                System.exit(0);
            } else {
                return testPizzaAddonsCount();
            }
            return 0;
        }
    }

    public PizzasAddons testPizzaAddonsEnum() {
        try {
            System.out.println("4.  Enter the name of addons. Available addons are: CHEESE, SAUSAGE, SPICE, FRUITS, TOMATO");
            readerText = data.getReader().readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (regexPizzaAddons(readerText)) {
            pizzaBuilder = pizzaBuilder.add(PizzasAddons.valueOf(readerText));
            countTries = 3;
            return null;
        } else {
            countTries--;
            System.err.println("You have " + countTries + " tries left ");
            if (countTries == 0) {
                System.exit(0);
            } else {
                return testPizzaAddonsEnum();
            }
            return null;
        }
    }

    public int testDrinksCount() {
        System.out.println("Please enter how many drinks do you want:");
        try {
            readerText = data.getReader().readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (regexCount(readerText)) {
            drinksCount = Integer.parseInt(readerText);
            countTries = 3;
            return 1;
        } else {
            countTries--;
            System.err.println("You can choose from 0 to 99 drinks. You have " + countTries + " tries left ");
            if (countTries == 0) {
                System.exit(0);
            } else {
                return testDrinksCount();
            }
            return 0;
        }

    }

    public DrinksNames testDrinksNamesEnum() {
        try {
            System.out.println("Please choose your drinks.");
            System.out.println("1. Enter the name of drink. Please choose from following : BEER, VINE, COCACOLA, FANTA, SPRITE, JUICE, COFFEE, PEPSI");
            readerText = data.getReader().readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (regexDrinksName(readerText)) {
            drinksBuilder = drinksBuilder.makeName(DrinksNames.valueOf(readerText)).makePrice();
            countTries = 3;
            return null;
        } else {
            countTries--;
            System.err.println("You have " + countTries + " tries left ");
            if (countTries == 0) {
                System.exit(0);
            } else {
                return testDrinksNamesEnum();
            }
            return null;
        }

    }

    public DrinksNames testDrinksSizeEnum() {
        try {
            System.out.println("2. Enter the size of drink: Please choose from following sizes: LOW(0.5L), MID1(1L), MID2(1.5L), BIG(2L)");
            readerText = data.getReader().readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (regexDrinksSize(readerText)) {
            drinksBuilder = drinksBuilder.makeSize(DrinksSize.valueOf(readerText));
            countTries = 3;
            return null;
        } else {
            countTries--;
            System.err.println("You have " + countTries + " tries left ");
            if (countTries == 0) {
                System.exit(0);
            } else {
                return testDrinksSizeEnum();
            }
            return null;
        }

    }

    public void finalPrice() {
        System.out.println("Please enter the date when you want to buy pizza or drinks in format : Year-month-day");
        try {
            data.setDate(LocalDate.parse(data.getReader().readLine()));
            System.out.println("Do you have a discount card?");
            discount = data.getReader().readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void makePizza() {
        try {
            testPizzaCount();
            if (pizzaCount > 0) {
                for (int j = 0; j < pizzaCount; j++) {
                    testPizzasNamesEnum();
                    testPizzaSize();
                    testPizzaAddonsCount();
                    if (data.getAddons() > 0) {
                        for (int k = 0; k < data.getAddons(); k++) {
                            testPizzaAddonsEnum();
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


        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Please type int number of pizzas");
            System.exit(0);
        }

    }

    public void makeDrinks() {
        try {
            testDrinksCount();
            if (drinksCount > 0) {
                for (int j = 0; j < drinksCount; j++) {
                    testDrinksNamesEnum();
                    testDrinksSizeEnum();
                    data.getDrinks().add(drinksBuilder.build());
                    System.out.println(data.getDrinks());
                }
            } else {
                System.err.println("As we see you don't want drinks, your number of drinks is :" + drinksCount);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Please type int number of drinks.");
            System.exit(0);
        }
    }
}
