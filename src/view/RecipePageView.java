package view;

import interface_adapter.RecipePageViewModel.RecipePageState;
import interface_adapter.RecipePageViewModel.RecipePageViewModel;
import interface_adapter.RecipeCancelButton.RecipeDoneController;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;

public class RecipePageView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "Recipe Search View";

    private final RecipePageViewModel recipePageViewModel;

    private final RecipeDoneController recipeDoneController;
    private final JTextField recipenameInputField = new JTextField(15);
    private final JButton search;
    private final JButton Done;
    private final JSlider caloriesSlider;

    // Use JComboBox for cuisine types
    private final JComboBox<String> cuisineTypeComboBox;

    // JList for diet labels
    private final JList<String> dietLabelList;

    // JLabel to display the exact value of the slider
    private final JLabel caloriesValueLabel = new JLabel("Calories: 0");

    // JLabel to display selected diet labels
    private final JLabel selectedDietLabelsLabel = new JLabel();

    public RecipePageView(RecipePageViewModel recipePageViewModel, RecipeDoneController recipeDoneController) {
        this.recipeDoneController = recipeDoneController;
        this.recipePageViewModel = recipePageViewModel;
        this.recipePageViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(RecipePageViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);






        // Create a JSlider for calories
        caloriesSlider = new JSlider(JSlider.HORIZONTAL, 0, 3000, 0);
        caloriesSlider.setMajorTickSpacing(500);
        caloriesSlider.setPaintTicks(true);
        caloriesSlider.setPaintLabels(true);

        LabelTextPanel recipenameinfo = new LabelTextPanel(
                new JLabel(RecipePageViewModel.RECIPE_NAME_LABEL), recipenameInputField);

        LabelSliderPanel caloriesinfo = new LabelSliderPanel(
                caloriesValueLabel, caloriesSlider);



        // Create JComboBox for cuisine types
        String[] cuisineTypes = {"American", "Asian", "British", "Caribbean", "Central European", "Chinese",
                "Eastern European", "French", "Greek", "Indian", "Italian", "Japanese", "Korean", "Kosher",
                "Mediterranean", "Mexican", "Middle Eastern", "Nordic", "South American", "Southeast Asian", "World"};

        cuisineTypeComboBox = new JComboBox<>(cuisineTypes);
        LabelComboBoxPanel countryoforigininfo = new LabelComboBoxPanel(
                new JLabel(RecipePageViewModel.COUSINE_TYPE_LABEL), cuisineTypeComboBox);





        // Create a JList for diet labels
        String[] dietLabels = {"Balanced", "High-Fiber", "High-Protein", "Low-Carb", "Low-Fat", "Low-Sodium"};
        dietLabelList = new JList<>(dietLabels);
        dietLabelList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        // Create a JScrollPane for the diet label list
        JScrollPane dietLabelScrollPane = new JScrollPane(dietLabelList);








        JPanel buttons = new JPanel();
        search = new JButton(RecipePageViewModel.SEARCH_BUTTON_LABEL);
        buttons.add(search);
        Done = new JButton(RecipePageViewModel.Done_BUTTON_LABEL);
        buttons.add(Done);






        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(search)) {
                    RecipePageState currentState = recipePageViewModel.getState();
                    // Access the calories, cuisine type, and diet label values and use them as needed
                    int calories = currentState.getCalories();
                    String cuisineType = (String) cuisineTypeComboBox.getSelectedItem();
                    String[] selectedDietLabels = dietLabelList.getSelectedValuesList().toArray(new String[0]);

                    // Continue with your existing code...
                }
            }
        });



        cuisineTypeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RecipePageState currentState = recipePageViewModel.getState();
                currentState.setCountryoforigin((String) cuisineTypeComboBox.getSelectedItem());
                recipePageViewModel.setState(currentState);
            }
        });

        // Add a ListSelectionListener to update the value in the RecipePageState when dietLabelList is changed
        dietLabelList.addListSelectionListener(e -> {
            RecipePageState currentState = recipePageViewModel.getState();
            currentState.setDietLabels(dietLabelList.getSelectedValuesList());
            System.out.println(dietLabelList.getSelectedValuesList());
            recipePageViewModel.setState(currentState);
            // Update the label to display the selected diet labels
            selectedDietLabelsLabel.setText("Selected Diet Labels: " + Arrays.toString(dietLabelList.getSelectedValuesList().toArray()));

        });

        Done.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(Done)) {
                    recipeDoneController.execute();
                }
            }
        });

        // Add a ChangeListener to update the value in the RecipePageState and the JLabel
        caloriesSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                RecipePageState currentState = recipePageViewModel.getState();
                int caloriesValue = caloriesSlider.getValue();
                currentState.setCalories(caloriesValue);
                recipePageViewModel.setState(currentState);

                // Update the JLabel to display the exact value
                caloriesValueLabel.setText("Calories: " + caloriesValue);
            }
        });

        recipenameInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                RecipePageState currentState = recipePageViewModel.getState();
                String text = recipenameInputField.getText() + e.getKeyChar();
                currentState.setRecipename(text);
                recipePageViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(recipenameinfo);
        this.add(countryoforigininfo);
        this.add(new LabelScrollPanePanel(new JLabel("Diet Label"), dietLabelScrollPane));
        this.add(selectedDietLabelsLabel); // Add the label directly to the panel
        this.add(caloriesinfo);
        this.add(buttons);
    }





    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "Cancel not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        RecipePageState state = (RecipePageState) evt.getNewValue();
        setFields(state);
    }

    private void setFields(RecipePageState state) {
        recipenameInputField.setText(state.getRecipename());
        caloriesSlider.setValue(state.getCalories());
        cuisineTypeComboBox.setSelectedItem(state.getCountryoforigin());
        dietLabelList.setSelectedIndices(new int[0]);
    }
}
