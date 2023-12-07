package view;

import interface_adapter.StartPage.RecipePageButton.RecipeSearchController;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoggedInView extends JPanel implements ActionListener, PropertyChangeListener {
    private final ViewManagerModel viewManagerModel; // Add this field

    public final String viewName = "logged in";
    private final LoggedInViewModel loggedInViewModel;

    JLabel username;

    final JButton logOut, homePage;

    /**
     * A window with a title and a JButton.
     */
    public LoggedInView(ViewManagerModel viewManagerModel, LoggedInViewModel loggedInViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.loggedInViewModel.addPropertyChangeListener(this);
        viewManagerModel.setLoggedInUsername(loggedInViewModel.getLoggedInUser());

        JLabel title = new JLabel("Logged In Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel usernameInfo = new JLabel("Currently logged in: ");
        username = new JLabel();

        JPanel buttons = new JPanel();
        logOut = new JButton(loggedInViewModel.LOGOUT_BUTTON_LABEL);
        homePage = new JButton(loggedInViewModel.HOMEPAGE_BUTTON_LABEL);
        buttons.add(homePage);
        buttons.add(logOut);

        homePage.addActionListener(this);
        logOut.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(usernameInfo);
        this.add(username);
        this.add(buttons);
    }


    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(logOut)) {
            LoggedInState loggedInState = new LoggedInState();
            loggedInState.setUsername("");
            loggedInViewModel.setState(loggedInState);
            loggedInViewModel.firePropertyChanged();
            LoggedInState currentState = loggedInViewModel.getState();
            currentState.setUsername("");
            loggedInViewModel.setState(currentState);
            viewManagerModel.setLoggedInUsername("");
            viewManagerModel.setActiveView("start page"); // Use the view name of StartPageView
            viewManagerModel.firePropertyChanged();
            JOptionPane.showMessageDialog(this, "You have been logged out. Thank you for using our service.");
        }
        if (evt.getSource().equals(homePage)) {
            viewManagerModel.setActiveView("start page"); // Use the view name of StartPageView
            viewManagerModel.firePropertyChanged();

        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoggedInState state = (LoggedInState) evt.getNewValue();
        username.setText(state.getUsername());
    }
}