package view;

import javax.swing.*;

import java.awt.EventQueue;
import java.awt.event.*;

import controller.Controller;
import model.solvers.fitness.Fitness;

public class MainForm_class {
    private JPanel root;
    private JTextArea textReport;
    private JPanel form;
    private JComboBox functionCB;
    private JScrollPane textReportScroll;
    private JPanel reportPanel;
    private JPanel window;
    private JButton launchNew;
    private JPanel solverParamsPanel;
    private JTextField crossTextField;
    private JTextField mutationTextField;
    private JPanel crossPercentPanel;
    private JPanel mutatePercentPanel;
    private JTabbedPane reportPane;
    private JPanel problemParamsPanel;
    private JPanel functionPanel;
    private JTextField xMinTextField;
    private JPanel xMinPanel;
    private JPanel xMaxPanel;
    private JTextField xMaxTextField;
    private JPanel tolerancePanel;
    private JTextField toleranceTextField;
    private JPanel populationPanel;
    private JPanel populationSizePanel;
    private JTextField populationSizeTextField;
    private JPanel numGenerationsPanel;
    private JTextField numberOfGenerationsTextField;
    private JTextField genomeSizeTextField;
    private JPanel genomeSizePanel;
    private JPanel numberCrossPointsPanel;
    private JTextField numberOfCrossPointsTextField;

    private Controller controller;

    public MainForm_class() {
        this.controller = new Controller();

        setupFunctions();
        setupHintedListeners();
        launchNew.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                gatherAndLaunch();
            }
        });
    }

    private void setupHintedListeners() {
        HintedInputListener crossTextFieldListener = new HintedInputListener(crossTextField, "Cross %");
        crossTextField.addFocusListener(crossTextFieldListener);

        HintedInputListener mutationTextFieldListener = new HintedInputListener(mutationTextField, "Mutation %");
        mutationTextField.addFocusListener(mutationTextFieldListener);

        HintedInputListener xMinTextFieldListener = new HintedInputListener(xMinTextField, "x Min");
        xMinTextField.addFocusListener(xMinTextFieldListener);

        HintedInputListener xMaxTextFieldListener = new HintedInputListener(xMaxTextField, "x Max");
        xMaxTextField.addFocusListener(xMaxTextFieldListener);

        HintedInputListener toleranceTextFieldListener = new HintedInputListener(toleranceTextField, "Tolerance");
        toleranceTextField.addFocusListener(toleranceTextFieldListener);

        HintedInputListener populationSizeTextFieldListener = new HintedInputListener(populationSizeTextField, "Population Size");
        populationSizeTextField.addFocusListener(populationSizeTextFieldListener);

        HintedInputListener numberOfGenerationsTextFieldListener = new HintedInputListener(numberOfGenerationsTextField, "Number of Generations");
        numberOfGenerationsTextField.addFocusListener(numberOfGenerationsTextFieldListener);

        HintedInputListener numberOfCrossPointsTextFieldListener = new HintedInputListener(numberOfCrossPointsTextField, "Number of Cross Points");
        numberOfCrossPointsTextField.addFocusListener(numberOfCrossPointsTextFieldListener);

        HintedInputListener genomeSizeTextFieldListener = new HintedInputListener(genomeSizeTextField, "Genome Size");
        genomeSizeTextField.addFocusListener(genomeSizeTextFieldListener);
    }

    private void setupFunctions() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(Fitness.fitnessFunctions);
        functionCB.setModel(model);
        functionCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox)e.getSource();
                String fitness = (String)cb.getSelectedItem();
                controller.setFitnessFunction(fitness);
            }
        });
    }

    private void gatherAndLaunch() {
        controller.setCrossPercent(gatherCrossPercent());
        controller.setMutationPercent(gatherMutationPercent());
        controller.setxMax(gatherXMax());
        controller.setxMin(gatherXMin());
        controller.setTolerance(gatherTolerance());
        controller.setGenomeSize(gatherGenomeSize());
        controller.setPopulationSize(gatherPopulationSize());
        controller.setNumberCrossPoints(gatherNumberCrossPoints());
        controller.setNumberGenerations(gatherNumberOfGenerations());
        controller.launch(textReport);
    }

    private int gatherNumberOfGenerations() {
        return FormCheck.readInt(numberOfGenerationsTextField);
    }

    private int gatherNumberCrossPoints() {
        return FormCheck.readInt(numberOfCrossPointsTextField);
    }

    private int gatherPopulationSize() {
        return FormCheck.readInt(populationSizeTextField);
    }

    private int gatherGenomeSize() {
        return FormCheck.readInt(genomeSizeTextField);
    }

    private double gatherTolerance() {
        return FormCheck.readDouble(toleranceTextField);
    }

    private double gatherXMin() {
        return FormCheck.readDouble(xMinTextField);
    }

    private double gatherXMax() {
        return FormCheck.readDouble(xMaxTextField);
    }

    private double gatherMutationPercent() {
        return FormCheck.readPercent(mutationTextField);
    }

    private double gatherCrossPercent() {
        return FormCheck.readPercent(crossTextField);
    }

    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm window = new MainForm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

