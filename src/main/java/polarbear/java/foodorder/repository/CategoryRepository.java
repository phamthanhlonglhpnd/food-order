package polarbear.java.foodorder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import polarbear.java.foodorder.model.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    public List<Category> findCategoryByRestaurantId(Long id);
}
