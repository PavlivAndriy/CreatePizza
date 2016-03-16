package domain;

import java.util.ArrayList;
import java.util.List;

public class Bill {
    private double pizzaPrice;
    private double drinksPrice;
    private double totalPrice;
    private String weekends;
    private String tips;
    private String discount;
    private String hollidays;
    private String order;
    private String finalPriceToString;
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

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public void setDrinks(List<Drinks> drinks) {
        this.drinks = drinks;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public void setWeekends(String weekends) {
        this.weekends = weekends;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public void setHollidays(String hollidays) {
        this.hollidays = hollidays;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public void setFinalPriceToString(String finalPriceToString) {
        this.finalPriceToString = finalPriceToString;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(order);
        for (Pizza p : pizzas) {
            sb.append("\n" + p);
        }
        for (Drinks dr : drinks) {
            sb.append("\n" + dr);
        }
        sb.append("\n" + tips);
        sb.append("\n" + weekends);
        sb.append("\n" + hollidays);
        sb.append("\n" + discount);
        sb.append("\n" + finalPriceToString);
        if (totalPrice == 0.0) {
            return " ";
        }
        return sb.toString();
    }


}
