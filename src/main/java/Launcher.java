import io.Creator;
import service.CalculationService;

/**
 * Created by Andriy on 1/24/2016.
 */
public class Launcher {
    public static void main(String[] args) {
        Creator creator = new Creator();
        CalculationService calculationService = new CalculationService();
        creator.makePizza();
        creator.makeDrinks();
        calculationService.storeInfo();

    }
}

