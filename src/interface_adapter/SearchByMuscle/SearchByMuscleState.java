package interface_adapter.SearchByMuscle;

import java.util.ArrayList;
import java.util.List;

public class SearchByMuscleState {

    private static List<String> muscles = new ArrayList<>();

    public SearchByMuscleState(SearchByMuscleState copy) {
        muscles = new ArrayList<>(copy.muscles);
    }

    public SearchByMuscleState() {}

    public static ArrayList getmuscles() {
        return new ArrayList<>(muscles);
    }

    public void setmuscles(List<String> dietLabels) {
        this.muscles = new ArrayList<>(muscles);
    }

    @Override
    public String toString() {
        return "RecipePageState{" +
                ", muscles=" + muscles +
                '}';
    }
}
