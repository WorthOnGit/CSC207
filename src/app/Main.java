package app;

import DataAccess.FileUserDataAccessObject;
import entity.CommonUserFactory;
import interface_adapter.CalorieCounter.CalorieCounterViewModel;
import interface_adapter.RecipePageViewModel.RecipePageViewModel;
import interface_adapter.MealPlannerPageViewModel.MealPlannerViewModel;
import interface_adapter.SearchByMuscle.SearchByMuscleViewModel;
import interface_adapter.SearchWorkoutByName.SearchWorkoutByNameViewModel;
import interface_adapter.StartPage.StartPageViewModel;
import interface_adapter.Workout.WorkoutViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

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
        
        MealPlannerViewModel mealPlannerViewModel=new MealPlannerViewModel();
        
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
    
        
        MealPlannerPageView mealPlannerPageView= MealPlannerUseCaseFactory.create(viewManagerModel,  mealPlannerViewModel, startPageViewModel, application);
        views.add(mealPlannerPageView,mealPlannerPageView.viewName);

    
    /* cehck this linen maybe some errrorrr   */
        StartPageView startPageView = StartPageUseCaseFactory.create(viewManagerModel, startPageViewModel, recipePageViewModel,workoutViewModel, loginViewModel, signupViewModel, new CalorieCounterViewModel(), mealPlannerViewModel);
        views.add(startPageView, startPageView.viewName);

        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject);
        views.add(signupView, signupView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel, userDataAccessObject);
        views.add(loginView, loginView.viewName);

        LoggedInView loggedInView = new LoggedInView(viewManagerModel, loggedInViewModel);
        views.add(loggedInView, loggedInView.viewName);

        WorkoutView workoutView = new WorkoutView(workoutViewModel, viewManagerModel, startPageViewModel, searchByMuscleViewModel, searchWorkoutByNameViewModel);
        views.add(workoutView, workoutView.viewName);

        SearchByMuscleView searchByMuscleView = SearchByMuscleUseCaseFactory.create(searchByMuscleViewModel, workoutViewModel, viewManagerModel, application);
        views.add(searchByMuscleView, searchByMuscleView.viewName);

        SearchWorkoutByNameView searchWorkoutByNameView = SearchWorkoutByNameUseCaseFactory.create(searchWorkoutByNameViewModel, viewManagerModel, workoutViewModel, application);
        views.add(searchWorkoutByNameView, searchWorkoutByNameView.viewName);

        viewManagerModel.setActiveView(startPageView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}