/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Lenovo
 */



import DataAccess.DataAccess;
import java.util.HashMap;
import java.util.List;



public class MealPlanner {

     private final String MealPlanner;
    private int healthLabelLine;
     
      public MealPlanner(String MealPlanner) {
        this.MealPlanner = MealPlanner;
    }
    
  
    public String getSelectedMealCount() {
         String[] lines = MealPlanner.split("\\n");

        // Return the fourth line of the recipe as meal type
        if (lines.length > 3) {
            return lines[10];
        } else {
            // Handle the case where the recipe is too short
            return "Meal Count Not Available";
        }
    }

    public String getSelectedPlanType() {
          String[] lines = MealPlanner.split("\\n");

        // Return the fourth line of the recipe as meal type
        if (lines.length > 3) {
            return lines[10];
        } else {
            // Handle the case where the recipe is too short
            return "Meal Plan Type Not Available";
        }
    }

      public String getDietLabels() {
        // Split the recipe into lines
        String[] lines = MealPlanner.split("\\n");

        // After line[12] add everything to a single string with a comma between each item until
        // you reach the line that starts with "Health Labels:"
        if (lines.length > 12) {
            StringBuilder result = new StringBuilder();
            for (int i = 13; i < lines.length; i++) {
                if (lines[i].startsWith("Health Label:")) {
                    healthLabelLine = i + 1;
                    System.out.println("h pre" + healthLabelLine);
                    break;
                }

                // Check if the line is not empty before appending a comma
                if (!lines[i].isEmpty()) {
                    // Check if the result is not empty before appending a comma
                    if (result.length() > 0) {
                        result.append(",\n");
                    }
                    result.append(lines[i]);
                }
            }
            return result.toString();
        } else {
            // Handle the case where the recipe is too short
            return "Diet Labels Not Available";
        }
    }

    public String getHealthLabels() {
         // Split the recipe into lines
        String[] lines = MealPlanner.split("\\n");

        // After line[12] add everything to a single string with a comma between each item until
        // you reach the line that starts with "Health Labels:"
        if (lines.length > 12) {
            StringBuilder result = new StringBuilder();
            for (int i = 13; i < lines.length; i++) {
                if (lines[i].startsWith("Health Label:")) {
                    healthLabelLine = i + 1;
                    System.out.println("h pre" + healthLabelLine);
                    break;
                }

                // Check if the line is not empty before appending a comma
                if (!lines[i].isEmpty()) {
                    // Check if the result is not empty before appending a comma
                    if (result.length() > 0) {
                        result.append(",\n");
                    }
                    result.append(lines[i]);
                }
            }
            return result.toString();
        } else {
            // Handle the case where the recipe is too short
            return "Diet Labels Not Available";
        }
    }

    public double getMinCalories() {
          String[] lines = MealPlanner.split("\\n");

        // Return the second line of the recipe as calories
        if (lines.length > 1) {
            try {
                return Double.parseDouble(lines[4]); // Change from Integer.parseInt to Double.parseDouble
            } catch (NumberFormatException e) {
                // Handle the case where calories are not a valid double
                return 0.0; // Change from 0 to 0.0 for a double
            }
        } else {
            // Handle the case where the recipe is too short
            return 0.0; // Change from 0 to 0.0 for a double
        }
    }

    public double getMaxCalories() {
          String[] lines = MealPlanner.split("\\n");

        // Return the second line of the recipe as calories
        if (lines.length > 1) {
            try {
                return Double.parseDouble(lines[4]); // Change from Integer.parseInt to Double.parseDouble
            } catch (NumberFormatException e) {
                // Handle the case where calories are not a valid double
                return 0.0; // Change from 0 to 0.0 for a double
            }
        } else {
            // Handle the case where the recipe is too short
            return 0.0; // Change from 0 to 0.0 for a double
        }
    }

  
}

