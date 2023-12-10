/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interface_adapter.MealPlannerPageViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class MealPlannerPageState {

    private static int selectedMealCount = 3;
    private static int selectedPlanType = 7; // Weekly by default
     private static List<String> dietLabels = new ArrayList<>();
    private static List<String> healthLabels = new ArrayList<>();
    private static double minCalories = 1800;
    private static double maxCalories = 2500;

    // MealPlanPageState.java
 public MealPlannerPageState(MealPlannerPageState copy) {
        selectedMealCount = copy.selectedMealCount;
        selectedPlanType = copy.selectedPlanType;
        dietLabels = new ArrayList<>(copy.dietLabels);
        healthLabels = new ArrayList<>(copy.healthLabels);
        minCalories = copy.minCalories;
        maxCalories = copy.maxCalories;
    }

    public MealPlannerPageState() {
    }

    public static int getSelectedMealCount() {
        return selectedMealCount;
    }

    public static int getSelectedPlanType() {
        return selectedPlanType;
    }

   
    public static List<String> getDietLabels() {
        return new ArrayList<>(dietLabels);
    }

    public static List<String> getHealthLabels() {
        return new ArrayList<>(healthLabels);
    }

    public static double getMinCalories() {
        return minCalories;
    }

    public static double getMaxCalories() {
        return maxCalories;
    }

    public void setSelectedMealCount(int selectedMealCount) {
        this.selectedMealCount = selectedMealCount;
    }

    public void setSelectedPlanType(int selectedPlanType) {
        this.selectedPlanType = selectedPlanType;
    }

   
      public void setDietLabels(List<String> dietLabels) {
        this.dietLabels = new ArrayList<>(dietLabels);
    }
    public void setHealthLabels(List<String> dietLabels) {
        this.healthLabels = new ArrayList<>(healthLabels);
    }

    public void setMinCalories(double minCalories) {
        this.minCalories = minCalories;
    }

    public void setMaxCalories(double maxCalories) {
        this.maxCalories = maxCalories;
    }

    // Constructors, getters, setters, and other methods

    // Add more methods as needed
@Override
public String toString() {
    return "MealPlannerPageState{" +
            "selectedMealCount=" + selectedMealCount +
            ", selectedPlanType=" + selectedPlanType +
            ", dietLabels=" + dietLabels +
            ", healthLabels=" + healthLabels +
            ", minCalories=" + minCalories +
            ", maxCalories=" + maxCalories +
            '}';
}

    
}


    // Add more methods as needed




