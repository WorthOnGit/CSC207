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
import java.util.List;

public class SearchByMuscleView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "Search By Muscle View";

    private final SearchByMuscleViewModel searchByMuscleViewModel;
    private final SearchByMuscleController searchByMuscleController;

    private final JButton search;
    private final JButton Done;
    // JList for diet labels
    private final JList<String> muscleList;

    public SearchByMuscleView(SearchByMuscleViewModel searchByMuscleViewModel, WorkoutViewModel workoutViewModel, ViewManagerModel viewManagerModel, SearchByMuscleController searchByMuscleController) {
        this.searchByMuscleViewModel = searchByMuscleViewModel;
        this.searchByMuscleController = searchByMuscleController;
        this.searchByMuscleViewModel.addPropertyChangeListener(this);

        // Create a JList for health labels
        String[] musclelabels = {
                "abductors",
                "abs",
                "adductors",
                "biceps",
                "calves",
                "cardiovascular system",
                "delts",
                "forearms",
                "glutes",
                "hamstrings",
                "lats",
                "levator scapulae",
                "pectorals",
                "quads",
                "serratus anterior",
                "spine",
                "traps",
                "triceps",
                "upper back"
        };

        muscleList = new JList<>(musclelabels);
        muscleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        muscleList.setFont(new Font(muscleList.getFont().getName(), Font.PLAIN, 60)); // Increased font size for selections

        // Create a JScrollPane for the health label list
        JScrollPane musclesScrollPane = new JScrollPane(muscleList);
        musclesScrollPane.setPreferredSize(new Dimension(800, 600)); // Increased size

        JPanel buttons = new JPanel();
        search = new JButton(RecipePageViewModel.SEARCH_BUTTON_LABEL);
        search.setPreferredSize(new Dimension(150, 50));
        buttons.add(search);

        Done = new JButton(RecipePageViewModel.Done_BUTTON_LABEL);
        Done.setPreferredSize(new Dimension(150, 50));
        buttons.add(Done);

        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                List<String> selectedValues = muscleList.getSelectedValuesList();

                if (!selectedValues.isEmpty()) {
                    searchByMuscleController.execute((ArrayList) selectedValues);
                    System.out.println(selectedValues);
                } else {
                    // Handle the case where nothing is selected
                    searchByMuscleController.handleNoMusclesSelected();
                }
            }
        });

        muscleList.addListSelectionListener(e -> {
            SearchByMuscleState currentState = searchByMuscleViewModel.getState();
            currentState.setmuscles(muscleList.getSelectedValuesList());
            searchByMuscleViewModel.setState(currentState);
        });

        Done.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                muscleList.setSelectedIndices(new int[0]);
                viewManagerModel.setActiveView(workoutViewModel.getViewName());
                viewManagerModel.firePropertyChanged();
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel thing = new JLabel("Pick One\n");
        thing.setFont(new Font(thing.getFont().getName(), Font.PLAIN, 40));

        JLabel Title = new JLabel("Search Workout By Muscle\n");
        Title.setFont(new Font(Title.getFont().getName(), Font.PLAIN, 80));
        Title.setAlignmentX(Component.CENTER_ALIGNMENT);


        this.add(Title);
        this.add(Box.createRigidArea(new Dimension(0, 50)));
        this.add(new LabelScrollPanePanel(thing, musclesScrollPane));
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
