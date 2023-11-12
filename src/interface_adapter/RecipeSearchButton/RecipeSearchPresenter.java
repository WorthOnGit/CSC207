package interface_adapter.RecipeSearchButton;

import use_case.RecipeSearchButton.RecipeSearchOutputBoundary;
import interface_adapter.ViewManagerModel;
import interface_adapter.recipepage.RecipePageViewModel;

public class RecipeSearchPresenter implements RecipeSearchOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final RecipePageViewModel recipePageViewModel;
    public RecipeSearchPresenter(ViewManagerModel viewManagerModel, RecipePageViewModel signupViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.recipePageViewModel = signupViewModel;
    }


    @Override
    public void present() {
        viewManagerModel.setActiveView(recipePageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}