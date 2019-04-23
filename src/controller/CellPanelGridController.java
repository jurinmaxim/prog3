package controller;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import model.GameSettings;
import view.CellPanel;

public class CellPanelGridController extends JPanel {

	private final int rows;
	private final int columns;
	private boolean[][] automataReference;
	protected Color aliveCellColor = GameSettings.getAliveCellColor();
	protected Color deadCellColor = GameSettings.getDeadCellColor();
	protected Color cellPointerColor = GameSettings.getCellPointerColor();

	/**
	 * Constructor of CellGridPanelController
	 * 
	 * @param rows
	 * @param columns
	 */
	public CellPanelGridController(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		automataReference = new boolean[rows][columns];
		initialize();

	}

	/**
	 * Initalizes the CellPanelGrid with by filling it up with CellPanels.
	 * 
	 */
	private void initialize() {
		this.setBackground(GameSettings.getBackgroundColor());
		this.setLayout(new GridLayout(rows, columns));
		addCellPanels();
		fillCellPanelGrid();
	}

	/**
	 * Adds a CellPanel to the CellPanelGrid.
	 */
	private void addCellPanels() {
		CellPanel cellPanel;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				cellPanel = createCellPanel(i, j);
				this.add(cellPanel);
			}
		}
	}

	/**
	 * Creates a CellPanel and adds a MouseListener to it.
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	private CellPanel createCellPanel(final int row, final int col) {
		final CellPanel cellPanel = new CellPanel();
		cellPanel.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent mouseEvent) {
			}

			@Override
			public void mousePressed(MouseEvent mouseEvent) {
				modifyCellPanelAtPosition(row, col);
				modifyAutomataAtPosition(row, col);
			}

			@Override
			public void mouseReleased(MouseEvent mouseEvent) {
			}

			@Override
			public void mouseEntered(MouseEvent mouseEvent) {
				paintMouseEnteredCell(row, col);
			}

			@Override
			public void mouseExited(MouseEvent mouseEvent) {
				paintMouseExitedCell(row, col);
			}
		});

		return cellPanel;
	}

	/**
	 * Paints the entered cell in the cell pointer color.
	 * 
	 * @param row
	 * @param col
	 */
	protected void paintMouseEnteredCell(int row, int col) {
		CellPanel cellPanel = getCellPanelAtPosition(row, col);
		cellPanel.setBackground(cellPointerColor);
	}

	/**
	 * Paints the entered cell in it's original color according to it's state of
	 * living after the mouse leaves the cell.
	 * 
	 * @param row
	 * @param col
	 */
	protected void paintMouseExitedCell(int row, int col) {
		CellPanel cellPanel = getCellPanelAtPosition(row, col);
		if (cellPanel.isAlive())
			cellPanel.setBackground(aliveCellColor);
		else
			cellPanel.setBackground(deadCellColor);
	}

	/**
	 * Modifies the state of the cell at a position.
	 * 
	 * @param row
	 * @param col
	 */
	private void modifyCellPanelAtPosition(int row, int col) {
		CellPanel cellPanel = getCellPanelAtPosition(row, col);
		if (cellPanel.isAlive())
			cellPanel.setDead();
		else
			cellPanel.setAlive();
	}

	/**
	 * Modifies the state of the automata at a position.
	 * 
	 * @param row
	 * @param col
	 */
	private void modifyAutomataAtPosition(int row, int col) {
		if (automataReference[row][col])
			automataReference[row][col] = false;
		else
			automataReference[row][col] = true;
	}

	/**
	 * Fills the CellPanelGrid according to the automataReference.
	 */
	public void fillCellPanelGrid() {
		CellPanel cellPanel;
		for (int i = 0; i < automataReference.length; i++) {
			for (int j = 0; j < automataReference[0].length; j++) {
				cellPanel = getCellPanelAtPosition(i, j);
				if (automataReference[i][j])
					cellPanel.setAlive();
				else
					cellPanel.setDead();
			}
		}
	}

	/**
	 * Returns the CellPanel at a given position.
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	protected CellPanel getCellPanelAtPosition(int row, int col) {
		int index = row * rows + col;
		return (CellPanel) getComponent(index);
	}

	/**
	 * Sets the whole grid dead.
	 */
	public void emptyGrid() {
		for (int i = 0; i < rows * columns; i++) {
			CellPanel cellPanel = (CellPanel) getComponent(i);
			cellPanel.setDead();
		}
	}

	/**
	 * Returns the CellPanelGridController's automataReference.
	 * 
	 * @return
	 */
	public boolean[][] getAutomataReference() {
		return automataReference;
	}

	/**
	 * Sets the CellPanelGridController's automataReference.
	 * 
	 * @param automataReference
	 */
	public void setAutomataReference(boolean[][] automataReference) {
		this.automataReference = automataReference;
	}

}
