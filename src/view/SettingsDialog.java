package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import model.GameSettings;

public class SettingsDialog extends JDialog {
	private JButton okButton;
	private JButton cancelButton;
	private JCheckBox displayBordersCheckBox;
	private JSpinner gameSizeSpinner;
	private JComboBox skinPresetComboBox;
	private JTextField birthRulesTextField;
	private JTextField survivalRulesTextField;

	/**
	 * Constructor of SettingsDialog.
	 */
	public SettingsDialog() {
		this.setLocationByPlatform(true);
		initialize();
	}

	/**
	 * This method initializes the SettingsDialog and it's components.
	 */
	private void initialize() {
		int HEIGHT = 370;
		int WIDTH = 300;
		okButton = new JButton("Ok");
		cancelButton = new JButton("Cancel");
		displayBordersCheckBox = new JCheckBox("");
		gameSizeSpinner = new JSpinner();
		birthRulesTextField = new JTextField();
		survivalRulesTextField = new JTextField();
		skinPresetComboBox = new JComboBox(GameSettings.getSKINS());
		SpinnerModel spinnerModel = new SpinnerNumberModel(GameSettings.getCellGridSize(), 10, 500, 5);
		Container dialogPane = getContentPane();
		dialogPane.setLayout(new BorderLayout());

		this.setTitle("Settings");
		this.setSize(new Dimension(WIDTH, HEIGHT));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		getDisplayBordersCheckBox().setSelected(GameSettings.isBorderedGrid());
		getDisplayBordersCheckBox().setFocusPainted(false);
		getGameSizeSpinner().setModel(spinnerModel);
		getSurvivalRulesTextField().setText(GameSettings.getSurvivalPreset());
		getBirthRulesTextField().setText(GameSettings.getBirthPreset());

		JPanel verticalLayoutPanel;
		verticalLayoutPanel = new JPanel();
		verticalLayoutPanel.setLayout(new BoxLayout(verticalLayoutPanel, BoxLayout.Y_AXIS));
		verticalLayoutPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		JPanel gridLayoutPanel;
		gridLayoutPanel = new JPanel();
		gridLayoutPanel.setLayout(new GridLayout(2, 2, 5, 5));
		TitledBorder titledBorder = new TitledBorder(" Visual ");
		titledBorder.setTitleJustification(TitledBorder.CENTER);
		gridLayoutPanel.setBorder(titledBorder);
		gridLayoutPanel.add(new JLabel(" Theme"));
		gridLayoutPanel.add(getSkinPresetComboBox());
		gridLayoutPanel.add(new JLabel(" Draw borders"));
		gridLayoutPanel.add(getDisplayBordersCheckBox());
		verticalLayoutPanel.add(gridLayoutPanel);

		gridLayoutPanel = new JPanel();
		gridLayoutPanel.setLayout(new GridLayout(3, 2, 5, 5));
		titledBorder = new TitledBorder(" Game ");
		titledBorder.setTitleJustification(TitledBorder.CENTER);
		gridLayoutPanel.setBorder(titledBorder);
		gridLayoutPanel.add(new JLabel(" Grid size"));
		gridLayoutPanel.add(getGameSizeSpinner());
		gridLayoutPanel.add(new JLabel(" Survival"));
		gridLayoutPanel.add(getSurvivalRulesTextField());
		gridLayoutPanel.add(new JLabel(" Birth"));
		gridLayoutPanel.add(getBirthRulesTextField());
		verticalLayoutPanel.add(gridLayoutPanel);
		dialogPane.add(verticalLayoutPanel, BorderLayout.CENTER);

		gridLayoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		gridLayoutPanel.add(getOkButton());
		gridLayoutPanel.add(getCancelButton());
		dialogPane.add(gridLayoutPanel, BorderLayout.SOUTH);

		addGUIEventListeners();
		this.setModal(true);
		this.setVisible(false);
		this.setLocationRelativeTo(null);
	}

	/**
	 * This method adds a listener to the skin preset combo box.
	 */
	private void addGUIEventListeners() {
		getSkinPresetComboBox().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				changeSkinPreset(getSkinPresetComboBox());
			}
		});
	}

	/**
	 * This method changes the skin preset of the game.
	 * 
	 * @param skinPresetComboBox
	 */
	protected void changeSkinPreset(JComboBox skinPresetComboBox) {
		int index = skinPresetComboBox.getSelectedIndex();
		Color deadCellColor = GameSettings.getDeadCellColorAt(index);
		Color aliveCellColor = GameSettings.getAliveCellColorAt(index);
		Color cellPointerColor = GameSettings.getCellPointerColorAt(index);
		Color backgroundColor = GameSettings.getBackgroundColorAt(index);
		Color borderColor = GameSettings.getBorderColorAt(index);
		GameSettings.setAliveCellColor(aliveCellColor);
		GameSettings.setDeadCellColor(deadCellColor);
		GameSettings.setCellPointerColor(cellPointerColor);
		GameSettings.setBackgroundColor(backgroundColor);
		GameSettings.setBorderColor(borderColor);

	}

	public String getBirthRulePreset() {
		return getBirthRulesTextField().getText();
	}

	public String getSurvivalRulePreset() {
		return getSurvivalRulesTextField().getText();
	}

	protected JComboBox getSkinPresetComboBox() {
		return skinPresetComboBox;
	}

	public JTextField getBirthRulesTextField() {
		return birthRulesTextField;
	}

	public JTextField getSurvivalRulesTextField() {
		return survivalRulesTextField;
	}

	public int getCellGridSize() {
		return (int) gameSizeSpinner.getValue();
	}

	private JCheckBox getDisplayBordersCheckBox() {
		return displayBordersCheckBox;
	}

	public JSpinner getGameSizeSpinner() {
		return gameSizeSpinner;
	}

	public boolean displaysBorders() {
		return displayBordersCheckBox.isSelected();
	}

	public JButton getOkButton() {
		return okButton;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}

}
