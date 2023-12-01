package interface_adapter.RecipeSearchButton;

import use_case.RecipeSearchButton.RecipeSearchButtonInputBoundary;
import use_case.RecipeSearchButton.RecipeSearchInputData;

import java.util.List;

public class RecipeSearchButtonController {

        private final RecipeSearchButtonInputBoundary recipeSearchButtonInputBoundary;

    public RecipeSearchButtonController(RecipeSearchButtonInputBoundary recipeSearchButtonInputBoundary) {
        this.recipeSearchButtonInputBoundary = recipeSearchButtonInputBoundary;
    }

    public void execute(String recipename, String countryoforigin, int Calories, List<String> dietLabels, List<String> healthLabels, String mealtype) {
            RecipeSearchInputData input = new RecipeSearchInputData(recipename, countryoforigin, Calories, dietLabels, healthLabels, mealtype);
            recipeSearchButtonInputBoundary.execute(input);

        }
}
