package view;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import controller.Controller;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.text.JTextComponent;

import com.jgoodies.forms.layout.FormSpecs;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import model.solvers.fitness.Fitness;
import model.solvers.fitness.Fitness.FitnessFunctions;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Font;

public class MainForm {

	private JFrame frmXxPevolinator;
	private Controller controller;
	private JTextField crossTextField;
	private JTextField mutationTextField;
	private JTextField xMinTextField;
	private JTextField xMaxTextField;
	private JTextField toleranceTextField;
	private JTextField populationSizeTextField;
	private JTextField numberOfGenerationsTextField;
	private JTextField numberOfCrossPointsTextField;
	private JTextField genomeSizeTextField;
	private JComboBox<String> functionCB;
	private JTextComponent textReportField;
	private JButton launchNewGA;
	private JPanel textReportPanel;
	private JScrollPane textReportScroll;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm window = new MainForm();
					window.frmXxPevolinator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainForm() {
				
		this.controller = new Controller();
		
		initialize();
		setupFunctions();
		//setupHintedListeners();
		launchNewGA.addMouseListener(new MouseAdapter() {
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
    	functionCB = new JComboBox<String>();
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
		/*
        controller.setCrossPercent(gatherCrossPercent());
        controller.setMutationPercent(gatherMutationPercent());
        controller.setxMax(gatherXMax());
        controller.setxMin(gatherXMin());
        controller.setTolerance(gatherTolerance());
        controller.setGenomeSize(gatherGenomeSize());
        controller.setPopulationSize(gatherPopulationSize());
        controller.setNumberCrossPoints(gatherNumberCrossPoints());
        controller.setNumberGenerations(gatherNumberOfGenerations());
        */
        controller.launch(textReportField);
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

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmXxPevolinator = new JFrame();
		frmXxPevolinator.setTitle("xX PEVOLINATOR  - 3000 Xx");
		frmXxPevolinator.setBounds(100, 100, 801, 507);
		frmXxPevolinator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		setupFunctions();		
		frmXxPevolinator.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel formPanel = new JPanel();
		frmXxPevolinator.getContentPane().add(formPanel, BorderLayout.WEST);
		GridBagLayout gbl_formPanel = new GridBagLayout();
		gbl_formPanel.columnWidths = new int[] {161};
		gbl_formPanel.rowHeights = new int[] {212, 56};
		gbl_formPanel.columnWeights = new double[]{0.0};
		gbl_formPanel.rowWeights = new double[]{0.0, 0.0};
		formPanel.setLayout(gbl_formPanel);
		
		JPanel problemSelectionPanel = new JPanel();
		problemSelectionPanel.setBorder(new TitledBorder(null, "Problem to solve", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_problemSelectionPanel = new GridBagConstraints();
		gbc_problemSelectionPanel.anchor = GridBagConstraints.WEST;
		gbc_problemSelectionPanel.insets = new Insets(0, 0, 5, 5);
		gbc_problemSelectionPanel.gridx = 0;
		gbc_problemSelectionPanel.gridy = 0;
		formPanel.add(problemSelectionPanel, gbc_problemSelectionPanel);
		problemSelectionPanel.add(functionCB);
		
		this.launchNewGA = new JButton("Launch new GA");
		GridBagConstraints gbc_launchNewGA = new GridBagConstraints();
		gbc_launchNewGA.insets = new Insets(0, 0, 0, 5);
		gbc_launchNewGA.anchor = GridBagConstraints.WEST;
		gbc_launchNewGA.gridx = 0;
		gbc_launchNewGA.gridy = 1;
		formPanel.add(launchNewGA, gbc_launchNewGA);
		
		JTabbedPane reportPanel = new JTabbedPane(JTabbedPane.TOP);
		frmXxPevolinator.getContentPane().add(reportPanel);
		
		textReportPanel = new JPanel();
		reportPanel.addTab("Text Report", null, textReportPanel, null);		
		textReportPanel.setLayout(new BorderLayout(0, 0));
		
		textReportField = new JTextArea();
		textReportField.setFont(new Font("Roboto", Font.PLAIN, 12));
		textReportField.setEditable(false);
		
		textReportScroll = new JScrollPane(textReportField);
		textReportPanel.add(textReportScroll);
	}

}
