package com.compass_ordering_food_java.exceptions;

public class CartNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1;

    public CartNotFoundException(String message) {
        super(message);
    }
}
