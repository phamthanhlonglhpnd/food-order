package polarbear.java.foodorder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import polarbear.java.foodorder.model.IngredientCategory;

import java.util.List;

public interface IngredientCategoryRepository extends JpaRepository<IngredientCategory, Long> {

    List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id);
}
