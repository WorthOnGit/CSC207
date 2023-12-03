package view;

import interface_adapter.SearchByMuscle.SearchByMuscleViewModel;
import interface_adapter.SearchWorkoutByName.SearchWorkoutByNameViewModel;
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



    public WorkoutView(WorkoutViewModel workoutViewModel, ViewManagerModel viewManagerModel, StartPageViewModel startPageViewModel, SearchByMuscleViewModel searchByMuscleViewModel, SearchWorkoutByNameViewModel searchWorkoutByNameViewModel) {
        this.WorkoutViewModel = workoutViewModel;
        workoutViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(WorkoutViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel imagePanel = new JPanel();
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\muaawiz\\IdeaProjects\\CSC207Muaawiz\\src\\studio_logo_5474_delhi.png");
        Image scaledImage = imageIcon.getImage().getScaledInstance(400, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(scaledImageIcon);
        imagePanel.add(imageLabel);

        JPanel buttons = new JPanel();

        Search_muscle_group = new JButton(workoutViewModel.muscle_search_BUTTON_LABEL);
        Search_muscle_group.setPreferredSize(new Dimension(200, 100));
        buttons.add(Search_muscle_group);

        Search_workout = new JButton(workoutViewModel.Workout_BUTTON_LABEL);
        Search_workout.setPreferredSize(new Dimension(200, 100));
        buttons.add(Search_workout);

        cancel.setPreferredSize(new Dimension(200, 100));

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
                            viewManagerModel.setActiveView(searchWorkoutByNameViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();

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



        this.setLayout(new BorderLayout());

        this.add(title, BorderLayout.NORTH);
        this.add(imagePanel, BorderLayout.CENTER);
        this.add(buttons, BorderLayout.SOUTH);
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