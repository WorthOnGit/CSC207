package interface_adapter.RecipePageViewModel;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class RecipePageViewModel extends ViewModel {

    public static final String TITLE_LABEL = "Find a Recipe";
    public static final String RECIPE_NAME_LABEL = "Recipe Name (ie. Chicken Parmesan)";
    public static final String COUSINE_TYPE_LABEL = "Cuisine Type";
    public static final String CALORIES_LABEL = "Calorie Limit (< 3000)";

    public static final String SEARCH_BUTTON_LABEL = "Search";
    public static final String Done_BUTTON_LABEL = "Done";

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