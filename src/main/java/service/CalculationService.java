package service;

import domain.Bill;
import domain.Data;

/**
 * Created by Andriy on 2/18/2016.
 */
public interface CalculationService {
    public Bill buildBill(Data data);
}
