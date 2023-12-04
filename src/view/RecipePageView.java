package view;

import interface_adapter.RecipePageViewModel.RecipePageState;
import interface_adapter.RecipePageViewModel.RecipePageViewModel;
import interface_adapter.RecipeDoneButton.RecipeDoneController;
import interface_adapter.RecipeSearchButton.RecipeSearchButtonController;

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
import java.util.ArrayList;
import java.util.Arrays;

public class RecipePageView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "Recipe Search View";

    private final RecipePageViewModel recipePageViewModel;

    private final RecipeDoneController recipeDoneController;
    private final RecipeSearchButtonController recipeSearchButtonController;
    private final JTextField recipenameInputField = new JTextField(15);
    private final JButton search;
    private final JButton clear;
    private final JButton Done;
    private final JSlider caloriesSlider;

    // Use JComboBox for cuisine types
    private final JComboBox<String> cuisineTypeComboBox;

    private final JComboBox<String> MealTypeComboBox;

    // JList for diet labels
    private final JList<String> dietLabelList;

    private final JList<String> healthLabelList;

    // JLabel to display the exact value of the slider
    private final JLabel caloriesValueLabel = new JLabel("Calories: 0");

    // JLabel to display selected diet labels
    private final JLabel selectedDietLabelsLabel = new JLabel();

    public RecipePageView(RecipePageViewModel recipePageViewModel, RecipeDoneController recipeDoneController, RecipeSearchButtonController recipeSearchButtonController) {
        this.recipeDoneController = recipeDoneController;
        this.recipePageViewModel = recipePageViewModel;
        this.recipePageViewModel.addPropertyChangeListener(this);
        this.recipeSearchButtonController = recipeSearchButtonController;

        JLabel title = new JLabel(RecipePageViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);






        // Create a JSlider for calories
        caloriesSlider = new JSlider(JSlider.HORIZONTAL, 0, 3000, 0);
        caloriesSlider.setMajorTickSpacing(500);
        caloriesSlider.setPaintTicks(true);
        caloriesSlider.setPaintLabels(true);
        caloriesSlider.setValue(1500);

        LabelTextPanel recipenameinfo = new LabelTextPanel(
                new JLabel(RecipePageViewModel.RECIPE_NAME_LABEL), recipenameInputField);

        // Set a larger font for the input field
        Font largerFont = new Font(recipenameInputField.getFont().getName(), Font.PLAIN, 24);
        recipenameInputField.setFont(largerFont);

        // Set the input field size and alignment
        recipenameInputField.setPreferredSize(new Dimension(250, 40));

        LabelSliderPanel caloriesinfo = new LabelSliderPanel(
                caloriesValueLabel, caloriesSlider);



        // Create JComboBox for cuisine types
        String[] cuisineTypes = {"World", "American", "Asian", "British", "Caribbean", "Central European", "Chinese",
                "Eastern European", "French", "Greek", "Indian", "Italian", "Japanese", "Korean", "Kosher",
                "Mediterranean", "Mexican", "Middle Eastern", "Nordic", "South American", "Southeast Asian"};

        cuisineTypeComboBox = new JComboBox<>(cuisineTypes);
        LabelComboBoxPanel countryoforigininfo = new LabelComboBoxPanel(
                new JLabel(RecipePageViewModel.COUSINE_TYPE_LABEL), cuisineTypeComboBox);


        // Create JComboBox for cuisine types
        String[] MealTypes = {"any", "lunch", "dinner", "brunch", "breakfast", "snack"};

        MealTypeComboBox = new JComboBox<>(MealTypes);
        LabelComboBoxPanel mealtypeinfo = new LabelComboBoxPanel(
                new JLabel(RecipePageViewModel.MEAL_TYPE_LABEL), MealTypeComboBox);



        // Create a JList for diet labels
        String[] dietLabels = {"Balanced", "High-Fiber", "High-Protein", "Low-Carb", "Low-Fat", "Low-Sodium"};
        dietLabelList = new JList<>(dietLabels);
        dietLabelList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        // Create a JScrollPane for the diet label list
        JScrollPane dietLabelScrollPane = new JScrollPane(dietLabelList);




        // Create a JList for health labels
        String[] healthLabels = {"Alcohol-Cocktail", "Alcohol-Free", "Celery-Free", "Crustacean-Free", "Dairy-Free", "DASH",
                "Egg-Free", "Fish-Free", "FODMAP-Free", "Gluten-Free", "Immuno-Supportive", "Keto-Friendly", "Kidney-Friendly",
                "Kosher", "Low Potassium", "Low Sugar", "Lupine-Free", "Mediterranean", "Mollusk-Free", "Mustard-Free",
                "No oil added", "Paleo", "Peanut-Free", "Pescatarian", "Pork-Free", "Red-Meat-Free", "Sesame-Free",
                "Shellfish-Free", "Soy-Free", "Sugar-Conscious", "Sulfite-Free", "Tree-Nut-Free", "Vegan", "Vegetarian", "Wheat-Free"};

        healthLabelList = new JList<>(healthLabels);
        healthLabelList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        // Create a JScrollPane for the health label list
        JScrollPane healthLabelScrollPane = new JScrollPane(healthLabelList);






        JPanel buttons = new JPanel();
        search = new JButton(RecipePageViewModel.SEARCH_BUTTON_LABEL);
        buttons.add(search);
        clear = new JButton("Clear");
        buttons.add(clear);
        Done = new JButton(RecipePageViewModel.Done_BUTTON_LABEL);
        buttons.add(Done);






        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(search)) {
                    recipeSearchButtonController.execute(recipePageViewModel.getState().getRecipename(),
                            recipePageViewModel.getState().getCountryoforigin(),
                            recipePageViewModel.getState().getCalories(),
                            recipePageViewModel.getState().getDietLabels(),
                            recipePageViewModel.getState().getHealthLabels(),
                            recipePageViewModel.getState().getmealtype());
                }
            }
        });


        clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(clear)) {
                    RecipePageState currentState = recipePageViewModel.getState();
                    currentState.setRecipename("");
                    currentState.setCalories(1500);
                    currentState.setCountryoforigin("World");
                    currentState.setmealtype("any");
                    currentState.setDietLabels(new ArrayList<>());

                    recipePageViewModel.setState(currentState);
                    recipePageViewModel.firePropertyChanged();

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




        MealTypeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RecipePageState currentState = recipePageViewModel.getState();
                currentState.setmealtype((String) MealTypeComboBox.getSelectedItem());
                recipePageViewModel.setState(currentState);
            }
        });



        // Add a ListSelectionListener to update the value in the RecipePageState when dietLabelList is changed
        dietLabelList.addListSelectionListener(e -> {
            RecipePageState currentState = recipePageViewModel.getState();
            currentState.setDietLabels(dietLabelList.getSelectedValuesList());
            recipePageViewModel.setState(currentState);
            // Update the label to display the selected diet labels
            selectedDietLabelsLabel.setText("Selected Diet Labels: " + Arrays.toString(dietLabelList.getSelectedValuesList().toArray()));

        });




//        // Add a ListSelectionListener to update the value in the RecipePageState when healthLabelList is changed
        healthLabelList.addListSelectionListener(e -> {
            RecipePageState currentState = recipePageViewModel.getState();
            currentState.setHealthLabels(healthLabelList.getSelectedValuesList());
            recipePageViewModel.setState(currentState);
            // Update the label to display the selected health labels
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

        JPanel labelPanel = new JPanel();

        labelPanel.add(new LabelScrollPanePanel(new JLabel("Diet Label(Hold CTRL) \n"), dietLabelScrollPane));
        labelPanel.add(new LabelScrollPanePanel(new JLabel("Health Label(Hold CTRL) \n"), healthLabelScrollPane));

        this.add(title);
        this.add(Box.createRigidArea(new Dimension(0, 40)));
        this.add(recipenameinfo);
        this.add(countryoforigininfo);
        this.add(mealtypeinfo);
        this.add(labelPanel);
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
        MealTypeComboBox.setSelectedItem(state.getmealtype());
        dietLabelList.setSelectedIndices(new int[0]);
        healthLabelList.setSelectedIndices(new int[0]);
    }
}
