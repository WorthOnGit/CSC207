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
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import javax.swing.plaf.basic.*;


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
    private final JList<String> cuisineTypeComboBox;
    private final JList<String> MealTypeComboBox;
    private final JList<String> dietLabelList;
    private final JList<String> healthLabelList;
    private final JLabel caloriesValueLabel = new JLabel("Calories: 0");
    private final JLabel selectedDietLabelsLabel = new JLabel();
    private Image backgroundImage;

    private final JLabel title;

        {
            title = new JLabel(RecipePageViewModel.TITLE_LABEL);
            title.setAlignmentX(Component.CENTER_ALIGNMENT);
            title.setForeground(Color.WHITE);

        }

    private JLabel findJLabelByText(Container container, String text) {
        for (Component component : container.getComponents()) {
            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                if (text.equals(label.getText())) {
                    return label;
                }
            } else if (component instanceof Container) {
                JLabel label = findJLabelByText((Container) component, text);
                if (label != null) {
                    return label;
                }
            }
        }
        return null;
    }

    public RecipePageView(RecipePageViewModel recipePageViewModel, RecipeDoneController recipeDoneController, RecipeSearchButtonController recipeSearchButtonController, String backgroundImagePath) {
        this.recipeDoneController = recipeDoneController;
        this.recipePageViewModel = recipePageViewModel;
        this.recipePageViewModel.addPropertyChangeListener(this);
        this.recipeSearchButtonController = recipeSearchButtonController;


        // Load the background image
        try {
            backgroundImage = ImageIO.read(new File(backgroundImagePath));
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception (e.g., show an error message)
        }

        // Create a JSlider for calories
        caloriesSlider = new JSlider(JSlider.HORIZONTAL, 0, 3000, 0);
        caloriesSlider.setMajorTickSpacing(500);
        caloriesSlider.setPaintTicks(true);
        caloriesValueLabel.setForeground(Color.WHITE);
        Font labelFont = new Font("Arial", Font.BOLD, 16);
        caloriesValueLabel.setFont(labelFont);
        caloriesSlider.setPaintLabels(true);
        caloriesSlider.setValue(1500);
        caloriesSlider.setOpaque(false);

        caloriesSlider.setUI(new CustomSliderUI(caloriesSlider));  // Set the custom UI for the slider
        // stretch the slider a bit
        caloriesSlider.setPreferredSize(new Dimension(300, 50));

        LabelTextPanel recipenameinfo = new LabelTextPanel(
                new JLabel(RecipePageViewModel.RECIPE_NAME_LABEL), recipenameInputField);

        // Assuming RecipePageViewModel.RECIPE_NAME_LABEL is a constant string
        String recipeNameLabel = RecipePageViewModel.RECIPE_NAME_LABEL;


// Find the JLabel with the specified text in the recipenameinfo panel
        JLabel recipeNameJLabel = findJLabelByText(recipenameinfo, recipeNameLabel);

// Set the foreground color to white
        recipeNameJLabel.setForeground(Color.WHITE);

        recipenameinfo.setForeground(Color.WHITE);

        Font labelFont1 = new Font("Arial", Font.BOLD, 16);
        recipeNameJLabel.setFont(labelFont1);

        // Set a larger font for the input field
        Font largerFont = new Font("Arial", Font.BOLD, 16);  // Set font to Arial, bold, size 20
        recipenameInputField.setFont(largerFont);


        // Set the input field size and alignment
        recipenameInputField.setPreferredSize(new Dimension(250, 40));

        recipenameInputField.setBackground(new Color(255, 255, 255, 100));  // Adjust the alpha value as needed

        int x = (getWidth() - recipenameinfo.getWidth()) / 2;
        int y = (getHeight() - recipenameinfo.getHeight()) / 2;

        // Set the bounds for recipenameinfo
        recipenameinfo.setBounds(x, y, recipenameinfo.getWidth(), recipenameinfo.getHeight());

        LabelSliderPanel caloriesinfo = new LabelSliderPanel(
                caloriesValueLabel, caloriesSlider);

        // Create JComboBox for cuisine types
        String[] cuisineTypes = {"World", "American", "Asian", "British", "Caribbean", "Central European", "Chinese",
                "Eastern European", "French", "Greek", "Indian", "Italian", "Japanese", "Korean", "Kosher",
                "Mediterranean", "Mexican", "Middle Eastern", "Nordic", "South American", "Southeast Asian"};

        cuisineTypeComboBox = new JList<>(cuisineTypes);
        cuisineTypeComboBox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cuisineTypeComboBox.setOpaque(false);
        cuisineTypeComboBox.setBackground(new Color(255, 255, 255, 100));  // Set the background color to translucent

        JScrollPane cuisineTypeScrollPane = new JScrollPane(cuisineTypeComboBox);
        cuisineTypeScrollPane.setOpaque(false);
        cuisineTypeScrollPane.getViewport().setOpaque(false);  // Set the JList (viewport) to be transparent
        cuisineTypeScrollPane.setBackground(new Color(0, 0, 0, 0));  // Set the background color to transparent

        cuisineTypeScrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        // Create JComboBox for cuisine types
        String[] MealTypes = {"any", "lunch", "dinner", "brunch", "breakfast", "snack"};

        MealTypeComboBox = new JList<>(MealTypes);
        MealTypeComboBox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        MealTypeComboBox.setOpaque(false);
        MealTypeComboBox.setBackground(new Color(255, 255, 255, 100));  // Set the background color to translucent

        JScrollPane MealTypeScrollPane = new JScrollPane(MealTypeComboBox);
        MealTypeScrollPane.setOpaque(false);
        MealTypeScrollPane.getViewport().setOpaque(false);  // Set the JList (viewport) to be transparent
        MealTypeScrollPane.setBackground(new Color(0, 0, 0, 0));  // Set the background color to transparent


        // Create a JList for diet labels
        String[] dietLabels = {"Balanced", "High-Fiber", "High-Protein", "Low-Carb", "Low-Fat", "Low-Sodium"};
        dietLabelList = new JList<>(dietLabels);
        dietLabelList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        dietLabelList.setOpaque(false);
        dietLabelList.setBackground(new Color(255, 255, 255, 100));  // Set the background color to translucent

        // Create a JScrollPane for the diet label list
        JScrollPane dietLabelScrollPane = new JScrollPane(dietLabelList);
        dietLabelScrollPane.setOpaque(false);
        dietLabelScrollPane.getViewport().setOpaque(false);  // Set the JList (viewport) to be transparent
        dietLabelScrollPane.setBackground(new Color(0, 0, 0, 0));  // Set the background color to transparent


        // Create a JList for health labels
        String[] healthLabels = {"Alcohol-Cocktail", "Alcohol-Free", "Celery-Free", "Crustacean-Free", "Dairy-Free", "DASH",
                "Egg-Free", "Fish-Free", "FODMAP-Free", "Gluten-Free", "Immuno-Supportive", "Keto-Friendly", "Kidney-Friendly",
                "Kosher", "Low Potassium", "Low Sugar", "Lupine-Free", "Mediterranean", "Mollusk-Free", "Mustard-Free",
                "No oil added", "Paleo", "Peanut-Free", "Pescatarian", "Pork-Free", "Red-Meat-Free", "Sesame-Free",
                "Shellfish-Free", "Soy-Free", "Sugar-Conscious", "Sulfite-Free", "Tree-Nut-Free", "Vegan", "Vegetarian", "Wheat-Free"};

        healthLabelList = new JList<>(healthLabels);
        healthLabelList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        healthLabelList.setOpaque(false);
        healthLabelList.setBackground(new Color(255, 255, 255, 100));  // Set the background color to translucent


        // Create a JScrollPane for the health label list
        JScrollPane healthLabelScrollPane = new JScrollPane(healthLabelList);
        healthLabelScrollPane.setOpaque(false);
        healthLabelScrollPane.getViewport().setOpaque(false);  // Set the JList (viewport) to be transparent
        healthLabelScrollPane.setBackground(new Color(0, 0, 0, 0));  // Set the background color to transparent

        healthLabelScrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());



        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        search = new JButton(RecipePageViewModel.SEARCH_BUTTON_LABEL);
        search.setContentAreaFilled(false);
        search.setBackground(new Color(30, 30, 30, 200));  // Darker translucent background
        search.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50, 150), 2));  // Thicker outline
        search.setForeground(Color.BLACK);  // Text color
        search.setFont(search.getFont().deriveFont(Font.BOLD, 16));  // Increase text size

