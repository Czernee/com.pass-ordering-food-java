package com.compass_ordering_food_java.repository;

import com.compass_ordering_food_java.models.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<Dish, Long> {
}
