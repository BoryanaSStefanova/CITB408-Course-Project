package org.nbu.data;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Product implements Serializable {
    public int id;
    public String name;
    public double deliveryPrice;
    public LocalDate expireDate;
    public double sellingPrice;
    public ProductType type;

    public double quantity;

    public Product(int id, String name, double deliveryPrice, LocalDate expireDate, double sellingPrice, ProductType type) {
        this.id = id;
        this.name = name;
        this.deliveryPrice = deliveryPrice;
        this.expireDate = expireDate;
        this.sellingPrice = sellingPrice;
        this.type = type;
    }


    public boolean isExpired(LocalDate currentDate){
        return expireDate.isBefore(currentDate) || expireDate.isEqual(currentDate);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(double deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", deliveryPrice=" + deliveryPrice +
                ", expireDate=" + expireDate +
                ", sellingPrice=" + sellingPrice +
                ", type=" + type +
                ", quantity=" + quantity +
                '}';
    }

    public void reduceProductQuantity(int amount){
        if(amount > this.quantity){
            throw new IllegalArgumentException(("Trying to sell more than what is in stock!" + name));
        }
        this.quantity -= amount;
    }


}