// Set preferred size
        Dimension buttonSize = new Dimension(120, 40);
        search.setPreferredSize(buttonSize);

        buttons.add(search);

        clear = new JButton("Clear");
        clear.setContentAreaFilled(false);
        clear.setBackground(new Color(30, 30, 30, 200));  // Darker translucent background
        clear.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50, 150), 2));  // Thicker outline
        clear.setForeground(Color.BLACK);  // Text color
        clear.setFont(clear.getFont().deriveFont(Font.BOLD, 16));  // Increase text size

// Set preferred size
        clear.setPreferredSize(buttonSize);

        buttons.add(clear);

        Done = new JButton(RecipePageViewModel.Done_BUTTON_LABEL);
        Done.setContentAreaFilled(false);
        Done.setBackground(new Color(30, 30, 30, 200));  // Darker translucent background
        Done.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50, 150), 2));  // Thicker outline
        Done.setForeground(Color.BLACK);  // Text color
        Done.setFont(Done.getFont().deriveFont(Font.BOLD, 16));  // Increase text size

// Set preferred size
        Done.setPreferredSize(buttonSize);

        buttons.add(Done);

        // Add horizontal glue to center the buttons
        buttons.add(Box.createHorizontalGlue());

// Calculate the y coordinate for the buttons
        int buttonsY = y + recipenameinfo.getHeight() + 10; // Adjust the spacing as needed

