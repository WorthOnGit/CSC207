package app;


import interface_adapter.RecipeCancelButton.*;

import interface_adapter.StartPage.StartPageViewModel;
import interface_adapter.RecipePageViewModel.RecipePageViewModel;
import interface_adapter.*;
import use_case.RecipeCancelButton.RecipeCancelInputBoundary;
import use_case.RecipeCancelButton.RecipeCancelInteractor;
import use_case.RecipeCancelButton.RecipeCancelOutputBoundary;
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