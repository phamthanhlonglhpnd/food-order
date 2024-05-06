package polarbear.java.foodorder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import polarbear.java.foodorder.model.Restaurant;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Query("SELECT r FROM Restaurant WHERE lower(r.name) LIKE lower(concat('%', :query, '%')) " +
            "OR lower(r.cuisineType) LIKE lower(concat('%', :query, '%'))")
    List<Restaurant> findBySearchQuery(String query);

    Restaurant findByOwnerId(Long userId);
}
