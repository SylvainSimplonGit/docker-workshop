package co.simplon.restaurant.repository;

import co.simplon.restaurant.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Query("SELECT DISTINCT restaurant FROM Restaurant restaurant JOIN restaurant.reviews reviews where reviews.note >= :lowest and reviews.note <= :highest")
    List<Restaurant> findAllByReviewsNoteBetween(Integer lowest, Integer highest);

}
