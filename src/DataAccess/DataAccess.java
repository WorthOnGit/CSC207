package DataAccess;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import entity.Recipe;
import use_case.DataAccessInterface;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;

public class DataAccess implements DataAccessInterface {

    public Recipe getRecipe(String recipename, String countryoforigin, int Calories, List<String> dietLabels, List<String> healthLabels, String mealtype) {

        recipename = recipename.replace(" ", "%");
        countryoforigin = countryoforigin.replace(" ", "%");



        StringBuilder dietLabelsUrl = new StringBuilder();
        for (String dietLabel : dietLabels) {
            dietLabelsUrl.append("&diet=").append(dietLabel);
        }

        // Build the health labels part of the URL
        StringBuilder healthLabelsUrl = new StringBuilder();
        for (String healthLabel : healthLabels) {
            healthLabelsUrl.append("&health=").append(healthLabel);
        }
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.edamam.com/api/recipes/v2?type=public&q=" + recipename + "&app_id=46fc17af&app_key=de222735d9046e67f7dff62e54ff616f" +  dietLabelsUrl.toString() +
                        healthLabelsUrl.toString() + "&cuisineType" + countryoforigin + "&mealtype=" + mealtype + "&calories=0-" + Calories))
                .header("app_id", "46fc17af")
                .header("app_key", "de222735d9046e67f7dff62e54ff616f")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }

        // Parse JSON response
        JsonParser parser = new JsonParser();
        JsonObject jsonResponse = parser.parse(response.body()).getAsJsonObject();

        // Extract recipe name from the first hit
        JsonArray hits = jsonResponse.getAsJsonArray("hits");
        JsonObject firstHit = hits.get(0).getAsJsonObject();
        JsonElement recipeElement = firstHit.get("recipe");

        JsonObject recipe;
        if (recipeElement.isJsonObject()) {
            recipe = recipeElement.getAsJsonObject();
        } else {
            // Handle the case where "recipe" is a primitive (e.g., a string or number)
            // You might need to adjust this based on your actual JSON structure
            recipe = new JsonObject();
            recipe.addProperty("label", recipeElement.getAsString());
            // Add other fields if needed
        }

        String recipeName = recipe.get("label").getAsString();
        JsonArray ingredientLines = recipe.getAsJsonArray("ingredientLines");

        HashMap output = new HashMap();
        output.put("recipeName", recipeName);
        output.put("ingredientLines", ingredientLines);
        output.put("ingredients", recipe.getAsJsonArray("ingredients"));
        output.put("calories", recipe.get("calories").getAsInt());
        output.put("healthLabels", recipe.getAsJsonArray("healthLabels"));
        output.put("dietLabels", recipe.getAsJsonArray("dietLabels"));
        output.put("mealType", recipe.get("mealType").getAsString());
        output.put("cuisineType", recipe.get("cuisineType").getAsString());


        return new Recipe(output);
    }
}

//class RecipeInfo {
//    private String recipeName;
//    private JsonArray ingredientLines;
//
//    public RecipeInfo(String recipeName, JsonArray ingredientLines) {
//        this.recipeName = recipeName;
//        this.ingredientLines = ingredientLines;
//    }
//
//    public String getRecipeName() {
//        return recipeName;
//    }
//
//    public JsonArray getIngredientLines() {
//        return ingredientLines;
//    }
//
//    public void printIngredientLines() {
//        for (int i = 0; i < ingredientLines.size(); i++) {
//            String ingredient = ingredientLines.get(i).getAsString();
//            System.out.println("- " + ingredient);
//        }
//    }
//}
