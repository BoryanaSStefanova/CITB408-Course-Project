package org.nbu;

import org.nbu.data.FoodProduct;
import org.nbu.data.NonFoodProduct;
import org.nbu.data.Product;
import org.nbu.data.ProductType;
import org.nbu.data.Store;

import java.sql.SQLOutput;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");
        Store store = new Store();
        Product milk = new FoodProduct(1, "Milk", 1.20, LocalDate.of(2025, 7, 1), 1.80, ProductType.FOOD);
        Product soap = new NonFoodProduct(2, "Soap", 0.80, LocalDate.of(2026, 1, 1), 1.50, ProductType.NON_FOOD);

        store.addProduct(milk);
        store.addProduct(soap);

        LocalDate today = LocalDate.now();

        for(Product p : store.getProducts()){
            double price = store.calculateProductPrice(p, today);
            System.out.println(p.getName() + "| Цена: " + (price == 0.0 ? "Изтекъл срок" : price));
        }
    }
}