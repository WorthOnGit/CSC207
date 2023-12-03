package app;

import interface_adapter.StartPage.StartPageViewModel;
import interface_adapter.*;
import interface_adapter.RecipePageViewModel.RecipePageViewModel;
import interface_adapter.Workout.WorkoutViewModel;
import use_case.RecipePageButton.RecipeSeachInteractor;
import use_case.RecipePageButton.RecipeSearchInputBoundary;
import use_case.RecipePageButton.RecipeSearchOutputBoundary;
import view.StartPageView;
import interface_adapter.StartPage.RecipePageButton.RecipeSearchController;
import interface_adapter.StartPage.RecipePageButton.RecipeSearchPresenter;
public class StartPageUseCaseFactory {

    /** Prevent instantiation. */
    private StartPageUseCaseFactory() {}

    public static StartPageView create(
            ViewManagerModel viewManagerModel, StartPageViewModel StartPageViewModel, RecipePageViewModel signupViewModel, WorkoutViewModel workoutViewModel) {
        RecipeSearchController RecipeSearchController = createcontroller(viewManagerModel, signupViewModel);
        return new StartPageView(StartPageViewModel, RecipeSearchController, workoutViewModel, viewManagerModel);

    }

    private static RecipeSearchController createcontroller(ViewManagerModel viewManagerModel, RecipePageViewModel signupViewModel) {

        RecipeSearchOutputBoundary recipeSearchPresenter = new RecipeSearchPresenter(viewManagerModel, signupViewModel);

        RecipeSearchInputBoundary recipeSearchusecaseinteractor = new RecipeSeachInteractor(recipeSearchPresenter);

        return new RecipeSearchController(recipeSearchusecaseinteractor);


    }
}