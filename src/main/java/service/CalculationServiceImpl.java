package service;

import domain.Bill;
import domain.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.Locale;
import java.util.ResourceBundle;

public class CalculationServiceImpl implements CalculationService {
    private static final Logger logger = LoggerFactory.getLogger(CalculationServiceImpl.class);
    private Bill bill = new Bill();
    private LocalDate hollidayChristmas = Year.now().atMonth(Month.JANUARY).atDay(7);
    private LocalDate hollidayIndepenanceDay = Year.now().atMonth(Month.AUGUST).atDay(24);
    private LocalDate hollidayProgrammerDay = Year.now().atDay(256);
    private Locale locale;
    private ResourceBundle resourceBundle;

    private void setDiscount(boolean discount, Data data) {
        double totalPrice = bill.getTotalPrice();
        LocalDate date = data.getDate();
        if ((date.equals(hollidayChristmas))
                || (date.equals(hollidayIndepenanceDay))
                || (date.equals(hollidayProgrammerDay))) {
            totalPrice *= 0.5;
            bill.setTotalPrice(totalPrice);
            discount = false;
            logger.info(resourceBundle.getString("economyHollidays") + bill.getTotalPrice());
            bill.setHollidays(resourceBundle.getString("economyHollidays") + String.format(" %.2f ", bill.getTotalPrice()));
        } else {
            logger.info(resourceBundle.getString("noEconomy"));
            bill.setHollidays(resourceBundle.getString("noEconomy"));
        }

        if (discount == true) {
            double discountPay = bill.getTotalPrice() * 0.1;
            totalPrice *= 0.9;
            bill.setTotalPrice(totalPrice);
            logger.info(resourceBundle.getString("yourDiscount") + discountPay);
            bill.setDiscount(resourceBundle.getString("yourDiscount") + " " + String.format(" %.2f ",discountPay));
        } else if (discount == false) {
            totalPrice *= 1;
            bill.setTotalPrice(totalPrice);
            logger.info(resourceBundle.getString("noDiscount"));
            bill.setDiscount(resourceBundle.getString("noDiscount"));
        }

    }

    private void check(Data data) {
        LocalDate date = data.getDate();
        double price = bill.getTotalPrice();
        if (date.getYear() > 2015 && date.getMonth().equals(Month.SEPTEMBER) && date.getDayOfMonth() < 8
                && date.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
            price *= 1;
            bill.setTotalPrice(price);
            logger.info(resourceBundle.getString("noTips"));
            bill.setTips(resourceBundle.getString("noTips"));
        } else {
            double tips = 0.05 * bill.getTotalPrice();
            price *= 1.05;
            bill.setTotalPrice(price);
            logger.info(resourceBundle.getString("payForTips") + tips);
            bill.setTips(resourceBundle.getString("payForTips") + " " + String.format(" %.2f ", tips));
        }
    }

    private void weekends(Data data) {
        LocalDate date = data.getDate();
        double price = bill.getTotalPrice();
        if (date.getDayOfWeek().equals(DayOfWeek.FRIDAY)
                || date.getDayOfWeek().equals(DayOfWeek.SATURDAY)
                || date.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            double weekendsPay = 0.05 * bill.getTotalPrice();
            price *= 1.05;
            bill.setTotalPrice(price);
            logger.info(resourceBundle.getString("payForWeekends") + weekendsPay);
            bill.setWeekends(resourceBundle.getString("payForWeekends") + " " +String.format(" %.2f",weekendsPay));
        }
    }

    private void storeInfo(Data data) {
        locale = data.getLocale();
        resourceBundle = ResourceBundle.getBundle("Bundle", locale);
        pizzaCost(data);
        drinksCost(data);
        bill.setTotalPrice(bill.getDrinksPrice() + bill.getPizzaPrice());
        if (bill.getTotalPrice() == 0) {
            logger.error(resourceBundle.getString("orderFor") + bill.getTotalPrice()
                    + resourceBundle.getString("thankYouForVisit"));
            System.out.println(resourceBundle.getString("orderFor") + bill.getTotalPrice()
                    + resourceBundle.getString("thankYouForVisit"));
        } else {
            bill.setOrder(resourceBundle.getString("yourOrder"));
            check(data);
            weekends(data);
            setDiscount(data.getDiscount(), data);
            logger.info(resourceBundle.getString("finalPrice") + bill.getTotalPrice());
            bill.setFinalPriceToString(resourceBundle.getString("finalPrice") + " "
                    + String.format(" %.2f ",bill.getTotalPrice()));
        }
    }

    private void pizzaCost(Data data) {
        bill.setPizzas(data.getPizzas());
        double pizzaTotalPrice = 0;
        try {
            for (int i = 0; i < data.getPizzas().size(); i++) {
                pizzaTotalPrice += data.getPizzas().get(i).getPrice();
            }
            bill.setPizzaPrice(pizzaTotalPrice);
        } catch (IndexOutOfBoundsException e) {
            logger.error(resourceBundle.getString("pizzaError"));
        }
    }

    private void drinksCost(Data data) {
        bill.setDrinks(data.getDrinks());
        double drinksTotalPrice = 0;
        try {
            for (int i = 0; i < data.getDrinks().size(); i++) {
                drinksTotalPrice += data.getDrinks().get(i).getPrice();
            }
            bill.setDrinksPrice(drinksTotalPrice);
        } catch (IndexOutOfBoundsException e) {
            logger.error(resourceBundle.getString("drinksError"));
        }
    }

    public Bill buildBill(Data data) {
        storeInfo(data);
        return bill;
    }
}
