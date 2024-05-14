package polarbear.java.foodorder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import polarbear.java.foodorder.model.IngredientCategory;
import polarbear.java.foodorder.model.IngredientsItem;
import polarbear.java.foodorder.model.Restaurant;
import polarbear.java.foodorder.repository.IngredientCategoryRepository;
import polarbear.java.foodorder.repository.IngredientItemRepository;
import polarbear.java.foodorder.service.IngredientService;
import polarbear.java.foodorder.service.RestaurantService;

import java.util.List;
import java.util.Optional;

public class IngredientServiceImpl implements IngredientService {

    @Autowired
    private IngredientItemRepository ingredientItemRepository;

    @Autowired
    private IngredientCategoryRepository ingredientCategoryRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Override
    public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        IngredientCategory ingredientCategory = new IngredientCategory();
        ingredientCategory.setName(name);
        ingredientCategory.setRestaurant(restaurant);
        return ingredientCategoryRepository.save(ingredientCategory);
    }

    @Override
    public IngredientCategory findIngredientCategoryById(Long id) throws Exception {
        Optional<IngredientCategory> ingredientCategory = ingredientCategoryRepository.findById(id);
        if(ingredientCategory.isEmpty()) {
            throw new Exception("Ingredient Category is not found");
        }
        return ingredientCategory.get();
    }

    @Override
    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception {
        return ingredientCategoryRepository.findIngredientCategoryByRestaurantId(id);
    }

    @Override
    public IngredientsItem createIngredientsItem(Long restaurantId, String ingredientName, Long categoryId) throws Exception {

        return null;
    }

    @Override
    public List<IngredientsItem> findRestaurantIngredients(Long restaurantId) {
        return null;
    }

    @Override
    public IngredientsItem updateStock(Long id) throws Exception {
        return null;
    }
}
