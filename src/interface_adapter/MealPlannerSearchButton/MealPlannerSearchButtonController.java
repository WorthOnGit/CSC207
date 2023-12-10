/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interface_adapter.MealPlannerSearchButton;

import java.util.List;
import use_case.MealPlannerSearchButton.MealPlannerSearchButtonInputBoundary;
import use_case.MealPlannerSearchButton.MealPlannerSearchInputData;
/**
 *
 * @author Lenovo
 */
public class MealPlannerSearchButtonController {
    private final MealPlannerSearchButtonInputBoundary mealPlannerSearchButtonInputBoundary;

    public MealPlannerSearchButtonController(MealPlannerSearchButtonInputBoundary mealPlannerSearchButtonInputBoundary) {
        this.mealPlannerSearchButtonInputBoundary = mealPlannerSearchButtonInputBoundary;
    }
    
    public void execute(int selectedMealCount,int selectedPlanType,List<String> dietLabels, List<String> healthLabels,double minCalories,double maxCalories)
    {
        MealPlannerSearchInputData input=new MealPlannerSearchInputData(selectedMealCount, selectedPlanType, dietLabels, healthLabels, minCalories, maxCalories);
        mealPlannerSearchButtonInputBoundary.execute(input);
    }
    
}
