package pizza;

/**
 * Created by Andriy on 1/12/2016.
 */
public class PizzaBuilder {
    public static int count;
    public String name = "pizza";
    public int size = 30;
    public String info = ": Cheese + Salami + Papper ";
    public int price = 50;
    public String addons = " ";
    public PizzasAddons pizzasAddons;
    public PizzasNames pizzasNames;

    public PizzaBuilder makeName(PizzasNames pizzasNames) {
        this.pizzasNames = pizzasNames;
        count++;


        return this;

    }

    public PizzaBuilder makeInfo() {
        switch (pizzasNames) {
            case Capricciosa:
                info = info + "+ Ham + Mushrooms";
                break;
            case Salami:
                info = info + "+ Sausage";
                break;
            case Vegeteriana:
                info = ": Cheese + Pepper + Corn + Tomato";
                break;
            case Mexicano:
                info = info + "+ Chili Pepper + Tomato + Onion";
                break;
            case Papperoni:
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
                    case Capricciosa:
                        price = 60;
                        break;
                    case Salami:
                        price = 65;
                        break;
                    case Vegeteriana:
                        price = 70;
                        break;
                    case Mexicano:
                        price = 63;
                        break;
                    case Papperoni:
                        price = 55;
                        break;
                    default:
                        System.err.println("This is incorrect pizza's size, please check it again. Available sizes are: 30 , 50");
                        System.exit(0);
                }
                break;
            case 50:
                switch (pizzasNames) {
                    case Capricciosa:
                        price = 80;
                        break;
                    case Salami:
                        price = 85;
                        break;
                    case Vegeteriana:
                        price = 80;
                        break;
                    case Mexicano:
                        price = 83;
                        break;
                    case Papperoni:
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
            case Cheese:
                price += 10;
                break;
            case Sausage:
                price += 15;
                break;
            case Spice:
                price += 5;
                break;
            case Fruits:
                price += 15;
                break;
            case Tomato:
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
