package com.webapp.app_rest_api.model.enums;

public enum TypeOfFood {
    DAIRY_AND_EGG_PRODUCTS("Dairy and Egg Products"),
    SPICES_AND_HERBS("Spices and Herbs"),
    BABY_FOODS("Baby Foods"),
    FATS_AND_OIL("Fats and Oils"),
    POULTRY_PRODUCTS("Poultry Products"),
    SOUPS_AND_SAUCES("Soups and Sauces"),
    MEATS("Meats"),
    BREAKFAST_CEREALS("Breakfast Cereals"),
    FRUITS("Fruits"),
    PORK_PRODUCTS("Pork Products"),
    VEGETABLES("Vegetables"),
    NUTS_AND_SEEDS("Nuts and Seeds"),
    BEVERAGES("Beverages"),
    FISH("Fish"),
    BAKED_FOODS("Baked Foods"),
    SWEETS("Sweets"),
    BEANS_AND_LENTILS("Beans and Lentils"),
    FAST_FOODS("Fast Foods"),
    GRAINS_AND_PASTA("Grains and Pasta"),
    SNACKS("Snacks"),
    AMERICAN_INDIAN("American Indian"),
    RESTAURANT_FOODS("Restaurant Foods"),
    PREPARED_MEALS("Prepared Meals"),
    OTHER("Other");
    private final String title;
    TypeOfFood(String title){
        this.title = title;
    }
}
