package view;

import interface_adapter.StartPage.StartPageState;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {
    private final ViewManagerModel viewManagerModel; // Add this field

    public final String viewName = "log in";
    private final LoginViewModel loginViewModel;

    final JTextField usernameInputField = new JTextField(15);
    private final JLabel usernameErrorField = new JLabel();

    final JPasswordField passwordInputField = new JPasswordField(15);
    private final JLabel passwordErrorField = new JLabel();

    final JButton logIn;
    final JButton cancel;
    private final LoginController loginController;

    public LoginView(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, LoginController controller) {
        this.viewManagerModel = viewManagerModel;

        this.loginController = controller;
        this.loginViewModel = loginViewModel;
        this.loginViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Login Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel("Username"), usernameInputField);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel("Password"), passwordInputField);

        JPanel buttons = new JPanel();
        logIn = new JButton(loginViewModel.LOGIN_BUTTON_LABEL);
        buttons.add(logIn);
        cancel = new JButton(loginViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);
        logIn.addActionListener(this);
        cancel.addActionListener(this);

//        logInlogIn.addActionListener(                // This creates an anonymous subclass of ActionListener and instantiates it.
//                new ActionListener() {
//                    public void actionPerformed(ActionEvent evt) {
//                        if (evt.getSource().equals(logIn)) {
//                            LoginState currentState = loginViewModel.getState();
//                            loginController.execute(
//                                    currentState.getUsername(),
//                                    currentState.getPassword()
//                            );
//                            loginViewModel.setState(currentState);
//
//
//
//                        }
//                    }
//                }
//        );


        usernameInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                LoginState currentState = loginViewModel.getState();
                currentState.setUsername(usernameInputField.getText() + e.getKeyChar());
                loginViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        passwordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        LoginState currentState = loginViewModel.getState();
                        currentState.setPassword(passwordInputField.getText() + e.getKeyChar());
                        loginViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        this.add(title);
        this.add(usernameInfo);
        this.add(usernameErrorField);
        this.add(passwordInfo);
        this.add(passwordErrorField);
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {

//        viewManagerModel.setActiveView("start page"); // Use the view name of StartPageView
//        viewManagerModel.firePropertyChanged();

        if (evt.getSource().equals(logIn)) {
            LoginState currentState = loginViewModel.getState();
            loginController.execute(
                    currentState.getUsername(),
                    currentState.getPassword()
            );
            // Check if login is successful
            // Update StartPageState with the logged-in user's details
            currentState.setUsername(currentState.getUsername()); // Assuming there's a method to set login status
            loginViewModel.setState(currentState);
//            this.viewManagerModel.setLoggedInUsername(currentState.getUsername());

            // Switch to StartPageView
//            this.viewManagerModel.setActiveView("start page");
//            this.viewManagerModel.firePropertyChanged();
        }
        if (evt.getSource().equals(cancel)) {
            viewManagerModel.setActiveView("start page"); // Use the view name of StartPageView
            viewManagerModel.firePropertyChanged();

        }

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoginState state = (LoginState) evt.getNewValue();
        setFields(state);
        state.setUsername(usernameInputField.getText());
        loginViewModel.setState(state);
        viewManagerModel.setActiveView("start page"); // Use the view name of StartPageView


    }

    private void setFields(LoginState state) {
        usernameInputField.setText(state.getUsername());
    }

}
