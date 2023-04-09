package com.webapp.app_rest_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
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

    @ManyToMany(mappedBy = "recipe")
    private List<Food> foods = new ArrayList<>();
}
