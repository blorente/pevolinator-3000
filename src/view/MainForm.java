package view;

import javax.swing.*;
import java.awt.event.*;

import controller.Controller;
import model.solvers.SolverParameters;

public class MainForm {
    private JPanel root;
    private JTextArea textReport;
    private JPanel form;
    private JLabel functionL;
    private JComboBox functionCB;
    private JScrollPane textReportScroll;
    private JPanel reportPanel;
    private JPanel window;
    private JButton launchNew;
    private JLabel solverParamsL;
    private JPanel solverParamsPanel;
    private JTextField crossTextField;
    private JTextField mutationTextField;
    private JPanel crossPercentPanel;
    private JPanel mutatePercentPanel;
    private JTabbedPane reportPane;

    private Controller controller;

    public MainForm() {
        this.controller = new Controller();
        launchNew.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                gatherAndLaunch();
            }
        });

        PercentageListener crossTextFieldListener = new PercentageListener(crossTextField, "Cross %");
        crossTextField.addFocusListener(crossTextFieldListener);

        PercentageListener mutationTextFieldListener = new PercentageListener(mutationTextField, "Mutation %");
        mutationTextField.addFocusListener(mutationTextFieldListener);
    }

    private void gatherAndLaunch() {
        controller.setCrossPercent(gatherCrossPercent());
        controller.setMutationPercent(gatherMutationPercent());
        controller.launch(textReport);
    }

    private double gatherMutationPercent() {
        return FormCheck.readPercent(mutationTextField);
    }

    private double gatherCrossPercent() {
        return FormCheck.readPercent(crossTextField);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainForm");
        frame.setContentPane(new MainForm().root);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

