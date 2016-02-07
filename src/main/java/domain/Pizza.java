package domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public static class PizzaBuilder {
        private Map<String, String> pizzaMaps = new HashMap<String, String>();
        private int count;
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
                    System.err.println("This is incorrect pizza's name, please check it again. Available pizzas are: Capricciosa, Salami," +
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
                    String csvFile = "C:/Users/Andriy/IdeaProjects/CreatePizza/src/main/java/domain/Pizza30Prices.csv";
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
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (priceReader != null) {
                            try {
                                priceReader.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    break;
                case 50:
                    csvFile = "C:/Users/Andriy/IdeaProjects/CreatePizza/src/main/java/domain/Pizza50Prices.csv";
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
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (priceReader != null) {
                            try {
                                priceReader.close();
                            } catch (IOException e) {
                                e.printStackTrace();
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
