import io.Creator;
import service.Calculation;

/**
 * Created by Andriy on 1/24/2016.
 */
public class Launcher {
    public static void main(String[] args) {
        Creator creator = new Creator();
        Calculation calculation = new Calculation();
        creator.makePizza();
        creator.makeDrinks();
        calculation.storeInfo();

    }
}

