import money.Calculation;
import money.Creator;

/**
 * Created by Andriy on 1/24/2016.
 */
public class Launcher {
    public static void main(String[] args) {
        Creator.makePizza();
        Creator.makeDrinks();
        Calculation.storeInfo();

    }
}

