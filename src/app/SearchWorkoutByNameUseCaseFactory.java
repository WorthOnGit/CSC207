package app;

import interface_adapter.SearchWorkoutByNameViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.Workout.WorkoutViewModel;
import view.SearchWorkoutByNameView;

public class SearchWorkoutByNameUseCaseFactory
{
    private SearchWorkoutByNameUseCaseFactory() {}

    public static SearchWorkoutByNameView create(SearchWorkoutByNameViewModel searchWorkoutByNameViewModel, ViewManagerModel viewManagerModel, WorkoutViewModel workoutViewModel)
    {
        return new SearchWorkoutByNameView(searchWorkoutByNameViewModel, viewManagerModel, workoutViewModel);
    }









}
