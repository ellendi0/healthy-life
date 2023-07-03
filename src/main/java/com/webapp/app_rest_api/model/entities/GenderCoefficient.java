package com.webapp.app_rest_api.model.entities;

import com.webapp.app_rest_api.model.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "gender_coefficient")
public class GenderCoefficient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "bmr_base")
    private double bmr_base;

    @Column(name = "bmr_weight_coefficient")
    private double bmr_weight_coefficient;

    @Column(name = "bmr_height_coefficient")
    private double bmr_height_coefficient;

    @Column(name = "bmr_age_coefficient")
    private double bmr_age_coefficient;
}