// Set the bounds for the buttons
        buttons.setBounds(0, buttonsY, getWidth(), buttons.getPreferredSize().height);


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
                }
            }
        });


        dietLabelList.addListSelectionListener(e -> {
            RecipePageState currentState = recipePageViewModel.getState();
            currentState.setDietLabels(dietLabelList.getSelectedValuesList());
            recipePageViewModel.setState(currentState);
            selectedDietLabelsLabel.setText("Selected Diet Labels: " + Arrays.toString(dietLabelList.getSelectedValuesList().toArray()));
        });

        healthLabelList.addListSelectionListener(e -> {
            RecipePageState currentState = recipePageViewModel.getState();
            currentState.setHealthLabels(healthLabelList.getSelectedValuesList());
            recipePageViewModel.setState(currentState);
        });

        Done.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(Done)) {
                    recipeDoneController.execute();
                }
            }
        });

        caloriesSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                RecipePageState currentState = recipePageViewModel.getState();
                int caloriesValue = caloriesSlider.getValue();
                currentState.setCalories(caloriesValue);
                recipePageViewModel.setState(currentState);
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
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
        labelPanel.setOpaque(false);

// Create a panel for Meal Type and Cuisine Type without any horizontal gap
        JPanel mealCuisinePanel = new JPanel();
        mealCuisinePanel.setOpaque(false);
        mealCuisinePanel.setLayout(new BoxLayout(mealCuisinePanel, BoxLayout.X_AXIS));

        JLabel mealTypeLabel = new JLabel("Meal Type");
        mealTypeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        mealTypeLabel.setForeground(Color.WHITE);
        LabelScrollPanePanel mealTypePanel = new LabelScrollPanePanel(mealTypeLabel, MealTypeScrollPane);
        mealCuisinePanel.add(mealTypePanel);

        labelPanel.add(mealCuisinePanel);

// Add vertical space between Meal Type and Cuisine Type
        labelPanel.add(Box.createRigidArea(new Dimension(0, 10)));  // Adjust the spacing as needed

// Create a panel for Cuisine Type
        JPanel cuisinePanel = new JPanel();
        cuisinePanel.setOpaque(false);
        cuisinePanel.setLayout(new BoxLayout(cuisinePanel, BoxLayout.X_AXIS));

        JLabel cuisineTypeLabel = new JLabel("Cuisine Type");
        cuisineTypeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        cuisineTypeLabel.setForeground(Color.WHITE);
        LabelScrollPanePanel cuisineTypePanel = new LabelScrollPanePanel(cuisineTypeLabel, cuisineTypeScrollPane);
        cuisinePanel.add(cuisineTypePanel);

        labelPanel.add(cuisinePanel);

