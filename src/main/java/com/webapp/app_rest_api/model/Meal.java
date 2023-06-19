package com.webapp.app_rest_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.webapp.app_rest_api.model.enums.TypeOfMeal;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.util.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "meal")

public class Meal{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeOfMeal typeOfMeal;

    @Column
    private double numberOfCalories;

    @Column
    private double numberOfProtein;

    @Column
    private double numberOfFat;

    @Column
    private double numberOfCarbohydrate;

    @Column
    private double numberOfSugar;

    @Column
    private double numberOfFiber;

    @Column
    private double weight;

//    @JsonIgnore
//    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "meals")
//    private Set<Food> food = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "meal")
    private Set<FoodToMeal> food = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "meal")
    private Set<RecipeToMeal> recipe = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Meal meal)) return false;
        return id.equals(meal.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
