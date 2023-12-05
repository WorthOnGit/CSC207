package interface_adapter.MealPlanner;

import entity.MealPlan;
import DataAccess.MDataAccess;
import interface_adapter.MealPlanner.MealPlanViewModel;

public class MealPlanController {
    private final MDataAccess mDataAccess;
    private final MealPlanViewModel mealPlanViewModel;

    public MealPlanController(MDataAccess mDataAccess, MealPlanViewModel mealPlanViewModel) {
        this.mDataAccess = mDataAccess;
        this.mealPlanViewModel = mealPlanViewModel;
    }

    public void createMealPlan(String query) {
        MealPlan mealPlan = mDataAccess.getMealPlan(query);
        if (mealPlan != null) {
            mealPlanViewModel.setMealPlan(mealPlan);
        } else {
            // Handle the error scenario, maybe set an error message in the ViewModel
            mealPlanViewModel.setErrorMessage("Failed to fetch meal plan.");
        }
    }
}
