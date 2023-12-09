package interface_adapter.StartPage;

import interface_adapter.StartPage.StartPageState;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class StartPageViewModel extends ViewModel {


    public static final String TITLE_LABEL = "MEAL PLANNER";
    public static final String recipe_search_BUTTON_LABEL = "Recipe Search";

    public static final String plan_meal_BUTTON_LABEL = "Plan Meal";

    public static final String view_saved_BUTTON_LABEL = "Saved Recipes";

    public static final String Calorie_counter_BUTTON_LABEL = "Calorie Counter";

    public static final String sign_up_BUTTON_LABEL = "Sign-Up";

    public static final String USER_SCREEN_BUTTON_LABEL = "User Screen";
    public static final String login_BUTTON_LABEL = "Login";
    public static final String username_Button_Label = "Current Logged-in User: ";

    public static final String Workout_BUTTON_LABEL = "Workout Search";


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