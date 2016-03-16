package service;

import domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PriceServiceImpl implements PriceService {
    private static final Logger logger = LoggerFactory.getLogger(PriceServiceImpl.class);
    private static final String CSV_FILE = "./DrinksPrices.csv";
    private static final String CSV_FILE_30 = "./Pizza30Prices.csv";
    private static final String CSV_FILE_50 = "./Pizza50Prices.csv";
    private static final String CSV_SPLIT_BY = ",";
    private Data data = new Data();
    private Locale locale = data.getLocale();
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("Bundle", locale);
    private String line = "";
    private Map<String, String> drinksMaps = new HashMap();
    private Map<String, String> pizzaMaps = new HashMap();

    public void getPricesForPizza(Pizza pizza) {
        setPriceForPizza(pizza);
        setPizzaAddons(pizza);
    }

    public void getPricesForDrinks(Drinks drinks) {
        setPriceForDrinks(drinks);
        setSizeForDrinks(drinks);
    }

    private void setPriceForDrinks(Drinks drinks) {
        BufferedReader priceReader = null;
        int priceForDrinks;
        try {
            priceReader = new BufferedReader(new FileReader(CSV_FILE));
            while ((line = priceReader.readLine()) != null) {
                String[] price = line.split(CSV_SPLIT_BY);
                drinksMaps.put(price[0], price[1]);
            }
            switch (drinks.getDrinksNames()) {
                case BEER:
                    priceForDrinks = Integer.parseInt(drinksMaps.get("BEER"));
                    drinks.setPrice(priceForDrinks);
                    break;
                case VINE:
                    priceForDrinks = Integer.parseInt(drinksMaps.get("VINE"));
                    drinks.setPrice(priceForDrinks);
                    break;
                case COCACOLA:
                    priceForDrinks = Integer.parseInt(drinksMaps.get("COCACOLA"));
                    drinks.setPrice(priceForDrinks);
                    break;
                case FANTA:
                    priceForDrinks = Integer.parseInt(drinksMaps.get("FANTA"));
                    drinks.setPrice(priceForDrinks);
                    break;
                case SPRITE:
                    priceForDrinks = Integer.parseInt(drinksMaps.get("SPRITE"));
                    drinks.setPrice(priceForDrinks);
                    break;
                case PEPSI:
                    priceForDrinks = Integer.parseInt(drinksMaps.get("PEPSI"));
                    drinks.setPrice(priceForDrinks);
                    break;
                case JUICE:
                    priceForDrinks = Integer.parseInt(drinksMaps.get("JUICE"));
                    drinks.setPrice(priceForDrinks);
                    break;
                case COFFEE:
                    priceForDrinks = Integer.parseInt(drinksMaps.get("COFFEE"));
                    drinks.setPrice(priceForDrinks);
                    break;
                default:
                    logger.error(resourceBundle.getString("drinksTypes"));
                    break;
            }

        } catch (FileNotFoundException e) {
            logger.error(resourceBundle.getString("csvError") + e);
            switch (drinks.getDrinksNames()) {
                case BEER:
                    priceForDrinks = 40;
                    drinks.setPrice(priceForDrinks);
                    break;
                case VINE:
                    priceForDrinks = 50;
                    drinks.setPrice(priceForDrinks);
                    break;
                case COCACOLA:
                    priceForDrinks = 20;
                    drinks.setPrice(priceForDrinks);
                    break;
                case FANTA:
                    priceForDrinks = 20;
                    drinks.setPrice(priceForDrinks);
                    break;
                case SPRITE:
                    priceForDrinks = 20;
                    drinks.setPrice(priceForDrinks);
                    break;
                case PEPSI:
                    priceForDrinks = 20;
                    drinks.setPrice(priceForDrinks);
                    break;
                case JUICE:
                    priceForDrinks = 25;
                    drinks.setPrice(priceForDrinks);
                    break;
                case COFFEE:
                    priceForDrinks = 21;
                    drinks.setPrice(priceForDrinks);
                    break;
                default:
                    logger.error(resourceBundle.getString("drinksTypes"));
                    break;
            }
        } catch (IOException e) {
            logger.error(resourceBundle.getString("correctName") + e);
        } finally {
            if (priceReader != null) {
                try {
                    priceReader.close();
                } catch (IOException e) {
                    logger.error(resourceBundle.getString("correctName") + e);
                }
            }
        }
    }

    private void setSizeForDrinks(Drinks drinks) {
        int price = drinks.getPrice();
        DrinksSize drinksSize = drinks.getDrinksSize();
        switch (drinksSize) {
            case LOW:
                price *= drinksSize.value();
                drinks.setPrice(price);
                break;
            case MID1:
                price *= drinksSize.value();
                drinks.setPrice(price);
                break;
            case MID2:
                price *= drinksSize.value();
                drinks.setPrice(price);
                break;
            case BIG:
                price *= drinksSize.value();
                drinks.setPrice(price);
                break;
            default:
                logger.error(resourceBundle.getString("drinksSize"));
                break;
        }
    }

    private void setPriceForPizza(Pizza pizza) {
        BufferedReader priceReader = null;
        int pizzaPrice;
        PizzasNames pizzasNames = pizza.getPizzaNames();
        if (pizza.getCount() % 3 == 0) {
            pizza.setPrice(0);
        } else {
            switch (pizza.getSize()) {
                case 30:
                    try {
                        priceReader = new BufferedReader(new FileReader(CSV_FILE_30));
                        while ((line = priceReader.readLine()) != null) {
                            String[] price = line.split(CSV_SPLIT_BY);
                            pizzaMaps.put(price[0], price[1]);
                        }
                        switch (pizzasNames) {
                            case CAPRICCIOSA:
                                pizzaPrice = Integer.parseInt(pizzaMaps.get("CAPRICCIOSA"));
                                pizza.setPrice(pizzaPrice);
                                break;
                            case SALAMI:
                                pizzaPrice = Integer.parseInt(pizzaMaps.get("SALAMI"));
                                pizza.setPrice(pizzaPrice);
                                break;
                            case VEGETERIANA:
                                pizzaPrice = Integer.parseInt(pizzaMaps.get("VEGETERIANA"));
                                pizza.setPrice(pizzaPrice);
                                break;
                            case MEXICANO:
                                pizzaPrice = Integer.parseInt(pizzaMaps.get("MEXICANO"));
                                pizza.setPrice(pizzaPrice);
                                break;
                            case PAPPERONI:
                                pizzaPrice = Integer.parseInt(pizzaMaps.get("PAPPERONI"));
                                pizza.setPrice(pizzaPrice);
                                break;
                            default:
                                logger.error(resourceBundle.getString("pizzaSize"));
                                break;
                        }
                    } catch (FileNotFoundException e) {
                        logger.error(resourceBundle.getString("csvError"));
                        switch (pizzasNames) {
                            case CAPRICCIOSA:
                                pizzaPrice = 60;
                                pizza.setPrice(pizzaPrice);
                                break;
                            case SALAMI:
                                pizzaPrice = 65;
                                pizza.setPrice(pizzaPrice);
                                break;
                            case VEGETERIANA:
                                pizzaPrice = 70;
                                pizza.setPrice(pizzaPrice);
                                break;
                            case MEXICANO:
                                pizzaPrice = 63;
                                pizza.setPrice(pizzaPrice);
                                break;
                            case PAPPERONI:
                                pizzaPrice = 55;
                                pizza.setPrice(pizzaPrice);
                                break;
                            default:
                                logger.error(resourceBundle.getString("pizzaSize"));
                                break;
                        }
                    } catch (IOException e) {
                        logger.error(resourceBundle.getString("correctName") + e);
                    } finally {
                        if (priceReader != null) {
                            try {
                                priceReader.close();
                                break;
                            } catch (IOException e) {
                                logger.error(resourceBundle.getString("correctName") + e);
                            }
                        }
                    }
                    break;
                case 50:
                    try {
                        priceReader = new BufferedReader(new FileReader(CSV_FILE_50));
                        while ((line = priceReader.readLine()) != null) {
                            String[] price = line.split(CSV_SPLIT_BY);
                            pizzaMaps.put(price[0], price[1]);
                        }
                        switch (pizzasNames) {
                            case CAPRICCIOSA:
                                pizzaPrice = Integer.parseInt(pizzaMaps.get("CAPRICCIOSA"));
                                pizza.setPrice(pizzaPrice);
                                break;
                            case SALAMI:
                                pizzaPrice = Integer.parseInt(pizzaMaps.get("SALAMI"));
                                pizza.setPrice(pizzaPrice);
                                break;
                            case VEGETERIANA:
                                pizzaPrice = Integer.parseInt(pizzaMaps.get("VEGETERIANA"));
                                pizza.setPrice(pizzaPrice);
                                break;
                            case MEXICANO:
                                pizzaPrice = Integer.parseInt(pizzaMaps.get("MEXICANO"));
                                pizza.setPrice(pizzaPrice);
                                break;
                            case PAPPERONI:
                                pizzaPrice = Integer.parseInt(pizzaMaps.get("PAPPERONI"));
                                pizza.setPrice(pizzaPrice);
                                break;
                            default:
                                logger.error(resourceBundle.getString("pizzaSize"));
                                break;
                        }
                    } catch (FileNotFoundException e) {
                        logger.error(resourceBundle.getString("csvError"));
                        switch (pizzasNames) {
                            case CAPRICCIOSA:
                                pizzaPrice = 80;
                                pizza.setPrice(pizzaPrice);
                                break;
                            case SALAMI:
                                pizzaPrice = 85;
                                pizza.setPrice(pizzaPrice);
                                break;
                            case VEGETERIANA:
                                pizzaPrice = 80;
                                pizza.setPrice(pizzaPrice);
                                break;
                            case MEXICANO:
                                pizzaPrice = 83;
                                pizza.setPrice(pizzaPrice);
                                break;
                            case PAPPERONI:
                                pizzaPrice = 85;
                                pizza.setPrice(pizzaPrice);
                                break;
                            default:
                                logger.error(resourceBundle.getString("pizzaSize"));
                                break;
                        }
                    } catch (IOException e) {
                        logger.error(resourceBundle.getString("correctName") + e);
                    } finally {
                        if (priceReader != null) {
                            try {
                                priceReader.close();
                            } catch (IOException e) {
                                logger.error(resourceBundle.getString("correctName") + e);
                            }
                        }
                    }
                    break;
                default:
                    logger.error(resourceBundle.getString("pizzaSize"));
                    break;
            }
        }
    }

    private void setPizzaAddons(Pizza pizza) {
        int priceForAddons = pizza.getPrice();
        List<PizzasAddons> pizzasAddons = pizza.getPizzaAddons();
        if (pizza.getCount() % 3 == 0) {
            pizza.setPrice(0);
        } else {
            for (int i = 0; i < pizzasAddons.size(); i++) {
                switch (pizzasAddons.get(i)) {
                    case CHEESE:
                        priceForAddons += 10;
                        pizza.setPrice(priceForAddons);
                        break;
                    case SAUSAGE:
                        priceForAddons += 15;
                        pizza.setPrice(priceForAddons);
                        break;
                    case SPICE:
                        priceForAddons += 5;
                        pizza.setPrice(priceForAddons);
                        break;
                    case FRUITS:
                        priceForAddons += 15;
                        pizza.setPrice(priceForAddons);
                        break;
                    case TOMATO:
                        priceForAddons += 7;
                        pizza.setPrice(priceForAddons);
                        break;
                    default:
                        logger.error(resourceBundle.getString("pizzaAddonsTypes"));
                        break;
                }
            }
        }
    }

}
