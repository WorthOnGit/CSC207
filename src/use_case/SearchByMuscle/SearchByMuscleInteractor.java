package use_case.SearchByMuscle;

import entity.Workout;
import use_case.WorkoutDataAccessInterface;

import java.util.ArrayList;
import java.util.List;

public class SearchByMuscleInteractor implements SearchByMuscleInputBoundary {

    private final SearchByMuscleOutputBoundary output;

    private final WorkoutDataAccessInterface workoutDataAccessInterface;

    public SearchByMuscleInteractor(SearchByMuscleOutputBoundary output, WorkoutDataAccessInterface workoutDataAccessInterface) {
        this.output = output;
        this.workoutDataAccessInterface = workoutDataAccessInterface;
    }

    @Override
    public void execute(ArrayList<String> muscles) {
        if (muscles.isEmpty()) {
            output.Presentnoneselected();
        } else {
            // Initialize workouts as a List instead of ArrayList
            List<Workout> workouts = new ArrayList<>();

            // Assuming you want to fetch workouts for each muscle individually
            for (String muscle : muscles) {
                List<Workout> muscleWorkouts = workoutDataAccessInterface.getworkout1(muscle);
                workouts.addAll(muscleWorkouts);
            }

            // Present all workouts together
            output.Presentworkout((ArrayList<Workout>) workouts);
        }
    }

    @Override
    public void handleNoMusclesSelected() {
        output.Presentnoneselected();
    }
}

