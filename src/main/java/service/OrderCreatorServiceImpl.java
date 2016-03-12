package service;

import domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Map;
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
    private DrinksNames drinksNames;
    private PizzasNames pizzasNames;

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

    private void checkDrinksPrice() {
        Map<String, String> drinksMaps;
        int price;
        drinksMaps = priceService.getPricesForDrinks();
        drinksNames = drinksBuilder.getDrinksNames();
        if (drinksMaps != null) {
            switch (drinksNames) {
                case BEER:
                    price = Integer.parseInt(drinksMaps.get("BEER"));
                    drinksBuilder.setPrice(price);
                    break;
                case VINE:
                    price = Integer.parseInt(drinksMaps.get("VINE"));
                    drinksBuilder.setPrice(price);
                    break;
                case COCACOLA:
                    price = Integer.parseInt(drinksMaps.get("COCACOLA"));
                    drinksBuilder.setPrice(price);
                    break;
                case FANTA:
                    price = Integer.parseInt(drinksMaps.get("FANTA"));
                    drinksBuilder.setPrice(price);
                    break;
                case SPRITE:
                    price = Integer.parseInt(drinksMaps.get("SPRITE"));
                    drinksBuilder.setPrice(price);
                    break;
                case PEPSI:
                    price = Integer.parseInt(drinksMaps.get("PEPSI"));
                    drinksBuilder.setPrice(price);
                    break;
                case JUICE:
                    price = Integer.parseInt(drinksMaps.get("JUICE"));
                    drinksBuilder.setPrice(price);
                    break;
                case COFFEE:
                    price = Integer.parseInt(drinksMaps.get("COFFEE"));
                    drinksBuilder.setPrice(price);
                    break;
                default:
                    logger.error(resourceBundle.getString("drinksTypes"));
                    break;
            }
        } else {
            logger.error(resourceBundle.getString("csvError"));
            switch (drinksNames) {
                case BEER:
                    price = 40;
                    drinksBuilder.setPrice(price);
                    break;
                case VINE:
                    price = 50;
                    drinksBuilder.setPrice(price);
                    break;
                case COCACOLA:
                    price = 20;
                    drinksBuilder.setPrice(price);
                    break;
                case FANTA:
                    price = 20;
                    drinksBuilder.setPrice(price);
                    break;
                case SPRITE:
                    price = 20;
                    drinksBuilder.setPrice(price);
                    break;
                case PEPSI:
                    price = 20;
                    drinksBuilder.setPrice(price);
                    break;
                case JUICE:
                    price = 25;
                    drinksBuilder.setPrice(price);
                    break;
                case COFFEE:
                    price = 21;
                    drinksBuilder.setPrice(price);
                    break;
                default:
                    logger.error(resourceBundle.getString("drinksTypes"));
                    break;
            }
        }
    }

    private void checkPizzaPrice() {
        Map<String, String> pizzaMaps;
        int price;
        switch (pizzaBuilder.getSize()) {
            case 30:
                pizzaMaps = priceService.getPricesForSmallPizza();
                pizzasNames = pizzaBuilder.getPizzasNames();
                if (pizzaMaps != null) {
                    switch (pizzasNames) {
                        case CAPRICCIOSA:
                            price = Integer.parseInt(pizzaMaps.get("CAPRICCIOSA"));
                            pizzaBuilder.setPrice(price);
                            break;
                        case SALAMI:
                            price = Integer.parseInt(pizzaMaps.get("SALAMI"));
                            pizzaBuilder.setPrice(price);
                            break;
                        case VEGETERIANA:
                            price = Integer.parseInt(pizzaMaps.get("VEGETERIANA"));
                            pizzaBuilder.setPrice(price);
                            break;
                        case MEXICANO:
                            price = Integer.parseInt(pizzaMaps.get("MEXICANO"));
                            pizzaBuilder.setPrice(price);
                            break;
                        case PAPPERONI:
                            price = Integer.parseInt(pizzaMaps.get("PAPPERONI"));
                            pizzaBuilder.setPrice(price);
                            break;
                        default:
                            logger.error(resourceBundle.getString("pizzaSize"));
                            break;
                    }
                } else {
                    logger.error(resourceBundle.getString("csvError"));
                    switch (pizzasNames) {
                        case CAPRICCIOSA:
                            price = 60;
                            pizzaBuilder.setPrice(price);
                            break;
                        case SALAMI:
                            price = 65;
                            pizzaBuilder.setPrice(price);
                            break;
                        case VEGETERIANA:
                            price = 70;
                            pizzaBuilder.setPrice(price);
                            break;
                        case MEXICANO:
                            price = 63;
                            pizzaBuilder.setPrice(price);
                            break;
                        case PAPPERONI:
                            price = 55;
                            pizzaBuilder.setPrice(price);
                            break;
                        default:
                            logger.error(resourceBundle.getString("pizzaSize"));
                            break;
                    }
                }
            case 50:
                pizzaMaps = priceService.getPricesForBigPizza();
                pizzasNames = pizzaBuilder.getPizzasNames();
                if (pizzaMaps != null) {
                    switch (pizzasNames) {
                        case CAPRICCIOSA:
                            price = Integer.parseInt(pizzaMaps.get("CAPRICCIOSA"));
                            pizzaBuilder.setPrice(price);
                            break;
                        case SALAMI:
                            price = Integer.parseInt(pizzaMaps.get("SALAMI"));
                            pizzaBuilder.setPrice(price);
                            break;
                        case VEGETERIANA:
                            price = Integer.parseInt(pizzaMaps.get("VEGETERIANA"));
                            pizzaBuilder.setPrice(price);
                            break;
                        case MEXICANO:
                            price = Integer.parseInt(pizzaMaps.get("MEXICANO"));
                            pizzaBuilder.setPrice(price);
                            break;
                        case PAPPERONI:
                            price = Integer.parseInt(pizzaMaps.get("PAPPERONI"));
                            pizzaBuilder.setPrice(price);
                            break;
                        default:
                            logger.error(resourceBundle.getString("pizzaSize"));
                            break;
                    }
                } else {
                    logger.error(resourceBundle.getString("csvError"));
                    switch (pizzasNames) {
                        case CAPRICCIOSA:
                            price = 80;
                            pizzaBuilder.setPrice(price);
                            break;
                        case SALAMI:
                            price = 85;
                            pizzaBuilder.setPrice(price);
                            break;
                        case VEGETERIANA:
                            price = 80;
                            pizzaBuilder.setPrice(price);
                            break;
                        case MEXICANO:
                            price = 83;
                            pizzaBuilder.setPrice(price);
                            break;
                        case PAPPERONI:
                            price = 85;
                            pizzaBuilder.setPrice(price);
                            break;
                        default:
                            logger.error(resourceBundle.getString("pizzaSize"));
                            break;
                    }
                }
            default:
                logger.error(resourceBundle.getString("pizzaSize"));
                break;
        }
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
                pizzaBuilder = pizzaBuilder.makeName(PizzasNames.valueOf(readerText));
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
                pizzaBuilder = pizzaBuilder.makeInfo().makeSize(Integer.parseInt(readerText))/*.makePrice()*/;
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
                pizzaBuilder = pizzaBuilder.add(PizzasAddons.valueOf(readerText));
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
                drinksBuilder = drinksBuilder.makeName(DrinksNames.valueOf(readerText))/*.makePrice()*/;
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
                drinksBuilder = drinksBuilder.makeSize(DrinksSize.valueOf(readerText));
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
                    checkPizzasNamesEnum();
                    checkPizzaSize();
                    checkPizzaPrice();
                    checkPizzaAddonsCount();
                    if (data.getAddons() > 0) {
                        for (int k = 0; k < data.getAddons(); k++) {
                            checkPizzaAddonsEnum();
                        }
                    } else {
                        System.out.println(resourceBundle.getString("pizzaWithoutAddons"));
                    }
                    data.getPizzas().add(pizzaBuilder.build());
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
                    /*priceService.makePrice(drinksBuilder);*/
                    checkDrinksPrice();
                    checkDrinksSizeEnum();
                    data.getDrinks().add(drinksBuilder.build());
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
