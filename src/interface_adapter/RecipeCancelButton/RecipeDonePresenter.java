package interface_adapter.RecipeCancelButton;

import interface_adapter.ViewManagerModel;
import interface_adapter.StartPage.StartPageViewModel;
import use_case.RecipeDoneButton.RecipeDoneOutputBoundary;

public class RecipeDonePresenter implements RecipeDoneOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final StartPageViewModel startPageViewModel;

    public RecipeDonePresenter(ViewManagerModel viewManagerModel, StartPageViewModel startPageViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.startPageViewModel = startPageViewModel;
    }

    public void present() {
        viewManagerModel.setActiveView(startPageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}