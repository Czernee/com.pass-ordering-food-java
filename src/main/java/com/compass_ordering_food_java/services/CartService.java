package com.compass_ordering_food_java.services;

import com.compass_ordering_food_java.dto.CartDto;

public interface CartService {
    CartDto getCartByClientId(int clientId);
    CartDto addItem(int clientId, int dishId);
    CartDto deleteItem(int clientId, int dishId);
    CartDto  clearCart(int clientId);
}
