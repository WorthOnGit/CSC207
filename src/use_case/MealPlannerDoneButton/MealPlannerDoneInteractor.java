/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package use_case.MealPlannerDoneButton;
import interface_adapter.MealPlannerPageViewModel.MealPlannerPageState;
import interface_adapter.MealPlannerPageViewModel.MealPlannerViewModel;
import java.util.ArrayList;
/**
 *
 * @author Lenovo
 */
public class MealPlannerDoneInteractor implements MealPlannerDoneInputBoundary{

    
    private final MealPlannerDoneOutputBoundary presenter;
    private final MealPlannerViewModel mealPlannerViewModel;
    
    public MealPlannerDoneInteractor(MealPlannerDoneOutputBoundary presenter, MealPlannerViewModel mealPlannerViewModel,MealPlannerViewModel mealPlannerViewModell) {
        this.presenter = presenter;
        this.mealPlannerViewModel = mealPlannerViewModel;
    }

    @Override
    public void execute() {
    MealPlannerPageState currentState=this.mealPlannerViewModel.getState();
    currentState.setMaxCalories(0);
    currentState.setMinCalories(0);
    currentState.setSelectedPlanType(7);
    currentState.setSelectedMealCount(0);
    currentState.setDietLabels(new ArrayList<>());
    currentState.setHealthLabels(new ArrayList<>());
    
    
    this.mealPlannerViewModel.setState(currentState);
    this.mealPlannerViewModel.firePropertyChanged();

     presenter.present();
    
    }
    
    
    
    
}
