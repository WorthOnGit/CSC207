package use_case;

import entity.Calculations;

import java.io.IOException;

public interface CalorieCounterDataAccessInterface {

    Calculations getCalculation(int age, int weight, int height, String gender, String activity) throws IOException, InterruptedException;
}
