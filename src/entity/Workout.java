package entity;

import java.util.List;

public class Workout {
    private final String name;
    private final String target;
    private final List<String> secondaryMuscles;
    private final String gifUrl;

    public Workout(String name, String target, List<String> secondaryMuscles, String gifUrl) {
        this.name = name;
        this.target = target;
        this.secondaryMuscles = secondaryMuscles;
        this.gifUrl = gifUrl;
    }

    public String getWholeWorkout() {
        // Generate HTML content for display
        StringBuilder result = new StringBuilder("<html><b>Workout Name:</b> " + name +
                "<br><b>Primary Muscle:</b> " + target +
                "<br><b>Secondary Muscles:</b> ");
        for (String muscle : secondaryMuscles) {
            result.append(muscle).append(", ");
        }
        result.append("<br><b>Visual:</b> ");
        result.append("<img src='").append(gifUrl).append("'><br><br></html>");

        return result.toString();
    }
}
