package co.simplon.restaurant.repository;

import co.simplon.restaurant.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
