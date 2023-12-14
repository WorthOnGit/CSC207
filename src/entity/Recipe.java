package entity;

import java.util.HashMap;
import java.util.List;
import DataAccess.DataAccess;

public class Recipe {

    private final String recipe;
    private int healthLabelLine;

    private int ingredientLine;

    public Recipe(String recipe) {
        this.recipe = recipe;
    }

    public String getRecipeName() {
        if (recipe == null) {
            return "Recipe Name Not Available";
        }

        System.out.println(recipe);


        String[] lines = recipe.split("\\n");

        // Return the first line of the recipe
        if (lines.length > 0) {
            return lines[1];
        } else {
            // Handle the case where the recipe is empty
            return "Recipe Name Not Available";
        }
    }


    public double getCalories() {
        // Split the recipe into lines until you reach Calories: line
        String[] lines = recipe.split("\\n");

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



    public String getCuisineType() {
        // Split the recipe into lines
        String[] lines = recipe.split("\\n");

        // Return the third line of the recipe as cuisine type
        if (lines.length > 2) {
            return lines[7];
        } else {
            // Handle the case where the recipe is too short
            return "Cuisine Type Not Available";
        }
    }

    public String getMealType() {
        // Split the recipe into lines
        String[] lines = recipe.split("\\n");

        // Return the fourth line of the recipe as meal type
        if (lines.length > 3) {
            return lines[10];
        } else {
            // Handle the case where the recipe is too short
            return "Meal Type Not Available";
        }
    }


    public String getDietLabels() {
        // Split the recipe into lines
        String[] lines = recipe.split("\\n");

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
        String[] lines = recipe.split("\\n");
        System.out.println("h" + healthLabelLine);
        System.out.println("l" + lines.length);
        System.out.println(healthLabelLine > 0 && lines.length > healthLabelLine + 1);
        // starting from the healthLabelLine add everything to a single string with a comma between each item until
        // you reach the line that starts with "Ingredients:"
        if (healthLabelLine > 0 && lines.length > healthLabelLine + 1) {
            StringBuilder result = new StringBuilder();
            for (int i = healthLabelLine; i < lines.length; i++) {
                if (lines[i].startsWith("Ingredients:")) {
                    ingredientLine = i + 1;
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
            return "Health Labels Not Available";
        }
    }

    public String getIngredients() {
        // Split the recipe into lines
        String[] lines = recipe.split("\\n");

        // starting from the ingredientLine add everything to a single string with a comma between each item until
        // you reach a new empty line
        if (ingredientLine > 0 && lines.length > ingredientLine + 1) {
            StringBuilder result = new StringBuilder();
            for (int i = ingredientLine; i < lines.length; i++) {
                if (lines[i].isEmpty()) {
                    break;
                }
                // Check if the line is not empty before appending a comma
                if (!lines[i].isEmpty()) {
                    // Check if the result is not empty before appending a comma
                    if (result.length() > 0) {
                        result.append(", ");
                    }
                    result.append(lines[i]);
                }
            }
            System.out.println(result.toString());
            return result.toString();
        } else {
            // Handle the case where the recipe is too short
            System.out.println("Ingredients Not Available");
            return "Ingredients Not Available";
        }

    }
}