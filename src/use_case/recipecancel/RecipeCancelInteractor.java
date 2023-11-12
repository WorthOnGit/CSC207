package use_case.recipecancel;


import interface_adapter.RecipeCancel.RecipeCancelPresenter;
import interface_adapter.RecipeSearchButton.RecipeSearchPresenter;

public class RecipeCancelInteractor implements RecipeCancelInputBoundary{
    private final RecipeCancelOutputBoundary presenter;


    public RecipeCancelInteractor(RecipeCancelOutputBoundary presenter) {
        this.presenter = presenter;
    }

    @Override
    public void execute() {
        presenter.present();

    }
}