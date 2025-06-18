package org.nbu.data;

import java.time.LocalDate;

public class NonFoodProduct extends Product{

    private double markupPercent = 0.5;//percent for discount of goods according to expire date

    private double discountPercent = 0.1;

    private int daysBeforeExpiration = 10;

    public NonFoodProduct(int id, String name, double deliveryPrice, LocalDate expireDate, double sellingPrice, ProductType nonFood) {
        super(id, name, deliveryPrice, expireDate, sellingPrice, ProductType.NON_FOOD);
    }
}
