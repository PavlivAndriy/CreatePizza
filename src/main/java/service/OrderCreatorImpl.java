package service;

import domain.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Andriy on 2/21/2016.
 */
public class OrderCreatorImpl implements OrderCreator{
    public static final String REGEX_PIZZA_SIZE = "^[3,5]0$";
    public static final String REGEX_COUNT = "[0-9]?[0-9]?";
    private static final String REGEX_ADDONS_COUNT = "[0-9]";
    private static final String REGEX_DISCOUNT = "([Y,y](es|ES|eS|Es))|([N,n](o|O))";
    private Data data = new Data();
    private Bill bill = new Bill();
    private String discount;
    private Pizza.PizzaBuilder pizzaBuilder = new Pizza.PizzaBuilder();
    private Drinks.DrinksBuilder drinksBuilder = new Drinks.DrinksBuilder();
    private String readerText;
    private int pizzaCount;
    private int drinksCount;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

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

    public Bill getBill() {
        return bill;
    }

    private boolean regex(String s, RegexTypes title) {
        Pattern pattern = null;
        try {
            switch (title) {
                case PIZZA_SIZE:
                    pattern = Pattern.compile(REGEX_PIZZA_SIZE);
                    break;
                case PIZZA_ADDONS:
                    pattern = Pattern.compile(String.valueOf(PizzasAddons.valueOf(s)));
                    break;
                case COUNT:
                    pattern = Pattern.compile(REGEX_COUNT);
                    break;
                case PIZZA_NAME:
                    pattern = Pattern.compile(String.valueOf(PizzasNames.valueOf(s)));
                    break;
                case ADDONS_COUNT:
                    pattern = Pattern.compile(REGEX_ADDONS_COUNT);
                    break;
                case DRINKS_NAME:
                    pattern = Pattern.compile(String.valueOf(DrinksNames.valueOf(s)));
                    break;
                case DRINKS_SIZE:
                    pattern = Pattern.compile(String.valueOf(DrinksSize.valueOf(s)));
                    break;
                case DATE:
                    pattern = Pattern.compile(String.valueOf(LocalDate.parse(s)));
                    break;
                case DISCOUNT:
                    pattern = Pattern.compile(REGEX_DISCOUNT);
                    break;
                default:
                    System.err.println("ERROR");
                    break;
            }
            Matcher matcher = pattern.matcher(s);
            return matcher.matches();
        } catch (IllegalArgumentException e) {
        } catch (DateTimeParseException e) {
        } catch (NullPointerException e) {
        }
        return false;

    }

