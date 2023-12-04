package DataAccess;

import com.google.gson.Gson;
import entity.MealPlan;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;

public class MDataAccess {
    private final String apiKey = "c04a2c3aee3e01bef87c64042874ed4a";
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final Gson gson = new Gson();

    public MealPlan getMealPlan(String query) {
        String url = "https://api.edamam.com/api/meal-planner/v1?query=" + query + "&app_key=" + apiKey;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return gson.fromJson(response.body(), MealPlan.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
