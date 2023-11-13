package app;

import interface_adapter.RecipePageViewModel.RecipePageViewModel;
import interface_adapter.StartPage.StartPageViewModel;
import interface_adapter.ViewManagerModel;
import view.RecipePageView;
import view.StartPageView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.

        // The main application window.
        JFrame application = new JFrame("Meal Planner");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();
        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        StartPageViewModel startPageViewModel = new StartPageViewModel();
        RecipePageViewModel recipePageViewModel = new RecipePageViewModel();

        RecipePageView recipePageView = RecipeSearchUseCaseFactory.create(viewManagerModel, recipePageViewModel, startPageViewModel, application);
        views.add(recipePageView, recipePageView.viewName);

        StartPageView startPageView = StartPageUseCaseFactory.create(viewManagerModel, startPageViewModel, recipePageViewModel);
        views.add(startPageView, startPageView.viewName);

        viewManagerModel.setActiveView(startPageView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}