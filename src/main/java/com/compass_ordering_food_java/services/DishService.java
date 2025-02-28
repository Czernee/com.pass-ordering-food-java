package com.compass_ordering_food_java.services;

import com.compass_ordering_food_java.dto.DishDto;
import com.compass_ordering_food_java.dto.DishResponse;

public interface DishService {
    DishDto createDish(DishDto dishDto);
    DishResponse getAllDishes(int pageNo, int pageSize);
    DishDto getDishById(int id);
    DishDto updateDish(int dishId, DishDto dishDto);
    void deleteDishById(int dishId);
}
