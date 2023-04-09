package com.webapp.app_rest_api.model.enums;

public enum Goal {
    LOSE_WEIGHT,
    GAIN_WEIGHT,
    SAVE_WEIGHT;

    public double getCaloriesByGoal(){
        return switch (this){
            case GAIN_WEIGHT -> 0.1;
            case LOSE_WEIGHT -> -0.1;
            case SAVE_WEIGHT -> 1;
        };
    }

}
