/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interface_adapter.MealPlannerButton;
import interface_adapter.ViewManagerModel;
import interface_adapter.StartPage.StartPageViewModel;
import use_case.MealPlannerDoneButton.MealPlannerDoneOutputBoundary;
/**
 *
 * @author Lenovo
 */
public class MealPlannerPresenter implements MealPlannerDoneOutputBoundary{
    private final ViewManagerModel viewManagerModel;
    private final StartPageViewModel startPageViewModel;

    public MealPlannerPresenter(ViewManagerModel viewManagerModel, StartPageViewModel startPageViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.startPageViewModel = startPageViewModel;
    }
    
    public void present(){
     viewManagerModel.setActiveView(startPageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();   
    }
  
}
    

