package interface_adapter.SearchByMuscle;

import interface_adapter.SearchByMuscle.SearchByMuscleState;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SearchByMuscleViewModel extends ViewModel {

    public static final String TITLE_LABEL = "Search Exercise By Muscle";
    public static final String SEARCH_BUTTON_LABEL = "Search";
    public static final String Done_BUTTON_LABEL = "Done";

    private SearchByMuscleState state = new SearchByMuscleState();

    public SearchByMuscleViewModel() {
        super("Search By Muscle View");
    }

    public void setState(SearchByMuscleState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);


    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public SearchByMuscleState getState() {
        return state;
    }
}