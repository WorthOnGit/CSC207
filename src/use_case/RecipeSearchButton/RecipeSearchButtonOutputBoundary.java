package use_case.RecipeSearchButton;

import entity.Recipe;

public interface RecipeSearchButtonOutputBoundary {
    void presentrecipe(Recipe recipe);

    void presentnoinputfail();

    void presentnoresultfail();
}
