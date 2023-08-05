package com.webapp.app_rest_api.model.entities;

import com.webapp.app_rest_api.model.enums.Activity;
import com.webapp.app_rest_api.model.enums.Gender;
import com.webapp.app_rest_api.model.enums.Goal;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "personal_info")
public class PersonalInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "height")
    private Double height;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "goal")
    @Enumerated(EnumType.STRING)
    private Goal goal;

    @Column(name = "activity")
    @Enumerated(EnumType.STRING)
    private Activity activity;

    @OneToOne(mappedBy = "personalInfo")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "diet_id", referencedColumnName = "id")
    private Diet diet;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personalInfo")
    private Set<DayDiet> dayDiets = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personalInfo")
    private Set<Post> posts = new HashSet<>();
}
