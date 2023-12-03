package interface_adapter.SearchWorkoutByName;

import entity.Workout;
import use_case.SearchWorkoutByName.SearchWorkoutByNameOutputBoundary;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SearchWorkoutByNamePresenter implements SearchWorkoutByNameOutputBoundary {

    private final JFrame view;

    public SearchWorkoutByNamePresenter(JFrame view) {
        this.view = view;
    }

    @Override
    public void Presentworkouts(ArrayList<Workout> workouts) {
        JEditorPane editorPane = new JEditorPane();
        editorPane.setContentType("text/html");
        editorPane.setEditable(false);

        StringBuilder htmlContent = new StringBuilder("<html><div style='text-align:center; font-family: Arial, sans-serif;'>");

        // Iterate through the list of workouts and append information
        for (Workout workout : workouts) {
            String wholeWorkout = workout.getWholeWorkout();

            // Append the workout information to the HTML content
            htmlContent.append(wholeWorkout);

            // Add a horizontal line between workouts
            htmlContent.append("<hr>");

            // Add some space after the horizontal line
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
    }





    @Override
    public void Presentemptystring() {
        JOptionPane.showMessageDialog(view, "Please Enter A Workout!!!", "Error", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void PresentNotFound() {
        JOptionPane.showMessageDialog(view, "A Workout With That Name Does Not Exist. Please Try Again.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
