package com.webapp.app_rest_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.webapp.app_rest_api.model.enums.TypeOfFood;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@Entity
@AllArgsConstructor

@NoArgsConstructor
@Table(name = "food")

public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "type_of_food")
    @Enumerated(EnumType.STRING)
    private TypeOfFood typeOfFood;

    @Column(nullable = false)
    private double weight;

    @Column(nullable = false)
    private double numberOfCalories;

    @Column(nullable = false)
    private double numberOfProtein;

    @Column(nullable = false)
    private double numberOfFat;

    @Column(nullable = false)
    private double numberOfCarbohydrate;

    @Column
    private double numberOfSugar;

    @Column
    private double numberOfFiber;

    @JsonIgnore
    @ManyToMany(mappedBy = "food")
    private Set<Meal> meals = new HashSet<>();

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "food_recipe",
            joinColumns = {@JoinColumn(name = "food_id")},
            inverseJoinColumns = {@JoinColumn(name = "recipe_id")})
    private List<Recipe> recipe = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Food food)) return false;
        return name.equals(food.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
