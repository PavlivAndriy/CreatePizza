package pizza;

/**
 * Created by Andriy on 1/12/2016.
 */
public class Pizza {
    private PizzasNames pizzasNames;
    private int size;
    private String info;
    private int price;
    private String add = "";
    private int count;
    private PizzasAddons pizzasAddons;


    public void setName(PizzasNames pizzasNames) {
        this.pizzasNames = pizzasNames;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setAdd(PizzasAddons pizzasAddons) {
        this.pizzasAddons = pizzasAddons;
        this.add += " " + pizzasAddons;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return count + " " + this.getClass().getSimpleName() + " " + this.pizzasNames + " " + this.info + ". Addons : " + add + ". Size is : "
                + size + " sm. Price is: " + price + " hrn";
    }
}
