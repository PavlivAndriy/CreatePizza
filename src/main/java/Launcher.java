import domain.Data;
import service.CalculationServiceImpl;
import service.OrderCreatorImpl;

/**
 * Created by Andriy on 1/24/2016.
 */
public class Launcher {
    public static void main(String[] args) {
        CalculationServiceImpl calculationServiceImpl = new CalculationServiceImpl();
        OrderCreatorImpl orderCreatorImpl = new OrderCreatorImpl();
        Data data = orderCreatorImpl.readData();
        calculationServiceImpl.buildBill(data);
    }
}

