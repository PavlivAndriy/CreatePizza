package service;

import domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class OrderCreatorServiceImpl implements OrderCreatorService {
    private static final String REGEX_PIZZA_SIZE = "^[3,5]0$";
    private static final String REGEX_COUNT = "[0-9]?[0-9]?";
    private static final String REGEX_ADDONS_COUNT = "[0-9]";
    private static final String REGEX_DISCOUNT_YES = "([Y,y](es|ES|eS|Es))";
    private static final String REGEX_DISCOUNT_NO = "([N,n](o|O))";
    private static final Logger logger = LoggerFactory.getLogger(OrderCreatorServiceImpl.class);
    private static final int COUNT_TRIES = 3;
    private PriceServiceImpl priceService = new PriceServiceImpl();
    private Data data = new Data();
    private Pizza.PizzaBuilder pizzaBuilder = new Pizza.PizzaBuilder();
    private Drinks.DrinksBuilder drinksBuilder = new Drinks.DrinksBuilder();
    private String readerText;
    private int pizzaCount;
    private int drinksCount;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private Locale locale;
    private ResourceBundle resourceBundle;

    private void checkLanguage() {
        if (System.getProperties().getProperty("user.language").equals("uk")) {
            data.setLocale(new Locale("uk", "UKR"));
        }
        locale = data.getLocale();
        resourceBundle = ResourceBundle.getBundle("Bundle", locale);
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
                case DISCOUNT_YES:
                    pattern = Pattern.compile(REGEX_DISCOUNT_YES);
                    break;
                case DISCOUNT_NO:
                    pattern = Pattern.compile(REGEX_DISCOUNT_NO);
                    break;
                default:
                    logger.error(resourceBundle.getString("regexError"));
                    break;
            }
            Matcher matcher = pattern.matcher(s);
            return matcher.matches();
        } catch (IllegalArgumentException e) {
            logger.error(resourceBundle.getString("anotherFormat") + e);
        } catch (DateTimeParseException e) {
            logger.error(resourceBundle.getString("incorrectFormat") + e);
        } catch (NullPointerException e) {
            logger.error(resourceBundle.getString("nullPointer") + e);
        }
        return false;

    }

    private void checkPizzaCount() {
        System.out.println(resourceBundle.getString("pizzaCount"));
        for (int i = COUNT_TRIES; i > 0; i--) {
            try {
                readerText = reader.readLine();
            } catch (IOException e) {
                logger.error(resourceBundle.getString("anotherFormat"), e);
            }
            if (regex(readerText, RegexTypes.COUNT)) {
                pizzaCount = Integer.parseInt(readerText);
                break;
            } else {
                logger.error(resourceBundle.getString("pizzas99") + " " + resourceBundle.getString("triesLeft") + " " + (i - 1));
                System.out.println(resourceBundle.getString("pizzas99") + " " + resourceBundle.getString("triesLeft") + " " + (i - 1));
                if (i == 1) {
                    break;
                }
            }
        }
    }

    private void checkPizzasNamesEnum() {
        for (int i = COUNT_TRIES; i > 0; i--) {
            try {
                System.out.println(resourceBundle.getString("pizzaChoose"));
                System.out.println(resourceBundle.getString("pizzaVariables"));
                readerText = reader.readLine().toUpperCase();
            } catch (IOException e) {
                logger.error(resourceBundle.getString("anotherFormat") + e);
            }
            if (regex(readerText, RegexTypes.PIZZA_NAME)) {
                pizzaBuilder = pizzaBuilder.setPizzaName(PizzasNames.valueOf(readerText));
                break;
            } else {
                logger.error(resourceBundle.getString("triesLeft") + (i - 1));
                System.out.println(resourceBundle.getString("triesLeft") + (i - 1));
                if (i == 1) {
                    break;
                }
            }
        }
    }

    private void checkPizzaSize() {
        for (int i = COUNT_TRIES; i > 0; i--) {
            System.out.println(resourceBundle.getString("pizzaSize"));
            try {
                readerText = reader.readLine();
            } catch (IOException e) {
                logger.error(resourceBundle.getString("anotherFormat") + e);
            }
            if (regex(readerText, RegexTypes.PIZZA_SIZE)) {
                pizzaBuilder = pizzaBuilder.setPizzaDescription().setPizzaSize(Integer.parseInt(readerText));
                break;
            } else {
                logger.error(resourceBundle.getString("triesLeft") + (i - 1));
                System.out.println(resourceBundle.getString("triesLeft") + (i - 1));
                if (i == 1) {
                    break;
                }
            }
        }
    }

    private void checkPizzaAddonsCount() {
        for (int i = COUNT_TRIES; i > 0; i--) {
            System.out.println(resourceBundle.getString("pizzaAddonsCount"));
            try {
                readerText = reader.readLine();
            } catch (IOException e) {
                logger.error(resourceBundle.getString("anotherFormat") + e);
            }
            if (regex(readerText, RegexTypes.ADDONS_COUNT)) {
                data.setAddons(Integer.parseInt(readerText));
                break;
            } else {
                logger.error(resourceBundle.getString("addonsCount") + resourceBundle.getString("triesLeft") + (i - 1));
                System.out.println(resourceBundle.getString("addonsCount") + resourceBundle.getString("triesLeft") + (i - 1));
                if (i == 1) {
                    break;
                }
            }
        }
    }

    private void checkPizzaAddonsEnum() {
        for (int i = COUNT_TRIES; i > 0; i--) {
            try {
                System.out.println(resourceBundle.getString("pizzaAddonsTypes"));
                readerText = reader.readLine().toUpperCase();
            } catch (IOException e) {
                logger.error(resourceBundle.getString("anotherFormat") + e);
            }
            if (regex(readerText, RegexTypes.PIZZA_ADDONS)) {
                pizzaBuilder = pizzaBuilder.setPizzaAddons(PizzasAddons.valueOf(readerText));
                break;
            } else {
                logger.error(resourceBundle.getString("triesLeft") + (i - 1));
                System.out.println(resourceBundle.getString("triesLeft") + (i - 1));
                if (i == 1) {
                    break;
                }
            }
        }
    }

    private void checkDrinksCount() {
        for (int i = COUNT_TRIES; i > 0; i--) {
            System.out.println(resourceBundle.getString("drinksCount"));
            try {
                readerText = reader.readLine();
            } catch (IOException e) {
                logger.error(resourceBundle.getString("anotherFormat") + e);
            }
            if (regex(readerText, RegexTypes.COUNT)) {
                drinksCount = Integer.parseInt(readerText);
                break;
            } else {
                logger.error(resourceBundle.getString("drinksUpTo99") + resourceBundle.getString("triesLeft") + (i - 1));
                System.out.println(resourceBundle.getString("drinksUpTo99") + resourceBundle.getString("triesLeft") + (i - 1));
                if (i == 1) {
                    break;
                }
            }
        }
    }

    private void checkDrinksNamesEnum() {
        for (int i = COUNT_TRIES; i > 0; i--) {
            try {
                System.out.println(resourceBundle.getString("drinkName"));
                System.out.println(resourceBundle.getString("drinksTypes"));
                readerText = reader.readLine().toUpperCase();
            } catch (IOException e) {
                logger.error(resourceBundle.getString("anotherFormat") + e);
            }
            if (regex(readerText, RegexTypes.DRINKS_NAME)) {
                drinksBuilder = drinksBuilder.setDrinksNames(DrinksNames.valueOf(readerText));
                break;
            } else {
                logger.error(resourceBundle.getString("triesLeft") + (i - 1));
                System.out.println(resourceBundle.getString("triesLeft") + (i - 1));
                if (i == 1) {
                    break;
                }
            }
        }
    }

    private void checkDrinksSizeEnum() {
        for (int i = COUNT_TRIES; i > 0; i--) {
            try {
                System.out.println(resourceBundle.getString("drinksSize"));
                readerText = reader.readLine().toUpperCase();
            } catch (IOException e) {
                logger.error(resourceBundle.getString("anotherFormat") + e);
            }
            if (regex(readerText, RegexTypes.DRINKS_SIZE)) {
                drinksBuilder = drinksBuilder.setDrinksSize(DrinksSize.valueOf(readerText));
                break;
            } else {
                logger.error(resourceBundle.getString("triesLeft") + (i - 1));
                System.out.println(resourceBundle.getString("triesLeft") + (i - 1));
                if (i == 1) {
                    break;
                }
            }
        }
    }

    private void checkDate() {
        for (int i = COUNT_TRIES; i > 0; i--) {
            try {
                System.out.println(resourceBundle.getString("dateType"));
                readerText = reader.readLine();
            } catch (IOException e) {
                logger.error(resourceBundle.getString("anotherFormat") + e);
            }
            if (regex(readerText, RegexTypes.DATE)) {
                data.setDate(LocalDate.parse(readerText));
                break;
            } else {
                logger.error(resourceBundle.getString("triesLeft") + (i - 1));
                System.out.println(resourceBundle.getString("triesLeft") + (i - 1));
                if (i == 1) {
                    break;
                }
            }
        }
    }

    private void checkDiscount() {
        for (int i = COUNT_TRIES; i > 0; i--) {
            try {
                System.out.println(resourceBundle.getString("isDiscount"));
                readerText = reader.readLine();
            } catch (IOException e) {
                logger.error(resourceBundle.getString("anotherFormat") + e);
            }
            if (regex(readerText, RegexTypes.DISCOUNT_YES)) {
                data.setDiscount(true);
                break;
            } else if (regex(readerText, RegexTypes.DISCOUNT_NO)) {
                data.setDiscount(false);
                break;
            } else {
                logger.error(resourceBundle.getString("yesOrNo") + resourceBundle.getString("triesLeft") + (i - 1));
                System.out.println(resourceBundle.getString("yesOrNo") + resourceBundle.getString("triesLeft") + (i - 1));
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
                    pizzaBuilder.setPizzaAddons(new ArrayList());
                    checkPizzasNamesEnum();
                    checkPizzaSize();
                    checkPizzaAddonsCount();
                    if (data.getAddons() > 0) {
                        for (int k = 0; k < data.getAddons(); k++) {
                            checkPizzaAddonsEnum();
                        }
                    } else {
                        System.out.println(resourceBundle.getString("pizzaWithoutAddons"));
                    }
                    data.getPizzas().add(pizzaBuilder.build());
                    priceService.getPricesForPizza(data.getPizzas().get(j));
                }
            } else {
                logger.error(resourceBundle.getString("noNeedPizza") + pizzaCount);
                System.out.println(resourceBundle.getString("noNeedPizza") + pizzaCount);
            }


        } catch (IllegalArgumentException e) {
            logger.error(resourceBundle.getString("anotherFormat") + e);
        } catch (NullPointerException e) {
            logger.error(resourceBundle.getString("anotherFormat") + e);
        } catch (Exception e) {
            logger.error(resourceBundle.getString("pizzaError") + e);
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
                    priceService.getPricesForDrinks(data.getDrinks().get(j));
                }
            } else {
                logger.error(resourceBundle.getString("noNeedDrinks") + drinksCount);
                System.out.println(resourceBundle.getString("noNeedDrinks") + drinksCount);
            }
        } catch (Exception e) {
            logger.error(resourceBundle.getString("drinksError") + e);
        }
    }

    public Data readData() {
        checkLanguage();
        makePizza();
        makeDrinks();
        checkDate();
        checkDiscount();
        return data;
    }
}
