package app;

import interface_adapter.CalorieCounter.CalorieCounterViewModel;
import interface_adapter.MealPlannerPageViewModel.MealPlannerViewModel;
import interface_adapter.StartPage.MealPlannerPageButton.MealPlannerController;
import interface_adapter.StartPage.StartPageViewModel;
import interface_adapter.*;
import interface_adapter.RecipePageViewModel.RecipePageViewModel;
import interface_adapter.Workout.WorkoutViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import use_case.MealPlannerPageButton.MealPlannerSearchInputBoundary;
import use_case.MealPlannerPageButton.MealPlannerSearchInteractor;
import use_case.MealPlannerPageButton.MealPlannerSearchOutputBoundary;
import use_case.RecipePageButton.RecipeSeachInteractor;
import use_case.RecipePageButton.RecipeSearchInputBoundary;
import use_case.RecipePageButton.RecipeSearchOutputBoundary;
import view.StartPageView;
import interface_adapter.StartPage.RecipePageButton.RecipeSearchController;
import interface_adapter.StartPage.RecipePageButton.RecipeSearchPresenter;
import interface_adapter.StartPage.MealPlannerPageButton.MealPlannerPresenter;
import use_case.MealPlannerPageButton.MealPlannerSearchInputBoundary;
import use_case.MealPlannerPageButton.MealPlannerSearchInteractor;
import use_case.MealPlannerPageButton.MealPlannerSearchOutputBoundary;
public class StartPageUseCaseFactory {

    /** Prevent instantiation. */
    private StartPageUseCaseFactory() {}

    public static StartPageView create(

            ViewManagerModel viewManagerModel, StartPageViewModel StartPageViewModel, RecipePageViewModel signupViewModel,
            WorkoutViewModel workoutViewModel, LoginViewModel loginViewModel, SignupViewModel viewModel, CalorieCounterViewModel
                    calorieCounterViewModel, MealPlannerViewModel mealPlannerViewModel) {
        RecipeSearchController RecipeSearchController = createcontroller(viewManagerModel, signupViewModel);

        MealPlannerController mealPlannerController = createcontroller(viewManagerModel, mealPlannerViewModel);

        return new StartPageView(StartPageViewModel, RecipeSearchController, workoutViewModel, loginViewModel, viewManagerModel, viewModel, calorieCounterViewModel, mealPlannerController);


    }

    private static RecipeSearchController createcontroller(ViewManagerModel viewManagerModel, RecipePageViewModel signupViewModel) {

        RecipeSearchOutputBoundary recipeSearchPresenter = new RecipeSearchPresenter(viewManagerModel, signupViewModel);

        RecipeSearchInputBoundary recipeSearchusecaseinteractor = new RecipeSeachInteractor(recipeSearchPresenter);

        return new RecipeSearchController(recipeSearchusecaseinteractor);


    }

    private static MealPlannerController createcontroller(ViewManagerModel viewManagerModel, MealPlannerViewModel mealPlannerViewModel) {

        MealPlannerSearchOutputBoundary mealPlannerSearchPresenter = new MealPlannerPresenter(viewManagerModel, mealPlannerViewModel);

        MealPlannerSearchInputBoundary mealPlannerSearchInteractor = new MealPlannerSearchInteractor(mealPlannerSearchPresenter);

        return new MealPlannerController(mealPlannerSearchInteractor);
    }
}

