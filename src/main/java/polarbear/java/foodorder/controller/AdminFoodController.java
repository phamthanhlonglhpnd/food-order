package polarbear.java.foodorder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import polarbear.java.foodorder.model.Food;
import polarbear.java.foodorder.model.Restaurant;
import polarbear.java.foodorder.request.CreateFoodRequest;
import polarbear.java.foodorder.response.MessageResponse;
import polarbear.java.foodorder.service.FoodService;
import polarbear.java.foodorder.service.RestaurantService;

@RestController
@RequestMapping("/api/admin/foods")
public class AdminFoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/create")
    public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest req) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(req.getRestaurantId());
        Food food = foodService.createFood(req, req.getCategory(), restaurant);
        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageResponse> deleteFood(@PathVariable Long id) throws Exception {
        foodService.deleteFood(id);
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMessage("Delete food successfully");
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @PutMapping("/update-food-availability/{id}")
    public ResponseEntity<Food> updateFoodAvailability(@PathVariable Long id) throws Exception {
        Food food = foodService.updateAvailabilityStatus(id);
        return new ResponseEntity<>(food, HttpStatus.OK);
    }
}
