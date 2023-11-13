package app;


import DataAccess.DataAccess;
import interface_adapter.RecipeDoneButton.*;

import interface_adapter.RecipePageButton.RecipeSearchPresenter;
import interface_adapter.StartPage.StartPageViewModel;
import interface_adapter.RecipePageViewModel.RecipePageViewModel;
import interface_adapter.*;
import use_case.DataAccessInterface;
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

        return new RecipePageView(recipePageViewModel, controller, searchcontroller);

    }

    private static RecipeDoneController createcontroller(ViewManagerModel viewManagerModel, StartPageViewModel startpageViewModel, RecipePageViewModel recipePageViewModel) {

        RecipeDoneOutputBoundary recipecancelPresenter = new RecipeDonePresenter(viewManagerModel, startpageViewModel);

        RecipeDoneInputBoundary recipecancelusecaseinteractor = new RecipeDoneInteractor(recipecancelPresenter, recipePageViewModel, recipePageViewModel);

        return new RecipeDoneController(recipecancelusecaseinteractor);


    }

    private static RecipeSearchButtonController createSearchcontroller(JFrame view) {

        RecipeSearchButtonOutputBoundary recipeSearchbuttonPresenter = new RecipeSeatchButtonPresenter(view);

        DataAccessInterface dataAccess = new DataAccess();

        RecipeSearchButtonInputBoundary recipeSearchusecaseinteractor = new RecipeSearchButtonInteractor(recipeSearchbuttonPresenter, dataAccess);

        return new RecipeSearchButtonController(recipeSearchusecaseinteractor);
    }
}