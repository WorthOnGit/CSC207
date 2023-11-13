package use_case;
import entity.Recipe;

import java.util.List;

public interface DataAccessInterface {

    Recipe getRecipe(String recipeName, String countryoforigin, int Calories, List<String> dietLabels, List<String> healthLabels, String mealtype);
}
