/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interface_adapter.StartPage.MealPlannerPageButton;

import use_case.MealPlannerPageButton.MealPlannerSearchInputBoundary;
/**
 *
 * @author Lenovo
 */
public class MealPlannerController {
    final MealPlannerSearchInputBoundary mealplannerusecaseinteractor;

    public MealPlannerController(MealPlannerSearchInputBoundary mealplannerusecaseinteractor) {
        this.mealplannerusecaseinteractor = mealplannerusecaseinteractor;
    }
    public void execute() {

        mealplannerusecaseinteractor.execute();
    }
}
