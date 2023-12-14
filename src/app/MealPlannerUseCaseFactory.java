/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;


import DataAccess.DataAccessMeal;
import interface_adapter.MealPlannerButton.*;

import interface_adapter.MealPlannerSearchButton.MealPlannerSearchButtonController;
import interface_adapter.MealPlannerSearchButton.MealPlannerSearchButtonPresenter;

import interface_adapter.StartPage.StartPageViewModel;
import interface_adapter.MealPlannerPageViewModel.MealPlannerViewModel;

import interface_adapter.*;

import use_case.MealPlannerDataAccessInterface;
import use_case.MealPlannerDoneButton.MealPlannerDoneInputBoundary;
import use_case.MealPlannerDoneButton.MealPlannerDoneInteractor;

import use_case.MealPlannerSearchButton.MealPlannerSearchButtonInputBoundary;
import use_case.MealPlannerSearchButton.MealPlannerSearchButtonInteractor;
import use_case.MealPlannerSearchButton.MealPlannerSearchButtonOutputBoundary;


import view.MealPlannerPageView;
import javax.swing.*;
import use_case.MealPlannerDoneButton.MealPlannerDoneOutputBoundary;

/**
 *
 * @author Lenovo
 */
public class MealPlannerUseCaseFactory {

    private MealPlannerUseCaseFactory() {
    }
    
  public static MealPlannerPageView create(
          ViewManagerModel viewManagerModel, MealPlannerViewModel mealPlannerViewModel,StartPageViewModel startpageViewModel, JFrame view){
    
      MealPlannerController controller=createcontroller(viewManagerModel, startpageViewModel,mealPlannerViewModel);
      MealPlannerSearchButtonController searchcontroller=createSearchcontroller(view);
      
      return new MealPlannerPageView(mealPlannerViewModel,controller,searchcontroller);
  
    
}
    
  private static MealPlannerController createcontroller(ViewManagerModel viewManagerModel, StartPageViewModel startpageViewModel, MealPlannerViewModel mealPlannerViewModel) {
  MealPlannerDoneOutputBoundary mealPlannercancelPresenter=new MealPlannerPresenter(viewManagerModel,mealPlannerViewModel);
  MealPlannerDoneInputBoundary  mealPlannercancelusecaseInteractor=new MealPlannerDoneInteractor(mealPlannercancelPresenter,mealPlannerViewModel,mealPlannerViewModel);  
  return new MealPlannerController(mealPlannercancelusecaseInteractor);
  
  }
  
  private static MealPlannerSearchButtonController createSearchcontroller(JFrame view){
      
      MealPlannerSearchButtonOutputBoundary mealPlannerSearchbuttonPresenter=new MealPlannerSearchButtonPresenter(view);
      MealPlannerDataAccessInterface dataAccess = new DataAccessMeal();
      
      MealPlannerSearchButtonInputBoundary mealPlannerSearchusecaseinteractor=new MealPlannerSearchButtonInteractor(mealPlannerSearchbuttonPresenter,dataAccess);
      
      return new MealPlannerSearchButtonController(mealPlannerSearchusecaseinteractor);
      
  }
  
}
