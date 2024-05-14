package polarbear.java.foodorder.service;

import polarbear.java.foodorder.model.IngredientCategory;
import polarbear.java.foodorder.model.IngredientsItem;

import java.util.List;

public interface IngredientService {
    public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception;

    public IngredientCategory findIngredientCategoryById(Long id) throws Exception;

    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception;

    public IngredientsItem createIngredientsItem(Long restaurantId, String ingredientName, Long categoryId) throws Exception;

    public List<IngredientsItem> findRestaurantIngredients(Long restaurantId);

    public IngredientsItem updateStock(Long id) throws Exception;
}
