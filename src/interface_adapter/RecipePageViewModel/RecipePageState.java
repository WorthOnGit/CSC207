package interface_adapter.RecipePageViewModel;

public class RecipePageState {
    private String recipename = "";
    private String recipenameError = null;
    private String countryoforigin = "";
    private String countryoforiginError = null;
    private int CalorieLimit = 0;
    private String CalorieLimitError = null;

    public RecipePageState(RecipePageState copy) {
        recipename = copy.recipename;
        recipenameError = copy.recipenameError;
        countryoforigin = copy.countryoforigin;
        countryoforiginError = copy.countryoforiginError;
        CalorieLimit = copy.CalorieLimit;
        CalorieLimitError = copy.CalorieLimitError;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public RecipePageState() {
    }

    public String getRecipename() {
        return recipename;
    }

    public String getRecipenameError() {
        return recipenameError;
    }

    public String getCountryoforigin() {
        return countryoforigin;
    }

    public String getCountryoforiginError() {
        return countryoforiginError;
    }

    public int getCalories() {
        return CalorieLimit;
    }

    public String getCaloriesError() {
        return CalorieLimitError;
    }

    public void setRecipename(String recipename) {
        this.recipename = recipename;
    }

    public void setRecipenameError(String recipenameError) {
        this.recipenameError = recipenameError;
    }

    public void setCountryoforigin(String countryoforigin) {
        this.countryoforigin = countryoforigin;
    }

    public void setCountryoforiginError(String countryoforiginError) {
        this.countryoforiginError = countryoforiginError;
    }

    public void setCalories(int calorieLimit) {
        this.CalorieLimit = CalorieLimit;
    }

    public void setCalorieLimitError(String portionsError) {
        this.CalorieLimitError = CalorieLimitError;
    }

    @Override
    public String toString() {
        return "RecipeSearch{" +
                "Recipe Name='" + recipename + '\'' +
                ", Country Of Origin='" + countryoforigin + '\'' +
                ", number of portions='" + CalorieLimit + '\'' +
                '}';
    }
}

