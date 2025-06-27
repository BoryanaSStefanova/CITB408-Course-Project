package org.nbu.data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nbu.exception.InsufficientQuantityException;
import org.nbu.service.Store;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

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

    @org.junit.jupiter.api.Test
    void generateReceiptAfterSellingProducts(){
        freshFood.setQuantity(5);
        store.addProduct(freshFood);
        Cashier cashier = new Cashier(2, "Mitaka", 2400);

        Map<Product, Integer> toSell = new HashMap<>();
        toSell.put(freshFood, 2);

        store.sellProduct(cashier, toSell, LocalDate.now(), 100.0);
        assertEquals(3, freshFood.getQuantity());
        assertEquals(1, store.getTotalReceipts());
        assertTrue(store.getTotalTurnover() > 0);
    }

    @org.junit.jupiter.api.Test
    void productWithInsufficientQuantity(){
        freshFood.setQuantity(1);
        store.addProduct(freshFood);
        Cashier cashier = new Cashier(2, "Mitaka", 2400);
        Map<Product, Integer> toSell = new HashMap<>();
        toSell.put(freshFood, 2);

        assertThrows(InsufficientQuantityException.class, () -> {
            store.sellProduct(cashier, toSell, LocalDate.now(), 100.0);
        });
    }
}