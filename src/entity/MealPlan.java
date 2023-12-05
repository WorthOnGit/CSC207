package entity;

import java.util.List;
public class MealPlan {
    private List<Meal> meals;
    private NutritionalInfo nutritionalInfo;
    private List<String> dietLabels;
    private List<String> healthLabels;
    private List<String> mealTypes;
    private List<String> dishTypes;
    private List<String> cuisineTypes;

    // Constructors, getters, and setters

    public static class Meal {
        private String label;
        private List<Ingredient> ingredients;
        private NutritionalInfo nutritionalInfo;

        // Constructors, getters, and setters
    }

    public static class Ingredient {
        private String foodId;
        private float quantity;
        private String measure;
        private float weight;
        private String food;
        private String foodCategory;

        // Constructors, getters, and setters
    }

    public static class NutritionalInfo {
        private double calories;
        private double fat;
        private double carbs;
        private double protein;

        // Constructors, getters, and setters
    }

}
