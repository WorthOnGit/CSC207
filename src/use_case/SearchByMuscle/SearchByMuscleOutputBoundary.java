package use_case.SearchByMuscle;

import entity.Workout;

public interface SearchByMuscleOutputBoundary {

    void Presentworkout(Workout workout);

    void Presentnoneselected();
}
