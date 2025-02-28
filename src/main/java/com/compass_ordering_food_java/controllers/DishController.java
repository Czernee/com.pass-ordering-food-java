package com.compass_ordering_food_java.controllers;

import com.compass_ordering_food_java.dto.DishDto;
import com.compass_ordering_food_java.dto.DishResponse;
import com.compass_ordering_food_java.services.DishService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class DishController {

    private DishService dishService;

    @Autowired
    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @PostMapping("dishes/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DishDto> createDish(@RequestBody DishDto dishDto) {
        return new ResponseEntity<>(dishService.createDish(dishDto), HttpStatus.CREATED);
    }

    @GetMapping("dishes")
    public ResponseEntity<DishResponse> getDishes(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        return new ResponseEntity<>(dishService.getAllDishes(pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping("dishes/{id}")
    public ResponseEntity<DishDto> dishDetail(@PathVariable int id) {
        return new ResponseEntity<>(dishService.getDishById(id), HttpStatus.OK);
    }

    @PutMapping("dishes/{id}/update")
    public ResponseEntity<DishDto> updateDish(@PathVariable("id") int dishId, @RequestBody DishDto dishDto) {
        return new ResponseEntity<>(dishService.updateDish(dishId, dishDto), HttpStatus.OK);
    }

    @DeleteMapping("dishes/{id}/delete")
    public ResponseEntity<String> deleteDish(@PathVariable(value = "id") int dishId) {
        dishService.deleteDishById(dishId);
        return new ResponseEntity<>("Dish deleted", HttpStatus.OK);
    }
}
