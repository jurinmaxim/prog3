package controller;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import view.CellPanel;

public class CellPanelGridControllerTest2 {
	CellPanel cp;
	CellPanelGridController cpg;
	@Before
	public void setUp() throws Exception {
		cpg = new CellPanelGridController(3,3);
		cpg.aliveCellColor = Color.WHITE;
		
	}

	@Test
	public void test() {
		
		
		cpg.getCellPanelAtPosition(1,1).setAlive();
		cpg.paintMouseExitedCell(1,1);
		Color bkg = cpg.getCellPanelAtPosition(1,1).getBackground();
		assertEquals(bkg,cpg.aliveCellColor);
		
	}

}
