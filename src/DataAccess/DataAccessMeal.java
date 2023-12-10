/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccess;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.MealPlanner;
import interface_adapter.MealPlannerPageViewModel.MealPlannerPageState;
import interface_adapter.RecipePageViewModel.RecipePageState;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import use_case.MealPlannerDataAccessInterface;

/**
 *
 * @author Lenovo
 */
public class DataAccessMeal implements MealPlannerDataAccessInterface {
    private static String WholeMealPlanner;
    
    
     @Override
    public MealPlanner getMealPlanner(int selectedMealCount, int selectedPlanType, List<String> dietLabels, List<String> healthLabels, double minCalories, double maxCalories) throws IOException
    {
    MealPlannerInfo mealPlannerInfo = getMealPlannerInfo();
    String WholeMealPlanner = "Meal Count:\n" + mealPlannerInfo.getSelectedMealCount() + "\n\nPlanType:\n" + mealPlannerInfo.getSelectedPlanType() +"\n\nDiet Labels:\n" + mealPlannerInfo.formattedDietLabels() +"\n\nHealth Labels:\n" + mealPlannerInfo.formattedHealthLabels() +
            "\n\nMin Calories:\n" + mealPlannerInfo.getMinCalories() +
            "\n\nMax Calories:\n" + mealPlannerInfo.getMaxCalories();
    return new MealPlanner(WholeMealPlanner);
     }
    
    
    
    public static MealPlannerInfo getMealPlannerInfo() throws IOException 
{
   int selectedMealcount=MealPlannerPageState.getSelectedMealCount();
   int selectedPlantype=MealPlannerPageState.getSelectedPlanType();
   double mincalory=MealPlannerPageState.getMinCalories();
   double maxcalory=MealPlannerPageState.getMaxCalories();
   
   
   
   StringBuilder dietLabelsUrl = new StringBuilder();
        for (String dietLabel : // dietLabels from RecipePageState
                RecipePageState.getDietLabels()) {
            // lower case it
            dietLabel = dietLabel.toLowerCase();
            dietLabelsUrl.append("&diet=").append(dietLabel);
        }
   // Build the health labels part of the URL
        StringBuilder healthLabelsUrl = new StringBuilder();
        for (String healthLabel : RecipePageState.getHealthLabels()) {
            healthLabelsUrl.append("&health=").append(healthLabel);
        }

        // if the calories arent set, set them to 3000
        if (mincalory == 0 || maxcalory == 0) {
             mincalory= 1800;
             maxcalory=3000;
        }

       

    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://api.edamam.com/api/recipes/v2?type=public&q=" + selectedPlantype +
                    "&app_id=46fc17af&app_key=de222735d9046e67f7dff62e54ff616f" + dietLabelsUrl +
                    healthLabelsUrl.toString() + "&min=" + mincalory + "&max=" + maxcalory))
            .header("app_id", "46fc17af")
            .header("app_key", "de222735d9046e67f7dff62e54ff616f")
            .method("GET", HttpRequest.BodyPublishers.noBody())
            .build();


   HttpResponse<String> response;

        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Print request
        System.out.println(request);

        // Parse JSON response
        JsonParser parser = new JsonParser();
        JsonObject jsonResponse = parser.parse(response.body()).getAsJsonObject();
        
        
        // Extract recipe name from the first hit
        JsonArray hits = jsonResponse.getAsJsonArray("hits");
        JsonObject firstHit = hits.get(0).getAsJsonObject();
        JsonElement recipeElement = firstHit.get("recipe");

        // Keep moving onto the next hit until the calories are within the range
        int i = 0;
        while (true) {
            // Get the recipe element from the current hit
            recipeElement = hits.get(i).getAsJsonObject().get("recipe");

            // Get the calories from the recipe element
            JsonObject recipe = recipeElement.getAsJsonObject();
            double calories = recipe.get("calories").getAsDouble();

            // Check if the calories are less than the max calories
            if (calories <= maxcalory) {
                break;
            }

            // Move onto the next hit
            i++;
        }
        
        JsonObject MealPlanner;
        if (recipeElement.isJsonObject()) {
            MealPlanner = recipeElement.getAsJsonObject();
        } else {
            // Handle the case where "MealPlanner" is a primitive (e.g., a string or number)
            // You might need to adjust this based on your actual JSON structure
            MealPlanner = new JsonObject();
            MealPlanner.addProperty("label", recipeElement.getAsString());
            // Add other fields if needed
        }
        String seletectMealcount = MealPlanner.get("label").getAsString();
        double calories = MealPlanner.get("calories").getAsDouble();
        JsonArray dietLabels = MealPlanner.getAsJsonArray("dietLabels");
        JsonArray healthLabels = MealPlanner.getAsJsonArray("healthLabels");
        // Ensure mealType is always the first item
        String mealType = "";
        JsonArray mealTypeArray = MealPlanner.getAsJsonArray("mealType");
         // Create RecipeInfo object
        MealPlannerInfo mealPlannerInfo = new MealPlannerInfo(selectedMealcount, selectedPlantype, dietLabels, healthLabels, calories, calories);
        return mealPlannerInfo;
}
    
    
    
    
    
}


class MealPlannerInfo{
    
    
    private int selectedMealCount;
    private int selectedPlanType;
    private JsonArray dietLabels;
    private JsonArray healthLabels;
    private double minCalories;
    private double maxCalories;

    public MealPlannerInfo(int selectedMealCount, int selectedPlanType, JsonArray dietLabels, JsonArray healthLabels, double minCalories, double maxCalories) {
        this.selectedMealCount = selectedMealCount;
        this.selectedPlanType = selectedPlanType;
        this.dietLabels = dietLabels;
        this.healthLabels = healthLabels;
        this.minCalories = minCalories;
        this.maxCalories = maxCalories;
    }

    public int getSelectedMealCount() {
        return selectedMealCount;
    }

    public int getSelectedPlanType() {
        return selectedPlanType;
    }

    public double getMinCalories() {
        return minCalories;
    }

    public double getMaxCalories() {
        return maxCalories;
    }
    public String formattedDietLabels() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < dietLabels.size(); i++) {
            String dietLabel = dietLabels.get(i).getAsString();
            result.append(dietLabel).append("\n");
        }
        return result.toString();
    }

    public String formattedHealthLabels() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < healthLabels.size(); i++) {
            String healthLabel = healthLabels.get(i).getAsString();
            result.append(healthLabel).append("\n");
        }
        return result.toString();
    }
    
}