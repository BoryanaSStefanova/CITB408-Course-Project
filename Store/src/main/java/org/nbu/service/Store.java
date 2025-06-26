package org.nbu.service;

import org.nbu.data.Cashier;
import org.nbu.data.Product;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    private int totalReceipts = 0;
    private double totalTurnover = 0;

    private double deliveryCosts = 0.0;

    public int getTotalReceipts() {
        return totalReceipts;
    }

    public double getTotalTurnover() {
        return totalTurnover;
    }

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
public Receipt sellProduct(Cashier cashier, Map<Product, Integer> productsToSell, LocalDate currentDate, double clientMoneyAmount){
       double total = 0.0;

       for(Map.Entry<Product, Integer> entry : productsToSell.entrySet()) {
           Product product = entry.getKey();
           int quantity = entry.getValue();

           if (product.isExpired(currentDate)) {
               throw new IllegalArgumentException("Product is expired");
           }
           if (product.getQuantity() < quantity) {
               throw new InsufficientQuantityException(product.getName(), quantity, product.getQuantity());
           }
       }

       for(Map.Entry<Product, Integer> entry : productsToSell.entrySet()){
           Product product = entry.getKey();
           int quantity = entry.getValue();
           double price = calculateProductPrice(product, currentDate);
           total +=price * quantity;
       }

           if(clientMoneyAmount < total){
               throw new IllegalArgumentException("Not enough amount to finish the payment!");
           }

           for (Map.Entry<Product, Integer> entry : productsToSell.entrySet()){
               entry.getKey().reduceProductQuantity(entry.getValue());
           }

           Receipt receipt = new Receipt(cashier, productsToSell, total);
           receipts.add(receipt);

            totalReceipts++;
            totalTurnover += receipt.getTotalSum();

           saveReceiptToFile(receipt);
           return receipt;
    }

    public void saveReceiptToFile(Receipt receipt){
        String fileName = "receipt_" + receipt.getNumber() + ".txt";
        try(PrintWriter writer = new PrintWriter(new FileWriter(fileName))){
            writer.println("====== RECEIPT #" + receipt.getNumber() + " ======");
            writer.println("Cashier: " + receipt.getCashier().getCashier_name() + " (ID: " + receipt.getCashier().getCashier_id() + ")");
            writer.println("Issued on: " + receipt.getTimestampOfReceipt());
            writer.println();
            writer.println("Products:");

            for(Map.Entry<Product, Integer> entry : receipt.getProductQuantityPriceInformation().entrySet()){
                Product product = entry.getKey();
                int quantity = entry.getValue();

                double unitPrice = calculateProductPrice(product, receipt.getTimestampOfReceipt().toLocalDate());
                double totalForProduct = unitPrice * quantity;

                writer.printf("- %s x %d @ %.2f = %.2f%n",
                        product.getName(), quantity, unitPrice, totalForProduct);
            }

            writer.println("----------------------------");
            writer.printf("TOTAL: %.2f%n", receipt.getTotalSum());
        }catch (IOException e){
            System.out.println("Error saving receipt to file: " + e.getMessage());
        }
        serializeReceipt(receipt);
    }

    public void serializeReceipt(Receipt receipt){
        try(ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream("receipt_" + receipt.getNumber() + ".ser"))){
                out.writeObject(receipt);
        }catch (IOException e){
            System.out.println("Error with serialization");
        }
    }

    public Receipt deserializeReceipt(int number) {
        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream("receipt_" + number + ".ser"))) {
            return (Receipt) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Грешка при десериализация: " + e.getMessage());
            return null;
        }
    }

    public double calculateTotalIncome(){
        return receipts.stream()
                .mapToDouble(Receipt::getTotalSum)
                .sum();
   }

   public double calculateTotalExpenses(){
        double salaries = cashiers.stream()
                .mapToDouble(Cashier::getMonthlySalary)
                .sum();

        return salaries + deliveryCosts;
   }

   public double calculateProfit(){
        return calculateTotalIncome() - calculateTotalExpenses();
   }

}
