package interface_adapter.CalorieCounter;


import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CalorieCounterViewModel extends ViewModel{
    public static final String TITLE_LABEL = "Calculate your Daily Calorie Intake and More Info";
    public static final String GENDER_LABEL = "Gender";
    public static final String ACTIVITY_LABEL = "Activity";

    public static final String CALCULATE_BUTTON_LABEL = "Calculate";
    public static final String Done_BUTTON_LABEL = "Done";

    private CalorieCounterState state = new CalorieCounterState();

    public CalorieCounterViewModel() {
        super("Calorie Counter View");
    }

    public void setState(CalorieCounterState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);


    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public CalorieCounterState getState() {
        return state;
    }




}

