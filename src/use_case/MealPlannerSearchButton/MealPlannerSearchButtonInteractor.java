/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package use_case.MealPlannerSearchButton;

import use_case.MealPlannerDataAccessInterface;
import entity.MealPlanner;
import java.io.IOException;
/**
 *
 * @author Lenovo
 */
public class MealPlannerSearchButtonInteractor implements MealPlannerSearchButtonInputBoundary{

    
    private final MealPlannerSearchButtonOutputBoundary presenter;

    private final MealPlannerDataAccessInterface dataAccess;
    
     private final MealPlanner mealPlanner = null;
    
   
   
    public MealPlannerSearchButtonInteractor(MealPlannerSearchButtonOutputBoundary presenter, MealPlannerDataAccessInterface dataAccess) {
        this.presenter = presenter;
        this.dataAccess = dataAccess;
    }
    
    
    @Override
    public void execute(MealPlannerSearchInputData inputData){
        if (inputData.noInput()) {
            presenter.presentnoinputfail();
            return;
        }
        
        /*    Implment try catch functionlity  */   
        
        try{
            MealPlanner mealPlanner=dataAccess.getMealPlanner(inputData.getSelectedMealCount(),
                    inputData.getSelectedPlanType(),
                    inputData.getDietLabels(),
                    inputData.getHealthLabels(),
                    inputData.getMinCalories(),
                    inputData.getMaxCalories());
            presenter.presentMealPlanner(mealPlanner);
            
            
        }
        catch (RuntimeException e) {
            e.printStackTrace();  // Add this line to print the exception details
            presenter.presentnoresultfail();
        } 
       catch (IOException e) {
            throw new RuntimeException(e);
        } 
        
    }
    
}



