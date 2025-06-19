package org.nbu.data;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

public class Receipt implements Serializable {
    private static int receiptCounter = 1;
    private int number;
    private Cashier cashier;
    private LocalDateTime timestampOfReceipt;
    private Map<Product, Integer> productQuantityPriceInformation;


}
