package pizza;

/**
 * Created by Andriy on 1/12/2016.
 */
public class PizzaBuilder {
    public static int count;
    public String n = "pizza";
    public int s = 30;
    public String i = ": Cheese + Salami + Papper ";
    public int p = 50;
    public String add = " ";

    public PizzaBuilder makeName(String n) {
        this.n = n;
        count++;
        return this;
    }

    public PizzaBuilder makeInfo() {
        if (n.equals("Capricciosa")) {
            i = i + "+ Ham + mushrooms";
        } else if (n.equals("Salami")) {
            i = i + "+ Sausage";
        } else if (n.equals("Vegeteriana")) {
            i = ": Cheese + Pepper + Corn + Tomato";
        } else if (n.equals("Mexicano")) {
            i = i + "+ Chili Pepper + Tomato + Onion";
        } else if (n.equals("Papperoni")) {
            i = i + "+ Red Hot Chili Pepper + Onion + Bacon";
        } else {
            System.err.println("This is incorrect pizza's name, please check it again. Available pizzas are: Capricciosa, Salami," +
                    "Vegeteriana, Mexicano, Papperoni");
        }
        return this;
    }

    public PizzaBuilder makeSize(int s) {
        this.s = s;
        return this;
    }

    public PizzaBuilder makePrice() {
        if (n.equals("Capricciosa") & s == 30) {
            p = 60;
        } else if (n.equals("Capricciosa") & s == 50) {
            p = 80;
        } else if (n.equals("Salami") & s == 30) {
            p = 65;
        } else if (n.equals("Salami") & s == 50) {
            p = 85;
        } else if (n.equals("Vegeteriana") & s == 30) {
            p = 70;
        } else if (n.equals("Vegeteriana") & s == 50) {
            p = 89;
        } else if (n.equals("Mexicano") & s == 30) {
            p = 63;
        } else if (n.equals("Mexicano") & s == 50) {
            p = 85;
        } else if (n.equals("Papperoni") & s == 30) {
            p = 55;
        } else if (n.equals("Papperoni") & s == 50) {
            p = 77;
        } else {
            System.err.println("This is incorrect pizza's size, please check it again. Available sizes are: 30 , 50");
        }
        return this;
    }

    public PizzaBuilder add(String add) {
        this.add += " " + add;
        if (add.equals("Cheese")) {
            p += 10;
        } else if (add.equals("Sausage")) {
            p += 15;
        } else if (add.equals("Spice")) {
            p += 5;
        } else if (add.equals("Fruits")) {
            p += 15;
        } else if (add.equals("Tomato")) {
            p += 7;
        }
        return this;
    }

    public PizzaBuilder add() {
        System.out.println("Without addons");
        return this;
    }


    public Pizza build() {
        Pizza pizza = new Pizza();
        pizza.setName(n);
        pizza.setInfo(i);
        pizza.setSize(s);
        pizza.setAdd(add);
        pizza.setCount(count);
        if (count % 3 == 0) {
            pizza.setPrice(0);
        } else {
            pizza.setPrice(p);
        }
        return pizza;
    }
}
