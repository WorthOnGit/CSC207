package DataAccess;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import interface_adapter.RecipePageViewModel.RecipePageState;
import use_case.DataAccessInterface;
import entity.Recipe;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;


public class DataAccess implements DataAccessInterface {

    private static String WholeRecipe;

    @Override
    public Recipe getRecipe(String recipename, String countryoforigin, int Calories, List<String> dietLabels, List<String> healthLabels, String mealtype) throws IOException {
        return new Recipe(WholeRecipe);
    }
    public static void main(String[] args) throws IOException {
        RecipeInfo recipeInfo = getRecipeInfo();
        String WholeRecipe = "Recipe name:\n" + recipeInfo.getRecipeName() + "\n\nCalories:\n" + recipeInfo.getCalories() + "\n\nCuisine Type:\n" + recipeInfo.getCuisineType() + "\n\nMeal Type\n" + recipeInfo.getMealType() + "\n\nDiet Label:\n" + recipeInfo.formattedDietLabels() + "\nHealth Label:\n" + recipeInfo.formattedHealthLabels() + "\nIngredients:\n" + recipeInfo.formattedIngredients();
        String recipeName = recipeInfo.getRecipeName();
        double calories = recipeInfo.getCalories();
        String cuisineType = recipeInfo.getCuisineType();
        String mealType = recipeInfo.getMealType();
        String dietLabels = recipeInfo.formattedDietLabels();
        String healthLabels = recipeInfo.formattedHealthLabels();
        String ingredients = recipeInfo.formattedIngredients();

    }

    public static RecipeInfo getRecipeInfo() throws IOException {
        // Build the diet labels part of the URL

        String recipename = RecipePageState.getRecipename().replace(" ", "%");
        String countryoforigin = RecipePageState.getCountryoforigin().replace(" ", "%");

        StringBuilder dietLabelsUrl = new StringBuilder();
        for (String dietLabel : // dietLabels from RecipePageState
                RecipePageState.getDietLabels()) {
            dietLabelsUrl.append("&diet=").append(dietLabel);
        }

        // Build the health labels part of the URL
        StringBuilder healthLabelsUrl = new StringBuilder();
        for (String healthLabel : RecipePageState.getHealthLabels()) {
            healthLabelsUrl.append("&health=").append(healthLabel);
        }
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.edamam.com/api/recipes/v2?type=public&q=" + recipename + "&app_id=46fc17af&app_key=de222735d9046e67f7dff62e54ff616f" + dietLabelsUrl.toString() +
                        healthLabelsUrl.toString() + "&cuisineType" + countryoforigin + "&mealtype=" + RecipePageState.getmealtype() + "&calories=" + RecipePageState.getCalories()))
                .header("app_id", "46fc17af")
                .header("app_key", "de222735d9046e67f7dff62e54ff616f")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Parse JSON response
        JsonParser parser = new JsonParser();
        JsonObject jsonResponse = parser.parse(response.body()).getAsJsonObject();

        JsonArray hits = jsonResponse.getAsJsonArray("hits");

        String recipeName;
        JsonObject recipe;
        if (hits != null && !hits.isJsonNull() && hits.size() > 0) {
            JsonObject firstHit = hits.get(0).getAsJsonObject();
            JsonElement recipeElement = firstHit.get("recipe");

            if (recipeElement.isJsonObject()) {
                recipe = recipeElement.getAsJsonObject();
            } else {
                // Handle the case where "recipe" is a primitive (e.g., a string or number)
                // You might need to adjust this based on your actual JSON structure
                recipe = new JsonObject();
                recipe.addProperty("label", recipeElement.getAsString());
                // Add other fields if needed
            }

        } else {
            throw new RuntimeException("No recipes found");
        }

        recipeName = recipe.get("label").getAsString();
        double calories = recipe.get("calories").getAsDouble();
        JsonArray dietLabels = recipe.getAsJsonArray("dietLabels");
        JsonArray healthLabels = recipe.getAsJsonArray("healthLabels");
        String cuisineType = recipe.get("cuisineType").getAsString();
        String mealType = recipe.get("mealType").getAsString();
        JsonArray ingredientLines = recipe.getAsJsonArray("ingredientLines");
        JsonArray ingredients = recipe.getAsJsonArray("ingredients");
        // Create RecipeInfo object
        RecipeInfo recipeInfo = new RecipeInfo(recipeName, calories, dietLabels, healthLabels, cuisineType, mealType, ingredientLines, ingredients);

        return recipeInfo;
    }
}

class RecipeInfo {
    private String recipeName;
    private double calories;
    private JsonArray dietLabels;
    private JsonArray healthLabels;
    private String cuisineType;
    private String mealType;
    private JsonArray ingredientLines;
    private JsonArray ingredients;  // Modified to handle objects

    public RecipeInfo(String recipeName, double calories, JsonArray dietLabels, JsonArray healthLabels,
                      String cuisineType, String mealType, JsonArray ingredientLines, JsonArray ingredients) {
        this.recipeName = recipeName;
        this.calories = calories;
        this.dietLabels = dietLabels;
        this.healthLabels = healthLabels;
        this.cuisineType = cuisineType;
        this.mealType = mealType;
        this.ingredientLines = ingredientLines;
        this.ingredients = ingredients;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public String getMealType() {
        return mealType;
    }

    public double getCalories() {
        return calories;
    }

    public String formattedDietLabels() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < dietLabels.size(); i++) {
            String dietLabel = dietLabels.get(i).getAsString();
            result.append("- ").append(dietLabel).append("\n");
        }
        return result.toString();
    }

    public String formattedHealthLabels() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < healthLabels.size(); i++) {
            String healthLabel = healthLabels.get(i).getAsString();
            result.append("- ").append(healthLabel).append("\n");
        }
        return result.toString();
    }
    public String formattedIngredients() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < ingredients.size(); i++) {
            JsonObject ingredientObject = ingredients.get(i).getAsJsonObject();
            String ingredient = ingredientObject.get("text").getAsString();
            result.append("- ").append(ingredient).append("\n");
        }
        return result.toString();
    }
}
