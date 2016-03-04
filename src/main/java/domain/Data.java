package domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andriy on 2/3/2016.
 */
public class Data {

    private LocalDate date = LocalDate.now();
    private int addons;
    private List<Pizza> pizzas = new ArrayList();
    private List<Drinks> drinks = new ArrayList();
    private String discount;
    private String lang = "en";
    private String country = "US";

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

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public List<Drinks> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<Drinks> drinks) {
        this.drinks = drinks;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }
}
