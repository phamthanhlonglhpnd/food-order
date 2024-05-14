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
    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long restaurantId) throws Exception {
        return ingredientCategoryRepository.findIngredientCategoryByRestaurantId(restaurantId);
    }

    @Override
    public IngredientsItem createIngredientsItem(Long restaurantId, String ingredientName, Long categoryId) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        IngredientCategory category = findIngredientCategoryById(categoryId);

        IngredientsItem item = new IngredientsItem();
        item.setName(ingredientName);
        item.setRestaurant(restaurant);
        item.setCategory(category);

        IngredientsItem ingredientsItem = ingredientItemRepository.save(item);
        category.getIngredients().add(ingredientsItem);
        return ingredientsItem;
    }

    @Override
    public List<IngredientsItem> findRestaurantIngredients(Long restaurantId) {
        return ingredientItemRepository.findIngredientsItemByRestaurantId(restaurantId);
    }

    @Override
    public IngredientsItem updateStoke(Long id) throws Exception {
        Optional<IngredientsItem> ingredientsItem = ingredientItemRepository.findById(id);
        if(ingredientsItem.isEmpty()) {
            throw new Exception("Ingredient item is not found");
        }
        IngredientsItem items = ingredientsItem.get();
        items.setInStoke(!items.isInStoke());
        return ingredientItemRepository.save(items);
    }
}
