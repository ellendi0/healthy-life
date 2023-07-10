package com.webapp.app_rest_api.model.entities;

import com.webapp.app_rest_api.model.enums.Activity;
import com.webapp.app_rest_api.model.enums.Goal;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "diet")
public class Diet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "number_of_daily_calories")
    private double numberOfDailyCalories;

    @Column(nullable = false, name = "number_of_protein")
    private double numberOfProtein;

    @Column(nullable = false, name = "number_of_fat")
    private double numberOfFat;

    @Column(nullable = false, name = "number_of_carbohydrate")
    private double numberOfCarbohydrate;

    @Column(nullable = false, name = "number_of_sugar")
    private double numberOfSugar;

    @Column(nullable = false, name = "number_of_fiber")
    private double numberOfFiber;

    @OneToOne(mappedBy = "diet")
    private PersonalInfo personalInfo;
}
