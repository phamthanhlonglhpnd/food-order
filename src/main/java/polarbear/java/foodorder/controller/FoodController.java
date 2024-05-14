package polarbear.java.foodorder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import polarbear.java.foodorder.model.Food;
import polarbear.java.foodorder.service.FoodService;

import java.util.List;

@RestController
@RequestMapping("/api/foods")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @GetMapping("/search")
    public ResponseEntity<List<Food>> searchFoods(@RequestParam String name) {
        List<Food> foods = foodService.searchFood(name);
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }

    @GetMapping("/restaurant-foods/{restaurantId}")
    public ResponseEntity<List<Food>> getRestaurantFoods(
            @PathVariable Long restaurantId,
            @RequestParam boolean vegetarian,
            @RequestParam boolean nonVeg,
            @RequestParam boolean seasonal,
            @RequestParam(required = false) String food_category
    ) {
        List<Food> foods = foodService.getRestaurantsFood(restaurantId, vegetarian, nonVeg, seasonal, food_category);
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }
}
