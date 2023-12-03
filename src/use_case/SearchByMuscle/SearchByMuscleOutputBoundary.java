package use_case.SearchByMuscle;

import entity.Workout;

import java.util.ArrayList;

public interface SearchByMuscleOutputBoundary {

    void Presentworkout(ArrayList<Workout> workouts);

    void Presentnoneselected();
}
