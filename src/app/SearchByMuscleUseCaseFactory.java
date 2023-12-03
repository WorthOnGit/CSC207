package app;

import DataAccess.WDataAccess;
import interface_adapter.SearchByMuscle.SearchByMuscleController;
import interface_adapter.SearchByMuscle.SearchByMusclePresenter;
import interface_adapter.SearchByMuscle.SearchByMuscleViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.Workout.WorkoutViewModel;
import use_case.SearchByMuscle.SearchByMuscleInputBoundary;
import use_case.SearchByMuscle.SearchByMuscleInteractor;
import use_case.SearchByMuscle.SearchByMuscleOutputBoundary;
import use_case.WorkoutDataAccessInterface;
import view.SearchByMuscleView;

import javax.swing.*;

public class SearchByMuscleUseCaseFactory {
    /** Prevent instantiation. */
    private SearchByMuscleUseCaseFactory() {}

    public static SearchByMuscleView create(SearchByMuscleViewModel searchByMuscleViewModel, WorkoutViewModel workoutViewModel, ViewManagerModel viewManagerModel, JFrame application) {

        SearchByMuscleController searchByMuscleController = createcontroller(application);

        return new SearchByMuscleView(searchByMuscleViewModel, workoutViewModel, viewManagerModel, searchByMuscleController);

    }

    public static SearchByMuscleController createcontroller(JFrame application) {

        SearchByMuscleOutputBoundary searchByMuscleOutputBoundary = new SearchByMusclePresenter(application);

        WorkoutDataAccessInterface workoutDataAccessInterface = new WDataAccess();

        SearchByMuscleInputBoundary searchByMuscleInputBoundary = new SearchByMuscleInteractor(searchByMuscleOutputBoundary, workoutDataAccessInterface);

        return new SearchByMuscleController(searchByMuscleInputBoundary);



    }
}
