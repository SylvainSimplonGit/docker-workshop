package co.simplon.restaurant.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Restaurant {

    @Id
    @SequenceGenerator(name = "restaurant_seq_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "restaurant_seq_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer seats;

    @Enumerated(EnumType.STRING)
    private RestaurantType restaurantType;

    @OneToMany(mappedBy = "restaurant")
    private List<Review> reviews = new ArrayList<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "restaurants_visits",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "client_id"))
    private Set<Client> visitors = new HashSet<>();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getSeats() {
        return seats;
    }

    public RestaurantType getRestaurantType() {
        return restaurantType;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public Set<Client> getVisitors() {
        return visitors;
    }
}
