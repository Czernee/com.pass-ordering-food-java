package com.compass_ordering_food_java.services.impl;

import com.compass_ordering_food_java.dto.CartDto;
import com.compass_ordering_food_java.exceptions.CartNotFoundException;
import com.compass_ordering_food_java.exceptions.DishNotFoundException;
import com.compass_ordering_food_java.models.Cart;
import com.compass_ordering_food_java.repository.CartRepository;
import com.compass_ordering_food_java.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;

@Service
public class CartServiceImpl implements CartService {

    private CartRepository cartRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public CartDto getCartByClientId(int clientId) {
        Cart cart = cartRepository.findByClientId(clientId).orElseThrow(() -> new CartNotFoundException("Cart with this client id can't be found"));
        return mapToDto(cart);
    }

    @Override
    public CartDto addItem(int clientId, int dishId) {
        Cart cart = cartRepository.findByClientId(clientId).orElseGet(() -> createNewCart(clientId));

        cart.getDishes().add(dishId);
        Cart savedCart = cartRepository.save(cart);

        return mapToDto(savedCart);
    }

    @Override
    public CartDto deleteItem(int clientId, int dishId) {
        Cart cart = cartRepository.findByClientId(clientId).orElseThrow(() -> new CartNotFoundException("Cart with this client id can't be found"));

        if (cart.getDishes().remove((Integer) dishId)) {
            Cart savedCart = cartRepository.save(cart);
            return mapToDto(savedCart);
        } else {
            throw new DishNotFoundException("Dish with this id not found");
        }
    }

    @Override
    public CartDto clearCart(int clientId) {
        Cart cart = cartRepository.findByClientId(clientId).orElseThrow(() -> new CartNotFoundException("Cart with this client id can't be found"));

        cart.setDishes(new ArrayList<>());
        Cart savedCart = cartRepository.save(cart);

        return mapToDto(savedCart);
    }

    public CartDto mapToDto(Cart cart) {
        CartDto cartDto = new CartDto();
        cartDto.setId(cart.getId());
        cartDto.setClientId(cart.getClientId());
        cartDto.setDishes(cart.getDishes());
        return cartDto;
    }

    private Cart createNewCart(int clientId) {
        Cart newCart = new Cart();
        newCart.setClientId(clientId);
        newCart.setDishes(new ArrayList<>(Collections.singletonList(0)));
        newCart.getDishes().clear();
        newCart.getDishes().add(0);
        return newCart;
    }
}
