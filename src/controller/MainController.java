package controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.Automata;
import model.GameSettings;
import view.GUIFrame;
import view.SettingsDialog;

public class MainController {

	private GUIFrame guiFrame;
	private volatile CellPanelGridController cellPanelGridController;
	private volatile Automata automata;
	private SettingsDialog settingsDialog;
	private FileHandler fileHandler;
	private volatile boolean isRunning;
	private int speed;
	private int generations;

	/**
	 * Constructor of MainController.
	 */
	public MainController() {
		createGame();
		initialize();
	}

	/**
	 * Creates the game at the start.
	 */
	private void createGame() {
		int GAME_SIZE = GameSettings.getCellGridSize();
		Integer[] survivalPreset = stringToIntegerArray(GameSettings.getSurvivalPreset());
		Integer[] birthPreset = stringToIntegerArray(GameSettings.getBirthPreset());
		this.guiFrame = new GUIFrame(GAME_SIZE, GAME_SIZE);
		this.automata = new Automata(GAME_SIZE, GAME_SIZE, survivalPreset, birthPreset);
		this.cellPanelGridController = guiFrame.getCellPanelGridController();
		this.settingsDialog = new SettingsDialog();
		this.fileHandler = new FileHandler();

	}

	/**
	 * Initializes the automata and start the evolution thread.
	 */
	private void initialize() {
		automata.initialize();
		isRunning = false;
		addGUIEventListeners();
		Thread evolutionThread = new Thread(new EvolutionThread());
		evolutionThread.start();
	}

	/**
	 * This Thread is responsible for the evolution of the grid. The threads sleep
	 * time is the speed of the evolution.
	 * 
	 * @author Maxim
	 *
	 */
	private class EvolutionThread implements Runnable {
		@Override
		public void run() {
			while (true)
				if (isRunning) {
					automata.evolve();
					cellPanelGridController.setAutomataReference(automata.getGrid());
					cellPanelGridController.fillCellPanelGrid();
					speed = guiFrame.getSpeedSlider().getValue();
					guiFrame.getCounterField().setText("" + generations++);

					try {
						Thread.sleep(speed);
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
				} else {
					automata.setGrid(cellPanelGridController.getAutomataReference());
				}
		}
	}

