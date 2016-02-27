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


}
