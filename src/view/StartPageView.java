package view;

import interface_adapter.StartPage.RecipePageButton.RecipeSearchController;
import interface_adapter.StartPage.StartPageViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.Workout.WorkoutViewModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import use_case.login.LoginInputData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.RandomAccessFile;

public class StartPageView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "start page";

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        LoginState currentState = loginViewModel.getState();

        String username = currentState.getUsername();
        usernameLabel.setText(username);

    }

    private final StartPageViewModel StartPageViewModel;
    private final RecipeSearchController RecipeSearchController;
    private final JButton recipe_search;
    private final JLabel usernameLabel;
    private final JButton plan_meal;
    private final JButton view_saved_rec;

    private final JButton workout_search;

    private final JButton calorie_count;

    private final JButton sign_up;

    private final JButton login, userScreen;
//    private final LoginController loginController;

    JPanel button1 = new JPanel();
    JPanel button2 = new JPanel();

    private void initializeAndAddButtons() {
        String username = this.viewManagerModel.getLoggedInUsername(); // Adjust this based on how you get the username
        button1.remove(sign_up);
        button1.remove(login);

        if (username == null || username.isEmpty()) {
            button1.add(sign_up);
            button1.add(login);
        }

        button1.revalidate();
        button1.repaint();
    }

    LoginViewModel loginViewModel;
    ViewManagerModel viewManagerModel;

    public StartPageView(StartPageViewModel signupViewModel, interface_adapter.StartPage.RecipePageButton.RecipeSearchController recipeSearchController, WorkoutViewModel workoutViewModel, LoginViewModel loginViewModel, ViewManagerModel viewManagerModel, SignupViewModel signupViewModel1) {
        this.StartPageViewModel = signupViewModel;
        this.RecipeSearchController = recipeSearchController;
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;

        this.loginViewModel.addPropertyChangeListener(this);
        this.StartPageViewModel.addPropertyChangeListener(this);


        JLabel title = new JLabel(StartPageViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);


        JPanel buttons = new JPanel();
        // Image panel in the center
        JPanel imagePanel = new JPanel();
        ImageIcon imageIcon = new ImageIcon("src/studio_logo_5474_delhi.png");
        Image scaledImage = imageIcon.getImage().getScaledInstance(1500, 600, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(scaledImageIcon);
        imagePanel.add(imageLabel);
        /*
        // Image panel in the center
        JPanel imagePanel = new JPanel();
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\muaawiz\\IdeaProjects\\CSC207Muaawiz\\src\\kong-fitness-logo-by-collin-bigart-dribbble.png");
        Image scaledImage = imageIcon.getImage().getScaledInstance(400, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(scaledImageIcon);
        imagePanel.add(imageLabel);
*/

        recipe_search = new JButton(StartPageViewModel.recipe_search_BUTTON_LABEL);
        recipe_search.setPreferredSize(new Dimension(200, 100));
        button1.add(recipe_search);
      /* I believe there is a bug in this commented code
        plan_meal = new JButton(interface_adapter.StartPage.StartPageViewModel.plan_meal_BUTTON_LABEL);
        buttons.add(plan_meal);

        recipe_search.setPreferredSize(new Dimension(200, 100));
        buttons.add(recipe_search);
    */
        plan_meal = new JButton(StartPageViewModel.plan_meal_BUTTON_LABEL);
        plan_meal.setPreferredSize(new Dimension(200, 100));
        button1.add(plan_meal);

        view_saved_rec = new JButton(StartPageViewModel.view_saved_BUTTON_LABEL);
        view_saved_rec.setPreferredSize(new Dimension(200, 100));
        button1.add(view_saved_rec);


        calorie_count = new JButton(StartPageViewModel.Calorie_counter_BUTTON_LABEL);
        calorie_count.setPreferredSize(new Dimension(200, 100));
        button2.add(calorie_count);

        workout_search = new JButton(StartPageViewModel.Workout_BUTTON_LABEL);
        workout_search.setPreferredSize(new Dimension(200, 100));
        button2.add(workout_search);

        sign_up = new JButton(StartPageViewModel.sign_up_BUTTON_LABEL);
        userScreen = new JButton(StartPageViewModel.USER_SCREEN_BUTTON_LABEL);
        button1.add(userScreen);

        login = new JButton(StartPageViewModel.login_BUTTON_LABEL);
        usernameLabel = new JLabel(StartPageViewModel.username_Button_Label);
        button2.add(usernameLabel);

        signupViewModel.addPropertyChangeListener(this);
        initializeAndAddButtons();

        userScreen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                viewManagerModel.setActiveView("logged in");
                viewManagerModel.firePropertyChanged();


            }
        });
        view_saved_rec.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readLatestRecipeData();
            }
        });
        recipe_search.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(recipe_search)) {
                            RecipeSearchController.execute();

                        }

                    }
                }
        );

        plan_meal.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(plan_meal)) {

                        }
                    }
                }
        );

        calorie_count.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(calorie_count)) {
                        }

                    }
                }
        );
        // write a method that shows my signup page


        sign_up.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(sign_up)) {
                            viewManagerModel.setActiveView(signupViewModel1.getViewName());
                            viewManagerModel.firePropertyChanged();

                        }
                    }
                }
        );

        login.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(login)) {
                            viewManagerModel.setActiveView(loginViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        workout_search.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(workout_search)) {
                            viewManagerModel.setActiveView(workoutViewModel.getViewName());
                            System.out.println("Workout button clicked" + workoutViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                        }

                    }
                }
        );


        this.setLayout(new BorderLayout());

        this.add(title, BorderLayout.NORTH);
        this.add(imagePanel, BorderLayout.CENTER);
        this.add(button1, BorderLayout.NORTH);
        this.add(button2, BorderLayout.SOUTH);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "Cancel not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoginState currentState = loginViewModel.getState();

//        String username = currentState.getUsername();
//
//        if (username != null && !username.isEmpty()) {
//            System.out.println("Logged in as: " + username);
//        } else {
//            System.out.println("Not Logged in ");
//        }


    }


    public void readLatestRecipeData() {
        String csvFile = "./saved_recipes.csv";
        String lastLine = "";

        try (RandomAccessFile fileHandler = new RandomAccessFile(csvFile, "r")) {
            long fileLength = fileHandler.length() - 1;
            StringBuilder sb = new StringBuilder();

            for (long filePointer = fileLength; filePointer != -1; filePointer--) {
                fileHandler.seek(filePointer);
                int readByte = fileHandler.readByte();

                if (readByte == 0xA) {
                    if (filePointer == fileLength) {
                        continue;
                    }
                    break;

                } else if (readByte == 0xD) {
                    if (filePointer == fileLength - 1) {
                        continue;
                    }
                    break;
                }

                sb.append((char) readByte);
            }

            lastLine = sb.reverse().toString();
            JOptionPane.showMessageDialog(null, lastLine);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error reading recipe data.");
        }

    }

}