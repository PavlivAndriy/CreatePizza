package domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class Pizza {
    private static Logger logger = LoggerFactory.getLogger(Pizza.class);
    private PizzasNames pizzasNames;
    private int size;
    private String info;
    private int price;
    private String addonsInformation = "";
    private int count;
    private PizzasAddons pizzasAddons;

    public void setCount(int count) {
        this.count = count;
    }

    public void setName(PizzasNames pizzasNames) {
        this.pizzasNames = pizzasNames;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setAddonsInformation(PizzasAddons pizzasAddons) {
        this.pizzasAddons = pizzasAddons;
        this.addonsInformation += " " + pizzasAddons;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return count + " " + this.getClass().getSimpleName() + " " + this.pizzasNames + " " + this.info +
                ". Addons : " + this.addonsInformation + ". Size is : " + size + " sm. Price is: " + price + " hrn";
    }

    public static class PizzaBuilder {
        private static final String CSV_FILE_30 = "./src/main/resources/Pizza30Prices.csv";
        private static final String CSV_FILE_50 = "./src/main/resources/Pizza50Prices.csv";
        private static final String CSV_SPLIT_BY = ",";
        private String line = "";
        private static int count;
        private Map<String, String> pizzaMaps = new HashMap<String, String>();
        private int size = 30;
        private String info = ": Cheese + Salami + Papper ";
        private int price;
        private String addons = " ";
        private PizzasAddons pizzasAddons;
        private PizzasNames pizzasNames;

        public PizzaBuilder makeName(PizzasNames pizzasNames) {
            this.pizzasNames = pizzasNames;
            count++;
            return this;
        }

        public PizzaBuilder makeInfo() {
            switch (pizzasNames) {
                case CAPRICCIOSA:
                    info = info + "+ Ham + Mushrooms";
                    break;
                case SALAMI:
                    info = info + "+ Sausage";
                    break;
                case VEGETERIANA:
                    info = ": Cheese + Pepper + Corn + Tomato";
                    break;
                case MEXICANO:
                    info = info + "+ Chili Pepper + Tomato + Onion";
                    break;
                case PAPPERONI:
                    info = info + "+ Red Hot Chili Pepper + Onion + Bacon";
                    break;
                default:
                    logger.error("This is incorrect pizza's name, please check it again." +
                            " Available pizzas are: Capricciosa, Salami," +
                            "Vegeteriana, Mexicano, Papperoni");
                    break;
            }
            return this;
        }

        public PizzaBuilder makeSize(int size) {
            this.size = size;
            return this;
        }

        public PizzaBuilder add(PizzasAddons pizzasAddons) {
            this.addons += " " + pizzasAddons;
            this.pizzasAddons = pizzasAddons;
            switch (pizzasAddons) {
                case CHEESE:
                    price += 10;
                    break;
                case SAUSAGE:
                    price += 15;
                    break;
                case SPICE:
                    price += 5;
                    break;
                case FRUITS:
                    price += 15;
                    break;
                case TOMATO:
                    price += 7;
                    break;
                default:
                    logger.error("Please enter correct addons name");
                    break;
            }
            return this;
        }

        public PizzaBuilder makePrice() {
            switch (size) {
                case 30:
                    BufferedReader priceReader = null;
                    try {
                        priceReader = new BufferedReader(new FileReader(CSV_FILE_30));
                        while ((line = priceReader.readLine()) != null) {
                            String[] price = line.split(CSV_SPLIT_BY);
                            pizzaMaps.put(price[0], price[1]);
                        }
                        switch (pizzasNames) {
                            case CAPRICCIOSA:
                                price = Integer.parseInt(pizzaMaps.get("CAPRICCIOSA"));
                                break;
                            case SALAMI:
                                price = Integer.parseInt(pizzaMaps.get("SALAMI"));
                                break;
                            case VEGETERIANA:
                                price = Integer.parseInt(pizzaMaps.get("VEGETERIANA"));
                                break;
                            case MEXICANO:
                                price = Integer.parseInt(pizzaMaps.get("MEXICANO"));
                                break;
                            case PAPPERONI:
                                price = Integer.parseInt(pizzaMaps.get("PAPPERONI"));
                                break;
                            default:
                                logger.error("This is incorrect pizza's size, " +
                                        "please check it again. Available sizes are: 30 , 50");
                                break;
                        }
                    } catch (FileNotFoundException e) {
                        logger.error("ERROR,couldn't take prices from csv file + e");
                        switch (pizzasNames) {
                            case CAPRICCIOSA:
                                price = 60;
                                break;
                            case SALAMI:
                                price = 65;
                                break;
                            case VEGETERIANA:
                                price = 70;
                                break;
                            case MEXICANO:
                                price = 63;
                                break;
                            case PAPPERONI:
                                price = 55;
                                break;
                            default:
                                logger.error("This is incorrect pizza's size, " +
                                        "please check it again. Available sizes are: 30 , 50");
                                break;
                        }
                    } catch (IOException e) {
                        logger.error("Please type in another format " + e);
                    } finally {
                        if (priceReader != null) {
                            try {
                                priceReader.close();
                            } catch (IOException e) {
                                logger.error("Please type in another format " + e);
                            }
                        }
                    }
                    break;
                case 50:
                    priceReader = null;
                    try {
                        priceReader = new BufferedReader(new FileReader(CSV_FILE_50));
                        while ((line = priceReader.readLine()) != null) {
                            String[] price = line.split(CSV_SPLIT_BY);
                            pizzaMaps.put(price[0], price[1]);
                        }
                        switch (pizzasNames) {
                            case CAPRICCIOSA:
                                price = Integer.parseInt(pizzaMaps.get("CAPRICCIOSA"));
                                break;
                            case SALAMI:
                                price = Integer.parseInt(pizzaMaps.get("SALAMI"));
                                break;
                            case VEGETERIANA:
                                price = Integer.parseInt(pizzaMaps.get("VEGETERIANA"));
                                break;
                            case MEXICANO:
                                price = Integer.parseInt(pizzaMaps.get("MEXICANO"));
                                break;
                            case PAPPERONI:
                                price = Integer.parseInt(pizzaMaps.get("PAPPERONI"));
                                break;
                            default:
                                logger.error("This is incorrect pizza's size, " +
                                        "please check it again. Available sizes are: 30 , 50");
                                break;
                        }
                        break;
                    } catch (FileNotFoundException e) {
                        logger.error("ERROR,couldn't take prices from csv file" + e);
                        switch (pizzasNames) {
                            case CAPRICCIOSA:
                                price = 80;
                                break;
                            case SALAMI:
                                price = 85;
                                break;
                            case VEGETERIANA:
                                price = 80;
                                break;
                            case MEXICANO:
                                price = 83;
                                break;
                            case PAPPERONI:
                                price = 85;
                                break;
                            default:
                                logger.error("This is incorrect pizza's size, " +
                                        "please check it again. Available sizes are: 30 , 50");
                               break;
                        }
                    } catch (IOException e) {
                        logger.error("Please type in another format " + e);
                    } finally {
                        if (priceReader != null) {
                            try {
                                priceReader.close();
                            } catch (IOException e) {
                                logger.error("Please type in another format " + e);
                            }
                        }
                    }
                default:
                    logger.error("This is incorrect pizza's size, " +
                            "please check it again. Available sizes are: 30 , 50");
                    break;
            }
            return this;
        }

        public Pizza build() {
            Pizza pizza = new Pizza();
            pizza.setName(pizzasNames);
            pizza.setInfo(info);
            pizza.setSize(size);
            pizza.setAddonsInformation(pizzasAddons);
            pizza.setCount(count);
            if (count % 3 == 0) {
                pizza.setPrice(0);
            } else {
                pizza.setPrice(price);
            }
            return pizza;
        }
    }

}
