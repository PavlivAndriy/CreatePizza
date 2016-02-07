package domain;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andriy on 1/14/2016.
 */
public class Drinks {
    private DrinksNames drinksNames;
    private DrinksSize drinksSize;
    private int price;
    private int count;


    public void setName(DrinksNames drinksNames) {
        this.drinksNames = drinksNames;
    }

    public void setSize(DrinksSize drinksSize) {
        this.drinksSize = drinksSize;
    }

    public void setCount(int count) {
        this.count = count;
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

    public static class DrinksBuilder {
        private int count;
        private String name;
        private double size;
        private int price;
        private DrinksNames drinksNames;
        private DrinksSize drinksSize;
        private Map<String, String> drinksMaps = new HashMap<String, String>();

        public int getCount() {
            return count;
        }

        public int getPrice() {
            return price;
        }

        public DrinksBuilder makeName(DrinksNames drinksNames) {
            this.drinksNames = drinksNames;
            count++;
            return this;
        }

        public DrinksBuilder makeSize(DrinksSize drinksSize) {
            this.drinksSize = drinksSize;
            switch (drinksSize) {
                case LOW:
                    price *= drinksSize.value();
                    break;
                case MID1:
                    price *= drinksSize.value();
                    break;
                case MID2:
                    price *= drinksSize.value();
                    break;
                case BIG:
                    price *= drinksSize.value();
                    break;
                default:
                    System.err.println("Please try again. You can choose from the following sizes: LOW - 0.5L, MID1 - 1L, MID2 - 1.5L, BIG - 2L");
                    System.exit(0);
            }
            return this;
        }

        public DrinksSize getDrinksSize() {
            return drinksSize;
        }

        public DrinksBuilder makePrice() {
            String csvFile = "C:/Users/Andriy/IdeaProjects/CreatePizza/src/main/java/domain/DrinksPrices.csv";
            String line = "";
            String csvSplitBy = ",";
            BufferedReader priceReader = null;
            try {
                priceReader = new BufferedReader(new FileReader(csvFile));
                while ((line = priceReader.readLine()) != null) {
                    String[] price = line.split(csvSplitBy);
                    drinksMaps.put(price[0], price[1]);
                }
                switch (drinksNames) {
                    case BEER:
                        price = Integer.parseInt(drinksMaps.get("BEER"));
                        break;
                    case VINE:
                        price = Integer.parseInt(drinksMaps.get("VINE"));
                        break;
                    case COCACOLA:
                        price = Integer.parseInt(drinksMaps.get("COCACOLA"));
                        break;
                    case FANTA:
                        price = Integer.parseInt(drinksMaps.get("FANTA"));
                        break;
                    case SPRITE:
                        price = Integer.parseInt(drinksMaps.get("SPRITE"));
                        break;
                    case PEPSI:
                        price = Integer.parseInt(drinksMaps.get("PEPSI"));
                        break;
                    case JUICE:
                        price = Integer.parseInt(drinksMaps.get("JUICE"));
                        break;
                    case COFFEE:
                        price = Integer.parseInt(drinksMaps.get("COFFEE"));
                        break;
                    default:
                        System.err.println("There are not this kind of drink. Please try again, " +
                                "you can choose from following : Beer, Vine, Cocacola, Fanta" +
                                "Sprite, Pepsi, Coffee, Juice");
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
            return this;
        }

        public Drinks build() {
            Drinks drinks = new Drinks();
            drinks.setName(drinksNames);
            drinks.setPrice(price);
            drinks.setSize(drinksSize);
            drinks.setCount(count);
            return drinks;
        }

    }

}
