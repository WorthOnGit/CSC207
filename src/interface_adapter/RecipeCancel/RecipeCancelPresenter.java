package interface_adapter.RecipeCancel;

import interface_adapter.ViewManagerModel;
import interface_adapter.StartPage.StartPageViewModel;
import interface_adapter.recipepage.RecipePageViewModel;
import use_case.recipecancel.RecipeCancelOutputBoundary;

public class RecipeCancelPresenter implements RecipeCancelOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final StartPageViewModel startPageViewModel;
    public RecipeCancelPresenter(ViewManagerModel viewManagerModel, StartPageViewModel startPageViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.startPageViewModel = startPageViewModel;
    }

    public void present() {
        viewManagerModel.setActiveView(startPageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}