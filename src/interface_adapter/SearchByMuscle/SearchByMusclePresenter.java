package interface_adapter.SearchByMuscle;

import entity.Workout;
import use_case.SearchByMuscle.SearchByMuscleOutputBoundary;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SearchByMusclePresenter implements SearchByMuscleOutputBoundary {

    private final JFrame view;

    public SearchByMusclePresenter(JFrame view) {
        this.view = view;
    }
    @Override
    public void Presentworkout(ArrayList<Workout> workouts) {
        if (!workouts.isEmpty()) {
            // Display workout information using a JOptionPane
            JEditorPane editorPane = new JEditorPane();
            editorPane.setContentType("text/html");
            editorPane.setEditable(false);

            StringBuilder htmlContent = new StringBuilder("<html><div style='text-align:center; font-family: Arial, sans-serif;'>");

            for (Workout workout : workouts) {
                String wholeWorkout = workout.getWholeWorkout();

                htmlContent.append(wholeWorkout);

                htmlContent.append("<hr>");

                htmlContent.append("<br>");
            }

            // remove all the </html> tags
            htmlContent = new StringBuilder(htmlContent.toString().replaceAll("</html>", ""));

            // Close the HTML document
            htmlContent.append("</div></html>");

            // Set the HTML content to the JEditorPane
            editorPane.setText(htmlContent.toString());

            // Create a JScrollPane and add the JEditorPane to it
            JScrollPane scrollPane = new JScrollPane(editorPane);

            // Set the preferred size of the scroll pane
            scrollPane.setPreferredSize(new Dimension(1000, 800));

            // Display the information in a JOptionPane if needed
            JOptionPane.showMessageDialog(view, scrollPane, "Workout Information", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Handle the case where no workout is found
            JOptionPane.showMessageDialog(view, "No workout found for the selected muscle group.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void Presentnoneselected() {
        JOptionPane.showMessageDialog(view, String.format("Please select at least one muscle group"));
    }
}
