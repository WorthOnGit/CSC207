package view;

import javax.swing.*;

/**
 * A panel containing a label and a text field.
 */
public class LabelScrollPanePanel extends JPanel {
    LabelScrollPanePanel(JLabel label, JScrollPane scrollPane) {
        this.add(label);
        this.add(scrollPane);

    }
}