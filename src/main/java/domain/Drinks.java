package domain;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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
            switch (drinksNames) {
                case BEER:
                    price = 30;
                    break;
                case VINE:
                    price = 50;
                    break;
                case COCACOLA:
                    price = 20;
                    break;
                case FANTA:
                    price = 20;
                    break;
                case SPRITE:
                    price = 20;
                    break;
                case PEPSI:
                    price = 20;
                    break;
                case JUICE:
                    price = 25;
                    break;
                case COFFEE:
                    price = 21;
                    break;
                default:
                    System.err.println("There are not this kind of drink. Please try again, you can choose from following : Beer, Vine, Cocacola, Fanta" +
                            "Sprite, Pepsi, Coffee, Juice");
                    System.exit(0);
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
