package interface_adapter.SearchWorkoutByName;

import use_case.SearchWorkoutByName.SearchWorkoutByNameInputBoundary;

public class SearchWorkoutByNameController {

    private final SearchWorkoutByNameInputBoundary searchWorkoutByNameInputBoundary;

    public SearchWorkoutByNameController(SearchWorkoutByNameInputBoundary searchWorkoutByNameInputBoundary) {
        this.searchWorkoutByNameInputBoundary = searchWorkoutByNameInputBoundary;
    }

    public void execute(String name) {
        searchWorkoutByNameInputBoundary.execute(name);
    }
}
