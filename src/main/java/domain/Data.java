package domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Andriy on 2/3/2016.
 */
public class Data {

    private LocalDate date = LocalDate.now();
    private int addons;
    private List<Pizza> pizzas = new ArrayList();
    private List<Drinks> drinks = new ArrayList();
    private boolean discount;
    private Locale locale = new Locale("en", "US");

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getAddons() {
        return addons;
    }

    public void setAddons(int addons) {
        this.addons = addons;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public List<Drinks> getDrinks() {
        return drinks;
    }

    public boolean getDiscount() {
        return discount;
    }

    public void setDiscount(Boolean discount) {
        this.discount = discount;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public void addPizza(Pizza pizza) {
        this.pizzas.add(pizza);
    }

    public void addDrinks(Drinks drink) {
        this.drinks.add(drink);
    }
}
