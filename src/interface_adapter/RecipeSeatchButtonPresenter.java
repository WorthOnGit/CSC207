package interface_adapter;

import entity.Recipe;
import use_case.RecipeSearchButton.RecipeSearchButtonOutputBoundary;
import view.RecipePageView;
import view.StartPageView;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class RecipeSeatchButtonPresenter implements RecipeSearchButtonOutputBoundary {
    private final JFrame view;

    public RecipeSeatchButtonPresenter(JFrame view) {
        this.view = view;
    }

    @Override
    public void presentrecipe(Recipe recipe) {
        // Create a custom JPanel with BorderLayout
        JPanel contentPanel = new JPanel(new BorderLayout());

        // Create a JPanel to hold the content
        JPanel detailsPanel = new JPanel(new GridBagLayout());
        detailsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Add labels and details to the detailsPanel using GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipady = 10;

        addLabelAndDetails(detailsPanel, gbc, "Recipe Name:"," " + recipe.getRecipeName() + "\n\n");
        addLabelAndDetails(detailsPanel, gbc, "Calories:"," " +  String.format("%.2f", recipe.getCalories()) + "\n\n");
        addLabelAndDetails(detailsPanel, gbc, "Diet Labels:"," " + String.join(", ", recipe.getDietLabels()) + "\n\n");
        addLabelAndDetails(detailsPanel, gbc, "Health Labels:", " " +  String.join(", ", recipe.getHealthLabels()) + "\n\n");
        addLabelAndDetails(detailsPanel, gbc, "Meal Type:", " " + recipe.getMealType() + "\n\n");
        addLabelAndDetails(detailsPanel, gbc, "Cuisine Type:", " " + recipe.getCuisineType() + "\n\n");
        addLabelAndDetails(detailsPanel, gbc, "Ingredients:",  " " + String.join(", ", recipe.getIngredients()) + "\n\n");

        // Add the detailsPanel to the content panel
        contentPanel.add(new JScrollPane(detailsPanel), BorderLayout.CENTER);

        // Set the preferred size for the content panel
        contentPanel.setPreferredSize(new Dimension(800, 500));

        JOptionPane.showMessageDialog(null, contentPanel, "Recipe Details", JOptionPane.INFORMATION_MESSAGE);
    }

    private void addLabelAndDetails(JPanel panel, GridBagConstraints gbc, String label, String details) {
        JLabel labelComponent = new JLabel("<html><b>" + label + "</b></html>");

        // Use HTML unordered list for details
        JLabel detailsComponent = new JLabel("<html><ul><li>" + details.replaceAll(", ", "</li><li>") + "</li></ul></html>");

        gbc.gridy++;
        panel.add(labelComponent, gbc);
        gbc.gridx++;
        panel.add(detailsComponent, gbc);
        gbc.gridx = 0;
    }


    @Override
    public void presentnoinputfail() {
        JOptionPane.showMessageDialog(view, String.format("Please Enter Minimum One Field!!!"));

    }

    @Override
    public void presentnoresultfail() {
        JOptionPane.showMessageDialog(view, String.format("No Result Found!!! Please Try Again"));

    }
}
