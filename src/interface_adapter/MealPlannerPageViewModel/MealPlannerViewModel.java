/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interface_adapter.MealPlannerPageViewModel;


import interface_adapter.ViewModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 *
 * @author Lenovo
 */
public class MealPlannerViewModel extends ViewModel {

    
    
     public static final String TITLE_LABEL = "Find a Meal Planner ";
    public static final String MEAL_COUNT_LABEL = "How many meals do you ( or want to have ) in a day?";
    public static final String MINCALORY_TYPE_LABEL = "Min calory";
   public static final String MAXCALORY_TYPE_LABEL = "Max calory";
    public static final String MEAL_PLAN_LABEL = "Meal Plan Type";
    public static final String SEARCH_BUTTON_LABEL = "Search";
    public static final String Done_BUTTON_LABEL = "Done";

    
    private MealPlannerPageState state=new MealPlannerPageState();
    
    public MealPlannerViewModel() {
        super("Meal Planner View");
        
    }
    
    public void setState(MealPlannerPageState state) {
        this.state = state;
    }
    
    
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);


    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public MealPlannerPageState getState() {
        return state;
    }

}










