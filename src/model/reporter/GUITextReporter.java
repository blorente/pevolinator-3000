package model.reporter;

import model.population.Population;

import javax.swing.*;
import javax.swing.text.JTextComponent;

public class GUITextReporter implements PopulationReporter  {

    private JTextComponent target;
    private StringBuilder out;

    public GUITextReporter(JTextComponent target) {
        this.target = target;
        out = new StringBuilder();
    }

    @Override
    public void report(int iteration, Population population) {
        out.append("Reporting population " + iteration + "\n");
        out.append(population.toString()).append("\n");
        out.append("-----------------------------------\n");

        target.setText(out.toString());
    }
}
