package org.nbu.data;

import java.time.LocalDate;

public abstract class Product {
    public int id;
    public String name;
    public double deliveryPrice;
    public LocalDate expireDate;

    public double sellingPrice;

    public Product(int id, String name, double deliveryPrice, LocalDate expireDate, double sellingPrice) {
        this.id = id;
        this.name = name;
        this.deliveryPrice = deliveryPrice;
        this.expireDate = expireDate;
        this.sellingPrice = sellingPrice;
    }


    public abstract double calculateSellingPrice(LocalDate currentDate);

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

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", deliveryPrice=" + deliveryPrice +
                ", expireDate=" + expireDate +
                ", sellingPrice=" + sellingPrice +
                '}';
    }
}