    private void checkPizzaCount() {
        System.out.println("Please enter how many pizzas do you want:");
        for (int i = 3; i > 0; i--) {
            try {
                readerText = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (regex(readerText, RegexTypes.COUNT)) {
                pizzaCount = Integer.parseInt(readerText);
                return;
            } else {
                System.err.println("You can choose from 0 to 99 pizzas. You have " + (i - 1) + " tries left ");
                if (i == 1) {
                    System.exit(0);
                }
            }
        }
    }

    private void checkPizzasNamesEnum() {
        for (int i = 3; i > 0; i--) {
            try {
                System.out.println("Please choose your pizza.");
                System.out.println("1. Enter name of pizza. Available pizzas are: CAPRICCIOSA, SALAMI, VEGETERIANA, MEXICANO, PAPPERONI");
                readerText = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (regex(readerText, RegexTypes.PIZZA_NAME)) {
                pizzaBuilder = pizzaBuilder.makeName(PizzasNames.valueOf(readerText));
                return;
            } else {
                System.err.println("You have " + (i - 1) + " tries left ");
                if (i == 1) {
                    System.exit(0);
                }
            }
        }
    }

    private void checkPizzaSize() {
        for (int i = 3; i > 0; i--) {
            System.out.println("2. Enter the size of pizza: Available sizes of pizza are: 30 and 50");
            try {
                readerText = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (regex(readerText,RegexTypes.PIZZA_SIZE)) {
                pizzaBuilder = pizzaBuilder.makeInfo().makeSize(Integer.parseInt(readerText)).makePrice();
                return;
            } else {
                System.err.println("You have " + (i - 1) + " tries left ");
                if (i == 1) {
                    System.exit(0);
                }
            }
        }
    }

    private void checkPizzaAddonsCount() {
        for (int i = 3; i > 0; i--) {
            System.out.println("3. If you want some addons, please enter the number of addons: ");
            try {
                readerText = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (regex(readerText, RegexTypes.ADDONS_COUNT)) {
                data.setAddons(Integer.parseInt(readerText));
                return;
            } else {
                System.err.println("You can choose from 0 to 9 addons. You have " + (i - 1) + " tries left ");
                if (i == 1) {
                    System.exit(0);
                }
            }
        }
    }

    private void checkPizzaAddonsEnum() {
        for (int i = 3; i > 0; i--) {
            try {
                System.out.println("4.  Enter the name of addons. Available addons are: CHEESE, SAUSAGE, SPICE, FRUITS, TOMATO");
                readerText = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (regex(readerText, RegexTypes.PIZZA_ADDONS)) {
                pizzaBuilder = pizzaBuilder.add(PizzasAddons.valueOf(readerText));
                return;
            } else {
                System.err.println("You have " + (i - 1) + " tries left ");
                if (i == 1) {
                    System.exit(0);
                }
            }
        }
    }

    private void checkDrinksCount() {
        for (int i = 3; i > 0; i--) {
            System.out.println("Please enter how many drinks do you want:");
            try {
                readerText = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (regex(readerText, RegexTypes.COUNT)) {
                drinksCount = Integer.parseInt(readerText);
                return;
            } else {
                System.err.println("You can choose from 0 to 99 drinks. You have " + (i - 1) + " tries left ");
                if (i == 1) {
                    System.exit(0);
                }
            }
        }
    }

    private void checkDrinksNamesEnum() {
        for (int i = 3; i > 0; i--) {
            try {
                System.out.println("Please choose your drinks.");
                System.out.println("1. Enter the name of drink. Please choose from following : BEER, VINE, COCACOLA, FANTA, SPRITE, JUICE, COFFEE, PEPSI");
                readerText = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (regex(readerText, RegexTypes.DRINKS_NAME)) {
                drinksBuilder = drinksBuilder.makeName(DrinksNames.valueOf(readerText)).makePrice();
                return;
            } else {
                System.err.println("You have " + (i - 1) + " tries left ");
                if (i == 1) {
                    System.exit(0);
                }
            }
        }
    }

    private void checkDrinksSizeEnum() {
        for (int i = 3; i > 0; i--) {
            try {
                System.out.println("2. Enter the size of drink: Please choose from following sizes: LOW(0.5L), MID1(1L), MID2(1.5L), BIG(2L)");
                readerText = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (regex(readerText, RegexTypes.DRINKS_SIZE)) {
                drinksBuilder = drinksBuilder.makeSize(DrinksSize.valueOf(readerText));
                return;
            } else {
                System.err.println("You have " + (i - 1) + " tries left ");
                if (i == 1) {
                    System.exit(0);
                }
            }
        }
    }

    private void checkDate() {
        for (int i = 3; i > 0; i--) {
            try {
                System.out.println("Please enter the date when you want to buy pizza or drinks in format : Year-month-day");
                readerText = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (regex(readerText, RegexTypes.DATE)) {
                data.setDate(LocalDate.parse(readerText));
                return;
            } else {
                System.err.println("You have " + (i - 1) + " tries left ");
                if (i == 1) {
                    System.exit(0);
                }
            }
        }
    }

    private void checkDiscount() {
        for (int i = 3; i > 0; i--) {
            try {
                System.out.println("Do you have a discount card?");
                readerText = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (regex(readerText, RegexTypes.DISCOUNT)) {
                discount = readerText;
                return;
            } else {
                System.err.println("Please type Yes or No.  You have " + (i - 1) + " tries left ");
                if (i == 1) {
                    System.exit(0);
                }
            }
        }
    }

    public void finalPrice() {
        checkDate();
        checkDiscount();
    }

    private void makePizza() {
        try {
            checkPizzaCount();
            if (pizzaCount > 0) {
                for (int j = 0; j < pizzaCount; j++) {
                    checkPizzasNamesEnum();
                    checkPizzaSize();
                    checkPizzaAddonsCount();
                    if (data.getAddons() > 0) {
                        for (int k = 0; k < data.getAddons(); k++) {
                            checkPizzaAddonsEnum();
                        }
                    } else {
                        System.out.println("Your pizza is without addons");
                    }
                    bill.getPizzas().add(pizzaBuilder.build());
                    System.out.println(bill.getPizzas().get(j));
                }
            } else {
                System.err.println("As we see you don't want pizza, your number of pizzas is: " + pizzaCount);
            }


        } catch (IllegalArgumentException e) {

        } catch (NullPointerException e) {

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Please type int number of pizzas");
            System.exit(0);
        }

    }

    private void makeDrinks() {
        try {
            checkDrinksCount();
            if (drinksCount > 0) {
                for (int j = 0; j < drinksCount; j++) {
                    checkDrinksNamesEnum();
                    checkDrinksSizeEnum();
                    bill.getDrinks().add(drinksBuilder.build());
                    System.out.println(bill.getDrinks());
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

    @Override
    public Data readData() {
        makePizza();
        makeDrinks();
        return data;
    }
}
