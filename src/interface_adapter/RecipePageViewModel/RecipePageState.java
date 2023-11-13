package interface_adapter.RecipePageViewModel;

import java.util.ArrayList;
import java.util.List;

public class RecipePageState {
    private String recipename = "";
    private String recipenameError = null;
    private String countryoforigin = "";
    private String mealtype = "";
    private String mealtypeerror = null;
    private String countryoforiginError = null;
    private int calories = 0;
    private String caloriesError = null;
    private List<String> dietLabels = new ArrayList<>();
    private List<String> healthLabels = new ArrayList<>();

    public RecipePageState(RecipePageState copy) {
        recipename = copy.recipename;
        recipenameError = copy.recipenameError;
        countryoforigin = copy.countryoforigin;
        countryoforiginError = copy.countryoforiginError;
        mealtype = copy.mealtype;
        mealtypeerror = copy.mealtypeerror;
        calories = copy.calories;
        caloriesError = copy.caloriesError;
        dietLabels = new ArrayList<>(copy.dietLabels);
        healthLabels = new ArrayList<>(copy.healthLabels);
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

    public String getmealtype() {
        return mealtype;
    }

    public String getmealtypeerror() {
        return mealtypeerror;
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

    public List<String> getHealthLabels() {
        return new ArrayList<>(healthLabels);
    }
    public List<String> getDietLabels() {
        return new ArrayList<>(dietLabels);
    }

    public void setRecipename(String recipename) {
        this.recipename = recipename;
    }

    public void setmealtype(String mealtype) {
        this.mealtype = mealtype;
    }

    public void setMealtypeerror(String mealtypeerror) {
        this.mealtypeerror = mealtypeerror;
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
    public void setHealthLabels(List<String> dietLabels) {
        this.healthLabels = new ArrayList<>(healthLabels);
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
