package DataAccess;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.Recipe;
import use_case.DataAccessInterface;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class DataAccess implements DataAccessInterface {
//    public static void main(String[] args) throws IOException {
//        RecipeInfo recipeInfo = getRecipeInfo("YourRecipeName", "YourCountryOfOrigin");
//        System.out.println("Recipe Name: " + recipeInfo.getRecipeName());
//        System.out.println("Ingredient Lines:");
//        recipeInfo.printIngredientLines();
//    }

    public Recipe getRecipe(String recipename, String countryoforigin) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.edamam.com/api/recipes/v2?type=public&q=" + recipename + "&app_id=46fc17af&app_key=de222735d9046e67f7dff62e54ff616f" + countryoforigin))
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
        JsonObject recipe = firstHit.getAsJsonObject("recipe");
        String recipeName = recipe.get("label").getAsString();

        // Extract ingredient lines
        JsonArray ingredientLines = recipe.getAsJsonArray("ingredientLines");

        HashMap output = new HashMap();
        output.put("recipeName", recipeName);
        output.put("ingredientLines", ingredientLines);
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
