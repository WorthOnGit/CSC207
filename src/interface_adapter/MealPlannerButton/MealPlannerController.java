/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interface_adapter.MealPlannerButton;
import use_case.MealPlannerDoneButton.MealPlannerDoneInputBoundary;
/**
 *
 * @author Lenovo
 */
public class MealPlannerController {
    final MealPlannerDoneInputBoundary mealPlannerCancelInputboundary;

    public MealPlannerController(MealPlannerDoneInputBoundary mealPlannerCancelInputboundary) {
        this.mealPlannerCancelInputboundary = mealPlannerCancelInputboundary;
    }
    public void execute(){
        
        mealPlannerCancelInputboundary.execute();
    }
}
