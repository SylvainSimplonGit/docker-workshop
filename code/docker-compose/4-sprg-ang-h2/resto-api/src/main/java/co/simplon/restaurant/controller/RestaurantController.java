package co.simplon.restaurant.controller;

import co.simplon.restaurant.model.Restaurant;
import co.simplon.restaurant.model.Review;
import co.simplon.restaurant.service.RestaurantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
@CrossOrigin("*")
public class RestaurantController {

    private RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public List<Restaurant> getRestaurants() {
        return restaurantService.getRestaurants();
    }

    @GetMapping("/filter")
    public List<Restaurant> getFilteredRestaurants(@RequestParam Integer lowestNote, @RequestParam Integer highestNote) {
        return restaurantService.getFilteredRestaurants(lowestNote, highestNote);
    }

    @PostMapping("/{restaurantId}/reviews")
    public ResponseEntity<Review> addReviewToRestaurant(@PathVariable Long restaurantId, @RequestBody Review reviewToAdd) {
        Review createdReview = restaurantService.createReview(restaurantId, reviewToAdd);
        if (createdReview != null) {
            return ResponseEntity.ok(createdReview);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{restaurantId}/reviews/{reviewIdToDelete}")
    public ResponseEntity<Review> deleteReview(@PathVariable Long restaurantId, @PathVariable Long reviewIdToDelete) {
        boolean isDeletionPossible = restaurantService.deleteReview(restaurantId, reviewIdToDelete);
        if (isDeletionPossible) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
