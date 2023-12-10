/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package use_case;

import entity.MealPlanner;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public interface MealPlannerDataAccessInterface {
   MealPlanner getMealPlanner(int selectedMealCount, int selectedPlanType, List<String> dietLabels, List<String> healthLabels, double minCalories, double maxCalories)  throws IOException;
}
