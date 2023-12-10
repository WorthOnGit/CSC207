/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import interface_adapter.MealPlannerSearchButton.MealPlannerSearchButtonController;
import interface_adapter.MealPlannerPageViewModel.MealPlannerPageState;
import interface_adapter.MealPlannerButton.MealPlannerController;
import interface_adapter.MealPlannerPageViewModel.MealPlannerViewModel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Lenovo
 */
public class MealPlannerPageView extends JPanel implements ActionListener, PropertyChangeListener{

    public final String viewName = "Meal Planner View";
    
    private final MealPlannerViewModel mealPlannerViewModel;
   
    
   private final MealPlannerController mealPlannerController; 
   private final MealPlannerSearchButtonController mealPlannerSearchButtonController;
   private final JButton search;
   private final JButton clear;
   private final JButton Done;
   private final JTextField mincaloryinputfield = new JTextField(15); 
   private final JTextField maxcaloryinputfield = new JTextField(15);  
    
    
   
       // Use JComboBox for Meal and plan types
    private final JComboBox<String> selectedMealCount;

    private final JComboBox<String> selectedPlanType;
    
     // JList for diet labels
    private final JList<String> dietLabelList;

    private final JList<String> healthLabelList;

    // JLabel to display selected diet labels
    private final JLabel selectedDietLabelsLabel = new JLabel();

    
    
    public MealPlannerPageView(MealPlannerViewModel mealPlannerViewModel, MealPlannerController mealPlannerController, MealPlannerSearchButtonController mealPlannerSearchButtonController) {
        this.mealPlannerViewModel = mealPlannerViewModel;
        this.mealPlannerViewModel.addPropertyChangeListener(this);
        this.mealPlannerController = mealPlannerController;
        this.mealPlannerSearchButtonController = mealPlannerSearchButtonController;
       
     JLabel title = new JLabel(MealPlannerViewModel.TITLE_LABEL);
     title.setAlignmentX(Component.CENTER_ALIGNMENT);

    
        
         LabelTextPanel mincaloryinfo = new LabelTextPanel(
                new JLabel(MealPlannerViewModel.MINCALORY_TYPE_LABEL), mincaloryinputfield);
        
         LabelTextPanel maxcaloryinfo = new LabelTextPanel(
                new JLabel(MealPlannerViewModel.MAXCALORY_TYPE_LABEL),  maxcaloryinputfield);
        
    //create comboBox for MealPlan
    String[] selectedMeal={"Three","Five","Two"};
    
       selectedMealCount = new JComboBox<>(selectedMeal);
       
        LabelComboBoxPanel mealcountinfo = new LabelComboBoxPanel(
                new JLabel(MealPlannerViewModel.MEAL_COUNT_LABEL), selectedMealCount);

        
        String[] selectedPlan={"Weekly","Day"};
        
        selectedPlanType=new JComboBox<>(selectedPlan);
        selectedPlanType.setPreferredSize(new Dimension(50, 30));
        LabelComboBoxPanel plantypeinfo=new LabelComboBoxPanel(
                new JLabel(MealPlannerViewModel.MEAL_PLAN_LABEL), selectedPlanType);
        
        
            // Create a JList for diet labels
        String[] dietLabels = {" Balanced Diet","Low-Carb","Low-Fat"};
        dietLabelList = new JList<>(dietLabels);
        dietLabelList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        // Create a JScrollPane for the diet label list
        JScrollPane dietLabelScrollPane = new JScrollPane(dietLabelList);




        // Create a JList for health labels
        String[] healthLabels = {"Vegan","Vegetarian","Alcohol-free","Peanut Free"};

        healthLabelList = new JList<>(healthLabels);
        healthLabelList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        // Create a JScrollPane for the health label list
        JScrollPane healthLabelScrollPane = new JScrollPane(healthLabelList);






        JPanel buttons = new JPanel();
        search = new JButton(MealPlannerViewModel.SEARCH_BUTTON_LABEL);
        buttons.add(search);
        clear = new JButton("Clear");
        buttons.add(clear);
        Done = new JButton(MealPlannerViewModel.Done_BUTTON_LABEL);
        buttons.add(Done);

        
        
        
        
       search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 if (evt.getSource().equals(search)) {
                   mealPlannerSearchButtonController.execute(
                            mealPlannerViewModel.getState().getSelectedMealCount(),
                            mealPlannerViewModel.getState().getSelectedPlanType(),
                            mealPlannerViewModel.getState().getDietLabels(),
                            mealPlannerViewModel.getState().getHealthLabels(),
                            mealPlannerViewModel.getState().getMaxCalories(),
                            mealPlannerViewModel.getState().getMinCalories());
                     
                 }
            }
       });
        
        


 clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(clear)) {
                    MealPlannerPageState currentState = mealPlannerViewModel.getState();
                    currentState.setSelectedMealCount(0);
                    currentState.setSelectedPlanType(7);
                    currentState.setMaxCalories(0.0);
                    currentState.setMinCalories(0.0);
                    currentState.setDietLabels(new ArrayList<>());
                    currentState.setHealthLabels(new ArrayList<>());
                    mealPlannerViewModel.setState(currentState);
                    mealPlannerViewModel.firePropertyChanged();

                    // Continue with your existing code...
                }
            }
        });







     Done.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(Done)) {
                    mealPlannerController.execute();
                }
            }
        });
     
     
     /*
      selectedMealCount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MealPlannerPageState currentState = mealPlannerViewModel.getState();
     
     
                currentState.setSelectedMealCount((int) selectedMealCount.getSelectedItem());
                System.out.println("important result"+selectedMealCount);
                mealPlannerViewModel.setState(currentState);
            }
        });*/
     
    selectedMealCount.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        MealPlannerPageState currentState = mealPlannerViewModel.getState();
        int convertedMealCount=0;
        // Assuming that the selected items are strings like "Two", "Three", etc.
        String selectedMealCountStr = (String) selectedMealCount.getSelectedItem();

        if(selectedMealCountStr.equals("Two"))
        {
             convertedMealCount=2;
        }else if(selectedMealCountStr.equals("Three")){
         convertedMealCount=3;   
        }
        else if(selectedMealCountStr.equals("Five"))
        {
            convertedMealCount=5;
        }
        currentState.setSelectedMealCount(convertedMealCount);
        System.out.println("Important result: " + convertedMealCount);
        mealPlannerViewModel.setState(currentState);
    }
});

      selectedPlanType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MealPlannerPageState currentState = mealPlannerViewModel.getState();
                 int convertedPlanType=0;
                  String selectPlanTypestr = (String)selectedPlanType.getSelectedItem();
                  
                   if(selectPlanTypestr.equals("Weekly"))
                 {
                       convertedPlanType=7;
                    }else if(selectPlanTypestr.equals("Day")){
                         convertedPlanType=1;   
                    }
                 currentState.setSelectedPlanType(convertedPlanType);
               // currentState.setSelectedMealCount((int) selectedPlanType.getSelectedItem());
                  System.out.println("important result 222"+convertedPlanType);
                mealPlannerViewModel.setState(currentState);
            }
        });
      
      
      
     
     
      // Add a ListSelectionListener to update the value in the RecipePageState when dietLabelList is changed
        dietLabelList.addListSelectionListener(e -> {
            MealPlannerPageState currentState = mealPlannerViewModel.getState();
            currentState.setDietLabels(dietLabelList.getSelectedValuesList());
            mealPlannerViewModel.setState(currentState);
            // Update the label to display the selected diet labels
            selectedDietLabelsLabel.setText("Selected Diet Labels: " + Arrays.toString(dietLabelList.getSelectedValuesList().toArray()));

        });




