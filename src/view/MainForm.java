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
import java.io.File;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
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
import model.solvers.cross.CrossAlgorithmData;
import model.solvers.fitness.FitnessFunctionData;
import model.solvers.mutation.MutationAlgorithmData;
import model.solvers.selection.SelectionAlgorithmData;
import java.awt.FlowLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;

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
	private JLabel lblNewLabel_2;
	private JTextField seedTextField;
	private JPanel problemSettingsContainer;
	private JPanel inputFileSelectorPanel;
	private JTextField selectedFileTextField;
	private JButton selectFileButton;
	private JLabel lblHello;
	private JLabel lblCrossAlgorithm;
	private JComboBox<CrossAlgorithmData> crossAlgorithmCB;
	private JLabel lblMutationAlgorithm;
	private JComboBox mutationAlgorithmCB;
	private JLabel lblNoMutationPoints;
	private JTextField numberOfMutationPointsTextField;
	private JPanel resultsPanel;
	private JLabel fitnessResultsLabel;
	private JLabel indivResultsLabel;
	private JPanel resultsLabels;
	private JPanel muxAPanel;
	private JLabel lblNewLabel_3;
	private JTextField muxNumATextField;

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
        
        HintedInputListener nTextFieldListener = new HintedInputListener(nTextField);
        nTextField.addFocusListener(nTextFieldListener);
        
        HintedInputListener muxNumATextFieldListener = new HintedInputListener(muxNumATextField);
        nTextField.addFocusListener(muxNumATextFieldListener);

        HintedInputListener numberOfGenerationsTextFieldListener = new HintedInputListener(numberOfGenerationsTextField);
        numberOfGenerationsTextField.addFocusListener(numberOfGenerationsTextFieldListener);

        HintedInputListener numberOfCrossPointsTextFieldListener = new HintedInputListener(numberOfCrossPointsTextField);
        numberOfCrossPointsTextField.addFocusListener(numberOfCrossPointsTextFieldListener);
        
        HintedInputListener numberOfMututationPointsTextFieldListener = new HintedInputListener(numberOfMutationPointsTextField);
        numberOfMutationPointsTextField.addFocusListener(numberOfMututationPointsTextFieldListener);
        
        HintedInputListener seedTextFieldListener = new HintedInputListener(seedTextField);
        seedTextField.addFocusListener(seedTextFieldListener);
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
		        hideNGenomeSizeEntry();
				hideInputFileSelector();
				hideMuxNumAEntry();
		        if (genomeSize == -1) {
		        	showNGenomeSizeEntry();
		        } else if (genomeSize == -2) {
		        	showInputFileSelector();
		        } else if (genomeSize == -3) {
		        	showMuxNumAEntry();
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
		        controller.setSelectionAlgorithm(selectionAlgorithmCB.getSelectedIndex());
		    }
		});
    }
    
    private void setupCrossAlgorithms() {
    	crossAlgorithmCB = new JComboBox<CrossAlgorithmData>();
    	DefaultComboBoxModel<CrossAlgorithmData> model = new DefaultComboBoxModel<>(CrossAlgorithmData.crossAlgorithms);
		crossAlgorithmCB.setModel(model);
		crossAlgorithmCB.addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
		        JComboBox<?> cb = (JComboBox<?>)e.getSource();
		        controller.setCrossAlgorithm(crossAlgorithmCB.getSelectedIndex());
		    }
		});
    }
    
    private void setupMutationAlgorithms() {
    	mutationAlgorithmCB = new JComboBox<MutationAlgorithmData>();
    	DefaultComboBoxModel<MutationAlgorithmData> model = new DefaultComboBoxModel<>(MutationAlgorithmData.mutationAlgorithms);
    	mutationAlgorithmCB.setModel(model);
    	mutationAlgorithmCB.addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
		        JComboBox<?> cb = (JComboBox<?>)e.getSource();
		        controller.setMutationAlgorithm(mutationAlgorithmCB.getSelectedIndex());
		    }
		});
    }
	
	protected void hideNGenomeSizeEntry() {
		nGenomePanel.setVisible(false);
	}

	protected void showNGenomeSizeEntry() {
		controller.setGenomeSize(gatherGenomeSize());
		nGenomePanel.setVisible(true);
	}
	
	protected void hideMuxNumAEntry() {
		muxAPanel.setVisible(false);
	}

	protected void showMuxNumAEntry() {
		controller.setMuxNumA(gatherMuxNumA());
		muxAPanel.setVisible(true);
	}
	
	protected void hideInputFileSelector() {
		inputFileSelectorPanel.setVisible(false);
	}

	protected void showInputFileSelector() {
		inputFileSelectorPanel.setVisible(true);
	}

	private void gatherAndLaunch() {		
        controller.setCrossPercent(gatherCrossPercent());
        controller.setMutationPercent(gatherMutationPercent());
        controller.setElitismPercent(gatherElitismPercent());
        
        controller.setPopulationSize(gatherPopulationSize());
        controller.setNumberCrossPoints(gatherNumberCrossPoints());
        controller.setNumberMutationPoints(gatherNumberMutationPoints());
        controller.setNumberGenerations(gatherNumberOfGenerations()); 
        controller.setSeed(gatherSeed());

        controller.setTolerance(gatherTolerance());
        
        if (inputFileSelectorPanel.isVisible()) {
        	controller.setInputFilePath(gatherInputFilePath());
        }
        launchSelectedTab();
    }

	private void launchSelectedTab() {
		if (this.reportPanel.getSelectedComponent() == this.graphReportPanel) {
			controller.launch(graphReportPanel, fitnessResultsLabel, indivResultsLabel);
		} else {
	        controller.launch(textReportField);
		}
	}
	
	private int gatherSeed() {
		return FormCheck.readInt(seedTextField);
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
    
    private int gatherNumberMutationPoints() {
        return FormCheck.readInt(numberOfMutationPointsTextField);
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

	private int gatherGenomeSize() {
		return FormCheck.readInt(nTextField);
	}
	
	private int gatherMuxNumA() {
		return FormCheck.readInt(muxNumATextField);
	}

	private String gatherInputFilePath() {
		return selectedFileTextField.getText();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmXxPevolinator = new JFrame();
		frmXxPevolinator.setTitle("xX PEVOLINATOR  - 3000 Xx");
		frmXxPevolinator.setBounds(100, 100, 1106, 662);
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
		
		problemSettingsContainer = new JPanel();
		problemSelectionPanel.add(problemSettingsContainer, BorderLayout.SOUTH);
		problemSettingsContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		muxAPanel = new JPanel();
		problemSettingsContainer.add(muxAPanel);
		
		lblNewLabel_3 = new JLabel("Selection Inputs");
		muxAPanel.add(lblNewLabel_3);
		
		muxNumATextField = new JTextField();
		muxNumATextField.setText("1");
		muxAPanel.add(muxNumATextField);
		muxNumATextField.setColumns(10);
		
		inputFileSelectorPanel = new JPanel();
		problemSettingsContainer.add(inputFileSelectorPanel);
		
		selectedFileTextField = new JTextField();
		inputFileSelectorPanel.add(selectedFileTextField);
		selectedFileTextField.setColumns(15);
		
		selectFileButton = new JButton("...");
		selectFileButton.addActionListener(new ActionListener() {
			
			String path = System.getProperty("user.home");
			
			public void actionPerformed(ActionEvent e) {
				String file = selectInputFile(path);
				if (file != "Not found") {
					path = file;
				}
				selectedFileTextField.setText(file);
			}
		});
		inputFileSelectorPanel.add(selectFileButton);
		
		nGenomePanel = new JPanel();
		problemSettingsContainer.add(nGenomePanel);
		nGenomePanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		lblNewLabel_1 = new JLabel("n");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		nGenomePanel.add(lblNewLabel_1);
		
		nTextField = new JTextField();
		nTextField.setText("1");
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
				if (nTextField.getText().matches("[0-9]+")){
					genomeSize = gatherGenomeSize();
					controller.setGenomeSize(genomeSize);
				}
			}
		});
		
		nGenomePanel.add(nTextField);
		nTextField.setColumns(10);
		hideNGenomeSizeEntry();
		hideInputFileSelector();
		hideMuxNumAEntry();
		
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
		crossTextField.setText("60");
		solverParametersMutationPanel.add(crossTextField);
		crossTextField.setColumns(10);
		
		lblMutation = new JLabel("Mutation %");
		solverParametersMutationPanel.add(lblMutation);
		
		mutationTextField = new JTextField();
		mutationTextField.setText("5");
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
		selectionPanel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Algorithm Selection", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		GridBagConstraints gbc_selectionPanel = new GridBagConstraints();
		gbc_selectionPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_selectionPanel.insets = new Insets(0, 0, 5, 0);
		gbc_selectionPanel.gridx = 0;
		gbc_selectionPanel.gridy = 2;
		formPanel.add(selectionPanel, gbc_selectionPanel);
		
		setupSelectionAlgorithms();
		selectionPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		lblHello = new JLabel("Selection Algorithm");
		selectionPanel.add(lblHello);
		selectionPanel.add(selectionAlgorithmCB);
		
		lblCrossAlgorithm = new JLabel("Cross Algorithm");
		selectionPanel.add(lblCrossAlgorithm);
		
		setupCrossAlgorithms();
		
		selectionPanel.add(crossAlgorithmCB);
		
		
		lblMutationAlgorithm = new JLabel("Mutation Algorithm");
		selectionPanel.add(lblMutationAlgorithm);
		
		setupMutationAlgorithms();
		selectionPanel.add(mutationAlgorithmCB);
		
		populationPanel = new JPanel();
		populationPanel.setBorder(new TitledBorder(null, "Population Parameters", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_populationPanel = new GridBagConstraints();
		gbc_populationPanel.insets = new Insets(0, 0, 5, 0);
		gbc_populationPanel.fill = GridBagConstraints.BOTH;
		gbc_populationPanel.gridx = 0;
		gbc_populationPanel.gridy = 3;
		formPanel.add(populationPanel, gbc_populationPanel);
		populationPanel.setLayout(new GridLayout(5, 2, 0, 0));
		
		lblPopulationSize = new JLabel("Population Size");
		populationPanel.add(lblPopulationSize);
		
		populationSizeTextField = new JTextField();
		populationSizeTextField.setText("100");
		populationPanel.add(populationSizeTextField);
		populationSizeTextField.setColumns(10);
		
		lblNewLabel = new JLabel("No. Generations");
		populationPanel.add(lblNewLabel);
		
		numberOfGenerationsTextField = new JTextField();
		numberOfGenerationsTextField.setText("100");
		populationPanel.add(numberOfGenerationsTextField);
		numberOfGenerationsTextField.setColumns(10);
		
		lblNoCrossPoints = new JLabel("No. Cross Points");
		populationPanel.add(lblNoCrossPoints);
		
		numberOfCrossPointsTextField = new JTextField();
		numberOfCrossPointsTextField.setText("1");
		populationPanel.add(numberOfCrossPointsTextField);
		numberOfCrossPointsTextField.setColumns(10);
		
		lblNoMutationPoints = new JLabel("No. Mutation Points");
		populationPanel.add(lblNoMutationPoints);
		
		numberOfMutationPointsTextField = new JTextField();
		numberOfMutationPointsTextField.setText("2");
		populationPanel.add(numberOfMutationPointsTextField);
		numberOfMutationPointsTextField.setColumns(10);
		
		lblNewLabel_2 = new JLabel("Seed");
		populationPanel.add(lblNewLabel_2);
		
		seedTextField = new JTextField();
		seedTextField.setText("0");
		populationPanel.add(seedTextField);
		seedTextField.setColumns(10);
		
		this.launchNewGA = new JButton("Launch new GA");
		GridBagConstraints gbc_launchNewGA = new GridBagConstraints();
		gbc_launchNewGA.gridx = 0;
		gbc_launchNewGA.gridy = 4;
		formPanel.add(launchNewGA, gbc_launchNewGA);
		
		resultsPanel = new JPanel();
		frmXxPevolinator.getContentPane().add(resultsPanel, BorderLayout.CENTER);
		resultsPanel.setLayout(new BorderLayout(0, 0));
		
		resultsLabels = new JPanel();
		resultsPanel.add(resultsLabels, BorderLayout.SOUTH);
		resultsLabels.setLayout(new GridLayout(2, 1, 0, 0));
		
		fitnessResultsLabel = new JLabel("Best fitness: ");
		resultsLabels.add(fitnessResultsLabel);
		fitnessResultsLabel.setHorizontalAlignment(SwingConstants.LEFT);
		fitnessResultsLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		
		indivResultsLabel = new JLabel("Best individual: ");
		indivResultsLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		indivResultsLabel.setHorizontalAlignment(SwingConstants.LEFT);
		resultsLabels.add(indivResultsLabel);
		
		reportPanel = new JTabbedPane(JTabbedPane.TOP);
		resultsPanel.add(reportPanel);
		
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

	protected String selectInputFile(String startingPath) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(startingPath));
		int result = fileChooser.showOpenDialog(this.frmXxPevolinator);
		if (result == JFileChooser.APPROVE_OPTION) {
		    File selectedFile = fileChooser.getSelectedFile();
		    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
		    return selectedFile.getAbsolutePath();
		}
		return "Not found";
	}
}
