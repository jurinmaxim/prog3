package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Automata {

	private boolean grid[][];
	private boolean clone[][];
	private final ArrayList<Integer> survivalRules;
	private final ArrayList<Integer> birthRules;

	/**
	 * This is the constructor of the Automata.
	 * 
	 * @param rows
	 * @param columns
	 * @param survivalRules
	 * @param birthRules
	 */
	public Automata(int rows, int columns, Integer[] survivalRules, Integer[] birthRules) {
		grid = new boolean[rows][columns];
		clone = new boolean[rows][columns];
		this.survivalRules = new ArrayList<>(Arrays.asList(survivalRules));
		this.birthRules = new ArrayList<>(Arrays.asList((birthRules)));
	}

	/**
	 * This method sets up the Automata with dead cells.
	 */
	public void initialize() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				grid[i][j] = false;
				clone[i][j] = false;
			}
		}
	}

	/**
	 * This method sets up the Automata with 50% dead 50% alive cells.
	 */
	public void randomInitialize() {
		Random random = new Random();
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				grid[i][j] = random.nextBoolean();
				clone[i][j] = false;
			}
		}
	}

	/**
	 * This method counts the neighbours around a given cell.
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	protected int countNeighbours(int i, int j) {
		int count = 0;
		if ((i > 0) && (j > 0))
			count += (grid[i - 1][j - 1] ? 1 : 0);
		if (i > 0)
			count += (grid[i - 1][j] ? 1 : 0);
		if ((i > 0) && (j < grid[0].length - 1))
			count += (grid[i - 1][j + 1] ? 1 : 0);
		if (j < grid[0].length - 1)
			count += (grid[i][j + 1] ? 1 : 0);
		if ((i < grid.length - 1) && (j < grid[0].length - 1))
			count += (grid[i + 1][j + 1] ? 1 : 0);
		if (i < grid.length - 1)
			count += (grid[i + 1][j] ? 1 : 0);
		if ((i < grid.length - 1) && (j > 0))
			count += (grid[i + 1][j - 1] ? 1 : 0);
		if (j > 0)
			count += (grid[i][j - 1] ? 1 : 0);
		return count;
	}

	/**
	 * This method is responsible for the logic of the game. It counts the
	 * neighbours around a given cell and evaluates it's next state according to the
	 * survival and birth rules.
	 */
	public void evolve() {
		int neighbours;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				neighbours = countNeighbours(i, j);
				if (grid[i][j] == true) {
					if (survivalRules.contains(neighbours))
						clone[i][j] = true;
					else
						clone[i][j] = false;
				} else {
					if (birthRules.contains(neighbours))
						clone[i][j] = true;
					else
						clone[i][j] = false;
				}
			}
		}

		for (int i = 0; i < grid.length; i++) {
			System.arraycopy(clone[i], 0, grid[i], 0, grid[0].length);
		}
	}

	/**
	 * This method returns the grid of the automata.
	 * 
	 * @return
	 */
	public boolean[][] getGrid() {
		return grid;
	}

	/**
	 * This method sets the grid of the automata to the grid that is given in the
	 * parameter.
	 * 
	 * @param grid
	 */
	public void setGrid(boolean[][] grid) {
		this.grid = grid;
	}
}