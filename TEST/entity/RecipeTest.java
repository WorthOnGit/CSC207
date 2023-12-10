package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecipeTest {

    @Test
    void getRecipeName() {
        String recipeString = "Recipe name:\nTest Recipe\n\nCalories:\n300.0\n\nCuisine Type:\nItalian\n\nMeal Type:\nDinner\n\nDiet Label:\nlabel1\nlabel2\nHealth Label:\nlabel3\nlabel4\nIngredients:\ningredient1\ningredient2";
        Recipe recipe = new Recipe(recipeString);
        assertEquals("Test Recipe", recipe.getRecipeName());
    }

    @Test
    void getCalories() {
        String recipeString = "Recipe name:\nTest Recipe\n\nCalories:\n300.0\n\nCuisine Type:\nItalian\n\nMeal Type:\nDinner\n\nDiet Label:\nlabel1\nlabel2\nHealth Label:\nlabel3\nlabel4\nIngredients:\ningredient1\ningredient2";
        Recipe recipe = new Recipe(recipeString);
        assertEquals(300.0, recipe.getCalories());
    }

    @Test
    void getCuisineType() {
        String recipeString = "Recipe name:\nTest Recipe\n\nCalories:\n300.0\n\nCuisine Type:\nItalian\n\nMeal Type:\nDinner\n\nDiet Label:\nlabel1\nlabel2\nHealth Label:\nlabel3\nlabel4\nIngredients:\ningredient1\ningredient2";
        Recipe recipe = new Recipe(recipeString);
        assertEquals("Italian", recipe.getCuisineType());
    }

    @Test
    void getMealType() {
        String recipeString = "Recipe name:\nTest Recipe\n\nCalories:\n300.0\n\nCuisine Type:\nItalian\n\nMeal Type:\nDinner\n\nDiet Label:\nlabel1\nlabel2\nHealth Label:\nlabel3\nlabel4\nIngredients:\ningredient1\ningredient2";
        Recipe recipe = new Recipe(recipeString);
        assertEquals("Dinner", recipe.getMealType());
    }

    @Test
    void formattedDietLabels() {
        String recipeString = "Recipe name:\nTest Recipe\n\nCalories:\n300.0\n\nCuisine Type:\nItalian\n\nMeal Type:\nDinner\n\nDiet Label:\nlabel1\nlabel2\nHealth Label:\nlabel3\nlabel4\nIngredients:\ningredient1\ningredient2";
        Recipe recipe = new Recipe(recipeString);
        assertEquals("label1,\nlabel2", recipe.getDietLabels());
    }

    @Test
    void formattedHealthLabels() {
        String recipeString = "Recipe name:\nTest Recipe\n\nCalories:\n300.0\n\nCuisine Type:\nItalian\n\nMeal Type:\nDinner\n\nDiet Label:\nlabel1\nlabel2\nHealth Label:\nIngredients:\ningredient1\ningredient2";
        Recipe recipe = new Recipe(recipeString);
        assertEquals("Health Labels Not Available", recipe.getHealthLabels());
    }

    @Test
    void formattedIngredients() {
        String recipeString = "Recipe name:\nTest Recipe\n\nCalories:\n300.0\n\nCuisine Type:\nItalian\n\nMeal Type:\nDinner\n\nDiet Label:\nlabel1\nlabel2\nHealth Label:\nlabel3\nlabel4\nIngredients:\ningredient1\ningredient2";
        Recipe recipe = new Recipe(recipeString);
        assertEquals("Ingredients Not Available", recipe.getIngredients());
    }

}