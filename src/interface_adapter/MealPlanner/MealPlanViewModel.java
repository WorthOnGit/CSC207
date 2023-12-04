package interface_adapter.MealPlanner;

import entity.MealPlan;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
public class MealPlanViewModel {

    private MealPlan mealPlan;
    private String errorMessage;
    private final PropertyChangeSupport support;

    public MealPlanViewModel() {
        this.support = new PropertyChangeSupport(this);
    }

    public MealPlan getMealPlan() {
        return mealPlan;
    }

    public void setMealPlan(MealPlan mealPlan) {
        MealPlan oldMealPlan = this.mealPlan;
        this.mealPlan = mealPlan;
        support.firePropertyChange("mealPlan", oldMealPlan, mealPlan);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        String oldErrorMessage = this.errorMessage;
        this.errorMessage = errorMessage;
        support.firePropertyChange("errorMessage", oldErrorMessage, errorMessage);
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }
}
