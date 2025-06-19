package org.nbu.exception;

public class InsufficientQuantityException extends RuntimeException{
    public InsufficientQuantityException(String productName, double requestedAmount, double availableAmount ){
        super("Insufficient amount of: " + productName + ", requested: " + requestedAmount + ", available: " + availableAmount);
    }
}
