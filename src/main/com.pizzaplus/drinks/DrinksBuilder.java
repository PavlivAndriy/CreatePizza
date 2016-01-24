package drinks;

/**
 * Created by Andriy on 1/14/2016.
 */
public class DrinksBuilder {
    public static int count = 0;
    public String name;
    public double size;
    public int price = 0;
    public DrinksNames drinksNames;
    public DrinksSize drinksSize;

    public DrinksBuilder makeName(DrinksNames drinksNames) {
        this.drinksNames = drinksNames;
        count++;
        return this;
    }

    public DrinksBuilder makeSize(DrinksSize drinksSize) {
        this.drinksSize = drinksSize;
        switch (drinksSize){
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


    public DrinksBuilder makePrice() {
        switch (drinksNames) {
            case Beer:
                price = 30;
                break;
            case Vine:
                price = 50;
                break;
            case Cocacola:
                price = 20;
                break;
            case Fanta:
                price = 20;
                break;
            case Sprite:
                price = 20;
                break;
            case Pepsi:
                price = 20;
                break;
            case Juice:
                price = 25;
                break;
            case Coffee:
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
