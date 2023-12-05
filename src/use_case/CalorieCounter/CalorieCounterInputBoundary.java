package use_case.CalorieCounter;


import java.io.IOException;

public interface CalorieCounterInputBoundary {
    void execute(CalorieCounterInputData input) throws IOException, InterruptedException;
}