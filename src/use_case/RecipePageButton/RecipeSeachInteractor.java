package use_case.RecipePageButton;

public class RecipeSeachInteractor implements RecipeSearchInputBoundary {

    private final RecipeSearchOutputBoundary recipeSearchPresenter;

    public RecipeSeachInteractor(RecipeSearchOutputBoundary recipeSearchPresenter) {
        this.recipeSearchPresenter = recipeSearchPresenter;
    }

    @Override
    public void execute() {
        recipeSearchPresenter.present();
    }
}