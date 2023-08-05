package com.webapp.app_rest_api.model.entities.connection;

import com.webapp.app_rest_api.model.entities.Meal;
import com.webapp.app_rest_api.model.entities.Recipe;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "recipe_to_meal")
public class RecipeToMeal {

    @Id
    @GeneratedValue
    private Long recipeToMealKeyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meal_id")
    private Meal meal;

    @Column(name = "weight")
    double weight;

    public RecipeToMeal(Recipe recipe, Meal meal, double weight) {
        this.recipe = recipe;
        this.meal = meal;
        this.weight = weight;
    }
}
