package interface_adapter.RecipePageButton;
import use_case.RecipePageButton.RecipeSearchInputBoundary;

public class RecipeSearchController {

    final RecipeSearchInputBoundary recipeSearchusecaseinteractor;

    public RecipeSearchController(RecipeSearchInputBoundary recipeSearchusecaseinteractor) {
        this.recipeSearchusecaseinteractor = recipeSearchusecaseinteractor;
    }

    public void execute() {

        recipeSearchusecaseinteractor.execute();
    }
}