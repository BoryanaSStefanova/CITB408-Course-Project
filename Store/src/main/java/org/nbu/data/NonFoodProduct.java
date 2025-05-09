package org.nbu.data;

import java.time.LocalDate;

public class NonFoodProduct extends Product{

    private double markupPercent;//percent for discount of goods according to expire date

    public NonFoodProduct(int id, String name, double deliveryPrice, LocalDate expireDate, double sellingPrice) {
        super(id, name, deliveryPrice, expireDate, sellingPrice);
    }

    @Override
    public double calculateSellingPrice(LocalDate currentDate) {
        return 0;
    }
}
