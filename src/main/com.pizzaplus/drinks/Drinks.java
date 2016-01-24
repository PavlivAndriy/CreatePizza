package drinks;


/**
 * Created by Andriy on 1/14/2016.
 */
public class Drinks {
    public DrinksNames drinksNames;
    public DrinksSize drinksSize;
    public int price;
    public int count;


    public void setName(DrinksNames drinksNames) {
        this.drinksNames = drinksNames;
    }

    public void setSize(DrinksSize drinksSize) {
        this.drinksSize = drinksSize;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return count + " " + this.getClass().getSimpleName() + ": " + this.drinksNames + " " + this.drinksSize.value() + "L Price: " + this.price + " hrn";
    }
}
