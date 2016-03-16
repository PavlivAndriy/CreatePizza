package service;

import domain.Drinks;
import domain.Pizza;

public interface PriceService {

    void getPricesForPizza(Pizza pizza);

    void getPricesForDrinks(Drinks drinks);

}
