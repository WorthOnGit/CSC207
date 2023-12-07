package app;

import DataAccess.CDataAccess;
import interface_adapter.CalorieCounter.CalorieCounterController;
import interface_adapter.CalorieCounter.CalorieCounterPresenter;
import interface_adapter.CalorieCounter.CalorieCounterViewModel;
import interface_adapter.StartPage.StartPageViewModel;
import interface_adapter.ViewManagerModel;
import use_case.CalorieCounter.CalorieCounterInputBoundary;
import use_case.CalorieCounter.CalorieCounterInteractor;
import use_case.CalorieCounter.CalorieCounterOutputBoundary;
import use_case.CalorieCounterDataAccessInterface;
import view.CalorieCounterView;

import javax.swing.*;

public class CalorieCounterUseCaseFactory {

    public static CalorieCounterView create(ViewManagerModel viewManagerModel,
                                            CalorieCounterViewModel calorieCounterViewModel,
                                            StartPageViewModel startPageViewModel,
                                            JFrame application) {

        CalorieCounterController calorieCounterController = createcontroller(application);

        return new CalorieCounterView(calorieCounterViewModel, viewManagerModel, startPageViewModel, calorieCounterController);
    }

    private static CalorieCounterController createcontroller(JFrame application) {

        CalorieCounterDataAccessInterface calorieCounterDataAccessInterface = new CDataAccess();

        CalorieCounterOutputBoundary outputBoundary = new CalorieCounterPresenter(application);

        CalorieCounterInputBoundary calorieCounterInputBoundary = new CalorieCounterInteractor(outputBoundary, calorieCounterDataAccessInterface);

        return new CalorieCounterController(calorieCounterInputBoundary);

    }
}
