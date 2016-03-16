package domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;


public class Pizza {
    private static Logger logger = LoggerFactory.getLogger(Pizza.class);
    private PizzasNames pizzaNames;
    private int size;
    private String pizzaDescription;
    private int price;
    private List<PizzasAddons> pizzaAddons = new ArrayList();
    private int count;

    public int getCount() {
        return count;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSize() {
        return size;
    }

    public PizzasNames getPizzaNames() {
        return pizzaNames;
    }

    public List<PizzasAddons> getPizzaAddons() {
        return pizzaAddons;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(count);
        sb.append(" " + this.getClass().getSimpleName());
        sb.append(": " + this.pizzaNames);
        sb.append(" " + this.pizzaDescription);
        sb.append(". Addons : ");
        sb.append(this.pizzaAddons);
        sb.append(". Size is : ");
        sb.append(this.size);
        sb.append(" sm. Price is: ");
        sb.append(this.price);
        sb.append(" hrn");
        return sb.toString();
    }

    public static class PizzaBuilder {
        private int count;
        private int size = 30;
        private String info = ": Cheese + Salami + Papper ";
        private List<PizzasAddons> pizzaAddons = new ArrayList();
        private PizzasNames pizzasNames;
        private Data data = new Data();
        private Locale locale = data.getLocale();
        private ResourceBundle resourceBundle = ResourceBundle.getBundle("Bundle", locale);

        public void setPizzaAddons(List<PizzasAddons> pizzaAddons) {
            this.pizzaAddons = pizzaAddons;
        }

        public PizzaBuilder setPizzaName(PizzasNames pizzasNames) {
            this.pizzasNames = pizzasNames;
            count++;
            return this;
        }

        public PizzaBuilder setPizzaDescription() {
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
                    logger.error(resourceBundle.getString("pizzaVariables"));
                    break;
            }
            return this;
        }

        public PizzaBuilder setPizzaSize(int size) {
            this.size = size;
            return this;
        }

        public PizzaBuilder setPizzaAddons(PizzasAddons pizzasAddons) {
            this.pizzaAddons.add(pizzasAddons);
            return this;
        }

        public Pizza build() {
            Pizza pizza = new Pizza();
            pizza.pizzaNames = pizzasNames;
            pizza.pizzaDescription = info;
            pizza.size = size;
            pizza.pizzaAddons = pizzaAddons;
            pizza.count = count;
            return pizza;
        }


    }

}
