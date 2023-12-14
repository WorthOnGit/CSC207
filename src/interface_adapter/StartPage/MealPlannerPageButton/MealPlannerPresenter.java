/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interface_adapter.StartPage.MealPlannerPageButton;

import interface_adapter.ViewManagerModel;
import use_case.MealPlannerPageButton.MealPlannerSearchOutputBoundary;
import interface_adapter.MealPlannerPageViewModel.MealPlannerViewModel;

/**
 *
 * @author Lenovo
 */
public class MealPlannerPresenter implements MealPlannerSearchOutputBoundary{
   private final ViewManagerModel viewManagerModel;
   private final MealPlannerViewModel mealPlannerViewModel;
    
 
    public MealPlannerPresenter(ViewManagerModel viewManagerModel, MealPlannerViewModel mealPlannerViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.mealPlannerViewModel = mealPlannerViewModel;
    }
    
    @Override
    public void present() {
        viewManagerModel.setActiveView(mealPlannerViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
    
}
