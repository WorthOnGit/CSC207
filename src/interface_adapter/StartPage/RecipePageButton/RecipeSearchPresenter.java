package interface_adapter.StartPage.RecipePageButton;

import use_case.RecipePageButton.RecipeSearchOutputBoundary;
import interface_adapter.ViewManagerModel;
import interface_adapter.RecipePageViewModel.RecipePageViewModel;

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