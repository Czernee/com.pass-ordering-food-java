package com.compass_ordering_food_java.exceptions;

public class OrderNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 3;

    public OrderNotFoundException(String message) {
        super(message);
    }
}
