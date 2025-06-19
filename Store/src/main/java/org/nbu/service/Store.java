package org.nbu.service;

import org.nbu.data.Cashier;
import org.nbu.data.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.nbu.data.Receipt;
import org.nbu.exception.InsufficientQuantityException;
public class Store {

    private List<Product> products = new ArrayList<>();

    private List<Receipt> receipts = new ArrayList<>();

    private List<Cashier> cashiers = new ArrayList<>();
    private double foodMarkup = 0.3;
    private double foodDiscount = 0.2;
    private int foodDaysBeforeExpiration = 5;
    private double nonFoodMarkup = 0.5;
    private double nonFoodDiscount = 0.1;
    private int nonFoodDaysBeforeExpiration = 10;

    public void addProduct(Product product){
        products.add(product);
    }

    public List<Product> getProducts(){
        return products;
    }

public double calculateProductPrice(Product product, LocalDate currentDate) {
    if (product.isExpired(currentDate)) {
        return 0.0;
    }

    double price = product.getDeliveryPrice();

    switch (product.getType()) {
        case FOOD:
            price += price * foodMarkup;
            if (product.getExpireDate().minusDays(foodDaysBeforeExpiration).isBefore(currentDate)) {
                price -= price * foodDiscount;
            }
            break;
        case NON_FOOD:
            if (product.getExpireDate().minusDays(nonFoodDaysBeforeExpiration).isBefore(currentDate)) {
                price -= price * nonFoodDiscount;
            }
            break;
    }

    return price;
}



}
