package polarbear.java.foodorder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import polarbear.java.foodorder.model.IngredientsItem;

public interface IngredientItemRepository extends JpaRepository<IngredientsItem, Long> {
}
