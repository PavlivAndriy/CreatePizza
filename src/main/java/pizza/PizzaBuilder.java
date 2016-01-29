package pizza;

/**
 * Created by Andriy on 1/12/2016.
 */
public class PizzaBuilder {
    private static int count;
    private String name = "pizza";
    private int size = 30;
    private String info = ": Cheese + Salami + Papper ";
    private int price = 50;
    private String addons = " ";
    private PizzasAddons pizzasAddons;
    private PizzasNames pizzasNames;

    public static int getCount() {
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

    public PizzaBuilder makePrice() {
        switch (size) {
            case 30:
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
                        System.err.println("This is incorrect pizza's size, please check it again. Available sizes are: 30 , 50");
                        System.exit(0);
                }
                break;
            case 50:
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
                        System.err.println("This is incorrect pizza's size, please check it again. Available sizes are: 30 , 50");
                        System.exit(0);
                }
                break;
            default:
                System.err.println("This is incorrect pizza's size, please check it again. Available sizes are: 30 , 50");
                System.exit(0);
        }


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


    public Pizza build() {
        Pizza pizza = new Pizza();
        pizza.setName(pizzasNames);
        pizza.setInfo(info);
        pizza.setSize(size);
        pizza.setAdd(pizzasAddons);
        pizza.setCount(count);
        if (count % 3 == 0) {
            pizza.setPrice(0);
        } else {
            pizza.setPrice(price);
        }
        return pizza;
    }
}
