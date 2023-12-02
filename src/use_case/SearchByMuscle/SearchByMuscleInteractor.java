package use_case.SearchByMuscle;

import entity.Workout;
import use_case.WorkoutDataAccessInterface;

import java.util.ArrayList;

public class SearchByMuscleInteractor implements SearchByMuscleInputBoundary {

    private final SearchByMuscleOutputBoundary output;

    private final WorkoutDataAccessInterface workoutDataAccessInterface;

    public SearchByMuscleInteractor(SearchByMuscleOutputBoundary output, WorkoutDataAccessInterface workoutDataAccessInterface) {
        this.output = output;
        this.workoutDataAccessInterface = workoutDataAccessInterface;
    }

    @Override
    public void execute(ArrayList<String> muscles) {
        if (muscles.size() == 0) {
            output.Presentnoneselected();
        } else {
            Workout workout = workoutDataAccessInterface.getworkout1(muscles);
            output.Presentworkout(workout);
        }
    }
}
