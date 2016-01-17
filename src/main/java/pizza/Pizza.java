package pizza;

/**
 * Created by Andriy on 1/12/2016.
 */
public class Pizza {
    public String name;
    public int size;
    public String info;
    public int price;
    public String add;
    public int count ;


    public void setName(String name) {
        this.name = name;
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

    public void setAdd(String add) {
        this.add = add;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return count + " " + this.getClass().getSimpleName() + " " + this.name + " " + this.info + ". Addons : " + add + ". Size is : "
                + size + " sm. Price is: " + price + " hrn";
    }
}
