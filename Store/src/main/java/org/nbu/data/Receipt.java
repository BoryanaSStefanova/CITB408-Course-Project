package org.nbu.data;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Receipt implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final AtomicInteger receiptCounter = new AtomicInteger(1);
    private int number;
    private Cashier cashier;
    private LocalDateTime timestampOfReceipt;
    private Map<Product, Integer> productQuantityPriceInformation;
    private double totalSum;

    public Receipt(Cashier cashier, Map<Product, Integer> productQuantityPriceInformation, double totalSum) {
        this.number = receiptCounter.getAndIncrement();
        this.cashier = cashier;
        this.timestampOfReceipt = timestampOfReceipt;
        this.productQuantityPriceInformation = productQuantityPriceInformation;
        this.totalSum = totalSum;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Cashier getCashier() {
        return cashier;
    }

    public void setCashier(Cashier cashier) {
        this.cashier = cashier;
    }

    public LocalDateTime getTimestampOfReceipt() {
        return timestampOfReceipt;
    }

    public void setTimestampOfReceipt(LocalDateTime timestampOfReceipt) {
        this.timestampOfReceipt = timestampOfReceipt;
    }

    public Map<Product, Integer> getProductQuantityPriceInformation() {
        return productQuantityPriceInformation;
    }

    public void setProductQuantityPriceInformation(Map<Product, Integer> productQuantityPriceInformation) {
        this.productQuantityPriceInformation = productQuantityPriceInformation;
    }

    public double getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(double totalSum) {
        this.totalSum = totalSum;
    }
}
