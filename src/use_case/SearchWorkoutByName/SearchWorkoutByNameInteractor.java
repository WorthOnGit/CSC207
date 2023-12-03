package use_case.SearchWorkoutByName;

import entity.Workout;
import use_case.WorkoutDataAccessInterface;

import java.util.ArrayList;
import java.util.List;

public class SearchWorkoutByNameInteractor implements SearchWorkoutByNameInputBoundary {

    private SearchWorkoutByNameOutputBoundary outputBoundary;

    private final WorkoutDataAccessInterface workoutDataAccessInterface;

    public SearchWorkoutByNameInteractor(SearchWorkoutByNameOutputBoundary outputBoundary, WorkoutDataAccessInterface workoutDataAccessInterface) {
        this.outputBoundary = outputBoundary;
        this.workoutDataAccessInterface = workoutDataAccessInterface;
    }

    @Override
    public void execute(String name) {
        if (name.isEmpty()) {
            outputBoundary.Presentemptystring();
            return;
        }

        try {
            // Modify this line to receive a list of workouts
            List<Workout> workouts = workoutDataAccessInterface.getworkout(name, "", "", "");

            if (workouts.isEmpty()) {
                outputBoundary.PresentNotFound();
            } else {
                // Pass the list of workouts to the output boundary
                outputBoundary.Presentworkouts((ArrayList<Workout>) workouts);
            }
        } catch (Exception e) {
            e.printStackTrace();
            outputBoundary.PresentNotFound();
        }
    }
}