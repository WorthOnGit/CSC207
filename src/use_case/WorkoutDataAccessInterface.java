package use_case;

import entity.Workout;

import java.util.ArrayList;
import java.util.List;

public interface WorkoutDataAccessInterface {
    List<Workout> getworkout(String workoutName, String target, String secondaryMuscles, String gifUrl);

    List<Workout> getworkout1(String muscle);

}
