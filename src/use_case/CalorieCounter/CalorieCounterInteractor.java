package use_case.CalorieCounter;


import entity.Calculations;
import use_case.CalorieCounterDataAccessInterface;

import java.io.IOException;

public class CalorieCounterInteractor implements CalorieCounterInputBoundary{
    private CalorieCounterOutputBoundary outputBoundary;

    private final Calculations calculations = null;

    private final CalorieCounterDataAccessInterface calorieCounterDataAccessInterface;

    public CalorieCounterInteractor(CalorieCounterOutputBoundary outputBoundary, CalorieCounterDataAccessInterface calorieCounterDataAccessInterface) {
        this.outputBoundary = outputBoundary;
        this.calorieCounterDataAccessInterface = calorieCounterDataAccessInterface;
    }

    @Override
    public void execute(CalorieCounterInputData inputData) throws IOException, InterruptedException {
        Calculations calculations = this.calorieCounterDataAccessInterface.getCalculation(
                inputData.getAge(),
                inputData.getWeight(),
                inputData.getHeight(),
                inputData.getGender(),
                inputData.getActivity());
        outputBoundary.presentCalculations(calculations);

        }


}
