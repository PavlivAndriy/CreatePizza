package domain;

import java.time.LocalDate;

/**
 * Created by Andriy on 2/3/2016.
 */
public class Data {

    private LocalDate date = LocalDate.now();
    private int addons;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getAddons() {
        return addons;
    }

    public void setAddons(int addons) {
        this.addons = addons;
    }


}
