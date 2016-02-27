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
    private PizzasNames pizzasNames;
    private int size;
    private String info;
    private int price;
    private String addonsInformation = "";
    private int count;
    private PizzasAddons pizzasAddons;
    private static Logger logger = LoggerFactory.getLogger(Pizza.class);

    public static int getCount() {
        return Pizza.getCount();
    }

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
                ". Addons : " + pizzasAddons + ". Size is : " + size + " sm. Price is: " + price + " hrn";
    }

    public static class PizzaBuilder {
        private static int count;
        private Map<String, String> pizzaMaps = new HashMap<String, String>();
        private String name = "pizza";
        private int size = 30;
        private String info = ": Cheese + Salami + Papper ";
        private int price;
        private String addons = " ";
        private PizzasAddons pizzasAddons;
        private PizzasNames pizzasNames;

        public int getCount() {
            return count;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getPrice() {
            return price;
        }

        public String getInfo() {
            return info;
        }

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
                    System.err.println("This is incorrect pizza's name, please check it again." +
                            " Available pizzas are: Capricciosa, Salami," +
                            "Vegeteriana, Mexicano, Papperoni");
                    System.exit(0);
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
                    System.err.println("Please enter correct addons name");
                    System.exit(0);
            }
            return this;
        }

        public PizzaBuilder makePrice() {
            switch (size) {
                case 30:
                    String csvFile = "./src/main/resources/Pizza30Prices.csv";
                    String line = "";
                    String csvSplitBy = ",";
                    BufferedReader priceReader = null;
                    try {
                        priceReader = new BufferedReader(new FileReader(csvFile));
                        while ((line = priceReader.readLine()) != null) {
                            String[] price = line.split(csvSplitBy);
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
                                System.err.println("This is incorrect pizza's size, " +
                                        "please check it again. Available sizes are: 30 , 50");
                                System.exit(0);
                        }
                    } catch (FileNotFoundException e) {
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
                                System.err.println("This is incorrect pizza's size, " +
                                        "please check it again. Available sizes are: 30 , 50");
                                System.exit(0);
                        }

                    } catch (IOException e) {
                        logger.error("Please type in another format " + e.toString());
                    } finally {
                        if (priceReader != null) {
                            try {
                                priceReader.close();
                            } catch (IOException e) {
                                logger.error("Please type in another format " + e.toString());
                            }
                        }
                    }
                    break;
                case 50:
                    csvFile = "./src/main/resources/Pizza50Prices.csv";
                    csvSplitBy = ",";
                    priceReader = null;
                    try {
                        priceReader = new BufferedReader(new FileReader(csvFile));
                        while ((line = priceReader.readLine()) != null) {
                            String[] price = line.split(csvSplitBy);
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
                                System.err.println("This is incorrect pizza's size, " +
                                        "please check it again. Available sizes are: 30 , 50");
                                System.exit(0);
                        }
                        break;
                    } catch (FileNotFoundException e) {
                        System.err.println("ERROR");
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
                                System.err.println("This is incorrect pizza's size, " +
                                        "please check it again. Available sizes are: 30 , 50");
                                System.exit(0);
                        }
                    } catch (IOException e) {
                        logger.error("Please type in another format " + e.toString());
                    } finally {
                        if (priceReader != null) {
                            try {
                                priceReader.close();
                            } catch (IOException e) {
                                logger.error("Please type in another format " + e.toString());
                            }
                        }
                    }
                default:
                    System.err.println("This is incorrect pizza's size, " +
                            "please check it again. Available sizes are: 30 , 50");
                    System.exit(0);
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
