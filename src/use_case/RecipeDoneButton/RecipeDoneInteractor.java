package use_case.RecipeDoneButton;


import interface_adapter.RecipePageViewModel.RecipePageState;
import interface_adapter.RecipePageViewModel.RecipePageViewModel;

import java.util.ArrayList;

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
        currentState.setCalories(1500);
        currentState.setCountryoforigin("World");
        currentState.setmealtype("any");
        currentState.setDietLabels(new ArrayList<>());

        this.recipePageViewModel.setState(currentState);
        this.recipePageViewModel.firePropertyChanged();

        presenter.present();



    }
}