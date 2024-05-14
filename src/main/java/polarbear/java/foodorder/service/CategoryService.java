package polarbear.java.foodorder.service;

import polarbear.java.foodorder.model.Category;

import java.util.List;

public interface CategoryService {
    public Category createCategory(String name, Long userId) throws Exception;

    public List<Category> findCategoryByRestaurantId(Long id) throws Exception;

    public Category findByCategoryId(Long id) throws Exception;
}