// Add vertical space between Cuisine Type and Diet/Health Labels
        labelPanel.add(Box.createRigidArea(new Dimension(0, 10)));  // Adjust the spacing as needed

// Create a panel for Diet Label and Health Label
        JPanel dietHealthPanel = new JPanel();
        dietHealthPanel.setOpaque(false);
        dietHealthPanel.setLayout(new BoxLayout(dietHealthPanel, BoxLayout.X_AXIS));

        JLabel dietLabel = new JLabel("Diet Label(Hold CTRL)");
        dietLabel.setFont(new Font("Arial", Font.BOLD, 16));
        dietLabel.setForeground(Color.WHITE);
        LabelScrollPanePanel dietLabelPanel = new LabelScrollPanePanel(dietLabel, dietLabelScrollPane);
        dietHealthPanel.add(dietLabelPanel);

        JLabel healthLabel = new JLabel("Health Label(Hold CTRL)");
        healthLabel.setFont(new Font("Arial", Font.BOLD, 16));
        healthLabel.setForeground(Color.WHITE);
        LabelScrollPanePanel healthLabelPanel = new LabelScrollPanePanel(healthLabel, healthLabelScrollPane);
        dietHealthPanel.add(healthLabelPanel);

        labelPanel.add(dietHealthPanel);


        this.add(title);
        this.add(Box.createRigidArea(new Dimension(0, 40)));
        this.add(recipenameinfo);
        this.add(labelPanel);
        this.add(caloriesinfo);
        buttons.setOpaque(false);
        this.setOpaque(false);
        this.add(buttons);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

    @Override
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
        cuisineTypeComboBox.setSelectedIndices(new int[0]);
        MealTypeComboBox.setSelectedIndices(new int[0]);
        dietLabelList.setSelectedIndices(new int[0]);
        healthLabelList.setSelectedIndices(new int[0]);
    }
}

class CustomScrollBarUI extends BasicScrollBarUI {
    @Override
    protected void configureScrollBarColors() {
        thumbColor = new Color(50, 50, 50, 150);  // Set thumb color to translucent
        trackColor = new Color(0, 0, 0, 50);  // Set track color to transparent
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return createZeroButton();
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return createZeroButton();
    }

    private JButton createZeroButton() {
        JButton button = new JButton();
        Dimension zeroDim = new Dimension(0, 0);
        button.setPreferredSize(zeroDim);
        button.setMinimumSize(zeroDim);
        button.setMaximumSize(zeroDim);
        return button;
    }
}

class CustomSliderUI extends BasicSliderUI {

    private final Color thumbColor = new Color(50, 50, 50, 150);  // Darker greyish-white color
    private final Color trackColor = new Color(0, 0, 0, 50);  // Set track color to translucent dark gray
    private final Color tickColor = new Color(50, 50, 50, 150);  // Set tick color to translucent dark gray

    public CustomSliderUI(JSlider slider) {
        super(slider);
    }

    @Override
    public void paintThumb(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(thumbColor);
        g2d.fillRoundRect(thumbRect.x, thumbRect.y + thumbRect.height / 4, thumbRect.width, thumbRect.height / 2, 5, 5);
    }

    @Override
    public void paintTrack(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(trackColor);
        g2d.fill(trackRect);
    }

    @Override
    public void paintTicks(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(tickColor);
        super.paintTicks(g);
    }

    @Override
    protected Dimension getThumbSize() {
        return new Dimension(10, 15);  // Adjust the size of the thumb
    }

    @Override
    protected Color getFocusColor() {
        return new Color(0, 0, 0, 0);  // Set focus color to transparent
    }

    @Override
    protected Color getHighlightColor() {
        return new Color(0, 0, 0, 0);  // Set highlight color to transparent
    }

    @Override
    protected Color getShadowColor() {
        return new Color(0, 0, 0, 0);  // Set shadow color to transparent
    }
}
