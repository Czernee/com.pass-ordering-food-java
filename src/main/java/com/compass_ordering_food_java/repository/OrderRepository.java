package com.compass_ordering_food_java.repository;

import com.compass_ordering_food_java.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
