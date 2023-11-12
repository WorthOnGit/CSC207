package interface_adapter.RecipeCancelButton;

import interface_adapter.ViewManagerModel;
import interface_adapter.StartPage.StartPageViewModel;
import use_case.RecipeCancelButton.RecipeCancelOutputBoundary;

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