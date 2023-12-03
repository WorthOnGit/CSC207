package app;

import DataAccess.WDataAccess;
import interface_adapter.SearchWorkoutByName.SearchWorkoutByNameController;
import interface_adapter.SearchWorkoutByName.SearchWorkoutByNamePresenter;
import interface_adapter.SearchWorkoutByName.SearchWorkoutByNameViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.Workout.WorkoutViewModel;
import use_case.SearchWorkoutByName.SearchWorkoutByNameInputBoundary;
import use_case.SearchWorkoutByName.SearchWorkoutByNameInteractor;
import use_case.SearchWorkoutByName.SearchWorkoutByNameOutputBoundary;
import use_case.WorkoutDataAccessInterface;
import view.SearchWorkoutByNameView;

import javax.swing.*;

public class SearchWorkoutByNameUseCaseFactory
{
    private SearchWorkoutByNameUseCaseFactory() {}

    public static SearchWorkoutByNameView create(SearchWorkoutByNameViewModel searchWorkoutByNameViewModel, ViewManagerModel viewManagerModel, WorkoutViewModel workoutViewModel, JFrame application)
    {
        SearchWorkoutByNameController SearchWorkoutByNameController = createcontroller(application);

        return new SearchWorkoutByNameView(searchWorkoutByNameViewModel, viewManagerModel, workoutViewModel, SearchWorkoutByNameController);
    }



    private static SearchWorkoutByNameController createcontroller (JFrame application) {

        WorkoutDataAccessInterface workoutDataAccessInterface = new WDataAccess();

        SearchWorkoutByNameOutputBoundary outputBoundary = new SearchWorkoutByNamePresenter(application);

        SearchWorkoutByNameInputBoundary searchWorkoutByNameInputBoundary = new SearchWorkoutByNameInteractor(outputBoundary, workoutDataAccessInterface);

        return new SearchWorkoutByNameController(searchWorkoutByNameInputBoundary);

    }





}
