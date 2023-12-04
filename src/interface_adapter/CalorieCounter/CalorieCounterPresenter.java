package interface_adapter.CalorieCounter;

import entity.Calculations;
import use_case.CalorieCounter.CalorieCounterOutputBoundary;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class CalorieCounterPresenter implements CalorieCounterOutputBoundary {

    private final JFrame view;

    public CalorieCounterPresenter(JFrame view) {
        this.view = view;
    }

    private JPanel createComponentPanel(String title, ArrayList<String> data) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);

        // Create Titled Border
        TitledBorder border = BorderFactory.createTitledBorder(title);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10),
                border));

        // Create JLabels
        gbc.gridy = 0;
        for (String info : data) {
            JLabel label = new JLabel(info);
            gbc.anchor = GridBagConstraints.WEST;  // Set anchor to left-align
            panel.add(label, gbc);
            gbc.gridy++;
        }

        return panel;
    }

    @Override
    public void presentCalculations(Calculations calculations) {
        JPanel contentPanel = new JPanel(new GridLayout(2, 2, 10, 10));

        // Create and add subpanels for each component
        contentPanel.add(createComponentPanel("Ideal Weight", new ArrayList<>(Arrays.asList(calculations.getIdealWeight()))));
        contentPanel.add(createComponentPanel("BMI Information", calculations.getBMI()));
        contentPanel.add(createComponentPanel("BMR", new ArrayList<>(Arrays.asList(calculations.getBMR()))));
        contentPanel.add(createComponentPanel("Daily Calorie Intake Based on Objective", calculations.getWeightGoals()));

        // Set the preferred size for the content panel
        contentPanel.setPreferredSize(new Dimension(600, 500)); // Increase the height

        JOptionPane.showMessageDialog(null, contentPanel, "Your Calculations", JOptionPane.INFORMATION_MESSAGE);
    }
}