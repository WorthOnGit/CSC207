package interface_adapter.recipepage;

public class RecipePageState {
    private String recipename = "";
    private String recipenameError = null;
    private String countryoforigin = "";
    private String countryoforiginError = null;
    private String portions = "";
    private String portionsError = null;

    public RecipePageState(RecipePageState copy) {
        recipename = copy.recipename;
        recipenameError = copy.recipenameError;
        countryoforigin = copy.countryoforigin;
        countryoforiginError = copy.countryoforiginError;
        portions = copy.portions;
        portionsError = copy.portionsError;
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

    public String getPortions() {
        return portions;
    }

    public String getPortionsError() {
        return portionsError;
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

    public void setPortions(String portions) {
        this.portions = portions;
    }

    public void setPortionsError(String portionsError) {
        this.portionsError = portionsError;
    }

    @Override
    public String toString() {
        return "RecipeSearch{" +
                "Recipe Name='" + recipename + '\'' +
                ", Country Of Origin='" + countryoforigin + '\'' +
                ", number of portions='" + portions + '\'' +
                '}';
    }
}