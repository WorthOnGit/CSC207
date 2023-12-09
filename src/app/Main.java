package app;

import DataAccess.FileUserDataAccessObject;
import entity.CommonUserFactory;
import interface_adapter.CalorieCounter.CalorieCounterViewModel;
import interface_adapter.RecipePageViewModel.RecipePageViewModel;
import interface_adapter.SearchByMuscle.SearchByMuscleViewModel;
import interface_adapter.SearchWorkoutByName.SearchWorkoutByNameViewModel;
import interface_adapter.StartPage.StartPageViewModel;
import interface_adapter.Workout.WorkoutViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.MealPlanner.MealPlanViewModel;
import interface_adapter.MealPlanner.MealPlanController;
import DataAccess.MDataAccess;
import view.MealPlanView;
import view.*;


import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.

        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // The main application window.
        JFrame application = new JFrame("Fitness App");
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

        CalorieCounterViewModel calorieCounterViewModel = new CalorieCounterViewModel();

        LoginViewModel loginViewModel = new LoginViewModel();
        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();

        FileUserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new FileUserDataAccessObject("./users.csv", new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        WorkoutViewModel workoutViewModel = new WorkoutViewModel();
        SearchByMuscleViewModel searchByMuscleViewModel = new SearchByMuscleViewModel();
        SearchWorkoutByNameViewModel searchWorkoutByNameViewModel = new SearchWorkoutByNameViewModel();

        RecipePageView recipePageView = RecipeSearchUseCaseFactory.create(viewManagerModel, recipePageViewModel, startPageViewModel, application);
        views.add(recipePageView, recipePageView.viewName);


        StartPageView startPageView = StartPageUseCaseFactory.create(viewManagerModel, startPageViewModel, recipePageViewModel, workoutViewModel, loginViewModel,signupViewModel, calorieCounterViewModel );

        startPageView.setBackground(Color.BLACK);


        views.add(startPageView, startPageView.viewName);

        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject);
        views.add(signupView, signupView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel, userDataAccessObject);
        views.add(loginView, loginView.viewName);

        LoggedInView loggedInView = new LoggedInView(viewManagerModel, loggedInViewModel);
        views.add(loggedInView, loggedInView.viewName);

        WorkoutView workoutView = new WorkoutView(workoutViewModel, viewManagerModel, startPageViewModel, searchByMuscleViewModel, searchWorkoutByNameViewModel);
        views.add(workoutView, workoutView.viewName);

        CalorieCounterView calorieCounterView = CalorieCounterUseCaseFactory.create(viewManagerModel, calorieCounterViewModel, startPageViewModel, application);
        views.add(calorieCounterView, calorieCounterView.viewName);

        SearchByMuscleView searchByMuscleView = SearchByMuscleUseCaseFactory.create(searchByMuscleViewModel, workoutViewModel, viewManagerModel, application);
        views.add(searchByMuscleView, searchByMuscleView.viewName);

        SearchWorkoutByNameView searchWorkoutByNameView = SearchWorkoutByNameUseCaseFactory.create(searchWorkoutByNameViewModel, viewManagerModel, workoutViewModel, application);
        views.add(searchWorkoutByNameView, searchWorkoutByNameView.viewName);

        MealPlanView mealPlanView = MealPlanUseCaseFactory.create(viewManagerModel, application);
        views.add(mealPlanView, mealPlanView.viewName);

        viewManagerModel.setActiveView(startPageView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}