package interface_adapter.CalorieCounter;

import entity.Calculations;
import use_case.CalorieCounter.CalorieCounterOutputBoundary;

import javax.swing.*;
import java.util.ArrayList;

public class CalorieCounterPresenter implements CalorieCounterOutputBoundary {

    private final JFrame view;

    public CalorieCounterPresenter(JFrame view) {
        this.view = view;
    }

    @Override
    public void presentCalculations(Calculations calculations) {
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        // Create JLabel for each Calculation
        JLabel idealweight = new JLabel(calculations.getIdealWeight());
        ArrayList<String> BMIinfo = calculations.getBMI();
        JLabel bmr = new JLabel(calculations.getBMR());
        ArrayList<String> weightgoals = calculations.getWeightGoals();


        // Add labels to the contentPanel
        contentPanel.add(idealweight);
        for (int i=0; i < BMIinfo.size(); i++) {
            String info = BMIinfo.get(i);
            JLabel charLabel = new JLabel(info);
            contentPanel.add(charLabel);
        contentPanel.add(bmr);}
        for (int i=0; i < weightgoals.size(); i++) {
            String goal = weightgoals.get(i);
            JLabel charLabel2 = new JLabel(goal);
            contentPanel.add(charLabel2);

        }

        // Set the preferred size for the content panel


        JOptionPane.showMessageDialog(null, contentPanel, "Your Calculations", JOptionPane.INFORMATION_MESSAGE);
    }
}