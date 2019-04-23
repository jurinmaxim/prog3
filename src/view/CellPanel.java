package view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import model.GameSettings;

public class CellPanel extends JPanel {

	protected boolean isAlive;
	protected Color deadCellColor = GameSettings.getDeadCellColor();
	protected Color aliveCellColor = GameSettings.getAliveCellColor();
	protected Color borderColor = GameSettings.getBorderColor();

	/**
	 * Constructor of CellPanel.
	 */
	public CellPanel() {
		if (GameSettings.isBorderedGrid()) {
			setBorder(BorderFactory.createLineBorder(borderColor));
		}
	}

	/**
	 * This method sets the cell's state to alive.
	 */
	public void setAlive() {
		this.setBackground(aliveCellColor);
		this.isAlive = true;
	}

	/**
	 * This method sets the cell's state to dead.
	 */
	public void setDead() {
		this.setBackground(deadCellColor);
		this.isAlive = false;
	}

	/**
	 * This method returns the state of the cell.
	 * 
	 * @return
	 */
	public boolean isAlive() {
		return isAlive;
	}

}
