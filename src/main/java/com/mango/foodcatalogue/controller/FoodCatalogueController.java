package com.mango.foodcatalogue.controller;

import com.mango.foodcatalogue.dto.FoodCataloguePage;
import com.mango.foodcatalogue.dto.FoodItemDTO;
import com.mango.foodcatalogue.service.FoodCatalogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/foodcatalogue")
public class FoodCatalogueController {
    @Autowired
    FoodCatalogueService foodCatalogueService;

    @PostMapping("/addFoodItem")
    public ResponseEntity<FoodItemDTO> addFoodItem(@RequestBody FoodItemDTO foodItemDTO) {
        FoodItemDTO savedFoodItem =  foodCatalogueService.addFoodItem(foodItemDTO);
        return new ResponseEntity<>(savedFoodItem, HttpStatus.CREATED);
    }

    @GetMapping("/fetchRestaurantAndFoodItemById/{id}")
    public ResponseEntity<FoodCataloguePage> fetchRestaurantAndFoodItemById(@PathVariable Integer id) {
        FoodCataloguePage foodCatalogue = foodCatalogueService.foodCataloguePageDetails(id);
        return new ResponseEntity<>(foodCatalogue, HttpStatus.OK);
    }
}
