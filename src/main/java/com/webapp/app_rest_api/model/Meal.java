package com.webapp.app_rest_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.webapp.app_rest_api.model.enums.TypeOfMeal;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "meal")

public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
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

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "meal_food",
            joinColumns = @JoinColumn(name = "meal_id"),
            inverseJoinColumns = @JoinColumn(name = "food_id")
    )
    private Set<Food> food = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "meal_recipe",
            joinColumns = @JoinColumn(name = "meal_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id")
    )
    private List<Recipe> recipe = new ArrayList<>();

    public void addFood(Food food) {
        this.food.add(food);
        food.getMeals().add(this);
    }

    public void addRecipe(Recipe recipe) {
        this.recipe.add(recipe);
        recipe.getMeals().add(this);
    }

    public void removeFood(Food food) {
        this.food.remove(food);
        food.getMeals().remove(this);
    }

    public void removeRecipe(Recipe recipe) {
        this.recipe.remove(recipe);
        recipe.getMeals().remove(this);
    }
}
