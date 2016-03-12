package service;

import java.util.Map;

public interface PriceService {
    Map<String, String> getPricesForSmallPizza();

    Map<String, String> getPricesForBigPizza();

    Map<String, String> getPricesForDrinks();

}
