/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package use_case.MealPlannerSearchButton;


import java.util.List;

/**
 *
 * @author Lenovo
 */
public class MealPlannerSearchInputData {

    private final int selectedMealCount;
    private final int selectedPlanType;
    private final List<String> dietLabels;
    private final List<String> healthLabels;
    private final double minCalories;
    private final double maxCalories;
    
      public MealPlannerSearchInputData(int selectedMealCount, int selectedPlanType, List<String> dietLabels, List<String> healthLabels, double minCalories, double maxCalories) {
        this.selectedMealCount = selectedMealCount;
        this.selectedPlanType = selectedPlanType;
        this.dietLabels = dietLabels;
        this.healthLabels = healthLabels;
        this.minCalories = minCalories;
        this.maxCalories = maxCalories;
    }
    
     public int getSelectedMealCount() {
        return selectedMealCount;
    }

    public int getSelectedPlanType() {
        return selectedPlanType;
    }

    public List<String> getDietLabels() {
        return dietLabels;
    }

    public List<String> getHealthLabels() {
        return healthLabels;
    }

    public double getMinCalories() {
        return minCalories;
    }

    public double getMaxCalories() {
        return maxCalories;
    }
  
    public boolean noInput() {
        return selectedMealCount==0 &&
                selectedPlanType==0 &&
                dietLabels.isEmpty() &&
                healthLabels.isEmpty()&&
                minCalories == 0 &&
                maxCalories==0;
    }
    
    
    
    
}
