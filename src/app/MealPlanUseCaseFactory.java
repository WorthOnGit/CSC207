package app;

import DataAccess.MDataAccess;
import interface_adapter.MealPlanner.MealPlanController;
import interface_adapter.MealPlanner.MealPlanViewModel;
import interface_adapter.ViewManagerModel;
import view.MealPlanView;

import javax.swing.*;

public class MealPlanUseCaseFactory {
    public static MealPlanView create(ViewManagerModel viewManager, JFrame application) {
        // Create instances of all components required for the Meal Plan feature
        MealPlanViewModel viewModel = new MealPlanViewModel();
        MDataAccess mDataAccess = new MDataAccess(); // Ensure MDataAccess is properly implemented to interact with Edamam API
        MealPlanController controller = new MealPlanController(mDataAccess, viewModel);
        MealPlanView view = new MealPlanView(viewModel, controller);

        // Optionally, register the view with the view manager here if needed
        // viewManager.registerView(view, view.viewName);

        // Return the fully constructed view
        return view;
    }
}
