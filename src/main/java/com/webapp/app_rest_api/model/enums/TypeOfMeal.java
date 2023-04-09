package com.webapp.app_rest_api.model.enums;

public enum TypeOfMeal {
    BREAKFAST("Breakfast"), LUNCH("Lunch"), EVENING_SNACKS("Evening snacks"), DINNER("Dinner");
    private final String title;

    TypeOfMeal(String title) {
        this.title = title;
    }
}
