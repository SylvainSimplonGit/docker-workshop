package co.simplon.restaurant.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
public class Review {

    @Id
    @SequenceGenerator(name = "review_seq_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_seq_id")
//    @GeneratedValue(strategy = GenerationType.ENTITY)
    private Long id;

    @Column(nullable = false)
    @Min(value = 0)
    @Max(value = 5)
    private Integer note;

    private String comment;

    @ManyToOne
    private Client client;

    @JsonIgnore
    @ManyToOne
    private Restaurant restaurant;

    public Long getId() {
        return id;
    }

    public Integer getNote() {
        return note;
    }

    public String getComment() {
        return comment;
    }

    public Client getClient() {
        return client;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
