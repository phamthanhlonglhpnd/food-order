package polarbear.java.foodorder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import polarbear.java.foodorder.model.Category;
import polarbear.java.foodorder.model.Restaurant;
import polarbear.java.foodorder.repository.CategoryRepository;
import polarbear.java.foodorder.service.CategoryService;
import polarbear.java.foodorder.service.RestaurantService;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Override
    public Category createCategory(String name, Long userId) throws Exception {
        Restaurant restaurant = restaurantService.getRestaurantByUserId(userId);
        Category category = new Category();
        category.setName(name);
        category.setRestaurant(restaurant);
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findCategoryByRestaurantId(Long id) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(id);
        return categoryRepository.findCategoryByRestaurantId(restaurant.getId());
    }

    @Override
    public Category findByCategoryId(Long id) throws Exception {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isEmpty()) {
            throw new Exception("Category is not found with id = " + id);
        }
        return category.get();
    }
}
