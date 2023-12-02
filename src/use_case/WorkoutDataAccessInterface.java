package use_case;

import entity.Workout;

import java.util.ArrayList;

public interface WorkoutDataAccessInterface {

    Workout getworkout(ArrayList<String> muscles);

    Workout getworkout(String name);
}
