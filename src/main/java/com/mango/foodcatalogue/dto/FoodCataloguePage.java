package com.mango.foodcatalogue.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodCataloguePage {
    /*private int pageNumber;
    private int pageSize;
    private int totalItems;
    private int totalPages;*/
    private List<FoodItemDTO> foodItems;
    private RestaurantDTO restaurant;

}
