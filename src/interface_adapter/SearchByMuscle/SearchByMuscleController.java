package interface_adapter.SearchByMuscle;

import use_case.SearchByMuscle.SearchByMuscleInputBoundary;

import java.util.ArrayList;

public class SearchByMuscleController {

        private final SearchByMuscleInputBoundary searchByMuscleInputBoundary;

        public SearchByMuscleController(SearchByMuscleInputBoundary searchByMuscleInputBoundary) {
            this.searchByMuscleInputBoundary = searchByMuscleInputBoundary;
        }

        public void execute(ArrayList muscle) {
            searchByMuscleInputBoundary.execute(muscle);
        }

}
