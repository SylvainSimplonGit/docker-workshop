package co.simplon.restaurant.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Client {

    @Id
     @SequenceGenerator(name = "client_seq_id", allocationSize = 1)
     @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_seq_id")
//    @GeneratedValue(strategy = GenerationType.ENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<Review> reviews = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "visitors")
    private Set<Restaurant> visitedRestaurants = new HashSet<>();

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public Set<Restaurant> getVisitedRestaurants() {
        return visitedRestaurants;
    }
}
