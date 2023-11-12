package use_case.RecipeCancelButton;


import interface_adapter.RecipePageViewModel.RecipePageState;
import interface_adapter.RecipePageViewModel.RecipePageViewModel;

public class RecipeDoneInteractor implements RecipeDoneInputBoundary {
    private final RecipeDoneOutputBoundary presenter;
    private final RecipePageViewModel recipePageViewModel;

    public RecipeDoneInteractor(RecipeDoneOutputBoundary presenter, RecipePageViewModel recipePageViewModel, RecipePageViewModel recipePageViewModel1) {
        this.presenter = presenter;
        this.recipePageViewModel = recipePageViewModel1;
    }

    @Override
    public void execute() {
        RecipePageState currentState = this.recipePageViewModel.getState();
        currentState.setRecipename("");
        currentState.setCalories(0);
        this.recipePageViewModel.setState(currentState);
        this.recipePageViewModel.firePropertyChanged();

        presenter.present();



    }
}