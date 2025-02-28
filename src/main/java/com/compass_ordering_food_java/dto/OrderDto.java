package com.compass_ordering_food_java.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private int id;
    private int clientId;
    private List<Integer> dishes;
    private int totalPrice;
    private LocalDateTime dateOrder;
    private LocalDateTime dateDeliver;
    private boolean completed;
}
