package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.CellPanelGridController;

public class GUIFrame extends JFrame {

	private JButton startStopButton;
	private JButton clearGridButton;
	private JButton randomizeGridButton;
	private JSlider speedSlider;
	private JMenuItem settingsMenu;
	private JMenuItem aboutMenu;
	private JMenuItem loadMenu;
	private JMenuItem saveMenu;
	private JTextField counterField;

	private CellPanelGridController cellPanelGridController;

	/**
	 * Constructor of GUIFrame.
	 * 
	 * @param rows
	 * @param columns
	 */
	public GUIFrame(int rows, int columns) {
		setCellPanelGridController(new CellPanelGridController(rows, columns));
		initialize();
	}

	/**
	 * This method initializes the GUIFrame and it's elements.
	 */
	private void initialize() {
		int MIN_HEIGHT = 680;
		int MIN_WIDTH = 500;
		this.setMinimumSize(new Dimension(MIN_HEIGHT, MIN_WIDTH));
		this.setSize(new Dimension(MIN_HEIGHT + 20, MIN_WIDTH + 300));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Game of Life");
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new BorderLayout());
		jPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

		JMenu jMenu;
		JMenuBar jMenuBar = new JMenuBar();
		jMenu = new JMenu("File");
		jMenuBar.add(jMenu);
		setSettingsMenu(new JMenuItem("Settings"));
		setLoadMenu(new JMenuItem("Load"));
		setSaveMenu(new JMenuItem("Save"));
		jMenu.add(getLoadMenu());
		jMenu.add(getSaveMenu());
		jMenu.add(getSettingsMenu());
		jMenu = new JMenu("About");
		jMenuBar.add(jMenu);
		setAboutMenu(new JMenuItem("About"));
		jMenu.add(getAboutMenu());

		jPanel.add(getCellPanelGridController(), BorderLayout.CENTER);

		JPanel flowLayoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		flowLayoutPanel.add(new JLabel("Generations:"));
		setCounterField(new JTextField(4));
		getCounterField().setEditable(false);
		flowLayoutPanel.add(getCounterField());
		flowLayoutPanel.add(new JLabel("Speed:"));
		setSpeedSlider(new JSlider(50, 500, 250));
		getSpeedSlider().setInverted(true);
		flowLayoutPanel.add(getSpeedSlider());
		setClearGridButton(new JButton("Clear"));
		flowLayoutPanel.add(getClearGridButton());
		setRandomizeGridButton(new JButton("Randomize"));
		flowLayoutPanel.add(getRandomizeGridButton());
		flowLayoutPanel.add(new JSeparator());
		setStartStopButton(new JButton("Start"));
		flowLayoutPanel.add(getStartStopButton());
		jPanel.add(flowLayoutPanel, BorderLayout.SOUTH);

		this.setJMenuBar(jMenuBar);
		this.setContentPane(jPanel);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private void setLoadMenu(JMenuItem loadMenu) {
		this.loadMenu = loadMenu;

	}

	public JMenuItem getLoadMenu() {
		return loadMenu;
	}

	private void setSaveMenu(JMenuItem saveMenu) {
		this.saveMenu = saveMenu;

	}

	public JMenuItem getSaveMenu() {
		return saveMenu;
	}

	public JMenuItem getSettingsMenu() {
		return settingsMenu;
	}

	private void setCounterField(JTextField counterField) {
		this.counterField = counterField;
	}

	public JTextField getCounterField() {
		return counterField;
	}

	private void setSettingsMenu(JMenuItem settingsMenu) {
		this.settingsMenu = settingsMenu;
	}

	public JMenuItem getAboutMenu() {
		return aboutMenu;
	}

	private void setAboutMenu(JMenuItem aboutMenu) {
		this.aboutMenu = aboutMenu;
	}

	public CellPanelGridController getCellPanelGridController() {
		return cellPanelGridController;
	}

	private void setCellPanelGridController(CellPanelGridController cellPanelGridController) {
		this.cellPanelGridController = cellPanelGridController;
	}

	public JButton getStartStopButton() {
		return startStopButton;
	}

	private void setStartStopButton(JButton startStopButton) {
		this.startStopButton = startStopButton;
	}

	public JButton getClearGridButton() {
		return clearGridButton;
	}

	private void setClearGridButton(JButton clearGridButton) {
		this.clearGridButton = clearGridButton;
	}

	public JButton getRandomizeGridButton() {
		return randomizeGridButton;
	}

	private void setRandomizeGridButton(JButton randomizeGridButton) {
		this.randomizeGridButton = randomizeGridButton;
	}

	public JSlider getSpeedSlider() {
		return speedSlider;
	}

	private void setSpeedSlider(JSlider speedSlider) {
		this.speedSlider = speedSlider;
	}
}
