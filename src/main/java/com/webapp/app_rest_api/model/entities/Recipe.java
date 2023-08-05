package com.webapp.app_rest_api.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.webapp.app_rest_api.model.entities.connection.FoodToRecipe;
import com.webapp.app_rest_api.model.entities.connection.RecipeToMeal;
import com.webapp.app_rest_api.model.enums.RecipeAccess;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Setter
@Getter
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
    private double numberOfCalories = 0.0;

    @Column
    private double weight = 0.0;

    @Column
    private double numberOfProtein = 0.0;

    @Column
    private double numberOfFat = 0.0;

    @Column
    private double numberOfCarbohydrate = 0.0;

    @Column
    private double numberOfSugar = 0.0;

    @Column
    private double numberOfFiber = 0.0;

    @Column(name = "recipe_access")
    @NonNull
    @Enumerated(EnumType.STRING)
    private RecipeAccess recipeAccess;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personal_info_id", nullable = false)
    private PersonalInfo personalInfo;

    @JsonIgnore
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<FoodToRecipe> food = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
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
