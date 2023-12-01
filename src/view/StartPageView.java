package view;

import interface_adapter.StartPage.RecipePageButton.RecipeSearchController;
import interface_adapter.StartPage.StartPageViewModel;
import interface_adapter.StartPage.StartPageState;
import interface_adapter.ViewManagerModel;
import interface_adapter.Workout.WorkoutViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class StartPageView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "start page";

    private final StartPageViewModel StartPageViewModel;
    private final RecipeSearchController RecipeSearchController;
    private final JButton recipe_search;
    private final JButton plan_meal;

    private final JButton workout_search;

    private final JButton calorie_count;


    public StartPageView(StartPageViewModel signupViewModel, interface_adapter.StartPage.RecipePageButton.RecipeSearchController recipeSearchController, WorkoutViewModel workoutViewModel, ViewManagerModel viewManagerModel) {
        this.StartPageViewModel = signupViewModel;
        this.RecipeSearchController = recipeSearchController;
        signupViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(StartPageViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);


        JPanel buttons = new JPanel();
        recipe_search = new JButton(StartPageViewModel.recipe_search_BUTTON_LABEL);
        buttons.add(recipe_search);
        plan_meal = new JButton(StartPageViewModel.plan_meal_BUTTON_LABEL);
        buttons.add(plan_meal);
        calorie_count = new JButton(StartPageViewModel.Calorie_counter_BUTTON_LABEL);
        buttons.add(calorie_count);
        workout_search = new JButton(StartPageViewModel.Workout_BUTTON_LABEL);
        buttons.add(workout_search);

        recipe_search.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(recipe_search)) {
                            RecipeSearchController.execute();

                        }

                    }
                }
        );

        plan_meal.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(plan_meal)) {

                        }
                    }
                }
        );

        calorie_count.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(calorie_count)) {

                        }

                    }
                }
        );

        workout_search.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(workout_search)) {
                            viewManagerModel.setActiveView(workoutViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                        }

                    }
                }
        );



        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "Cancel not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        StartPageState state = (StartPageState) evt.getNewValue();
    }
}