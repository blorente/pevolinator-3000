package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

import org.math.plot.Plot2DPanel;

import controller.Controller;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import model.solvers.fitness.FitnessFunctionData;
import model.solvers.selection.SelectionAlgorithmData;

public class MainForm {

	private JFrame frmXxPevolinator;
	private Controller controller;
	private JTextField crossTextField;
	private JTextField mutationTextField;
	private JTextField toleranceTextField;
	private JComboBox<FitnessFunctionData> functionCB;
	private JTextComponent textReportField;
	private JButton launchNewGA;
	private JPanel textReportPanel;
	private JScrollPane textReportScroll;
	private JPanel solverParametersMutationPanel;
	private JLabel crossPercentLabel;
	private JLabel lblMutation;
	private JLabel lblElitism;
	private JTextField elitismTextField;
	private JPanel populationPanel;
	private JLabel lblPopulationSize;
	private JTextField populationSizeTextField;
	private JLabel lblNewLabel;
	private JTextField numberOfGenerationsTextField;
	private JLabel lblNoCrossPoints;
	private JTextField numberOfCrossPointsTextField;

    private int genomeSize;
    private JPanel nGenomePanel;
    private JLabel lblNewLabel_1;
    private JTextField nTextField;
    private JLabel lblTolerance;
    private JPanel selectionPanel;
    private JComboBox<SelectionAlgorithmData> selectionAlgorithmCB;
	private JTabbedPane reportPanel;
	private Plot2DPanel graphReportPanel;

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
		setupHintedListeners();
		launchNewGA.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        gatherAndLaunch();
		    }
		});
	}
	
	private void setupHintedListeners() {
        HintedInputListener crossTextFieldListener = new HintedInputListener(crossTextField);
        crossTextField.addFocusListener(crossTextFieldListener);

        HintedInputListener mutationTextFieldListener = new HintedInputListener(mutationTextField);
        mutationTextField.addFocusListener(mutationTextFieldListener);

        HintedInputListener toleranceTextFieldListener = new HintedInputListener(toleranceTextField);
        toleranceTextField.addFocusListener(toleranceTextFieldListener);

        HintedInputListener populationSizeTextFieldListener = new HintedInputListener(populationSizeTextField);
        populationSizeTextField.addFocusListener(populationSizeTextFieldListener);
        
        HintedInputListener nTextFieldListener = new HintedInputListener(nTextField, "0");
        nTextField.addFocusListener(nTextFieldListener);

        HintedInputListener numberOfGenerationsTextFieldListener = new HintedInputListener(numberOfGenerationsTextField, "100");
        numberOfGenerationsTextField.addFocusListener(numberOfGenerationsTextFieldListener);

        HintedInputListener numberOfCrossPointsTextFieldListener = new HintedInputListener(numberOfCrossPointsTextField, "1");
        numberOfCrossPointsTextField.addFocusListener(numberOfCrossPointsTextFieldListener);
    }

    private void setupFunctions() {
    	functionCB = new JComboBox<FitnessFunctionData>();
        DefaultComboBoxModel<FitnessFunctionData> model = new DefaultComboBoxModel<>(FitnessFunctionData.fitnessFunctions);
        functionCB.setModel(model);
        functionCB.addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
		        JComboBox<?> cb = (JComboBox<?>)e.getSource();
		        String fitness = cb.getSelectedItem().toString();
		        controller.setFitnessFunction(fitness);		        
		        genomeSize = FitnessFunctionData.fitnessFunctions[cb.getSelectedIndex()].genomeSize;
		        if (genomeSize == -1) {
		        	showNGenomeSizeEntry();
		        } else {
					hideNGenomeSizeEntry();
		        }
		    }
		});
    }
    
    private void setupSelectionAlgorithms() {
		selectionAlgorithmCB = new JComboBox<SelectionAlgorithmData>();
		DefaultComboBoxModel<SelectionAlgorithmData> model = new DefaultComboBoxModel<>(SelectionAlgorithmData.selectionAlgorithms);
		selectionAlgorithmCB.setModel(model);
		selectionAlgorithmCB.addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
		        JComboBox<?> cb = (JComboBox<?>)e.getSource();
		        String fitness = cb.getSelectedItem().toString();
		        controller.setSelectionAlgorithm(cb.getSelectedIndex());		        
		        genomeSize = FitnessFunctionData.fitnessFunctions[cb.getSelectedIndex()].genomeSize;
		        if (genomeSize == -1) {
		        	showNGenomeSizeEntry();
		        } else {
					hideNGenomeSizeEntry();
		        }
		    }
		});
    }
	
	protected void hideNGenomeSizeEntry() {
		nGenomePanel.setVisible(false);
	}

	protected void showNGenomeSizeEntry() {
		nGenomePanel.setVisible(true);
	}

	private void gatherAndLaunch() {		
        controller.setCrossPercent(gatherCrossPercent());
        controller.setMutationPercent(gatherMutationPercent());
        controller.setElitismPercent(gatherElitismPercent());
        
        controller.setPopulationSize(gatherPopulationSize());
        controller.setNumberCrossPoints(gatherNumberCrossPoints());
        controller.setNumberGenerations(gatherNumberOfGenerations());        

        controller.setTolerance(gatherTolerance()); 
		controller.setGenomeSize(genomeSize);
        launchSelectedTab();
    }
	
	private void launchSelectedTab() {
		if (this.reportPanel.getSelectedComponent() == this.graphReportPanel) {
			controller.launch(graphReportPanel);
		} else {
	        controller.launch(textReportField);
		}
	}

	private double gatherElitismPercent() {
		return FormCheck.readPercent(elitismTextField);
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

    private double gatherTolerance() {
        return FormCheck.readDouble(toleranceTextField);
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
		gbl_formPanel.rowHeights = new int[] {0, 0, 0, 0, 56};
		gbl_formPanel.columnWeights = new double[]{1.0};
		gbl_formPanel.rowWeights = new double[]{0.0, 1.0, 1.0, 1.0, 0.0};
		formPanel.setLayout(gbl_formPanel);
		
		JPanel problemSelectionPanel = new JPanel();
		problemSelectionPanel.setBorder(new TitledBorder(null, "Problem to solve", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_problemSelectionPanel = new GridBagConstraints();
		gbc_problemSelectionPanel.fill = GridBagConstraints.BOTH;
		gbc_problemSelectionPanel.insets = new Insets(0, 0, 5, 0);
		gbc_problemSelectionPanel.gridx = 0;
		gbc_problemSelectionPanel.gridy = 0;
		formPanel.add(problemSelectionPanel, gbc_problemSelectionPanel);
		problemSelectionPanel.setLayout(new BorderLayout(0, 0));
		problemSelectionPanel.add(functionCB);
		
		nGenomePanel = new JPanel();
		problemSelectionPanel.add(nGenomePanel, BorderLayout.SOUTH);
		nGenomePanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		lblNewLabel_1 = new JLabel("n");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		nGenomePanel.add(lblNewLabel_1);
		
		nTextField = new JTextField();
		nTextField.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				updateGenomeSize();
			}

			public void removeUpdate(DocumentEvent e) {
				updateGenomeSize();
			}

			public void insertUpdate(DocumentEvent e) {
				updateGenomeSize();
			}
			
			private void updateGenomeSize() {
				if (nTextField.getText().matches("[0-9]+"))
					genomeSize = FormCheck.readInt((JTextField)nTextField);				
			}
		});
		
		nGenomePanel.add(nTextField);
		nTextField.setColumns(10);
		hideNGenomeSizeEntry();
		
		solverParametersMutationPanel = new JPanel();
		solverParametersMutationPanel.setBorder(new TitledBorder(null, "Solver Paremeters", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_solverParametersMutationPanel = new GridBagConstraints();
		gbc_solverParametersMutationPanel.insets = new Insets(0, 0, 5, 0);
		gbc_solverParametersMutationPanel.fill = GridBagConstraints.BOTH;
		gbc_solverParametersMutationPanel.gridx = 0;
		gbc_solverParametersMutationPanel.gridy = 1;
		formPanel.add(solverParametersMutationPanel, gbc_solverParametersMutationPanel);
		solverParametersMutationPanel.setLayout(new GridLayout(4, 2, 0, 0));
		
		crossPercentLabel = new JLabel("Cross %");
		solverParametersMutationPanel.add(crossPercentLabel);
		
		crossTextField = new JTextField();
		crossTextField.setText("25");
		solverParametersMutationPanel.add(crossTextField);
		crossTextField.setColumns(10);
		
		lblMutation = new JLabel("Mutation %");
		solverParametersMutationPanel.add(lblMutation);
		
		mutationTextField = new JTextField();
		mutationTextField.setText("1");
		solverParametersMutationPanel.add(mutationTextField);
		mutationTextField.setColumns(10);
		
		lblElitism = new JLabel("Elitism %");
		solverParametersMutationPanel.add(lblElitism);
		
		elitismTextField = new JTextField();
		elitismTextField.setText("5");
		solverParametersMutationPanel.add(elitismTextField);
		elitismTextField.setColumns(10);
		
		lblTolerance = new JLabel("Tolerance");
		solverParametersMutationPanel.add(lblTolerance);
		
		toleranceTextField = new JTextField();
		toleranceTextField.setText("0.001");
		solverParametersMutationPanel.add(toleranceTextField);
		toleranceTextField.setColumns(10);
		
		selectionPanel = new JPanel();
		selectionPanel.setBorder(new TitledBorder(null, "Selection Algorithm", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_selectionPanel = new GridBagConstraints();
		gbc_selectionPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_selectionPanel.insets = new Insets(0, 0, 5, 0);
		gbc_selectionPanel.gridx = 0;
		gbc_selectionPanel.gridy = 2;
		formPanel.add(selectionPanel, gbc_selectionPanel);
		GridBagLayout gbl_selectionPanel = new GridBagLayout();
		gbl_selectionPanel.columnWidths = new int[] {244};
		gbl_selectionPanel.rowHeights = new int[] {0};
		gbl_selectionPanel.columnWeights = new double[]{0.0};
		gbl_selectionPanel.rowWeights = new double[]{0.0};
		selectionPanel.setLayout(gbl_selectionPanel);
		
		setupSelectionAlgorithms();
		GridBagConstraints gbc_selectionAlgorithmCB = new GridBagConstraints();
		gbc_selectionAlgorithmCB.anchor = GridBagConstraints.NORTH;
		gbc_selectionAlgorithmCB.fill = GridBagConstraints.HORIZONTAL;
		gbc_selectionAlgorithmCB.gridx = 0;
		gbc_selectionAlgorithmCB.gridy = 0;
		selectionPanel.add(selectionAlgorithmCB, gbc_selectionAlgorithmCB);
		
		populationPanel = new JPanel();
		populationPanel.setBorder(new TitledBorder(null, "Population Parameters", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_populationPanel = new GridBagConstraints();
		gbc_populationPanel.insets = new Insets(0, 0, 5, 0);
		gbc_populationPanel.fill = GridBagConstraints.BOTH;
		gbc_populationPanel.gridx = 0;
		gbc_populationPanel.gridy = 3;
		formPanel.add(populationPanel, gbc_populationPanel);
		populationPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		lblPopulationSize = new JLabel("Population Size");
		populationPanel.add(lblPopulationSize);
		
		populationSizeTextField = new JTextField();
		populationSizeTextField.setText("10");
		populationPanel.add(populationSizeTextField);
		populationSizeTextField.setColumns(10);
		
		lblNewLabel = new JLabel("No. Generations");
		populationPanel.add(lblNewLabel);
		
		numberOfGenerationsTextField = new JTextField();
		numberOfGenerationsTextField.setText("10");
		populationPanel.add(numberOfGenerationsTextField);
		numberOfGenerationsTextField.setColumns(10);
		
		lblNoCrossPoints = new JLabel("No. Cross Points");
		populationPanel.add(lblNoCrossPoints);
		
		numberOfCrossPointsTextField = new JTextField();
		numberOfCrossPointsTextField.setText("1");
		populationPanel.add(numberOfCrossPointsTextField);
		numberOfCrossPointsTextField.setColumns(10);
		
		this.launchNewGA = new JButton("Launch new GA");
		GridBagConstraints gbc_launchNewGA = new GridBagConstraints();
		gbc_launchNewGA.gridx = 0;
		gbc_launchNewGA.gridy = 4;
		formPanel.add(launchNewGA, gbc_launchNewGA);
		
		reportPanel = new JTabbedPane(JTabbedPane.TOP);
		frmXxPevolinator.getContentPane().add(reportPanel);
		
		graphReportPanel = new Plot2DPanel();
		reportPanel.addTab("Graph Report", null, graphReportPanel, null);
		
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
