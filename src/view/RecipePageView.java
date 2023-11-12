package view;

import interface_adapter.recipepage.RecipePageState;
import interface_adapter.recipepage.RecipePageViewModel;
import interface_adapter.RecipeCancel.RecipeCancelController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class RecipePageView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "Recipe Search View";

    private final RecipePageViewModel recipePageViewModel;

    private final RecipeCancelController recipeCancelController;
    private final JTextField recipenameInputField = new JTextField(15);
    private final JTextField countryoforiginInputField = new JTextField(15);
    private final JTextField portionsInputField = new JTextField(15);

    private final JButton search;
    private final JButton cancel;

    public RecipePageView(RecipePageViewModel recipePageViewModel, RecipeCancelController recipeCancelController) {

        this.recipeCancelController = recipeCancelController;

        this.recipePageViewModel = recipePageViewModel;
        recipePageViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(RecipePageViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel recipenameinfo = new LabelTextPanel(
                new JLabel(RecipePageViewModel.RECIPE_NAME_LABEL), recipenameInputField);
        LabelTextPanel countryoforigininfo = new LabelTextPanel(
                new JLabel(RecipePageViewModel.COUNTRY_OF_ORIGIN_LABEL), countryoforiginInputField);
        LabelTextPanel portionsinfo = new LabelTextPanel(
                new JLabel(RecipePageViewModel.PORTIONS_LABEL), portionsInputField);

        JPanel buttons = new JPanel();
        search = new JButton(RecipePageViewModel.SEARCH_BUTTON_LABEL);
        buttons.add(search);
        cancel = new JButton(RecipePageViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);


        search.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(search)) {
                            RecipePageState currentState = recipePageViewModel.getState();

//                            signupController.execute(
//                                    currentState.getUsername(),
//                                    currentState.getPassword(),
//                                    currentState.getRepeatPassword()
//                            );
                        }
                    }
                }
        );


        cancel.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(cancel)) {
                            recipeCancelController.execute();
//
                        }
                    }
                }
        );

        // This makes a new KeyListener implementing class, instantiates it, and
        // makes it listen to keystrokes in the usernameInputField.
        //
        // Notice how it has access to instance variables in the enclosing class!
        recipenameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        RecipePageState currentState = recipePageViewModel.getState();
                        String text = recipenameInputField.getText() + e.getKeyChar();
                        currentState.setRecipename(text);
                        recipePageViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        countryoforiginInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        RecipePageState currentState = recipePageViewModel.getState();
                        currentState.setCountryoforigin(countryoforiginInputField.getText() + e.getKeyChar());
                        recipePageViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        portionsInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        RecipePageState currentState = recipePageViewModel.getState();
                        currentState.setPortions(portionsInputField.getText() + e.getKeyChar());
                        recipePageViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(recipenameinfo);
        this.add(countryoforigininfo);
        this.add(portionsinfo);
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "Cancel not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        RecipePageState state = (RecipePageState) evt.getNewValue();
        if (state.getRecipenameError() != null) {
            JOptionPane.showMessageDialog(this, state.getRecipenameError());
        }
    }
}