package view;

import interface_adapter.SearchByMuscle.SearchByMuscleViewModel;
import interface_adapter.StartPage.StartPageViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.Workout.WorkoutViewModel;
import interface_adapter.StartPage.StartPageState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class WorkoutView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "Workout page";

    private final WorkoutViewModel WorkoutViewModel;
    private final JButton Search_muscle_group;
    private final JButton Search_workout;

    private final JButton cancel = new JButton("Cancel");



    public WorkoutView(WorkoutViewModel workoutViewModel, ViewManagerModel viewManagerModel, StartPageViewModel startPageViewModel, SearchByMuscleViewModel searchByMuscleViewModel) {
        this.WorkoutViewModel = workoutViewModel;
        workoutViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(WorkoutViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);


        JPanel buttons = new JPanel();

        Search_muscle_group = new JButton(workoutViewModel.muscle_search_BUTTON_LABEL);
        buttons.add(Search_muscle_group);

        Search_workout = new JButton(workoutViewModel.Workout_BUTTON_LABEL);
        buttons.add(Search_workout);


        buttons.add(cancel);

        Search_muscle_group.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(Search_muscle_group)) {
                            viewManagerModel.setActiveView(searchByMuscleViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                        }

                    }
                }
        );

        Search_workout.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(Search_workout)) {
                            // TODO

                        }
                    }
                }
        );

        cancel.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(cancel)) {
                            viewManagerModel.setActiveView(startPageViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();

                        }
                    }
                }
        );



        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(Box.createVerticalGlue());  // Adds flexible space
        this.add(buttons);
        this.add(Box.createVerticalStrut(20));  // Adds flexible space
        this.add(cancel);
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