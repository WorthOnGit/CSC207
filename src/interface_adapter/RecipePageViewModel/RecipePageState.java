package interface_adapter.RecipePageViewModel;

import java.util.ArrayList;
import java.util.List;

public class RecipePageState {
    private String recipename = "";
    private String recipenameError = null;
    private String countryoforigin = "";
    private String countryoforiginError = null;
    private int calories = 0;
    private String caloriesError = null;
    private List<String> dietLabels = new ArrayList<>();

    public RecipePageState(RecipePageState copy) {
        recipename = copy.recipename;
        recipenameError = copy.recipenameError;
        countryoforigin = copy.countryoforigin;
        countryoforiginError = copy.countryoforiginError;
        calories = copy.calories;
        caloriesError = copy.caloriesError;
        dietLabels = new ArrayList<>(copy.dietLabels);
    }

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
        return calories;
    }

    public String getCaloriesError() {
        return caloriesError;
    }

    public List<String> getDietLabels() {
        return new ArrayList<>(dietLabels);
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

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setCaloriesError(String caloriesError) {
        this.caloriesError = caloriesError;
    }

    public void setDietLabels(List<String> dietLabels) {
        this.dietLabels = new ArrayList<>(dietLabels);
    }

    @Override
    public String toString() {
        return "RecipePageState{" +
                "recipename='" + recipename + '\'' +
                ", recipenameError='" + recipenameError + '\'' +
                ", countryoforigin='" + countryoforigin + '\'' +
                ", countryoforiginError='" + countryoforiginError + '\'' +
                ", calories=" + calories +
                ", caloriesError='" + caloriesError + '\'' +
                ", dietLabels=" + dietLabels +
                '}';
    }
}
