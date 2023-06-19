package com.webapp.app_rest_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "recipe")

public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NonNull
    private String name;
    @Column
    private double numberOfCalories;
    @Column
    private double weight;
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

    @JsonIgnore
    @OneToMany(mappedBy = "recipe")
    private Set<FoodToRecipe> food = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "recipe")
    private Set<RecipeToMeal> meal = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recipe)) return false;
        Recipe recipe = (Recipe) o;
        return Objects.equals(id, recipe.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
