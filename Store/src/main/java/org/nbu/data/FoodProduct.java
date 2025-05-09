package org.nbu.data;

import java.time.LocalDate;

public class FoodProduct extends Product{

    public FoodProduct(int id, String name, double deliveryPrice, LocalDate expireDate, double sellingPrice) {
        super(id, name, deliveryPrice, expireDate, sellingPrice);
    }

    @Override
    public double calculateSellingPrice(LocalDate currentDate) {

        return 0;
    }
}
