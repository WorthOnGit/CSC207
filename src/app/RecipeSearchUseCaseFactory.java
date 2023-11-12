package app;


import interface_adapter.RecipeCancel.*;

import interface_adapter.RecipeSearchButton.RecipeSearchController;
import interface_adapter.RecipeSearchButton.RecipeSearchPresenter;
import interface_adapter.StartPage.StartPageViewModel;
import interface_adapter.recipepage.RecipePageViewModel;
import interface_adapter.*;
import use_case.RecipeSearchButton.RecipeSeachInteractor;
import use_case.RecipeSearchButton.RecipeSearchInputBoundary;
import use_case.RecipeSearchButton.RecipeSearchOutputBoundary;
import use_case.recipecancel.RecipeCancelInputBoundary;
import use_case.recipecancel.RecipeCancelInteractor;
import use_case.recipecancel.RecipeCancelOutputBoundary;
import view.RecipePageView;


public class RecipeSearchUseCaseFactory {

    /** Prevent instantiation. */
    private RecipeSearchUseCaseFactory() {}

    public static RecipePageView create(
            ViewManagerModel viewManagerModel, RecipePageViewModel recipePageViewModel, StartPageViewModel startpageViewModel) {

        RecipeCancelController controller = createcontroller(viewManagerModel, startpageViewModel);

        return new RecipePageView(recipePageViewModel, controller);

    }

    private static RecipeCancelController createcontroller(ViewManagerModel viewManagerModel, StartPageViewModel startpageViewModel) {

        RecipeCancelOutputBoundary recipecancelPresenter = new RecipeCancelPresenter(viewManagerModel, startpageViewModel);

        RecipeCancelInputBoundary recipecancelusecaseinteractor = new RecipeCancelInteractor(recipecancelPresenter);

        return new RecipeCancelController(recipecancelusecaseinteractor);


    }

}