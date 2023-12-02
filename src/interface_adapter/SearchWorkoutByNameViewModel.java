package interface_adapter;

import interface_adapter.SearchWorkoutByNameState;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SearchWorkoutByNameViewModel extends ViewModel {

    public static final String TITLE_LABEL = "Find a Exercise By Name";
    public static final String WORKOUT_NAME_LABEL = "Exercise Name (ie. Squat):";

    private SearchWorkoutByNameState state = new SearchWorkoutByNameState();

    public SearchWorkoutByNameViewModel() {
        super("Workout Name Search View");
    }

    public void setState(SearchWorkoutByNameState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);


    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public SearchWorkoutByNameState getState() {
        return state;
    }
}