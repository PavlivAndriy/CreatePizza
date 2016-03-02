package service;

import domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class OrderCreatorImpl implements OrderCreator {
    private static final String REGEX_PIZZA_SIZE = "^[3,5]0$";
    private static final String REGEX_COUNT = "[0-9]?[0-9]?";
    private static final String REGEX_ADDONS_COUNT = "[0-9]";
    private static final String REGEX_DISCOUNT = "([Y,y](es|ES|eS|Es))|([N,n](o|O))";
    private static final Logger logger = LoggerFactory.getLogger(OrderCreatorImpl.class);
    private Data data = new Data();
    private Pizza.PizzaBuilder pizzaBuilder = new Pizza.PizzaBuilder();
    private Drinks.DrinksBuilder drinksBuilder = new Drinks.DrinksBuilder();
    private String readerText;
    private int pizzaCount;
    private int drinksCount;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

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
                    logger.error("ERROR, Please type correct Regex name");
                    break;
            }
            Matcher matcher = pattern.matcher(s);
            return matcher.matches();
        } catch (IllegalArgumentException e) {
            logger.error("Please type in another format " + e);
        } catch (DateTimeParseException e) {
            logger.error("Incorrect Data format " + e);
        } catch (NullPointerException e) {
            logger.error("Please put information different from null " + e);
        }
        return false;

    }

    private void checkPizzaCount() {
        System.out.println("Please enter how many pizzas do you want:");
        for (int i = 3; i > 0; i--) {
            try {
                readerText = reader.readLine();
            } catch (IOException e) {
                logger.error("Please type in another format", e);
            }
            if (regex(readerText, RegexTypes.COUNT)) {
                pizzaCount = Integer.parseInt(readerText);
                return;
            } else {
                logger.error("You can choose from 0 to 99 pizzas. You have " + (i - 1) + " tries left ");
                if (i == 1) {
                    break;
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
                logger.error("Please type in another format " + e);
            }
            if (regex(readerText, RegexTypes.PIZZA_NAME)) {
                pizzaBuilder = pizzaBuilder.makeName(PizzasNames.valueOf(readerText));
                return;
            } else {
                logger.error("You have " + (i - 1) + " tries left ");
                if (i == 1) {
                    break;
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
                logger.error("Please type in another format " + e);
            }
            if (regex(readerText, RegexTypes.PIZZA_SIZE)) {
                pizzaBuilder = pizzaBuilder.makeInfo().makeSize(Integer.parseInt(readerText)).makePrice();
                return;
            } else {
                logger.error("You have " + (i - 1) + " tries left ");
                if (i == 1) {
                    break;
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
                logger.error("Please type in another format " + e);
            }
            if (regex(readerText, RegexTypes.ADDONS_COUNT)) {
                data.setAddons(Integer.parseInt(readerText));
                return;
            } else {
                logger.error("You can choose from 0 to 9 addons. You have " + (i - 1) + " tries left ");
                if (i == 1) {
                    break;
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
                logger.error("Please type in another format " + e);
            }
            if (regex(readerText, RegexTypes.PIZZA_ADDONS)) {
                pizzaBuilder = pizzaBuilder.add(PizzasAddons.valueOf(readerText));
                return;
            } else {
                logger.error("You have " + (i - 1) + " tries left ");
                if (i == 1) {
                    break;
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
                logger.error("Please type in another format " + e);
            }
            if (regex(readerText, RegexTypes.COUNT)) {
                drinksCount = Integer.parseInt(readerText);
                return;
            } else {
                logger.error("You can choose from 0 to 99 drinks. You have " + (i - 1) + " tries left ");
                if (i == 1) {
                    break;
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
                logger.error("Please type in another format " + e);
            }
            if (regex(readerText, RegexTypes.DRINKS_NAME)) {
                drinksBuilder = drinksBuilder.makeName(DrinksNames.valueOf(readerText)).makePrice();
                return;
            } else {
                logger.error("You have " + (i - 1) + " tries left ");
                if (i == 1) {
                    break;
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
                logger.error("Please type in another format " + e);
            }
            if (regex(readerText, RegexTypes.DRINKS_SIZE)) {
                drinksBuilder = drinksBuilder.makeSize(DrinksSize.valueOf(readerText));
                return;
            } else {
                logger.error("You have " + (i - 1) + " tries left ");
                if (i == 1) {
                    break;
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
                logger.error("Please type in another format " + e);
            }
            if (regex(readerText, RegexTypes.DATE)) {
                data.setDate(LocalDate.parse(readerText));
                return;
            } else {
                logger.error("You have " + (i - 1) + " tries left ");
                if (i == 1) {
                    break;
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
                logger.error("Please type in another format " + e);
            }
            if (regex(readerText, RegexTypes.DISCOUNT)) {
                data.setDiscount(readerText);
                return;
            } else {
                logger.error("Please type Yes or No.  You have " + (i - 1) + " tries left ");
                if (i == 1) {
                    break;
                }
            }
        }
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
                    data.getPizzas().add(pizzaBuilder.build());
                }
            } else {
                logger.error("As we see you don't want pizza, your number of pizzas is: " + pizzaCount);
            }


        } catch (IllegalArgumentException e) {
            logger.error("Please type in another format " + e);
        } catch (NullPointerException e) {
            logger.error("Please type not null variable " + e);
        } catch (Exception e) {
            logger.error("Error with number of pizzas " + e);
            /*System.exit(0);*/
        }
    }

    private void makeDrinks() {
        try {
            checkDrinksCount();
            if (drinksCount > 0) {
                for (int j = 0; j < drinksCount; j++) {
                    checkDrinksNamesEnum();
                    checkDrinksSizeEnum();
                    data.getDrinks().add(drinksBuilder.build());
                }
            } else {
                logger.error("As we see you don't want drinks, your number of drinks is :" + drinksCount);
            }
        } catch (Exception e) {
            logger.error("Please type int number of drinks." + e);
            /*System.exit(0);*/
        }
    }

    @Override
    public Data readData() {
        makePizza();
        makeDrinks();
        checkDate();
        checkDiscount();
        return data;
    }
}
