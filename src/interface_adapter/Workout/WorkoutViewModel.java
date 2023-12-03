package interface_adapter.Workout;

import interface_adapter.ViewModel;
import interface_adapter.Workout.WorkoutState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class WorkoutViewModel extends ViewModel {


    public static final String TITLE_LABEL = "Find a Workout";
    public static final String muscle_search_BUTTON_LABEL = "Search by Muscle Group";

    public static final String Workout_BUTTON_LABEL = "Search by Workout Name";

    private WorkoutState state = new WorkoutState();

    public WorkoutViewModel() {
        super("Workout page");
    }

    public void setState(WorkoutState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Signup Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public WorkoutState getState() {
        return state;
    }
}