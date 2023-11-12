package interface_adapter.RecipeCancel;
import use_case.recipecancel.RecipeCancelInputBoundary;

public class RecipeCancelController {

    final RecipeCancelInputBoundary recipeCancelInputBoundary;

    public RecipeCancelController(RecipeCancelInputBoundary recipeCancelInputBoundary) {

        this.recipeCancelInputBoundary = recipeCancelInputBoundary;
    }

    public void execute() {

        recipeCancelInputBoundary.execute();
    }

}