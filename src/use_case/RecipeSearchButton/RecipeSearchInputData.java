package use_case.RecipeSearchButton;

import java.util.List;

public class RecipeSearchInputData {
    private final String recipeName;
    private final String countryOfOrigin;
    private final int calories;
    private final List<String> dietLabels;
    private final List<String> healthLabels;
    private final String mealType;

    public RecipeSearchInputData(String recipeName, String countryOfOrigin, int calories, List<String> dietLabels, List<String> healthLabels, String mealType) {
        this.recipeName = recipeName;
        this.countryOfOrigin = countryOfOrigin;
        this.calories = calories;
        this.dietLabels = dietLabels;
        this.healthLabels = healthLabels;
        this.mealType = mealType;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public int getCalories() {
        return calories;
    }

    public List<String> getDietLabels() {
        return dietLabels;
    }

    public List<String> getHealthLabels() {
        return healthLabels;
    }

    public String getMealType() {
        return mealType;
    }

    public boolean noInput() {
        return recipeName.isEmpty() &&
                countryOfOrigin.isEmpty() &&
                calories == 0 &&
                dietLabels.isEmpty() &&
                healthLabels.isEmpty() &&
                mealType.isEmpty();
    }
}