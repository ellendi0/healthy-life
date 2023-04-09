package com.webapp.app_rest_api.model;

import com.webapp.app_rest_api.model.enums.TypeOfMeal;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
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

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "meals")
    private Set<Food> food = new HashSet<>();

    public void addFood(Food food) {
        this.food.add(food);
        food.getMeals().add(this);
    }

    public void removeFood(Food food) {
        this.food.remove(food);
        food.getMeals().remove(this);
    }

}
