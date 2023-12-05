package view;

import interface_adapter.MealPlanner.MealPlanController;
import interface_adapter.MealPlanner.MealPlanViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
public class MealPlanView extends JPanel implements PropertyChangeListener{
    public static final String viewName = "MealPlanView";
    private MealPlanViewModel viewModel;
    private MealPlanController controller;

    // UI Components
    private JTextArea mealPlanTextArea;
    private JButton fetchMealPlanButton;
    private JTextField searchQueryTextField;

    public MealPlanView(MealPlanViewModel viewModel, MealPlanController controller) {
        this.viewModel = viewModel;
        this.controller = controller;
        this.viewModel.addPropertyChangeListener(this);
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());

        searchQueryTextField = new JTextField();
        fetchMealPlanButton = new JButton("Get Meal Plan");
        fetchMealPlanButton.addActionListener(e -> controller.createMealPlan(searchQueryTextField.getText()));

        mealPlanTextArea = new JTextArea("Meal Plan Details Here...");
        mealPlanTextArea.setEditable(false);

        add(searchQueryTextField, BorderLayout.NORTH);
        add(new JScrollPane(mealPlanTextArea), BorderLayout.CENTER);
        add(fetchMealPlanButton, BorderLayout.SOUTH);

        setPreferredSize(new Dimension(400, 300)); // Adjust size as needed
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("mealPlan".equals(evt.getPropertyName())) {
            mealPlanTextArea.setText(viewModel.getMealPlan().toString()); // Update to display meal plan details properly
        } else if ("errorMessage".equals(evt.getPropertyName())) {
            JOptionPane.showMessageDialog(this, viewModel.getErrorMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
