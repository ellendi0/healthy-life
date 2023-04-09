package com.webapp.app_rest_api.model.enums;

public enum Activity {
    SEDENTARY, //little or no exercise
    LIGHTLY_ACTIVE, //light exercise or sports 1-3 days per week
    MODERATELY_ACTIVE, //moderate exercise or sports 3-5 days per week
    VERY_ACTIVE, //hard exercise or sports 6-7 days per week
    EXTRA_ACTIVE;//very hard exercise or sports, physical job or training twice a day

    public double getALF(){
        return switch (this) {
            case SEDENTARY -> 1.2;
            case LIGHTLY_ACTIVE -> 1.375;
            case MODERATELY_ACTIVE -> 1.55;
            case VERY_ACTIVE -> 1.75;
            case EXTRA_ACTIVE -> 1.9;
        };
    }
}