//        // Add a ListSelectionListener to update the value in the RecipePageState when healthLabelList is changed
        healthLabelList.addListSelectionListener(e -> {
            MealPlannerPageState currentState = mealPlannerViewModel.getState();
            currentState.setHealthLabels(healthLabelList.getSelectedValuesList());
            mealPlannerViewModel.setState(currentState);
            // Update the label to display the selected health labels
        });
     
     
     
    mincaloryinputfield.addKeyListener(new KeyListener() {
    @Override
    public void keyTyped(KeyEvent e) {
        
       MealPlannerPageState currentState = mealPlannerViewModel.getState();
                double text = Double.parseDouble(mincaloryinputfield.getText() + e.getKeyChar());
                currentState.setMinCalories(text);
                mealPlannerViewModel.setState(currentState);
    }
     @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
});

   
         maxcaloryinputfield.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                MealPlannerPageState currentState = mealPlannerViewModel.getState();
                double text = Double.parseDouble(maxcaloryinputfield.getText() + e.getKeyChar());
                currentState.setMaxCalories(text);
                mealPlannerViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
     
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(selectedMealCount);
        this.add(selectedPlanType);
        this.add(new LabelScrollPanePanel(new JLabel("Any dietary preferences?(Hold CTRL) \n"), dietLabelScrollPane));
        this.add(new LabelScrollPanePanel(new JLabel("Any health preferences?(Hold CTRL) \n"), healthLabelScrollPane));
        this.add(mincaloryinfo);
        this.add(maxcaloryinfo);
        this.add(buttons);
         
}
      public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "Cancel not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        MealPlannerPageState state = (MealPlannerPageState) evt.getNewValue();
        setFields(state);
    }

    private void setFields(MealPlannerPageState state) {
        mincaloryinputfield.setText(String.valueOf(state.getMinCalories()));
        maxcaloryinputfield.setText(String.valueOf(state.getMaxCalories()));
        selectedPlanType.setSelectedItem(state.getSelectedPlanType());
        selectedMealCount.setSelectedItem(state.getSelectedMealCount());
        dietLabelList.setSelectedIndices(new int[0]);
        healthLabelList.setSelectedIndices(new int[0]);
    }
    
}






