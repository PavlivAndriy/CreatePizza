package io;

import domain.Drinks;
import domain.Pizza;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by Andriy on 2/3/2016.
 */
public class Data {
    private ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
    private ArrayList<Drinks> drinks = new ArrayList<Drinks>();
    private double pizzaPrice;
    private double drinksPrice;
    private double totalPrice;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private LocalDate date = LocalDate.now();
    private int addons;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

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

    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    public ArrayList<Drinks> getDrinks() {
        return drinks;
    }

    public BufferedReader getReader() {
        return reader;
    }

    public int getAddons() {
        return addons;
    }

    public void setAddons(int addons) {
        this.addons = addons;
    }


}