	/**
	 * This method adds Listeners to the GUI.
	 */
	private void addGUIEventListeners() {
		guiFrame.getStartStopButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startStopEvolution();
			}
		});
		guiFrame.getRandomizeGridButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				randomizeGrid();
			}
		});
		guiFrame.getClearGridButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				clearGrid();

			}
		});
		guiFrame.getSettingsMenu().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				openSettingsDialog();
			}
		});
		guiFrame.getLoadMenu().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				loadGame();
			}
		});
		guiFrame.getSaveMenu().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				fileHandler.saveToFile(automata.getGrid());
			}
		});
		guiFrame.getAboutMenu().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				openAboutDialog();
			}
		});
		settingsDialog.getCancelButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				closeSettingsDialog();
			}
		});
		settingsDialog.getOkButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				saveSettings();
			}
		});
	}

	/**
	 * This method is responsible for the start/stop button's function.
	 */
	private void startStopEvolution() {
		if (!isRunning) {
			guiFrame.getStartStopButton().setText("Stop");
			guiFrame.getRandomizeGridButton().setEnabled(false);
			guiFrame.getClearGridButton().setEnabled(false);
			isRunning = true;
		} else {
			guiFrame.getStartStopButton().setText("Resume");
			guiFrame.getRandomizeGridButton().setEnabled(true);
			guiFrame.getClearGridButton().setEnabled(true);
			isRunning = false;
		}
	}

	/**
	 * This method randomizes the grid, filling it with 50% alive 50% dead cells.
	 */
	private void randomizeGrid() {
		automata.randomInitialize();
		cellPanelGridController.setAutomataReference(automata.getGrid());
		cellPanelGridController.fillCellPanelGrid();
		reset();
	}

	/**
	 * This clears the grid and sets all the cells to dead.
	 */
	private void clearGrid() {
		automata.initialize();
		cellPanelGridController.setAutomataReference(automata.getGrid());
		cellPanelGridController.emptyGrid();
		reset();

	}

	/**
	 * This method is used to reset the GUI when the player loads a game or starts a
	 * new one by randomizing or clearing the grid.
	 */
	private void reset() {
		generations = 0;
		guiFrame.getCounterField().setText("");
		guiFrame.getStartStopButton().setText("Start");
		guiFrame.getRandomizeGridButton().setEnabled(true);
		guiFrame.getClearGridButton().setEnabled(true);
		isRunning = false;
	}

	/**
	 * This opens the settings menu.
	 */
	private void openSettingsDialog() {
		settingsDialog.setVisible(true);
	}

	/**
	 * This closes the settings menu.
	 */
	private void closeSettingsDialog() {
		settingsDialog.dispose();
	}

	/**
	 * This opens the About menu.
	 */
	private void openAboutDialog() {
		JOptionPane.showMessageDialog(settingsDialog,
				"Game of Life is a cellular automaton invented by John Conway." + "\n Made by Maxim Jurin" + "\n 2017",
				"About Game of Life", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * This method loads a game into the CellPanelGrid with the help of the
	 * fileHandler.
	 */
	private void loadGame() {
		boolean[][] grid = fileHandler.loadFromFile();

		if (fileHandler.getSuccess()) {
			Integer[] survivalPreset = stringToIntegerArray(GameSettings.getSurvivalPreset());
			Integer[] birthPreset = stringToIntegerArray(GameSettings.getBirthPreset());
			automata = new Automata(grid.length, grid.length, survivalPreset, birthPreset);
			guiFrame.remove(cellPanelGridController);
			cellPanelGridController = new CellPanelGridController(grid.length, grid.length);
			guiFrame.add(cellPanelGridController, BorderLayout.CENTER);
			settingsDialog.getGameSizeSpinner().setValue(grid.length);
			guiFrame.validate();
			guiFrame.repaint();
			cellPanelGridController.setAutomataReference(grid);
			cellPanelGridController.fillCellPanelGrid();
			reset();
		}
		fileHandler.setSuccess(false);
	}

	/**
	 * This method creates a new game when the settings for the size of game
	 * changes.
	 */
	private void createNewGame() {
		int GAME_SIZE = GameSettings.getCellGridSize();
		Integer[] survivalPreset = stringToIntegerArray(GameSettings.getSurvivalPreset());
		Integer[] birthPreset = stringToIntegerArray(GameSettings.getBirthPreset());
		automata = new Automata(GAME_SIZE, GAME_SIZE, survivalPreset, birthPreset);
		guiFrame.remove(cellPanelGridController);
		cellPanelGridController = new CellPanelGridController(GAME_SIZE, GAME_SIZE);
		guiFrame.add(cellPanelGridController, BorderLayout.CENTER);
		guiFrame.validate();
		guiFrame.repaint();
	}

	/**
	 * This method changes the settings that the player chose when the player clicks
	 * on the OK button in the settings menu.
	 */
	private void saveSettings() {
		boolean[][] grid = cellPanelGridController.getAutomataReference();
		int gameSize = GameSettings.getCellGridSize();
		GameSettings.setAliveCellColor(GameSettings.getAliveCellColor());
		GameSettings.setDeadCellColor(GameSettings.getDeadCellColor());
		GameSettings.setBorderColor(GameSettings.getBorderColor());
		GameSettings.setCellPointerColor(GameSettings.getCellPointerColor());
		GameSettings.setBackgroundColor(GameSettings.getBackgroundColor());
		GameSettings.setBorderedGrid(settingsDialog.displaysBorders());
		GameSettings.setCellGridSize(settingsDialog.getCellGridSize());

		String survivalRulesInput = settingsDialog.getSurvivalRulesTextField().getText();
		String birthRulesInput = settingsDialog.getBirthRulesTextField().getText();

		if (isValidInput(survivalRulesInput) && isValidInput(birthRulesInput)) {
			GameSettings.setSurvivalPreset(settingsDialog.getSurvivalRulePreset());
			GameSettings.setBirthPreset(settingsDialog.getBirthRulePreset());

			if (gameSize != settingsDialog.getCellGridSize()) {
				reset();
				createNewGame();

			} else {
				int GAME_SIZE = GameSettings.getCellGridSize();
				automata = new Automata(GAME_SIZE, GAME_SIZE,
						stringToIntegerArray(settingsDialog.getSurvivalRulePreset()),
						stringToIntegerArray(settingsDialog.getBirthRulePreset()));
				automata.setGrid(grid);
				guiFrame.remove(cellPanelGridController);
				cellPanelGridController = new CellPanelGridController(GAME_SIZE, GAME_SIZE);
				guiFrame.add(cellPanelGridController, BorderLayout.CENTER);
				if (automata.getGrid() != null) {
					cellPanelGridController.setAutomataReference(grid);
					cellPanelGridController.fillCellPanelGrid();
					guiFrame.validate();
					guiFrame.repaint();
				}
			}
			settingsDialog.dispose();
		} else {
			JOptionPane.showMessageDialog(settingsDialog, "Please choose the numbers from 0 to 8."+"\nUse the following format: n1,n2,n3,etc..",
					"Rule formatting", JOptionPane.WARNING_MESSAGE);
		}
	}

	/**
	 * This method checks if the input at the the text field in the settings is
	 * correct.
	 * 
	 * @param input
	 * @return
	 */
	protected boolean isValidInput(String input) {
		return input.matches("[0-8]?(,[0-8])*");
	}

	/**
	 * This method converts a string into an integerArray. It is used for creating
	 * the Birth and Surviving Rules.
	 * 
	 * @param text
	 * @return
	 */
	private Integer[] stringToIntegerArray(String text) {
		String[] textFieldString = text.split(",");
		Integer[] rules = new Integer[textFieldString.length];
		for (int i = 0; i < rules.length; i++) {
			try {
				rules[i] = Integer.parseInt(textFieldString[i]);
			} catch (NumberFormatException nfe) {
				nfe.printStackTrace();
			}
		}
		return rules;
	}

	/**
	 * This is the main method of the program.
	 * 
	 * @param arg
	 */
	public static void main(String[] arg) {
		new MainController();
	}

}