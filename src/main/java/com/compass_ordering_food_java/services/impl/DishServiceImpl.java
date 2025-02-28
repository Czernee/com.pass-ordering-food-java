package com.compass_ordering_food_java.services.impl;

import com.compass_ordering_food_java.dto.CartDto;
import com.compass_ordering_food_java.dto.DishDto;
import com.compass_ordering_food_java.dto.DishResponse;
import com.compass_ordering_food_java.exceptions.DishNotFoundException;
import com.compass_ordering_food_java.models.Cart;
import com.compass_ordering_food_java.models.Dish;
import com.compass_ordering_food_java.repository.DishRepository;
import com.compass_ordering_food_java.services.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    private DishRepository dishRepository;

    @Autowired
    public DishServiceImpl(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @Override
    public DishDto createDish(DishDto dishDto) {
        Dish dish = new Dish();
        dish.setName(dishDto.getName());
        dish.setComposition(dishDto.getComposition());
        dish.setPrice(dishDto.getPrice());

        Dish newDish = dishRepository.save(dish);

        DishDto dishResponse = new DishDto();
        dishResponse.setId(newDish.getId());
        dishResponse.setName(newDish.getName());
        dishResponse.setComposition(newDish.getComposition());
        dishResponse.setPrice(newDish.getPrice());
        return dishResponse;
    }

    @Override
    public DishResponse getAllDishes(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Dish> dishes = dishRepository.findAll(pageable);
        List<Dish> listOfDishes = dishes.getContent();
        List<DishDto> content = listOfDishes.stream().map(this::mapToDto).toList();

        DishResponse dishResponse = new DishResponse();
        dishResponse.setContent(content);
        dishResponse.setPageNo(dishes.getNumber());
        dishResponse.setPageSize(dishes.getSize());
        dishResponse.setTotalElements(dishes.getTotalElements());
        dishResponse.setTotalPages(dishes.getTotalPages());
        dishResponse.setLast(dishes.isLast());

        return dishResponse;
    }

    @Override
    public DishDto getDishById(int dishId) {
        Dish dish = dishRepository.findById((long) dishId).orElseThrow(() ->  new DishNotFoundException("Dish with this id can't be found"));
        return mapToDto(dish);
    }

    @Override
    public DishDto updateDish(int dishId, DishDto dishDto) {
        Dish dish = dishRepository.findById((long) dishId).orElseThrow(() ->  new DishNotFoundException("Dish with this id can't be found"));

        dish.setName(dishDto.getName());
        dish.setComposition(dishDto.getComposition());
        dish.setPrice(dishDto.getPrice());

        Dish updatedDish = dishRepository.save(dish);
        return mapToDto(updatedDish);
    }

    @Override
    public void deleteDishById(int dishId) {
        Dish dish = dishRepository.findById((long) dishId).orElseThrow(() ->  new DishNotFoundException("Dish with this id can't be found"));
        dishRepository.delete(dish);
    }

    public DishDto mapToDto(Dish dish) {
        DishDto dishDto = new DishDto();
        dishDto.setId(dish.getId());
        dishDto.setName(dish.getName());
        dishDto.setComposition(dish.getComposition());
        dishDto.setPrice(dish.getPrice());
        return dishDto;
    }
}
