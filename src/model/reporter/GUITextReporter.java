package model.reporter;

import model.population.Population;

import javax.swing.*;
import javax.swing.text.JTextComponent;

public class GUITextReporter extends PopulationReporter  {

    private JTextComponent target;
    private StringBuilder out;

    public GUITextReporter(JTextComponent target) {
        this.target = target;
        out = new StringBuilder();
    }

    @Override
    public void report(int iteration, Population population, boolean isMinimization) {
        out.append("Reporting population " + iteration + "\n");
        out.append(population.toString()).append("\n");
        out.append("-----------------------------------\n");
    }

	@Override
	public void setup() {
		out.append("Welcome to Pevolinator - 3000\n");
	}

	@Override
	public void teardown() {
        target.setText(out.toString());
	}
}
