package com.webapp.app_rest_api.model.entities.connection;

import com.webapp.app_rest_api.model.entities.Food;
import com.webapp.app_rest_api.model.entities.Meal;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "food_to_meal")

public class FoodToMeal {

    @Id
    @GeneratedValue
    private Long foodToMealId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    private Food food;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meal_id")
    private Meal meal;

    @Column(name = "weight")
    double weight;

    public FoodToMeal(Food food, Meal meal, double weight) {
        this.food = food;
        this.meal = meal;
        this.weight = weight;
    }
}
