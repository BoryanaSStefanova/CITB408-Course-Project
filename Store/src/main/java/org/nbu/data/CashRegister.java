package org.nbu.data;
import java.util.ArrayList;
import java.util.List;

public class CashRegister {
    private int id;
    private Cashier cashier;
    private List<Product> scannedProducts = new ArrayList<>();

    public CashRegister(int id, Cashier cashier, List<Product> scannedProducts) {
        this.id = id;
        this.cashier = cashier;
        this.scannedProducts = scannedProducts;
    }

    public int getId() {
        return id;
    }

    public Cashier getCashier() {
        return cashier;
    }

    public List<Product> getScannedProducts() {
        return scannedProducts;
    }
}
