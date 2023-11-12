package use_case;
import entity.Recipe;
public interface DataAccessInterface {

    Recipe getRecipe(String recipename, String cousinetype, int Calories);
}
