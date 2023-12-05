package interface_adapter.CalorieCounter;

import use_case.CalorieCounter.CalorieCounterInputBoundary;
import use_case.CalorieCounter.CalorieCounterInputData;

import java.io.IOException;

public class CalorieCounterController {
    private final CalorieCounterInputBoundary calorieCounterInputBoundary;

    public CalorieCounterController(CalorieCounterInputBoundary calorieCounterInputBoundary) {
        this.calorieCounterInputBoundary = calorieCounterInputBoundary;
    }

    public void execute(int age, int weight, int height, String gender, String activity) throws IOException, InterruptedException {
        CalorieCounterInputData input = new CalorieCounterInputData(age, weight, height, gender, activity);
        calorieCounterInputBoundary.execute(input);
    }
}
