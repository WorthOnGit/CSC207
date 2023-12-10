/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interface_adapter.MealPlannerSearchButton;

import entity.MealPlanner;
import use_case.MealPlannerSearchButton.MealPlannerSearchButtonOutputBoundary;
import view.MealPlannerPageView;
import view.StartPageView;
        
        
import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;


/**
 *
 * @author Lenovo
 */
public class MealPlannerSearchButtonPresenter implements MealPlannerSearchButtonOutputBoundary{
    private final JFrame view;

    public MealPlannerSearchButtonPresenter(JFrame view) {
        this.view = view;
    }
    
 
    @Override
 public void presentMealPlanner(MealPlanner mealPlanner){
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
        
        addLabelAndDetails(detailsPanel, gbc, "Meal Count:"," " + String.join("", mealPlanner.getSelectedMealCount()) + "\n\n");
        addLabelAndDetails(detailsPanel, gbc, "Plan Type:"," " +  String.join(mealPlanner.getSelectedPlanType()) + "\n\n");
        addLabelAndDetails(detailsPanel, gbc, "Diet Labels:"," " + String.join(", ", mealPlanner.getDietLabels()) + "\n\n");
        addLabelAndDetails(detailsPanel, gbc, "Health Labels:", " " +  String.join(", ", mealPlanner.getHealthLabels()) + "\n\n");
        addLabelAndDetails(detailsPanel, gbc, "Minimum Calories:", " " + String.format("%.2f",mealPlanner.getMinCalories()) + "\n\n");
        addLabelAndDetails(detailsPanel, gbc, "Maximum Calories:", " " + String.format("%.2f",mealPlanner.getMaxCalories()) + "\n\n");
        
        
         // Add the detailsPanel to the content panel
        contentPanel.add(new JScrollPane(detailsPanel), BorderLayout.CENTER);

        // Set the preferred size for the content panel
        contentPanel.setPreferredSize(new Dimension(800, 500));

        JOptionPane.showMessageDialog(null, contentPanel, "MealPlan Details", JOptionPane.INFORMATION_MESSAGE);
      
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
