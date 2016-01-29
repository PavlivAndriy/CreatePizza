package drinks;

/**
 * Created by Andriy on 1/24/2016.
 */
public enum DrinksSize {
    LOW(0.5), MID1(1), MID2(1.5), BIG(2);

    private final double value;

    DrinksSize(double value) {
        this.value = value;
    }

    public double value() {
        return value;
    }
}
