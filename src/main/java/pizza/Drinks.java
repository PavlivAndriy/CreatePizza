package pizza;



/**
 * Created by Andriy on 1/14/2016.
 */
public class Drinks {
    public String name;
    public double size;
    public int price;
    public int count;


    public void setName(String name) {
        this.name = name;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return count + " " + this.getClass().getSimpleName() + ": " + this.name + " " + this.size + "L Price: " + this.price + " hrn";
    }
}
