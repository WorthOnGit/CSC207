package app;


import DataAccess.DataAccess;
import interface_adapter.RecipeDoneButton.*;

import interface_adapter.RecipeSearchButton.RecipeSearchButtonController;
import interface_adapter.RecipeSearchButton.RecipeSeatchButtonPresenter;
import interface_adapter.StartPage.StartPageViewModel;
import interface_adapter.RecipePageViewModel.RecipePageViewModel;
import interface_adapter.*;
import use_case.RecipeDataAccessInterface;
import use_case.RecipeDoneButton.RecipeDoneInputBoundary;
import use_case.RecipeDoneButton.RecipeDoneInteractor;
import use_case.RecipeDoneButton.RecipeDoneOutputBoundary;
import use_case.RecipeSearchButton.RecipeSearchButtonInputBoundary;
import use_case.RecipeSearchButton.RecipeSearchButtonInteractor;
import use_case.RecipeSearchButton.RecipeSearchButtonOutputBoundary;
import view.RecipePageView;

import javax.swing.*;


public class RecipeSearchUseCaseFactory {

    /**
     * Prevent instantiation.
     */
    private RecipeSearchUseCaseFactory() {
    }

    public static RecipePageView create(
            ViewManagerModel viewManagerModel, RecipePageViewModel recipePageViewModel, StartPageViewModel startpageViewModel, JFrame view) {

        RecipeDoneController controller = createcontroller(viewManagerModel, startpageViewModel, recipePageViewModel);

        RecipeSearchButtonController searchcontroller = createSearchcontroller(view);

        // Assuming backgroundImagePath is a placeholder. Replace it with the actual path.
        String backgroundImagePath = "src/rback.jpeg";

        // Pass the backgroundImagePath parameter to the RecipePageView constructor.
        return new RecipePageView(recipePageViewModel, controller, searchcontroller, backgroundImagePath);
    }


    private static RecipeDoneController createcontroller(ViewManagerModel viewManagerModel, StartPageViewModel startpageViewModel, RecipePageViewModel recipePageViewModel) {

        RecipeDoneOutputBoundary recipecancelPresenter = new RecipeDonePresenter(viewManagerModel, startpageViewModel);

        RecipeDoneInputBoundary recipecancelusecaseinteractor = new RecipeDoneInteractor(recipecancelPresenter, recipePageViewModel, recipePageViewModel);

        return new RecipeDoneController(recipecancelusecaseinteractor);


    }

    private static RecipeSearchButtonController createSearchcontroller(JFrame view) {

        RecipeSearchButtonOutputBoundary recipeSearchbuttonPresenter = new RecipeSeatchButtonPresenter(view);

        RecipeDataAccessInterface dataAccess = new DataAccess();

        RecipeSearchButtonInputBoundary recipeSearchusecaseinteractor = new RecipeSearchButtonInteractor(recipeSearchbuttonPresenter, dataAccess);

        return new RecipeSearchButtonController(recipeSearchusecaseinteractor);
    }
}