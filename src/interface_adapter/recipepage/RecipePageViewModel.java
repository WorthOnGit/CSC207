package interface_adapter.recipepage;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class RecipePageViewModel extends ViewModel {

    public static final String TITLE_LABEL = "Find a Recipe";
    public static final String RECIPE_NAME_LABEL = "Recipe Name (ie. Chicken Parmesan)";
    public static final String COUNTRY_OF_ORIGIN_LABEL = "Country Of Origin (ie. Italy)";
    public static final String PORTIONS_LABEL = "Enter Portions (ie. 4)";

    public static final String SEARCH_BUTTON_LABEL = "Search";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";

    private RecipePageState state = new RecipePageState();

    public RecipePageViewModel() {
        super("Recipe Search View");
    }

    public void setState(RecipePageState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);


    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public RecipePageState getState() {
        return state;
    }
}