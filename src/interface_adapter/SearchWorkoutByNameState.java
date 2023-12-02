package interface_adapter;

import java.util.ArrayList;
import java.util.List;

public class SearchWorkoutByNameState {
    private static String workoutname = "";
    private String workoutnameError = null;


    public SearchWorkoutByNameState(SearchWorkoutByNameState copy) {
        workoutname = copy.workoutname;

    }


    public SearchWorkoutByNameState() {
    }

    public static String getworkoutname() {
        return workoutname;
    }

    public String getworkoutnameError() {
        return workoutnameError;
    }



    public void setworkoutname(String workoutname) {
        this.workoutname = workoutname;
    }


    public void setworkoutnameError(String workoutnameError) {
        this.workoutnameError = workoutnameError;
    }



    @Override
    public String toString() {
        return "RecipePageState{" +
                "workoutname='" + workoutname + '\'' +
                '}';
    }
}
