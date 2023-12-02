package interface_adapter.SearchWorkoutByName;

import entity.Workout;
import use_case.SearchWorkoutByName.SearchWorkoutByNameOutputBoundary;

import javax.swing.*;

public class SearchWorkoutByNamePresenter implements SearchWorkoutByNameOutputBoundary {

    private final JFrame view;

    public SearchWorkoutByNamePresenter(JFrame view) {
        this.view = view;
    }

    @Override
    public void Presentworkout(Workout workout) {
        JOptionPane.showMessageDialog(view, String.format("Good JOB!!! You Have Completed The %s Workout!!!"));

    }

    @Override
    public void Presentemptystring() {
        JOptionPane.showMessageDialog(view, String.format("Please Enter A Workout!!!"));


    }

    @Override
    public void PresentNotFound() {
        JOptionPane.showMessageDialog(view, String.format("A Workout With That Name Does Not Exist!!! Please Try Again."));

    }
}
