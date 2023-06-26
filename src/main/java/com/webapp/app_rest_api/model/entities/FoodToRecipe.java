package com.webapp.app_rest_api.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "food_to_recipe")
public class FoodToRecipe {

    @Id
    @GeneratedValue
    private Long foodToRecipeId;

    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @Column(name = "weight")
    double weight;

    public FoodToRecipe(Food food, Recipe recipe, double weight) {
        this.food = food;
        this.recipe = recipe;
        this.weight = weight;
    }
}
