package org.nbu.data;

import java.time.LocalDate;

public class FoodProduct extends Product {
    private double markupPercent = 0.3;
    private double discountPercent = 0.2;
    private int daysBeforeExpiration = 5;

    public FoodProduct(int id, String name, double deliveryPrice, LocalDate expireDate, double sellingPrice, ProductType food) {
        super(id, name, deliveryPrice, expireDate, sellingPrice, ProductType.FOOD);
    }
}
