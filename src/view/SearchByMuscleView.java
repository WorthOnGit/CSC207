package view;

import interface_adapter.RecipePageViewModel.RecipePageViewModel;
import interface_adapter.SearchByMuscle.SearchByMuscleController;
import interface_adapter.SearchByMuscle.SearchByMuscleState;
import interface_adapter.SearchByMuscle.SearchByMuscleViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.Workout.WorkoutViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class SearchByMuscleView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "Search By Muscle View";

    private final SearchByMuscleViewModel searchByMuscleViewModel;

    private final SearchByMuscleController searchByMuscleController;

    private final JButton search;
    private final JButton clear;
    private final JButton Done;
    // JList for diet labels
    private final JList<String> muscleList;

    public SearchByMuscleView(SearchByMuscleViewModel searchByMuscleViewModel, WorkoutViewModel workoutViewModel, ViewManagerModel viewManagerModel, SearchByMuscleController searchByMuscleController) {
        this.searchByMuscleViewModel = searchByMuscleViewModel;
        this.searchByMuscleController = searchByMuscleController;
        this.searchByMuscleViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(searchByMuscleViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create a JList for health labels
        String[] musclelabels = {"Abductors", "Abs", "Adductors",
                "Biceps",
                "Calves", "Chest",
                "Forearms",
                "Glutes",
                "Hamstrings", "Hip Flexors",
                "IT Band",
                "Lats", "Lower Back",
                "Middle Back",
                "Neck",
                "Obliques",
                "Palmer Fascia", "Plantar Fascia",
                "Quads",
                "Shoulders",
                "Traps", "Triceps"
                ,"Upper Back"
                };

        muscleList = new JList<>(musclelabels);
        muscleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Create a JScrollPane for the health label list
        JScrollPane musclesScrollPane = new JScrollPane(muscleList);
        musclesScrollPane.setPreferredSize(new Dimension(200, 300));






        JPanel buttons = new JPanel();
        search = new JButton(RecipePageViewModel.SEARCH_BUTTON_LABEL);
        buttons.add(search);
        clear = new JButton("Clear");
//        buttons.add(clear);
        Done = new JButton(RecipePageViewModel.Done_BUTTON_LABEL);
        buttons.add(Done);






        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(search)) {
                    searchByMuscleController.execute((ArrayList) muscleList.getSelectedValuesList());
                    System.out.println(muscleList.getSelectedValuesList());



                }
            }
        });



//        // Add a ListSelectionListener to update the value in the RecipePageState when healthLabelList is changed
        muscleList.addListSelectionListener(e -> {
            SearchByMuscleState currentState = searchByMuscleViewModel.getState();;
            currentState.setmuscles(muscleList.getSelectedValuesList());
            searchByMuscleViewModel.setState(currentState);

            // Update the label to display the selected health labels
        });


        Done.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(Done)) {
                    viewManagerModel.setActiveView(workoutViewModel.getViewName());
                    viewManagerModel.firePropertyChanged();
                }
            }
        });


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(new LabelScrollPanePanel(new JLabel("Choose Muscle\n"), musclesScrollPane));
        this.add(buttons);
    }





    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "Cancel not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SearchByMuscleState state = (SearchByMuscleState) evt.getNewValue();
        setFields(state);
    }

    private void setFields(SearchByMuscleState state) {
        muscleList.setSelectedIndices(new int[0]);
    }
}
