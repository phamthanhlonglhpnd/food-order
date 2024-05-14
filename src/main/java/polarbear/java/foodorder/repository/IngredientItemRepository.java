package polarbear.java.foodorder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import polarbear.java.foodorder.model.IngredientsItem;

import java.util.List;

public interface IngredientItemRepository extends JpaRepository<IngredientsItem, Long> {
    List<IngredientsItem> findIngredientsItemByRestaurantId(Long id);
}
