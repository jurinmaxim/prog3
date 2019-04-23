package model;

import java.awt.Color;

public final class GameSettings {

	private final static Color[] DEAD_CELL_PRESETS = { Color.DARK_GRAY, Color.WHITE };
	private final static Color[] ALIVE_CELL_PRESETS = { Color.WHITE, Color.DARK_GRAY };
	private final static Color[] CELL_POINTER_PRESETS = { Color.GRAY, Color.GRAY};
	private final static Color[] BACKGROUND_PRESETS = { Color.GRAY, Color.WHITE };
	private final static Color[] BORDER_PRESETS = { Color.BLACK, Color.DARK_GRAY };

	private static Color DEAD_CELL_COLOR = DEAD_CELL_PRESETS[0];
	private static Color ALIVE_CELL_COLOR = ALIVE_CELL_PRESETS[0];
	private static Color BORDER_COLOR = BORDER_PRESETS[0];
	private static Color CELL_POINTER_COLOR = CELL_POINTER_PRESETS[0];
	private static Color BACKGROUND_COLOR = BACKGROUND_PRESETS[0];
	private static int CELL_GRID_SIZE = 50;

	private final static String[] SKIN_NAMES = { "Dark", "Light" };

	private static boolean BORDERED_GRID = true;

	private static String SURVIVAL_PRESET = "2,3";

	private static String BIRTH_PRESET = "3";

	public static void setSurvivalPreset(String survivalPreset) {
		SURVIVAL_PRESET = survivalPreset;
	}

	public static String getSurvivalPreset() {
		return SURVIVAL_PRESET;
	}

	public static void setBirthPreset(String birthPreset) {
		BIRTH_PRESET = birthPreset;
	}

	public static String getBirthPreset() {
		return BIRTH_PRESET;
	}

	public static Color getDeadCellPreset() {
		return DEAD_CELL_PRESETS[0];
	}

	public static Color getAliveCellPreset() { return ALIVE_CELL_PRESETS[0]; }

	public static Color getCellPointerPreset() {
		return CELL_POINTER_PRESETS[0];
	}

	public static Color getBackgroundPreset() {
		return BACKGROUND_PRESETS[0];
	}

	public static Color getBorderPreset() {
		return BORDER_PRESETS[0];
	}

	public static Color getDeadCellColor() {
		return DEAD_CELL_COLOR;
	}

	public static Color getAliveCellColor() {
		return ALIVE_CELL_COLOR;
	}

	public static Color getCellPointerColor() {
		return CELL_POINTER_COLOR;
	}

	public static Color getBackgroundColor() {
		return BACKGROUND_COLOR;
	}

	public static Color getBorderColor() {
		return BORDER_COLOR;
	}

	public static Color getDeadCellColorAt(int index) {
		return DEAD_CELL_PRESETS[index];
	}

	public static Color getBackgroundColorAt(int index) {
		return BACKGROUND_PRESETS[index];
	}

	public static Color getBorderColorAt(int index) {
		return BORDER_PRESETS[index];
	}

	public static Color getAliveCellColorAt(int index) {
		return ALIVE_CELL_PRESETS[index];
	}

	public static Color getCellPointerColorAt(int index) {
		return CELL_POINTER_PRESETS[index];
	}

	public static void setDeadCellColor(Color deadCellColor) {
		DEAD_CELL_COLOR = deadCellColor;
	}

	public static void setCellPointerColor(Color cellPointerColor) {
		CELL_POINTER_COLOR = cellPointerColor;
	}

	public static void setBackgroundColor(Color backgroundColor) {
		BACKGROUND_COLOR = backgroundColor;
	}

	public static void setBorderColor(Color borderColor) {
		BORDER_COLOR = borderColor;
	}

	public static void setAliveCellColor(Color aliveCellColor) {
		ALIVE_CELL_COLOR = aliveCellColor;
	}

	public static int getCellGridSize() {
		return CELL_GRID_SIZE;
	}

	public static void setCellGridSize(int cellGridSize) {
		CELL_GRID_SIZE = cellGridSize;
	}

	public static boolean isBorderedGrid() {
		return BORDERED_GRID;
	}

	public static void setBorderedGrid(boolean borderedGrid) {
		BORDERED_GRID = borderedGrid;
	}

	public static String[] getSKINS() {
		return SKIN_NAMES;
	}
}
