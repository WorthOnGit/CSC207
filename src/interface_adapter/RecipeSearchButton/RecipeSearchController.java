package interface_adapter.RecipeSearchButton;
import use_case.RecipeSearchButton.RecipeSearchInputBoundary;

public class RecipeSearchController {

    final RecipeSearchInputBoundary recipeSearchusecaseinteractor;

    public RecipeSearchController(RecipeSearchInputBoundary recipeSearchusecaseinteractor) {
        this.recipeSearchusecaseinteractor = recipeSearchusecaseinteractor;
    }

    public void execute() {

        recipeSearchusecaseinteractor.execute();
    }
}