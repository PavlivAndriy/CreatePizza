package domain;

public class Drinks {
    private DrinksNames drinksNames;
    private DrinksSize drinksSize;
    private int price;
    private int count;

    public DrinksNames getDrinksNames() {
        return drinksNames;
    }

    public DrinksSize getDrinksSize() {
        return drinksSize;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(count);
        sb.append(" " + this.getClass().getSimpleName());
        sb.append(": " + this.drinksNames);
        sb.append(" " + this.drinksSize);
        sb.append(" size Price: ");
        sb.append(this.price);
        sb.append(" hrn");
        return sb.toString();
    }

    public static class DrinksBuilder {
        private int count;
        private DrinksNames drinksNames;
        private DrinksSize drinksSize;

        public DrinksBuilder setDrinksNames(DrinksNames drinksNames) {
            this.drinksNames = drinksNames;
            count++;
            return this;
        }

        public DrinksBuilder setDrinksSize(DrinksSize drinksSize) {
            this.drinksSize = drinksSize;
            return this;
        }

        public Drinks build() {
            Drinks drinks = new Drinks();
            drinks.drinksNames = drinksNames;
            drinks.drinksSize = drinksSize;
            drinks.count = count;
            return drinks;
        }
    }

}
