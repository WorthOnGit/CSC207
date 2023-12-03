package DataAccess;

import entity.Workout;
import use_case.WorkoutDataAccessInterface;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WDataAccess implements WorkoutDataAccessInterface {

    private static String wholeworkout;
    @Override
    public List<Workout> getworkout(String workoutName, String target, String secondaryMuscles, String gifUrl) {
        try {
            ArrayList<WorkoutInfo> workoutInfoList = getWorkoutInfo(workoutName);

            // Create a list to store Workout objects
            List<Workout> workoutList = new ArrayList<>();

            // Iterate through the workoutInfoList and create Workout objects
            for (WorkoutInfo workoutInfo : workoutInfoList) {
                String wholeWorkout = "Workout name: " + workoutInfo.getName() +
                        "\n\nPrimary Muscles: " + workoutInfo.getTarget() +
                        "\n\nSecondary Muscles: " + workoutInfo.formattedSecondaryMuscles() +
                        "\n\nGif URL: " + workoutInfo.getGifUrl() + "\n\n\n";


                // Create a Workout object and add it to the list
                workoutList.add(new Workout(
                        workoutInfo.getName(),
                        workoutInfo.getTarget(),
                        Collections.singletonList(workoutInfo.formattedSecondaryMuscles()),
                        workoutInfo.getGifUrl()
                ));

            }

            // You may want to return the list of Workout objects or a specific one based on your requirements
            return workoutList;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            // Handle the exception as needed
            return Collections.emptyList(); // Return an empty list on error
        }
    }


    public static ArrayList<WorkoutInfo> getWorkoutInfo(String workoutName) throws IOException, InterruptedException {
        // Replace whitespaces with %20
        //lowercase the workout name
        workoutName = workoutName.toLowerCase();
        String formattedWorkoutName = workoutName.replace(" ", "%20");

        try {
            // Send the request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://exercisedb.p.rapidapi.com/exercises/name/" + formattedWorkoutName + "?limit=10"))
                    .header("X-RapidAPI-Key", "be4d3f9924msh198990d307e91cbp138e82jsn05a9175436f1")
                    .header("X-RapidAPI-Host", "exercisedb.p.rapidapi.com")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();

            HttpResponse<String> response = null;
            try {
                response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // Parse the JSON array and extract exercise data
            JsonParser parser = new JsonParser();
            JsonArray exercisesArray = parser.parse(response.body()).getAsJsonArray();

            // Create a list to store workout data
            ArrayList<WorkoutInfo> workoutInfoList = new ArrayList<>();

            // Iterate through the exercises array and extract information
            for (JsonElement exerciseElement : exercisesArray) {
                JsonObject exerciseObject = exerciseElement.getAsJsonObject();

                String name = exerciseObject.get("name").getAsString();
                String target = exerciseObject.get("target").getAsString();
                String gifUrl = exerciseObject.get("gifUrl").getAsString();

                // Extract secondary muscles as a comma-separated string
                JsonArray secondaryMusclesArray = exerciseObject.getAsJsonArray("secondaryMuscles");
                String secondaryMuscles = extractSecondaryMuscles(secondaryMusclesArray);

                // Create a WorkoutInfo object
                WorkoutInfo workoutInfo = new WorkoutInfo(name, target, secondaryMusclesArray, gifUrl);

                workoutInfoList.add(workoutInfo);
            }

            return workoutInfoList;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<Workout> getworkout1(String muscle) {
        try {
            ArrayList<WorkoutInfo> workoutInfoList = getWorkoutInfoByMuscle(muscle);

            // Create a list to store Workout objects
            List<Workout> workoutList = new ArrayList<>();

            // Iterate through the workoutInfoList and create Workout objects
            for (WorkoutInfo workoutInfo : workoutInfoList) {
                String wholeWorkout = "Workout name: " + workoutInfo.getName() +
                        "\n\nPrimary Muscles: " + workoutInfo.getTarget() +
                        "\n\nSecondary Muscles: " + workoutInfo.formattedSecondaryMuscles() +
                        "\n\nGif URL: " + workoutInfo.getGifUrl() + "\n\n\n";

                // Create a Workout object and add it to the list
                workoutList.add(new Workout(
                        workoutInfo.getName(),
                        workoutInfo.getTarget(),
                        Collections.singletonList(workoutInfo.formattedSecondaryMuscles()),
                        workoutInfo.getGifUrl()
                ));
            }

            // You may want to return the list of Workout objects or a specific one based on your requirements
            return workoutList;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            // Handle the exception as needed
            return Collections.emptyList(); // Return an empty list on error
        }
    }

    public static ArrayList<WorkoutInfo> getWorkoutInfoByMuscle(String muscle) throws IOException, InterruptedException {
        // Replace whitespaces with %20
        // Lowercase the muscle name
        muscle = muscle.toLowerCase();
        String formattedMuscle = muscle.replace(" ", "%20");

        try {
            // Send the request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://exercisedb.p.rapidapi.com/exercises/target/" + formattedMuscle + "?limit=10"))
                    .header("X-RapidAPI-Key", "be4d3f9924msh198990d307e91cbp138e82jsn05a9175436f1")
                    .header("X-RapidAPI-Host", "exercisedb.p.rapidapi.com")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            // Parse the JSON array and extract exercise data
            JsonParser parser = new JsonParser();
            JsonArray exercisesArray = parser.parse(response.body()).getAsJsonArray();

            // Create a list to store workout data
            ArrayList<WorkoutInfo> workoutInfoList = new ArrayList<>();

            // Iterate through the exercises array and extract information
            for (JsonElement exerciseElement : exercisesArray) {
                JsonObject exerciseObject = exerciseElement.getAsJsonObject();

                String name = exerciseObject.get("name").getAsString();
                String target = exerciseObject.get("target").getAsString();
                String gifUrl = exerciseObject.get("gifUrl").getAsString();

                // Extract secondary muscles as a comma-separated string
                JsonArray secondaryMusclesArray = exerciseObject.getAsJsonArray("secondaryMuscles");
                String secondaryMuscles = extractSecondaryMuscles(secondaryMusclesArray);

                // Create a WorkoutInfo object
                WorkoutInfo workoutInfo = new WorkoutInfo(name, target, secondaryMusclesArray, gifUrl);

                workoutInfoList.add(workoutInfo);
            }

            return workoutInfoList;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }



    private static String extractSecondaryMuscles(JsonArray secondaryMusclesArray) {
        StringBuilder secondaryMuscles = new StringBuilder();

        for (JsonElement muscleElement : secondaryMusclesArray) {
            if (secondaryMuscles.length() > 0) {
                secondaryMuscles.append(", ");
            }
            secondaryMuscles.append(muscleElement.getAsString());
        }

        return secondaryMuscles.toString();
    }
}

class WorkoutInfo {
    private String name;
    private String target;
    private String gifUrl;
    private JsonArray secondaryMuscles;

    public WorkoutInfo(String name, String target, JsonArray secondaryMuscles, String gifUrl) {
        this.name = name;
        this.target = target;
        this.secondaryMuscles = secondaryMuscles;
        this.gifUrl = gifUrl;
    }

    public String getName() {
        return name;
    }

    public String getTarget() {
        return target;
    }

    public JsonArray getSecondaryMuscles() {
        return secondaryMuscles;
    }

    public String getGifUrl() {
        return gifUrl;
    }

    public String formattedSecondaryMuscles() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < secondaryMuscles.size(); i++) {
            String muscle = secondaryMuscles.get(i).getAsString();
            result.append(muscle);
            if (i < secondaryMuscles.size() - 1) {
                result.append(", ");
            }
        }
        return result.toString();
    }
}


