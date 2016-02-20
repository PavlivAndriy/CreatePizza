package domain;

import java.util.ArrayList;
import java.util.List;


public class Bill {
    private double pizzaPrice;
    private double drinksPrice;
    private double totalPrice;
    private List<Pizza> pizzas = new ArrayList<>();
    private List<Drinks> drinks = new ArrayList<>();

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

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public List<Drinks> getDrinks() {
        return drinks;
    }

    @Override
    public String toString() {
        for (Pizza p : pizzas) {
            System.out.println(p);
        }
        for (Drinks d : drinks) {
            System.out.println(d);
        }
        return "";
    }
}