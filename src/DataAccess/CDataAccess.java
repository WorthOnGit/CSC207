package DataAccess;


// Go to ProjectStructure >> Libraries >> Add New From Maven
// >> search up org.json and download the most recent one(org.json:json:20231013) and Apply
import entity.Calculations;
import org.json.JSONObject;
import use_case.CalorieCounterDataAccessInterface;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class CDataAccess implements CalorieCounterDataAccessInterface {

    public String copyactivity;
    @Override
    public Calculations getCalculation(int age, int weight, int height, String gender, String activity) throws IOException, InterruptedException {

        // Sets Up Activity for HTTP Request
        if (activity.equals("Sedentary Lifestyle")){
            copyactivity = "level_1";}
        if (activity.equals("Slightly Active")){
            copyactivity = "level_2";}
        if (activity.equals("Moderately Active Lifestyle")){
            copyactivity = "level_3";}
        if (activity.equals("Active Lifestyle")){
            copyactivity = "level_4";}
        if (activity.equals("Very Active Lifestyle")){
            copyactivity = "level_5";}

        // IdealWeight
        HttpRequest request1 = HttpRequest.newBuilder()
                .uri(URI.create("https://fitness-calculator.p.rapidapi.com/idealweight?gender=" + gender + "&height="
                        + height))
                .header("X-RapidAPI-Key", "246209e3dcmsha75ece2e406c89dp1a52b6jsnb9ce09294696")
                .header("X-RapidAPI-Host", "fitness-calculator.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response1 = HttpClient.newHttpClient().send(request1, HttpResponse.BodyHandlers.ofString());

        // Parse JSON response for IdealWeight
        JSONObject idealWeightResponse = new JSONObject(response1.body());
        int hamwiMin = idealWeightResponse.getJSONObject("data").getInt("Hamwi");
        int hamwiMax = (int)Math.round(hamwiMin * 1.1);  // Assuming a 10% variation, you can adjust this as needed

        String IdealWeight = "Ideal Weight(kg): Range(" + hamwiMin + " - " + hamwiMax + ")";
        System.out.println("Ideal Weigh(kg)t: Range(" + hamwiMin + " - " + hamwiMax + ")");

        // BMI
        HttpRequest request2 = HttpRequest.newBuilder()
                .uri(URI.create("https://fitness-calculator.p.rapidapi.com/bmi?age=25&weight=65&height=180"))
                .header("X-RapidAPI-Key", "246209e3dcmsha75ece2e406c89dp1a52b6jsnb9ce09294696")
                .header("X-RapidAPI-Host", "fitness-calculator.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response2 = HttpClient.newHttpClient().send(request2, HttpResponse.BodyHandlers.ofString());

        // Parse JSON response for BMI
        JSONObject bmiResponse = new JSONObject(response2.body());
        int bmiValue = bmiResponse.getJSONObject("data").getInt("bmi");
        String healthStatus = bmiResponse.getJSONObject("data").getString("health");
        String bmiRange = bmiResponse.getJSONObject("data").getString("healthy_bmi_range");

        String BMI = "BMI: " + bmiValue;
        String Health = "Health: " + healthStatus;
        String HealthyRange = "Healthy BMI Range: " + bmiRange;

        ArrayList<String> BMIinfo = new ArrayList<>();
        BMIinfo.add(BMI);
        BMIinfo.add(Health);
        BMIinfo.add(HealthyRange);

        System.out.println("BMI: " + bmiValue + "\nHealth: " + healthStatus + "\nHealthy BMI Range: " + bmiRange);

        // BMR and Different Goals
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://fitness-calculator.p.rapidapi.com/dailycalorie?age="
                        + age + "&gender=" + gender +
                        "&height=" + height + "&weight=" +
                        weight + "&activitylevel=" + copyactivity))
                .header("X-RapidAPI-Key", "246209e3dcmsha75ece2e406c89dp1a52b6jsnb9ce09294696")
                .header("X-RapidAPI-Host", "fitness-calculator.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        // Parse JSON response for BMR and Different Goals
        JSONObject jsonResponse = new JSONObject(response.body());

// Extract BMR value
        int bmrValue = jsonResponse.getJSONObject("data").getInt("BMR");
        String BMR = "BMR: " + bmrValue + " Calories";

// Extract goals
        JSONObject goals = jsonResponse.getJSONObject("data").getJSONObject("goals");
        ArrayList<String> goalsList = new ArrayList<>();
        goalsList.add("Maintain Weight: " + goals.getDouble("maintain weight") + " Calories");

        for (String goal : goals.keySet()) {
            if (!goal.equals("maintain weight")) {
                Object goalDetails = goals.get(goal);
                if (goalDetails instanceof JSONObject) {
                    // If the goal is represented as an object
                    JSONObject goalDetailsObj = (JSONObject) goalDetails;
                    String lossWeight = goalDetailsObj.has("loss weight") ? goalDetailsObj.getString("loss weight") : "";
                    String gainWeight = goalDetailsObj.has("gain weight") ? goalDetailsObj.getString("gain weight") : "";
                    int goalCalories = goalDetailsObj.getInt("calory");
                    goalsList.add(goal + " (" + (lossWeight.isEmpty() ? gainWeight : lossWeight) + "/week): " + goalCalories + " Calories");
                } else if (goalDetails instanceof String) {
                    // If the goal is represented as a plain string
                    goalsList.add(goal + ": " + goalDetails + " Calories");
                }
            }
        }

        ArrayList<String> weightgoals = goalsList;
        return new Calculations(IdealWeight, BMIinfo, BMR, weightgoals);

}

}
