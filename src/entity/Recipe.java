package entity;

import java.util.HashMap;
import java.util.List;

public class Recipe {

    private final HashMap<String, Object> recipe;

    public Recipe(HashMap<String, Object> recipe) {
        this.recipe = recipe;
    }

    public String getRecipeName() {
        return (String) recipe.get("recipeName");
    }

    public List<String> getIngredientLines() {
        return (List<String>) recipe.get("ingredientLines");
    }

    public List<Object> getIngredients() {
        return (List<Object>) recipe.get("ingredients");
    }

    public int getCalories() {
        return (int) recipe.get("calories");
    }

    public CharSequence getHealthLabels() {
        return (CharSequence) recipe.get("healthLabels");
    }

    public CharSequence getDietLabels() {
        return (CharSequence) recipe.get("dietLabels");
    }

    public String getMealType() {
        return (String) recipe.get("mealType");
    }

    public String getCuisineType() {
        return (String) recipe.get("cuisineType");
    }
}