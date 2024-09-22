package com.mango.foodcatalogue.service;

import com.mango.foodcatalogue.dto.FoodCataloguePage;
import com.mango.foodcatalogue.dto.FoodItemDTO;
import com.mango.foodcatalogue.dto.RestaurantDTO;
import com.mango.foodcatalogue.entity.FoodItem;
import com.mango.foodcatalogue.mapper.FoodItemMapper;
import com.mango.foodcatalogue.repo.FoodItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodCatalogueService {
    @Autowired
    FoodItemRepo foodItemRepo;

    @Autowired
    RestTemplate restTemplate;
    public FoodItemDTO addFoodItem(FoodItemDTO foodItemDTO) {
         FoodItem foodItem = foodItemRepo.save(FoodItemMapper.INSTANCE.mapFoodItemDtoToFoodItemEntity(foodItemDTO));
         return FoodItemMapper.INSTANCE.mapFoodItemEntityToFoodItemDto(foodItem);
    }

    public FoodCataloguePage foodCataloguePageDetails(Integer restaurantId) {
        // food items list
        List<FoodItem> foodItems = foodItemList(restaurantId);
        List<FoodItemDTO> foodItemDtos = foodItems.stream().map(foodItem -> FoodItemMapper.INSTANCE.mapFoodItemEntityToFoodItemDto(foodItem)).collect(Collectors.toList());
        // restaurant details from restaurant microservice
        RestaurantDTO restaurant = fetchRestaurantDetailsFromRestaurantMS(restaurantId);
        return createFoodCataloguePage(foodItemDtos, restaurant);

    }

    private FoodCataloguePage createFoodCataloguePage(List<FoodItemDTO> foodItems, RestaurantDTO restaurant) {
        FoodCataloguePage foodCataloguePage = new FoodCataloguePage();
        foodCataloguePage.setFoodItems(foodItems);
        foodCataloguePage.setRestaurant(restaurant);
        return foodCataloguePage;
    }

    private RestaurantDTO fetchRestaurantDetailsFromRestaurantMS(Integer restaurantId) {
        return restTemplate.getForObject("http://RESTAURANT-SERVICE/restaurant/fetchRestaurantById/" + restaurantId, RestaurantDTO.class);

    }
    private List<FoodItem> foodItemList(Integer restaurantId) {
        return foodItemRepo.findByRestaurantId(restaurantId);
    }
}
