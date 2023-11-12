package app;


import interface_adapter.RecipeCancelButton.*;

import interface_adapter.StartPage.StartPageViewModel;
import interface_adapter.RecipePageViewModel.RecipePageViewModel;
import interface_adapter.*;
import use_case.RecipeCancelButton.RecipeDoneInputBoundary;
import use_case.RecipeCancelButton.RecipeDoneInteractor;
import use_case.RecipeCancelButton.RecipeDoneOutputBoundary;
import view.RecipePageView;


public class RecipeSearchUseCaseFactory {

    /** Prevent instantiation. */
    private RecipeSearchUseCaseFactory() {}

    public static RecipePageView create(
            ViewManagerModel viewManagerModel, RecipePageViewModel recipePageViewModel, StartPageViewModel startpageViewModel) {

        RecipeDoneController controller = createcontroller(viewManagerModel, startpageViewModel, recipePageViewModel);

        return new RecipePageView(recipePageViewModel, controller);

    }

    private static RecipeDoneController createcontroller(ViewManagerModel viewManagerModel, StartPageViewModel startpageViewModel, RecipePageViewModel recipePageViewModel) {

        RecipeDoneOutputBoundary recipecancelPresenter = new RecipeDonePresenter(viewManagerModel, startpageViewModel);

        RecipeDoneInputBoundary recipecancelusecaseinteractor = new RecipeDoneInteractor(recipecancelPresenter, recipePageViewModel, recipePageViewModel);

        return new RecipeDoneController(recipecancelusecaseinteractor);


    }

}