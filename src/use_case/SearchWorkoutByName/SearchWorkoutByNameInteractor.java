package use_case.SearchWorkoutByName;

import entity.Workout;
import use_case.WorkoutDataAccessInterface;

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
        } else {
            try {
                Workout workout = workoutDataAccessInterface.getworkout(name);
                outputBoundary.Presentworkout(workout);
            } catch (Exception e) {
                outputBoundary.PresentNotFound();
            }
            outputBoundary.Presentworkout(new Workout());
        }
    }
}
