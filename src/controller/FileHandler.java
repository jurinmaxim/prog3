package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileHandler {

    private boolean grid[][];
    private boolean success;

    /**
     * Turns a String array into a boolean array.
     *
     * @param string
     * @return
     */
    public boolean[] stringToBoolean(String[] string) {
        boolean clone[] = new boolean[string.length];
        for (int i = 0; i < string.length; i++) {
            if (string[i].equals("1")) {
                clone[i] = true;
            } else {
                clone[i] = false;
            }
        }
        return clone;
    }

    /**
     * Loads a game from a text file.
     *
     * @return
     */
    public boolean[][] loadFromFile() {
        JButton button = new JButton();
        JFileChooser fileLoader = new JFileChooser();
        fileLoader.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileLoader.addChoosableFileFilter(filter);
        if (fileLoader.showOpenDialog(button) == JFileChooser.APPROVE_OPTION) {
            try {
                FileInputStream fis = new FileInputStream(fileLoader.getSelectedFile());
                BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                String str;
                int line = 0;
                while ((str = br.readLine()) != null) {
                    boolean[] clone = stringToBoolean(str.split(" "));
                    if (line == 0)
                        grid = new boolean[clone.length][clone.length];
                    for (int i = 0; i < clone.length; i++)
                        grid[line][i] = clone[i];
                    line++;
                }
                br.close();

            } catch (FileNotFoundException fnfe) {
            } catch (IOException ioe) {
            }
            success = true;
        }

        return grid;
    }

    /**
     * Saves the current state of the game (the grid) to a text file.
     *
     * @param grid
     */
    public void saveToFile(boolean[][] grid) {
        JButton button = new JButton();
        JFileChooser fileSaver = new JFileChooser();
        fileSaver.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileSaver.addChoosableFileFilter(filter);
        if (fileSaver.showSaveDialog(button) == JFileChooser.APPROVE_OPTION) {
            try {

                FileWriter writer = new FileWriter(fileSaver.getSelectedFile());
                PrintWriter pr = new PrintWriter(new BufferedWriter(writer));
                for (int i = 0; i < grid.length; i++) {
                    for (int j = 0; j < grid[0].length; j++) {
                        if (grid[i][j]) {
                            pr.print("1");
                        } else {
                            pr.print("0");
                        }
                        pr.print(" ");
                    }
                    pr.println();
                }
                pr.close();
            } catch (IOException ioe) {
            }

        }
    }

    /**
     * Returns the value of success.
     *
     * @return
     */
    public boolean getSuccess() {
        return success;
    }

    /**
     * Sets the value of success.
     *
     * @param success
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }
}
