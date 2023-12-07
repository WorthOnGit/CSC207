package app;

import interface_adapter.CalorieCounter.CalorieCounterViewModel;
import interface_adapter.StartPage.StartPageViewModel;
import interface_adapter.*;
import interface_adapter.RecipePageViewModel.RecipePageViewModel;
import interface_adapter.Workout.WorkoutViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
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

            ViewManagerModel viewManagerModel, StartPageViewModel StartPageViewModel, RecipePageViewModel signupViewModel, WorkoutViewModel workoutViewModel, LoginViewModel loginViewModel, SignupViewModel viewModel) {
        RecipeSearchController RecipeSearchController = createcontroller(viewManagerModel, signupViewModel);
        return new StartPageView(StartPageViewModel, RecipeSearchController, workoutViewModel, loginViewModel, viewManagerModel, viewModel, calorieCounterViewModel);


    }

    private static RecipeSearchController createcontroller(ViewManagerModel viewManagerModel, RecipePageViewModel signupViewModel) {

        RecipeSearchOutputBoundary recipeSearchPresenter = new RecipeSearchPresenter(viewManagerModel, signupViewModel);

        RecipeSearchInputBoundary recipeSearchusecaseinteractor = new RecipeSeachInteractor(recipeSearchPresenter);

        return new RecipeSearchController(recipeSearchusecaseinteractor);


    }
}