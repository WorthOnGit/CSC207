package view;

import javax.swing.*;
import java.awt.*;

/**
 * A panel containing a label and a text field.
 */
public class LabelComboBoxPanel extends JPanel {
    LabelComboBoxPanel(JLabel label, JComboBox<String> comboBox) {
        this.add(label);
        this.add(comboBox);
        this.setOpaque(false);
    }
}