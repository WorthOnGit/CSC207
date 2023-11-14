package interface_adapter;

import entity.Recipe;
import use_case.RecipeSearchButton.RecipeSearchButtonOutputBoundary;
import view.RecipePageView;
import view.StartPageView;

import javax.swing.*;

public class RecipeSeatchButtonPresenter implements RecipeSearchButtonOutputBoundary {
    private final JFrame view;

    public RecipeSeatchButtonPresenter(JFrame view) {
        this.view = view;
    }

    @Override
    public void presentrecipe(Recipe recipe) {
        String recipeDetails = String.format(
                "Recipe Name: %s\n" +
                        "Ingredient Lines: %s\n" +
                        "Ingredients: %s\n" +
                        "Calories: %d\n" +
                        "Health Labels: %s\n" +
                        "Diet Labels: %s\n" +
                        "Meal Type: %s\n" +
                        "Cuisine Type: %s",
                recipe.getRecipeName(),
                String.join(", ", recipe.getIngredientLines()),
                recipe.getIngredients(),
                recipe.getCalories(),
                String.join(", ", recipe.getHealthLabels()),
                String.join(", ", recipe.getDietLabels()),
                recipe.getMealType(),
                recipe.getCuisineType()
        );

        JOptionPane.showMessageDialog(null, recipeDetails, "Recipe Details", JOptionPane.INFORMATION_MESSAGE);
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
