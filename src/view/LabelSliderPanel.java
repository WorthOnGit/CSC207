package view;

import javax.swing.*;
import java.awt.*;

/**
 * A panel containing a label and a text field.
 */
public class LabelSliderPanel extends JPanel {
    LabelSliderPanel(JLabel label, JSlider Slider) {
        this.add(label);
        this.add(Slider);
        this.setOpaque(false);

    }
}