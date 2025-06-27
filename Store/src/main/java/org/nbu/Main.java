package org.nbu;
import org.nbu.data.*;
import org.nbu.service.Store;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");
        Store store = new Store();

        Cashier cashier = new Cashier(1, "Ivancho", 2000);

        Product cake = new FoodProduct(1, "Choco cake", 6, LocalDate.now().plusDays(4), 8, ProductType.FOOD);
        cake.setQuantity(30);

        Product sandwich = new FoodProduct(2, "Salmon sandwich", 3, LocalDate.now().plusDays(3), 5, ProductType.FOOD);
        sandwich.setQuantity(15);

        Product milk = new FoodProduct(3, "Milk", 1.20, LocalDate.of(2025, 7, 1), 1.80, ProductType.FOOD);
        milk.setQuantity(25);

        Product soap = new NonFoodProduct(4, "Soap", 0.80, LocalDate.of(2026, 1, 1), 1.50, ProductType.NON_FOOD);
        soap.setQuantity(22);

        Product meat = new FoodProduct(5, "lamb", 120, LocalDate.now().plusDays(4), 200, ProductType.FOOD);
        meat.setQuantity(15);

        store.addProduct(soap);
        store.addProduct(cake);
        store.addProduct(sandwich);
        store.addProduct(milk);
        store.addProduct(meat);

        //first purchase
        Map<Product, Integer> productsToSell = new HashMap<>();
        productsToSell.put(cake, 3);

        try {
            store.sellProduct(cashier, productsToSell, LocalDate.now(), 10000.0);
            System.out.println("First purchase complete!");
        } catch (Exception e) {
            System.out.println("Error! Cannot finish the selling action: " + e.getMessage());
        }

        //second purchase
        Map<Product, Integer> productsToSell1 = new HashMap<>();
        productsToSell1.put(cake, 1);
        productsToSell1.put(soap, 2);
        productsToSell1.put(sandwich, 3);
        productsToSell1.put(milk, 4);

        try {
            store.sellProduct(cashier, productsToSell1, LocalDate.now(), 50.0);
            System.out.println("Second purchase successful!");
        } catch (Exception e) {
            System.out.println("Second purchase error: " + e.getMessage());
        }

        //third purchase
        Cashier cashier3 = new Cashier(3, "Djaro", 2000);
        Map<Product, Integer> productsToSell3 = new HashMap<>();
        productsToSell3.put(meat, 6);
        productsToSell3.put(cake, 1);

        try {
            store.sellProduct(cashier3, productsToSell3, LocalDate.now(), 5500.0);
            System.out.println("Third purchase successful!");
        } catch (Exception e) {
            System.out.println("Third purchase error: " + e.getMessage());
        }

        int lastReceiptNumber = store.getTotalReceipts();
        Receipt r = store.deserializeReceipt(lastReceiptNumber);
        if (r != null) {
            System.out.println("Cashier(name): " + r.getCashier().getCashier_name());
        }


        double profit = store.calculateProfit();
        System.out.println("Profit: " + profit);

        System.out.println("Goodbye world!");
    }


}