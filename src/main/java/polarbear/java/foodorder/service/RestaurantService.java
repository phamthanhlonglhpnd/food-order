package polarbear.java.foodorder.service;

import polarbear.java.foodorder.dto.RestaurantDto;
import polarbear.java.foodorder.model.Restaurant;
import polarbear.java.foodorder.model.User;
import polarbear.java.foodorder.request.CreateRestaurantRequest;

import java.util.List;

public interface RestaurantService {
    public Restaurant createRestaurant(CreateRestaurantRequest req, User user);

    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updateRestaurant) throws Exception;

    public void deleteRestaurant(Long restaurantId) throws Exception;

    public List<Restaurant> getAllRestaurants();

    public List<Restaurant> searchRestaurant();

    public Restaurant findRestaurantById(Long id) throws Exception;

    public Restaurant getRestaurantByUserId(Long userId) throws Exception;

    public RestaurantDto addToFavorites(Long restaurantId, User user) throws Exception;

    public Restaurant updateRestaurantStatus(Long id) throws Exception;
}
