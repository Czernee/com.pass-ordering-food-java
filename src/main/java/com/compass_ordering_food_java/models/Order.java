package com.compass_ordering_food_java.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int clientId;

    private List<Integer> dishes;

    private int totalPrice;

    private LocalDateTime dateOrder;

    private LocalDateTime dateDeliver;

    private boolean completed;
}
