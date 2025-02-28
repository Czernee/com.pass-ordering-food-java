package com.compass_ordering_food_java.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishDto {
    private int id;
    private String name;
    private String composition;
    private int price;
}
