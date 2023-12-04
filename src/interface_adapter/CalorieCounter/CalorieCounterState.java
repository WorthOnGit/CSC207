package interface_adapter.CalorieCounter;

public class CalorieCounterState {
    // Variables and Errors
    private static int age = 0;
    private String ageError = null;
    private static int weight = 40;
    private String weightError = null;
    private static int height = 130;
    private String heightError = null;
    private static String gender = "male";
    private String genderError = null;
    private static String activity = "Sedentary Lifestyle";
    private String activityError = null;


    // CalorieCounter Initializer
    public CalorieCounterState(CalorieCounterState copy) {
        age = copy.age;
        ageError = copy.ageError;
        weight = copy.weight;
        weightError = copy.weightError;
        height = copy.height;
        heightError = copy.heightError;
        gender = copy.gender;
        genderError = copy.genderError;
        activity = copy.activity;
        activityError = copy.activityError;
    }

    public CalorieCounterState() {
    }

    // Get Methods for variables and Errors
    public static int getAge() {
        return age;
    }

    public String getAgeError() {
        return ageError;
    }

    public static int getWeight() {
        return weight;
    }

    public String getWeightError() {
        return weightError;
    }

    public static int getHeight() {
        return height;
    }

    public String getHeightError() {
        return heightError;
    }

    public static String getGender() {
        return gender;
    }

    public String getGenderError() {
        return genderError;
    }

    public static String getActivity() {
        return activity;
    }

    public String getActivityError() {
        return activityError;
    }


    // Set Methods for Variables and Errors

    public void setAge(int age) {
        this.age = age;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setAgeError(String ageError) {
        this.ageError = ageError;
    }

    public void setHeightError(String heightError) {
        this.heightError = heightError;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setGenderError(String genderError) {
        this.genderError = genderError;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public void setActivityError(String activityError) {
        this.activityError = activityError;
    }


    @Override
    public String toString() {
        return "CalorieCounterState{" +
                "Age='" + age + '\'' +
                ", AgeError='" + ageError + '\'' +
                ", Weight='" + weight + '\'' +
                ", WeightError='" + weightError + '\'' +
                ", Height=" + height + '\'' +
                ", HeightError='" + heightError + '\'' +
                ", Gender=" + gender + '\'' +
                ", GenderError='" + genderError + '\'' +
                ", Activity=" + activity + '\'' +
                ", ActivityError='" + activityError + '\'' +
                '}';
    }
}

