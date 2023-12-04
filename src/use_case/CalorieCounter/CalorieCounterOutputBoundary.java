package use_case.CalorieCounter;

import entity.Calculations;
import entity.Workout;

public interface CalorieCounterOutputBoundary {

    void presentCalculations(Calculations calculations);

}
