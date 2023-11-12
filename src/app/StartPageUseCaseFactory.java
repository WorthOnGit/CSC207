package app;

import interface_adapter.StartPage.StartPageViewModel;
import interface_adapter.*;
import interface_adapter.recipepage.RecipePageViewModel;
import use_case.RecipeSearchButton.RecipeSeachInteractor;
import use_case.RecipeSearchButton.RecipeSearchInputBoundary;
import use_case.RecipeSearchButton.RecipeSearchOutputBoundary;
import view.StartPageView;
import interface_adapter.RecipeSearchButton.RecipeSearchController;
import interface_adapter.RecipeSearchButton.RecipeSearchPresenter;
public class StartPageUseCaseFactory {

    /** Prevent instantiation. */
    private StartPageUseCaseFactory() {}

    public static StartPageView create(
            ViewManagerModel viewManagerModel, StartPageViewModel StartPageViewModel, RecipePageViewModel signupViewModel) {
        RecipeSearchController RecipeSearchController = createcontroller(viewManagerModel, signupViewModel);
        return new StartPageView(StartPageViewModel, RecipeSearchController);

    }

    private static RecipeSearchController createcontroller(ViewManagerModel viewManagerModel, RecipePageViewModel signupViewModel) {

        RecipeSearchOutputBoundary recipeSearchPresenter = new RecipeSearchPresenter(viewManagerModel, signupViewModel);

        RecipeSearchInputBoundary recipeSearchusecaseinteractor = new RecipeSeachInteractor(recipeSearchPresenter);

        return new RecipeSearchController(recipeSearchusecaseinteractor);


    }
}