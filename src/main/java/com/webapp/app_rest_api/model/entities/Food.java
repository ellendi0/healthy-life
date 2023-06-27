package com.webapp.app_rest_api.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.webapp.app_rest_api.model.entities.connection.FoodToMeal;
import com.webapp.app_rest_api.model.entities.connection.FoodToRecipe;
import com.webapp.app_rest_api.model.enums.TypeOfFood;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "food")
//ставити всюди назви колонок
public class Food{
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
    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<FoodToMeal> meal = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<FoodToRecipe> recipe = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Food)) return false;
        Food food = (Food) o;
        return Objects.equals(id, food.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", typeOfFood=" + typeOfFood +
                ", weight=" + weight +
                ", numberOfCalories=" + numberOfCalories +
                ", numberOfProtein=" + numberOfProtein +
                ", numberOfFat=" + numberOfFat +
                ", numberOfCarbohydrate=" + numberOfCarbohydrate +
                ", numberOfSugar=" + numberOfSugar +
                ", numberOfFiber=" + numberOfFiber +
                '}';
    }

    // add custom toString
}
