package interface_adapter.SearchByMuscle;

import entity.Workout;
import use_case.SearchByMuscle.SearchByMuscleOutputBoundary;

import javax.swing.*;

public class SearchByMusclePresenter implements SearchByMuscleOutputBoundary {

    private final JFrame view;

    public SearchByMusclePresenter(JFrame view) {
        this.view = view;
    }
    @Override
    public void Presentworkout(Workout workout) {
        JOptionPane.showMessageDialog(view, String.format("Good gob"));
    }

    @Override
    public void Presentnoneselected() {
        JOptionPane.showMessageDialog(view, String.format("Please select at least one muscle group"));


    }
}
