package interface_adapter.StartPage;

import interface_adapter.StartPage.StartPageState;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class StartPageViewModel extends ViewModel {


    public static final String TITLE_LABEL = "MEAL PLANNER";
    public static final String recipe_search_BUTTON_LABEL = "Recipe Search";

    public static final String plan_meal_BUTTON_LABEL = "Plan Meal";
    public static final String Calorie_counter_BUTTON_LABEL = "Calorie Counter";

    private StartPageState state = new StartPageState();

    public StartPageViewModel() {
        super("start page");
    }

    public void setState(StartPageState state) {
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

    public StartPageState getState() {
        return state;
    }
}