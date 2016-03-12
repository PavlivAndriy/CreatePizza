package service;

import domain.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class PriceServiceImpl implements PriceService {
    private static final Logger logger = LoggerFactory.getLogger(PriceServiceImpl.class);
    private static final String CSV_FILE = "./DrinksPrices.csv";
    private static final String CSV_FILE_30 = "./Pizza30Prices.csv";
    private static final String CSV_FILE_50 = "./Pizza50Prices.csv";
    private static final String CSV_SPLIT_BY = ",";
    private Data data = new Data();
    private Locale locale = data.getLocale();
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("Bundle", locale);
    private String line = "";
    private Map<String, String> drinksMaps = new HashMap();
    private Map<String, String> pizzaMapsSmall = new HashMap();
    private Map<String, String> pizzaMapsBig = new HashMap();

    public Map<String, String> getPricesForSmallPizza() {
        makePriceForBigPizza();
        return pizzaMapsBig;
    }

    public Map<String, String> getPricesForBigPizza() {
        makePriceForSmallPizza();
        return pizzaMapsSmall;
    }

    public Map<String, String> getPricesForDrinks() {
        makePriceForDrinks();
        return drinksMaps;
    }

    private void makePriceForDrinks() {
        BufferedReader priceReader = null;
            try {
                priceReader = new BufferedReader(new FileReader(CSV_FILE));
                while ((line = priceReader.readLine()) != null) {
                    String[] price = line.split(CSV_SPLIT_BY);
                    drinksMaps.put(price[0], price[1]);
                }
            } catch (FileNotFoundException e) {
                drinksMaps = null;
                logger.error(resourceBundle.getString("csvError") + e);
            } catch (IOException e) {
                logger.error(resourceBundle.getString("correctName") + e);
            } finally {
                if (priceReader != null) {
                    try {
                        priceReader.close();
                    } catch (IOException e) {
                        logger.error(resourceBundle.getString("correctName") + e);
                    }
                }
            }
        }

    private void makePriceForSmallPizza() {
        BufferedReader priceReader = null;
        try {
            priceReader = new BufferedReader(new FileReader(CSV_FILE_30));
            while ((line = priceReader.readLine()) != null) {
                String[] price = line.split(CSV_SPLIT_BY);
                pizzaMapsSmall.put(price[0], price[1]);
            }
        } catch (FileNotFoundException e) {
            pizzaMapsSmall = null;
            logger.error(resourceBundle.getString("csvError") + e);
        } catch (IOException e) {
            logger.error(resourceBundle.getString("correctName") + e);
        } finally {
            if (priceReader != null) {
                try {
                    priceReader.close();
                } catch (IOException e) {
                    logger.error(resourceBundle.getString("correctName") + e);
                }
            }
        }

    }

    private void makePriceForBigPizza() {
        BufferedReader priceReader = null;
        try {
            priceReader = new BufferedReader(new FileReader(CSV_FILE_50));
            while ((line = priceReader.readLine()) != null) {
                String[] price = line.split(CSV_SPLIT_BY);
                pizzaMapsBig.put(price[0], price[1]);
            }
        } catch (FileNotFoundException e) {
            pizzaMapsBig = null;
            logger.error(resourceBundle.getString("csvError") + e);
        } catch (IOException e) {
            logger.error(resourceBundle.getString("correctName") + e);
        } finally {
            if (priceReader != null) {
                try {
                    priceReader.close();
                } catch (IOException e) {
                    logger.error(resourceBundle.getString("correctName") + e);
                }
            }
        }

    }
}
