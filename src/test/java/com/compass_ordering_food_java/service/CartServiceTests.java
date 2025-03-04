package com.compass_ordering_food_java.service;

import com.compass_ordering_food_java.dto.CartDto;
import com.compass_ordering_food_java.models.Cart;
import com.compass_ordering_food_java.repository.CartRepository;
import com.compass_ordering_food_java.services.impl.CartServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CarServiceTests {

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private CartServiceImpl cartService;

    @Test
    public void CartService_GetCartByClientId_ReturnsCartDto() {
        Cart cart = Cart.builder()
                .clientId(1)
                .dishes(Arrays.asList(1, 2, 3))
                .build();

        cartRepository.save(cart);

        CartDto cartDto = cartService.getCartByClientId(cart.getClientId());

        Assertions.assertThat(cartDto).isNotNull();
        Assertions.assertThat(cartDto.getClientId()).isEqualTo(1);
        Assertions.assertThat(cartDto.getDishes()).isEqualTo(Arrays.asList(1, 2, 3));
    }
}
