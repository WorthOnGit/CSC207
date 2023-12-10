package use_case.CalorieCounter;

public class CalorieCounterInputData {
    private final int age;
    private final int weight;
    private final int height;
    private final String gender;
    private final String activity;

    public CalorieCounterInputData(int age, int weight, int height, String gender, String activity) {
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.gender = gender;
        this.activity = activity;
    }

    public int getAge() {
        return age;
    }

    public int getWeight() {
        return weight;
    }

    public int getHeight() {
        return height;
    }

    public String getGender() {return gender;}

    public String getActivity() {
        return activity;
    }

}
