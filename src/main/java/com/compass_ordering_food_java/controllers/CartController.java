package com.compass_ordering_food_java.controllers;

import com.compass_ordering_food_java.dto.CartDto;
import com.compass_ordering_food_java.dto.DishDto;
import com.compass_ordering_food_java.dto.DishResponse;
import com.compass_ordering_food_java.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class CartController {

    private CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("carts/{id}")
    public ResponseEntity<CartDto> getCartByClientId(@PathVariable(value = "id") int clientId) {
        return new ResponseEntity<>(cartService.getCartByClientId(clientId), HttpStatus.OK);
    }

    @PutMapping("carts/{clientId}/addItem/{dishId}")
    public ResponseEntity<CartDto> addItem(@PathVariable(value = "clientId") int clientId, @PathVariable(value = "dishId") int dishId) {
        return new ResponseEntity<>(cartService.addItem(clientId, dishId), HttpStatus.OK);
    }

    @PutMapping("carts/{clientId}/deleteItem/{dishId}")
    public ResponseEntity<CartDto> deleteItem(@PathVariable(value = "clientId") int clientId, @PathVariable(value = "dishId") int dishId) {
        return new ResponseEntity<>(cartService.deleteItem(clientId, dishId), HttpStatus.OK);
    }

    @PostMapping("carts/{clientId}/clear")
    public ResponseEntity<CartDto> clearCartByClientId(@PathVariable(value = "clientId") int clientId) {
        return new ResponseEntity<>(cartService.clearCart(clientId), HttpStatus.OK);
    }
}

