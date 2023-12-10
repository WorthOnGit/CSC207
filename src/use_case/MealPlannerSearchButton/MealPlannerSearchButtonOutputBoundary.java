/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package use_case.MealPlannerSearchButton;


/* Add MealPlanner instead of recipe  ************************************/
import entity.MealPlanner;

/**
 *
 * @author Lenovo
 */
public interface MealPlannerSearchButtonOutputBoundary {
   void presentMealPlanner(MealPlanner mealPlanner);

    void presentnoinputfail();

    void presentnoresultfail();
}
