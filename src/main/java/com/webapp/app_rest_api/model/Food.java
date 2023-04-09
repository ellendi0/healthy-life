package com.webapp.app_rest_api.model;

import com.webapp.app_rest_api.model.enums.TypeOfFood;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "meal_food",
            joinColumns = {@JoinColumn(name = "food_id")},
            inverseJoinColumns = {@JoinColumn(name = "meal_id")})
    private Set<Meal> meals = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "recipe_food",
            joinColumns = {@JoinColumn(name = "food_id")},
            inverseJoinColumns = {@JoinColumn(name = "recipe_id")})
    private List<Recipe> recipe = new ArrayList<>();



}
