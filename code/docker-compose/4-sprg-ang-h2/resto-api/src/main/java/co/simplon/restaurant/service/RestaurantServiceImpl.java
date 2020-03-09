package co.simplon.restaurant.service;

import co.simplon.restaurant.model.Restaurant;
import co.simplon.restaurant.model.Review;
import co.simplon.restaurant.repository.RestaurantRepository;
import co.simplon.restaurant.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private RestaurantRepository restaurantRepository;
    private ReviewRepository reviewRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository,
                                 ReviewRepository reviewRepository) {
        this.restaurantRepository = restaurantRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Restaurant> getRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> getFilteredRestaurants(Integer lowestNote, Integer highestNote) {
        return restaurantRepository.findAllByReviewsNoteBetween(lowestNote, highestNote);
    }

    @Override
    public Review createReview(Long restaurantId, Review reviewToCreate) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);

        if (restaurant.isPresent()) {
            reviewToCreate.setRestaurant(restaurant.get());
            return reviewRepository.save(reviewToCreate);
        } else {
            // On devrait renvoyer une exception
            return null;
        }
    }

    @Override
    public boolean deleteReview(Long restaurantId, Long reviewId) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);

        if (restaurant.isPresent()) {
            List<Review> restReviews = restaurant.get().getReviews();

            boolean reviewExisting = false;
            for (Review restReview : restReviews) {
                if (restReview.getId() == reviewId) {
                    reviewExisting = true;
                    break;
                }
            }
            if (reviewExisting) {
                reviewRepository.deleteById(reviewId);
                return true;
            } else {
                return false;
            }
        } else {
            // On devrait renvoyer une exception
            return false;
        }
    }
}
