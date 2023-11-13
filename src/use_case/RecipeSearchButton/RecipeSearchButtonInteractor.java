package use_case.RecipeSearchButton;

import use_case.DataAccessInterface;
import entity.Recipe;

public class RecipeSearchButtonInteractor implements RecipeSearchButtonInputBoundary {

    private final RecipeSearchButtonOutputBoundary presenter;

    private final DataAccessInterface dataAccess;

    private final Recipe recipe = null;

    public RecipeSearchButtonInteractor(RecipeSearchButtonOutputBoundary presenter, DataAccessInterface dataAccess) {
        this.presenter = presenter;
        this.dataAccess = dataAccess;
    }


    @Override
    public void execute() {
        // TODO Auto-generated method stub
    }
}
