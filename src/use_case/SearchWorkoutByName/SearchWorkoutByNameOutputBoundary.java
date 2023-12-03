package use_case.SearchWorkoutByName;

import entity.Workout;

import java.util.ArrayList;

public interface SearchWorkoutByNameOutputBoundary {

        void Presentworkouts(ArrayList<Workout> workout);

        void Presentemptystring();

        void PresentNotFound();
}
