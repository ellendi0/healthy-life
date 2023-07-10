package com.webapp.app_rest_api.model.entities;

import com.webapp.app_rest_api.model.enums.Activity;
import com.webapp.app_rest_api.model.enums.Gender;
import com.webapp.app_rest_api.model.enums.Goal;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "personal_info")
public class PersonalInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "weight")
    private Double weight;

    @Column(nullable = false, name = "height")
    private Double height;

    @Column(nullable = false, name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(nullable = false, name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false, name = "goal")
    @Enumerated(EnumType.STRING)
    private Goal goal;

    @Column(nullable = false, name = "activity")
    @Enumerated(EnumType.STRING)
    private Activity activity;

    @OneToOne(mappedBy = "personalInfo")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "diet_id", referencedColumnName = "id")
    private Diet diet;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "personalInfo")
    private List<DayDiet> dayDiets = new ArrayList<>();
}
