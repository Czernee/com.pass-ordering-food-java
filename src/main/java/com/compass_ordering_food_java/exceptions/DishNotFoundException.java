package com.compass_ordering_food_java.exceptions;

public class DishNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 2;

    public DishNotFoundException(String message) {
        super(message);
    }
}
