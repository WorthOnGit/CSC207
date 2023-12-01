package use_case;

import entity.Recipe;
import java.io.IOException;
import java.util.List;

public interface RecipeDataAccessInterface {
    Recipe getRecipe(String recipename, String countryoforigin, int Calories, List<String> dietLabels, List<String> healthLabels, String mealtype) throws IOException;
}