import domain.Data;
import service.CalculationServiceImpl;
import service.OrderCreatorServiceImpl;

/**
 * Created by Andriy on 1/24/2016.
 */
public class Launcher {
    public static void main(String[] args) {
        CalculationServiceImpl calculationServiceImpl = new CalculationServiceImpl();
        OrderCreatorServiceImpl orderCreatorImpl = new OrderCreatorServiceImpl();
        Data data = orderCreatorImpl.readData();
        calculationServiceImpl.buildBill(data);
    }
}

