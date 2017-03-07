package view;

import javax.swing.*;
import javax.swing.text.JTextComponent;

public class FormCheck {
    public FormCheck() {

    }

    public static boolean isAPercentage(JTextComponent field) {
        double value = Double.parseDouble(field.getText());
        return value <= 100 && value >= 0;
    }

    public static double readPercent(JTextField field) {
        double value = Double.parseDouble(field.getText());
        return value / 100.0;
    }
}
