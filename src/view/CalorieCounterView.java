package view;

import interface_adapter.CalorieCounter.CalorieCounterController;
import interface_adapter.CalorieCounter.CalorieCounterState;
import interface_adapter.CalorieCounter.CalorieCounterViewModel;
import interface_adapter.StartPage.StartPageViewModel;
import interface_adapter.ViewManagerModel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class CalorieCounterView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "Calorie Counter View";

    private final JButton calculate;
    private final JButton Done;

    private final CalorieCounterViewModel calorieCounterViewModel;
    private final CalorieCounterController calorieCounterController;

    private final JSlider AgeSlider;

    private final JSlider WeightSlider;

    private final JSlider HeightSlider;

    private final JLabel AgeValueLabel = new JLabel("Age: 0");

    private final JLabel WeightValueLabel = new JLabel("Weight (In kg): 40");

    private final JLabel HeightValueLabel = new JLabel("Height (In cm): 130");

    private final JComboBox<String> GenderComboBox;

    private final JComboBox<String> ActivityComboBox;

    public CalorieCounterView(CalorieCounterViewModel calorieCounterViewModel,
                              ViewManagerModel viewManagerModel,
                              StartPageViewModel startPageViewModel,
                              CalorieCounterController calorieCounterController) {


        this.calorieCounterViewModel = calorieCounterViewModel;
        this.calorieCounterViewModel.addPropertyChangeListener(this);
        this.calorieCounterController = calorieCounterController;


        // Create JLabel for Calorie Counter Title and Align
        JLabel title = new JLabel(CalorieCounterViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create a JSlider for Age
        AgeSlider = new JSlider(JSlider.HORIZONTAL, 0, 80, 0);
        AgeSlider.setMajorTickSpacing(10);
        AgeSlider.setPaintTicks(true);
        AgeSlider.setPaintLabels(true);
        AgeSlider.setValue(0);
        LabelSliderPanel Ageinfo = new LabelSliderPanel(AgeValueLabel, AgeSlider);

        // Create a JSlider for Weight
        WeightSlider = new JSlider(JSlider.HORIZONTAL, 40, 160, 40);
        WeightSlider.setMajorTickSpacing(20);
        WeightSlider.setPaintTicks(true);
        WeightSlider.setPaintLabels(true);
        WeightSlider.setValue(40);
        LabelSliderPanel Weightinfo = new LabelSliderPanel(WeightValueLabel, WeightSlider);

        // Create a JSlider for Height
        HeightSlider = new JSlider(JSlider.HORIZONTAL, 130, 230, 130);
        HeightSlider.setMajorTickSpacing(20);
        HeightSlider.setPaintTicks(true);
        HeightSlider.setPaintLabels(true);
        HeightSlider.setValue(130);
        LabelSliderPanel Heightinfo = new LabelSliderPanel(HeightValueLabel, HeightSlider);


        String[] genders = {"male", "female"};

        GenderComboBox = new JComboBox<>(genders);
        LabelComboBoxPanel genderinfo = new LabelComboBoxPanel(
                new JLabel(CalorieCounterViewModel.GENDER_LABEL), GenderComboBox);



        // Create JComboBox for cuisine types
        String[] activities = {"Sedentary Lifestyle",
                "Slightly Active",
                "Moderately Active Lifestyle",
                "Active Lifestyle",
                "Very Active Lifestyle"};

        ActivityComboBox = new JComboBox<>(activities);
        LabelComboBoxPanel activityinfo = new LabelComboBoxPanel(
                new JLabel(CalorieCounterViewModel.ACTIVITY_LABEL), ActivityComboBox);

        JPanel buttons = new JPanel();
        calculate = new JButton(CalorieCounterViewModel.CALCULATE_BUTTON_LABEL);
        buttons.add(calculate);
        Done = new JButton(CalorieCounterViewModel.Done_BUTTON_LABEL);
        buttons.add(Done);


        // This Search Button Starts the Use Case
        calculate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(calculate)) {
                    CalorieCounterState currentstate = calorieCounterViewModel.getState();
                    try {
                        calorieCounterController.execute(currentstate.getAge(),
                                currentstate.getWeight(),
                                currentstate.getHeight(),
                                currentstate.getGender(),
                                currentstate.getActivity());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        // This Done ActionListener Takes User Back to Start Page
        Done.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(Done)) {
                    CalorieCounterState currentState = calorieCounterViewModel.getState();
                    currentState.setAge(0);
                    currentState.setWeight(40);
                    currentState.setHeight(130);
                    currentState.setGender("male");
                    currentState.setActivity("Sedentary Lifestyle");
                    calorieCounterViewModel.setState(currentState);
                    calorieCounterViewModel.firePropertyChanged();
                    viewManagerModel.setActiveView(startPageViewModel.getViewName());
                    viewManagerModel.firePropertyChanged();
                }
            }

        });

        // This Age ActionListener updates value in CalorieCounterState and the JLabel
        AgeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                CalorieCounterState currentState = calorieCounterViewModel.getState();
                int ageValue = AgeSlider.getValue();
                currentState.setAge(ageValue);
                calorieCounterViewModel.setState(currentState);

                // Update the JLabel to display the exact value
                AgeValueLabel.setText("Age: " + ageValue);
            }
        });

        // This Weight ActionListener updates value in CalorieCounterState and the JLabel
        WeightSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                CalorieCounterState currentState = calorieCounterViewModel.getState();
                int weightValue = WeightSlider.getValue();
                currentState.setWeight(weightValue);
                calorieCounterViewModel.setState(currentState);

                // Update the JLabel to display the exact value
                WeightValueLabel.setText("Weight (In kg): " + weightValue);
            }
        });

        // This Height ActionListener updates value in CalorieCounterState and the JLabel
        HeightSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                CalorieCounterState currentState = calorieCounterViewModel.getState();
                int heightValue = HeightSlider.getValue();
                currentState.setHeight(heightValue);
                calorieCounterViewModel.setState(currentState);

                // Update the JLabel to display the exact value
                HeightValueLabel.setText("Height (In cm): " + heightValue);
            }
        });


        // For Selecting Gender
        GenderComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CalorieCounterState currentState = calorieCounterViewModel.getState();
                currentState.setGender((String) GenderComboBox.getSelectedItem());
                calorieCounterViewModel.setState(currentState);
            }
        });

        // For Selecting Activity Level
        GenderComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CalorieCounterState currentState = calorieCounterViewModel.getState();
                currentState.setGender((String) GenderComboBox.getSelectedItem());
                calorieCounterViewModel.setState(currentState);
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(Ageinfo);
        this.add(Weightinfo);
        this.add(Heightinfo);
        this.add(genderinfo);
        this.add(activityinfo);
        this.add(buttons);

}
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "Cancel not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        CalorieCounterState state = (CalorieCounterState) evt.getNewValue();
        setFields(state);
    }

    private void setFields(CalorieCounterState state)
    {
        AgeSlider.setValue(state.getAge());
        WeightSlider.setValue(state.getWeight());
        HeightSlider.setValue(state.getHeight());
        GenderComboBox.setSelectedItem(state.getGender());
        ActivityComboBox.setSelectedItem(state.getActivity());
    }
}