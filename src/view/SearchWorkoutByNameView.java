package view;

import interface_adapter.RecipePageViewModel.RecipePageState;
import interface_adapter.RecipePageViewModel.RecipePageViewModel;
import interface_adapter.SearchWorkoutByName.SearchWorkoutByNameController;
import interface_adapter.SearchWorkoutByName.SearchWorkoutByNameState;
import interface_adapter.SearchWorkoutByName.SearchWorkoutByNameViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.Workout.WorkoutViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SearchWorkoutByNameView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "Workout Name Search View";

    private final SearchWorkoutByNameViewModel searchWorkoutByNameViewModel;

    private final JTextField workoutnameInputField = new JTextField(25);
    private final JButton search;
    private final JButton clear;
    private final JButton Done;

    public SearchWorkoutByNameView(SearchWorkoutByNameViewModel searchWorkoutByNameViewModel, ViewManagerModel viewManagerModel, WorkoutViewModel workoutViewModel, SearchWorkoutByNameController searchWorkoutByNameController) {
        this.searchWorkoutByNameViewModel = searchWorkoutByNameViewModel;
        this.searchWorkoutByNameViewModel.addPropertyChangeListener(this);

        // Larger font for label and input field
        Font largerFont = new Font("Arial", Font.PLAIN, 30);

        JLabel workoutnameLabel = new JLabel(searchWorkoutByNameViewModel.WORKOUT_NAME_LABEL);
        workoutnameLabel.setFont(largerFont);

        workoutnameInputField.setFont(largerFont);

        LabelTextPanel workoutnameinfo = new LabelTextPanel(workoutnameLabel, workoutnameInputField);


        JPanel buttons = new JPanel();
        search = new JButton("Search"); // Adjusted label for clarity
        search.setPreferredSize(new Dimension(150, 50));
        buttons.add(search);
        clear = new JButton("Clear");
        clear.setPreferredSize(new Dimension(150, 50));
        buttons.add(clear);
        Done = new JButton("Done");
        Done.setPreferredSize(new Dimension(150, 50));
        buttons.add(Done);

        // Set a larger font for the buttons
        Font buttonFont = new Font("Arial", Font.PLAIN, 20);
        search.setFont(buttonFont);
        clear.setFont(buttonFont);
        Done.setFont(buttonFont);

        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(search)) {
                    SearchWorkoutByNameState currentState = searchWorkoutByNameViewModel.getState();
                    searchWorkoutByNameController.execute(currentState.getworkoutname());
                }
            }
        });

        clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(clear)) {
                    SearchWorkoutByNameState currentState = searchWorkoutByNameViewModel.getState();
                    currentState.setworkoutname("");
                    searchWorkoutByNameViewModel.setState(currentState);
                    searchWorkoutByNameViewModel.firePropertyChanged();
                }
            }
        });

        Done.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(Done)) {
                    SearchWorkoutByNameState currentState = searchWorkoutByNameViewModel.getState();
                    currentState.setworkoutname("");
                    searchWorkoutByNameViewModel.setState(currentState);
                    searchWorkoutByNameViewModel.firePropertyChanged();
                    viewManagerModel.setActiveView(workoutViewModel.getViewName());
                    viewManagerModel.firePropertyChanged();
                }
            }
        });

        workoutnameInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                SearchWorkoutByNameState currentState = searchWorkoutByNameViewModel.getState();
                String text = workoutnameInputField.getText() + e.getKeyChar();
                currentState.setworkoutname(text);
                searchWorkoutByNameViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


        JLabel Title = new JLabel("Search Workout By Muscle\n");
        Title.setFont(new Font(Title.getFont().getName(), Font.PLAIN, 80));
        Title.setAlignmentX(Component.CENTER_ALIGNMENT);


        this.add(Title);
        this.add(Box.createVerticalGlue());  // Adds flexible space
        this.add(workoutnameinfo);
        this.add(Box.createVerticalStrut(20));  // Adds flexible space
        this.add(buttons);

        // Center the components
        workoutnameinfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttons.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "Cancel not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SearchWorkoutByNameState state = (SearchWorkoutByNameState) evt.getNewValue();
        setFields(state);
    }

    private void setFields(SearchWorkoutByNameState state) {
        workoutnameInputField.setText(state.getworkoutname());
    }
}
