package view;

import javax.swing.*;
import java.awt.*;

/**
 * A panel containing a label and a text field.
 */
public class LabelScrollPanePanel extends JPanel {
    LabelScrollPanePanel(JLabel label, JScrollPane scrollPane) {
        this.add(label);
        this.add(scrollPane);
        this.setOpaque(false);


    }
}