package org.nbu.data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class StoreTest {

    private Store store;
    private FoodProduct freshFood;
    private FoodProduct expiringFood;
    private FoodProduct expiredFood;
    private NonFoodProduct nonFood;

    @BeforeEach
    void setUp() {
        store = new Store();

        freshFood = new FoodProduct(1, "Bread", 2.0,
                LocalDate.now().plusDays(10), 0.0, ProductType.FOOD);

        expiringFood = new FoodProduct(2, "Yogurt", 2.0,
                LocalDate.now().plusDays(3), 0.0, ProductType.FOOD);

        expiredFood = new FoodProduct(3, "Milk", 2.0,
                LocalDate.now().minusDays(1), 0.0, ProductType.FOOD);

        nonFood = new NonFoodProduct(4, "Shampoo", 5.0,
                LocalDate.now().plusDays(20), 0.0, ProductType.NON_FOOD);
    }

    @org.junit.jupiter.api.Test
    void addProduct() {
        store.addProduct(freshFood);
        assertEquals(1, store.getProducts().size());
        assertEquals("Bread", store.getProducts().get(0).getName());
    }

    @org.junit.jupiter.api.Test
    void getProducts() {
        store.addProduct(freshFood);
        store.addProduct(nonFood);
        assertEquals(2, store.getProducts().size());
    }

    @org.junit.jupiter.api.Test
    void calculateProductPrice() {
        double price = store.calculateProductPrice(nonFood, LocalDate.now());
        assertEquals(5.0, price, 0.001);
    }
}