package com.webapp.app_rest_api.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "day_diet")
public class DayDiet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "date")
    private LocalDate date;

    @Column(nullable = false, name = "number_of_daily_calories")
    private double totalNumberOfDailyCalories;

    @Column(nullable = false, name = "number_of_protein")
    private double totalNumberOfProtein;

    @Column(nullable = false, name = "number_of_fat")
    private double totalNumberOfFat;

    @Column(nullable = false, name = "number_of_carbohydrate")
    private double totalNumberOfCarbohydrate;

    @Column(nullable = false, name = "number_of_sugar")
    private double totalNumberOfSugar;

    @Column(nullable = false, name = "number_of_fiber")
    private double totalNumberOfFiber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personal_info_id", nullable = false)
    private PersonalInfo personalInfo;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "dayDiet")
    private List<Meal> meals = new ArrayList<>();
}
