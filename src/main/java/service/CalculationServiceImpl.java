package service;

import domain.Bill;
import domain.Data;
import domain.Drinks;
import domain.Pizza;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;


public class CalculationServiceImpl implements CalculationService {
    private static final Logger logger = LoggerFactory.getLogger(CalculationServiceImpl.class);
    private double pizzaTotalPrice;
    private double drinksTotalPrice;
    private double totalPrice;
    private Data data = new Data();
    private Bill bill = new Bill();
    private LocalDate hollidayChristmas = Year.now().atMonth(Month.JANUARY).atDay(7);
    private LocalDate hollidayIndepenanceDay = Year.now().atMonth(Month.AUGUST).atDay(24);
    private LocalDate hollidayProgrammerDay = Year.now().atDay(256);
    private List<Pizza> pizzas = new ArrayList();
    private List<Drinks> drinks = new ArrayList();
    private Locale locale = new Locale(data.getLang(), data.getCountry());
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("Bundle", locale);

    private void setDiscount(String discount) {
        totalPrice = bill.getTotalPrice();
        LocalDate date = data.getDate();
        if ((date.equals(hollidayChristmas))
                || (date.equals(hollidayIndepenanceDay))
                || (date.equals(hollidayProgrammerDay))) {
            totalPrice *= 0.5;
            bill.setTotalPrice(totalPrice);
            discount = "No";
            logger.info(resourceBundle.getString("economyHollidays") + bill.getTotalPrice());
        } else {
            logger.info(resourceBundle.getString("noEconomy"));
        }

        if (discount.equalsIgnoreCase("Yes")) {
            double discountPay = bill.getTotalPrice() * 0.1;
            totalPrice *= 0.9;
            bill.setTotalPrice(totalPrice);
            logger.info(resourceBundle.getString("yourDiscount")+ discountPay);
        } else if (discount.equalsIgnoreCase("No")) {
            totalPrice *= 1;
            bill.setTotalPrice(totalPrice);
            logger.info(resourceBundle.getString("noDiscount"));
        }

    }

    private void check() {
        LocalDate date = data.getDate();
        double price = bill.getTotalPrice();
        if (date.getYear() > 2015 && date.getMonth().equals(Month.SEPTEMBER) && date.getDayOfMonth() < 8
                && date.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
            price *= 1;
            bill.setTotalPrice(price);
            logger.info(resourceBundle.getString("noTips"));
        } else {
            double tips = 0.05 * bill.getTotalPrice();
            price *= 1.05;
            bill.setTotalPrice(price);
            logger.info(resourceBundle.getString("payForTips") + tips );
        }
    }

    private void weekends() {
        LocalDate date = data.getDate();
        double price = bill.getTotalPrice();
        if (date.getDayOfWeek().equals(DayOfWeek.FRIDAY)
                || date.getDayOfWeek().equals(DayOfWeek.SATURDAY)
                || date.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            double weekendsPay = 0.05 * bill.getTotalPrice();
            price *= 1.05;
            bill.setTotalPrice(price);
            logger.info(resourceBundle.getString("payForWeekends")+ weekendsPay );
        }
    }

    private void storeInfo() {
        pizzaCost();
        drinksCost();
        bill.setTotalPrice(bill.getDrinksPrice() + bill.getPizzaPrice());
        if (bill.getTotalPrice() == 0) {
            logger.error(resourceBundle.getString("orderFor") + bill.getTotalPrice()
                    + resourceBundle.getString("thankYouForVisit"));
        } else {
            System.out.println(resourceBundle.getString("yourOrder"));
            for (Pizza p : pizzas) {
                System.out.println(p);
            }
            for (Drinks dr : drinks) {
                System.out.println(dr);
            }
            check();
            weekends();
            setDiscount(data.getDiscount());
            logger.info(resourceBundle.getString("finalPrice") + bill.getTotalPrice());

        }
    }

    private void pizzaCost() {
        for (int i = 0; i < pizzas.size(); i++) {
            pizzaTotalPrice += pizzas.get(i).getPrice();
            bill.setPizzaPrice(pizzaTotalPrice);
        }
    }

    private void drinksCost() {
        for (int i = 0; i < drinks.size(); i++) {
            drinksTotalPrice += drinks.get(i).getPrice();
            bill.setDrinksPrice(drinksTotalPrice);
        }
    }


    public Bill buildBill(Data data) {
        this.data = data;
        pizzas = data.getPizzas();
        drinks = data.getDrinks();
        storeInfo();
        return bill;
    }
}
