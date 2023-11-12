package interface_adapter.RecipeCancelButton;
import use_case.RecipeCancelButton.RecipeCancelInputBoundary;

public class RecipeCancelController {

    final RecipeCancelInputBoundary recipeCancelInputBoundary;

    public RecipeCancelController(RecipeCancelInputBoundary recipeCancelInputBoundary) {

        this.recipeCancelInputBoundary = recipeCancelInputBoundary;
    }

    public void execute() {

        recipeCancelInputBoundary.execute();
    }

}