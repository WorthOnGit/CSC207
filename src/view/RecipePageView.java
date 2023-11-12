package view;

import interface_adapter.RecipePageViewModel.RecipePageState;
import interface_adapter.RecipePageViewModel.RecipePageViewModel;
import interface_adapter.RecipeCancelButton.RecipeCancelController;

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

public class RecipePageView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "Recipe Search View";

    private final RecipePageViewModel recipePageViewModel;

    private final RecipeCancelController recipeDoneController;
    private final JTextField recipenameInputField = new JTextField(15);
    private final JButton search;
    private final JButton Done;
    private final JSlider caloriesSlider;

    // Use JComboBox for cuisine types
    private final JComboBox<String> cuisineTypeComboBox;

    public RecipePageView(RecipePageViewModel recipePageViewModel, RecipeCancelController recipeDoneController) {

        this.recipeDoneController = recipeDoneController;

        this.recipePageViewModel = recipePageViewModel;
        recipePageViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(RecipePageViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create a JSlider for calories
        caloriesSlider = new JSlider(JSlider.HORIZONTAL, 0, 3000, 0);
        caloriesSlider.setMajorTickSpacing(500);
        caloriesSlider.setPaintTicks(true);
        caloriesSlider.setPaintLabels(true);

        LabelTextPanel recipenameinfo = new LabelTextPanel(
                new JLabel(RecipePageViewModel.RECIPE_NAME_LABEL), recipenameInputField);

        // Create JComboBox for cuisine types
        String[] cuisineTypes = {"American", "Asian", "British", "Caribbean", "Central European", "Chinese",
                "Eastern European", "French", "Greek", "Indian", "Italian", "Japanese", "Korean", "Kosher",
                "Mediterranean", "Mexican", "Middle Eastern", "Nordic", "South American", "Southeast Asian", "World"};

        cuisineTypeComboBox = new JComboBox<>(cuisineTypes);
        LabelComboBoxPanel countryoforigininfo = new LabelComboBoxPanel(
                new JLabel(RecipePageViewModel.COUSINE_TYPE_LABEL), cuisineTypeComboBox);

        LabelSliderPanel caloriesinfo = new LabelSliderPanel(
                new JLabel(RecipePageViewModel.CALORIES_LABEL), caloriesSlider);

        JPanel buttons = new JPanel();
        search = new JButton(RecipePageViewModel.SEARCH_BUTTON_LABEL);
        buttons.add(search);
        Done = new JButton(RecipePageViewModel.Done_BUTTON_LABEL);
        buttons.add(Done);

        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(search)) {
                    RecipePageState currentState = recipePageViewModel.getState();
                    // Access the calories and cuisine type values and use them as needed
                    int calories = currentState.getCalories();
                    String cuisineType = (String) cuisineTypeComboBox.getSelectedItem();

                    // Continue with your existing code...
                }
            }
        });

        Done.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(Done)) {
                    recipeDoneController.execute();
                }
            }
        });

        // Add a ChangeListener to update the value in the RecipePageState
        caloriesSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                RecipePageState currentState = recipePageViewModel.getState();
                currentState.setCalories(caloriesSlider.getValue());
                recipePageViewModel.setState(currentState);
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
        this.add(caloriesinfo);
        this.add(buttons);
    }

    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "Cancel not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        RecipePageState state = (RecipePageState) evt.getNewValue();
        if (state.getRecipenameError() != null) {
            JOptionPane.showMessageDialog(this, state.getRecipenameError());
        }
    }
}


