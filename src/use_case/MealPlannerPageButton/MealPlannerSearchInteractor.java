/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package use_case.MealPlannerPageButton;

/**
 *
 * @author Lenovo
 */
public class MealPlannerSearchInteractor implements MealPlannerSearchInputBoundary{
    private final MealPlannerSearchOutputBoundary MealPlannerSearchPresenter ;

    public MealPlannerSearchInteractor(MealPlannerSearchOutputBoundary MealPlannerSearchPresenter) {
        this.MealPlannerSearchPresenter = MealPlannerSearchPresenter;
    }
     @Override
    public void execute() {
        MealPlannerSearchPresenter.present();
    }
    
}
