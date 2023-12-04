package use_case.RecipeSearchButton;

import entity.Recipe;
import org.junit.jupiter.api.Test;
import use_case.RecipeDataAccessInterface;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecipeSearchButtonInteractorTest {

    private static class MockPresenter implements RecipeSearchButtonOutputBoundary {
        private boolean recipePresented = false;
        private boolean noInputFailPresented = false;
        private boolean noResultFailPresented = false;

        @Override
        public void presentrecipe(Recipe recipe) {
            recipePresented = true;
        }

        @Override
        public void presentnoinputfail() {
            noInputFailPresented = true;
        }

        @Override
        public void presentnoresultfail() {
            noResultFailPresented = true;
        }
    }

    private static class MockDataAccess implements RecipeDataAccessInterface {
        private Recipe mockRecipe;

        public void setMockRecipe(Recipe mockRecipe) {
            this.mockRecipe = mockRecipe;
        }

        @Override
        public Recipe getRecipe(String recipename, String countryoforigin, int Calories, List<String> dietLabels, List<String> healthLabels, String mealtype) throws IOException {
            return mockRecipe;
        }
    }

    @Test
    void execute_WithValidInput_PresentsRecipe() {
        // Arrange
        MockPresenter presenter = new MockPresenter();
        MockDataAccess dataAccess = new MockDataAccess();
        RecipeSearchButtonInteractor interactor = new RecipeSearchButtonInteractor(presenter, dataAccess);

        RecipeSearchInputData inputData = new RecipeSearchInputData(
                "Test Recipe",
                "Italy",
                500,
                List.of("label1", "label2"),
                List.of("label3", "label4"),
                "Dinner"
        );

        Recipe mockRecipe = new Recipe("Recipe name:\nTest Recipe\n\nCalories:\n500.0\n\nCuisine Type:\nItalian\n\nMeal Type:\nDinner\n\nDiet Label:\nlabel1\nlabel2\nHealth Label:\nlabel3\nlabel4\nIngredients:\ningredient1\ningredient2");
        dataAccess.setMockRecipe(mockRecipe);

        // Act
        interactor.execute(inputData);

        // Assert
        assertTrue(presenter.recipePresented);
        assertFalse(presenter.noInputFailPresented);
        assertFalse(presenter.noResultFailPresented);
    }

    @Test
    void execute_WithNoInput_PresentsNoInputFail() {
        // Arrange
        MockPresenter presenter = new MockPresenter();
        MockDataAccess dataAccess = new MockDataAccess();
        RecipeSearchButtonInteractor interactor = new RecipeSearchButtonInteractor(presenter, dataAccess);

        RecipeSearchInputData inputData = new RecipeSearchInputData(
                "", // Empty recipe name
                "Italy",
                500,
                List.of("label1", "label2"),
                List.of("label3", "label4"),
                "Dinner"
        );

        // Act
        interactor.execute(inputData);


        assertFalse(presenter.noResultFailPresented);
    }

    @Test
    void execute_WithRuntimeException_PresentsNoResultFail() {
        // Arrange
        MockPresenter presenter = new MockPresenter();
        MockDataAccess dataAccess = new MockDataAccess();
        RecipeSearchButtonInteractor interactor = new RecipeSearchButtonInteractor(presenter, dataAccess);

        RecipeSearchInputData inputData = new RecipeSearchInputData(
                "Test Recipe",
                "Italy",
                500,
                List.of("label1", "label2"),
                List.of("label3", "label4"),
                "Dinner"
        );

        dataAccess.setMockRecipe(null); // Simulating a RuntimeException

        // Act
        interactor.execute(inputData);

        // Assert
        assertFalse(presenter.noInputFailPresented);
    }
}