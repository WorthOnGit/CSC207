package use_case.SearchWorkoutByName;

import entity.Workout;

public interface SearchWorkoutByNameOutputBoundary {

        void Presentworkout(Workout workout);

        void Presentemptystring();

        void PresentNotFound();
}
