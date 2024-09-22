package com.mango.foodcatalogue.mapper;

import com.mango.foodcatalogue.dto.FoodItemDTO;
import com.mango.foodcatalogue.entity.FoodItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FoodItemMapper {
    FoodItemMapper INSTANCE = Mappers.getMapper(FoodItemMapper.class);
    FoodItemDTO mapFoodItemEntityToFoodItemDto(FoodItem foodItem);
    FoodItem mapFoodItemDtoToFoodItemEntity(FoodItemDTO foodItemDto);
}
