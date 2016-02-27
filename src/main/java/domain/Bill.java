package domain;

import java.util.ArrayList;
import java.util.List;


public class Bill {
    private double pizzaPrice;
    private double drinksPrice;
    private double totalPrice;
    private List<Pizza> pizzas = new ArrayList();
    private List<Drinks> drinks = new ArrayList();

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getDrinksPrice() {
        return drinksPrice;
    }

    public void setDrinksPrice(double drinksPrice) {
        this.drinksPrice = drinksPrice;
    }

    public double getPizzaPrice() {
        return pizzaPrice;
    }

    public void setPizzaPrice(double pizzaPrice) {
        this.pizzaPrice = pizzaPrice;
    }

    @Override
    public String toString() {
        System.out.println("Your order is :");
        return " ";
    }


}
